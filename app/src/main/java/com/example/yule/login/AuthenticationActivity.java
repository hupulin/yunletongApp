package com.example.yule.login;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yule.R;
import com.example.yule.login.presenter.AuthenticationPresenter;
import com.fskj.applibrary.MainApp;
import com.fskj.applibrary.base.ActivityManager;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.Constant;
import com.fskj.applibrary.domain.main.CardInfoTo;
import com.fskj.applibrary.impl.PermissionListener;
import com.fskj.applibrary.util.CommonDialog;
import com.fskj.applibrary.util.DateUtil;
import com.fskj.applibrary.util.FileUtil;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.wildma.idcardcamera.camera.IDCardCamera;
import com.wildma.idcardcamera.utils.FileUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/30 15:49
 * 身份信息
 * Email:635768909@qq.com
 */
public class AuthenticationActivity extends BaseActivity implements PermissionListener {

    @BindView(R.id.iv_portrait)
    ImageView ivPortrait;//头像
    @BindView(R.id.iv_national)
    ImageView ivNational;//国徽
    AuthenticationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_authentication);
        ButterKnife.bind(this);
        initData();

    }

    private String type = "";

    private void initData() {
        presenter = new AuthenticationPresenter(this);
        setTitleName("身份证认证");
    }
    private String photoPath;

    private void openCamera() {
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                ContentValues values = new ContentValues(1);
                File mOutPhotoFile = new File(MainApp.getCacheImagePath(), DateUtil.getDateString(DateUtil.mFormatTimeCamaraDetail) + ".png");
                photoPath = mOutPhotoFile.getAbsolutePath();
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
                values.put(MediaStore.Images.Media.DATA, photoPath);
                uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            File mOutPhotoFile = new File(MainApp.getCacheImagePath(), DateUtil.getDateString(DateUtil.mFormatTimeCamaraDetail) + ".png");
            photoPath = mOutPhotoFile.getAbsolutePath();
            uri = Uri.fromFile(mOutPhotoFile);

        }
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intentCamera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        isCamera=true;
        startActivityForResult(intentCamera, Constant.RESULT_CAMERA);
    }
    private void uploadHeadImageDialog() {
        CommonDialog alertDialog = new CommonDialog(this, getScreenWidth(), (int) (getScreenWidth() * 0.4), R.layout.dialog_pic_type, R.style.DialogDown);
        alertDialog.findViewById(R.id.btn_cancel).setOnClickListener(v -> alertDialog.dismiss());
        alertDialog.findViewById(R.id.btn_camera).setOnClickListener(v -> {
            alertDialog.dismiss();
            isCamera = true;
            getPermission(Manifest.permission.CAMERA, this);
            isUpload=true;

        });
        alertDialog.findViewById(R.id.btn_album).setOnClickListener(v -> {
            alertDialog.dismiss();
            isCamera = false;
            getPermission(Manifest.permission.READ_EXTERNAL_STORAGE, this);

        });
        alertDialog.show();
        alertDialog.setCanceledOnTouchOutside(true);
    }

    boolean isCamera;
boolean isUpload;
    @Override
    public void accept(String permission) {
        switch (permission) {
            case Manifest.permission.CAMERA:
                getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, this);
                break;
            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                if(isUpload){
                    getPermission(Manifest.permission.READ_EXTERNAL_STORAGE, this);
                }else{
                    openCamera();
                }
                break;
            case Manifest.permission.READ_EXTERNAL_STORAGE:
                if (isCamera) {
                    if ("back".equals(type)) {
                        portrait();
                    } else {
                        national(); //国徽
                    }
                } else {
                    enterAlbum();
                }

                break;
        }
    }
    private void enterAlbum() {
        // 打开相册
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("return_data", true);
        startActivityForResult(intent, Constant.RESULT_SDCARD);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.RESULT_SDCARD:
                    Uri uri = data.getData();
                    String mPhotoPath = FileUtil.getPath(this, uri);
                    if ("back".equals(type)) {
                        ivNational.setImageBitmap(BitmapFactory.decodeFile(mPhotoPath));
                        presenter.upLoadIdCard("back", mPhotoPath);
                    } else {
                        ivPortrait.setImageBitmap(BitmapFactory.decodeFile(mPhotoPath));
                        presenter.upLoadIdCard("face", mPhotoPath);
                    }
                    break;
                case Constant.RESULT_CAMERA:
                    presenter.uploadPic(photoPath);
                    break;
            }
        }
        if (resultCode == IDCardCamera.RESULT_CODE) {
            //获取图片路径，显示图片
            final String path = IDCardCamera.getImagePath(data);
            if (!TextUtils.isEmpty(path)) {
                if (requestCode == IDCardCamera.TYPE_IDCARD_FRONT) { //身份证正面
                    ivPortrait.setImageBitmap(BitmapFactory.decodeFile(path));
                    presenter.upLoadIdCard("face", path);
                } else if (requestCode == IDCardCamera.TYPE_IDCARD_BACK) {  //身份证反面
                    ivNational.setImageBitmap(BitmapFactory.decodeFile(path));
                    presenter.upLoadIdCard("back", path);
                }
//                实际开发中将图片上传到服务器成功后需要删除全部缓存图片
            }
        }
    }

    @OnClick({R.id.national_emblem, R.id.portrait_layout, R.id.confirm,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.national_emblem:
                type = "back";

                uploadHeadImageDialog();
                break;
            case R.id.portrait_layout://头像
                type = "face";
                uploadHeadImageDialog();

                break;
            case R.id.confirm:
                presenter.getCardIdentification();//获取身份证信息
                break;

        }
    }

    /**
     * 身份证正面 (头像)
     */
    public void national() {
        IDCardCamera.create(this).openCamera(IDCardCamera.TYPE_IDCARD_FRONT);
    }

    /**
     * 身份证反面（国徽）
     */
    public void portrait() {
        IDCardCamera.create(this).openCamera(IDCardCamera.TYPE_IDCARD_BACK);
    }


    @Override
    protected void submitDataSuccess(Object data) {
        super.submitDataSuccess(data);
        showMessage("身份证照片上传成功");
        FileUtils.clearCache(this);
    }

    private void showCardInfo() {
        NiftyDialogBuilder dialog = NiftyDialogBuilder.getInstance(this);
        dialog.setContentView(R.layout.dialog_id_info);
        TextView name = dialog.findViewById(R.id.name);
        TextView code = dialog.findViewById(R.id.code);
        name.setText("姓名：" + model.getCardInfo().getName());
        code.setText("身份证：" + model.getCardInfo().getNum());
        dialog.show();
        dialog.findViewById(R.id.cancel).setOnClickListener(view -> dialog.dismiss());
        dialog.findViewById(R.id.parent).setOnClickListener(view -> dialog.dismiss());
        dialog.findViewById(R.id.confirm).setOnClickListener(view -> {
//            //TOdo    去认证页面
//            Intent intent = new Intent(AuthenticationActivity.this, FaceVerificationActivity.class);
//            startActivity(intent);
//            goToAnimation(1);
            dialog.dismiss();
            isUpload=false;
            getPermission(Manifest.permission.CAMERA, this);
        });
    }

    // 获取到的身份信息
    private CardInfoTo model;

    @Override
    public void loadDataSuccess(Object data) {
        model = (CardInfoTo) data;
        showCardInfo();

    }
    public void upLoadPicSuccess() {
        Observable.from(ActivityManager.activityList).subscribe((BaseActivity baseActivity) -> baseActivity.finish());
        Intent intent = new Intent(this, AuthenticationResultActivity.class);
        startActivity(intent);
        goToAnimation(1);
    }
}
