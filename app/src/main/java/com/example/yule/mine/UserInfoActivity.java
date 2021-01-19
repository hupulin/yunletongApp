package com.example.yule.mine;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yule.R;
import com.example.yule.login.CancellationActivity;
import com.example.yule.login.SetPasswordActivity;
import com.example.yule.mine.presenter.EditInfoPresenter;
import com.example.yule.web.StaticWebViewActivity;
import com.fskj.applibrary.MainApp;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.Constant;
import com.fskj.applibrary.base.image.UploadImageListener;
import com.fskj.applibrary.impl.PermissionListener;
import com.fskj.applibrary.util.CommonDialog;
import com.fskj.applibrary.util.DateUtil;
import com.fskj.applibrary.util.FileUtil;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/24 12:58
 * Email:635768909@qq.com
 */
public class UserInfoActivity extends BaseActivity implements PermissionListener, UploadImageListener {
    @BindView(R.id.head_image)
    ImageView headImage;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_phone)
    TextView userPhone;
    EditInfoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        presenter = new EditInfoPresenter(this);
        Glide.with(this).load(userInfoTo.getHeadimg()).placeholder(R.mipmap.use_image).into(headImage);
       String phone= userInfoTo.getPhone_number().substring(0,3) +"****" + userInfoTo.getPhone_number().substring(7, userInfoTo.getPhone_number().length());
        userName.setText(userInfoTo.getNickname());
        userPhone.setText(phone);
        setTitleName("个人信息");
    }

    private void uploadHeadImageDialog() {
        CommonDialog alertDialog = new CommonDialog(this, getScreenWidth(), (int) (getScreenWidth() * 0.4), R.layout.dialog_pic_type, R.style.DialogDown);
        alertDialog.findViewById(R.id.btn_cancel).setOnClickListener(v -> alertDialog.dismiss());
        alertDialog.findViewById(R.id.btn_camera).setOnClickListener(v -> {
            alertDialog.dismiss();
            getPermission(Manifest.permission.CAMERA, this);
        });
        alertDialog.findViewById(R.id.btn_album).setOnClickListener(v -> {
            alertDialog.dismiss();
            getPermission(Manifest.permission.READ_EXTERNAL_STORAGE, this);
        });
        alertDialog.show();
        alertDialog.setCanceledOnTouchOutside(true);
    }

    @OnClick({R.id.head_layout, R.id.nick_name, R.id.agreement_layout, R.id.password_layout, R.id.cancellation_layout,})
    public void onViewClicked(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.head_layout:
                uploadHeadImageDialog();
                break;
            case R.id.nick_name:
                ChangeNicknameDialog();
                break;
                case R.id.password_layout:
                    intent   = new Intent(this, SetPasswordActivity.class);
                    intent.putExtra("type","3");
                    startActivity(intent);
                    goToAnimation(1);
                break;
            case R.id.agreement_layout:
//                user/cancellationAgreement 注销协议
                 intent = new Intent(this, StaticWebViewActivity.class);
                intent.putExtra("Title", "服务协议及隐私协议");
                intent.putExtra("Url", "user/agreement");
                startActivity(intent);
                break;
                case R.id.cancellation_layout:
//                user/cancellationAgreement 注销协议
                 intent = new Intent(this, CancellationActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void ChangeNicknameDialog() {
        NiftyDialogBuilder dialog = NiftyDialogBuilder.getInstance(this);
        dialog.setContentView(R.layout.dialog_change_nickname);
        EditText etName = dialog.findViewById(R.id.edit_name);
        dialog.show();
        dialog.findViewById(R.id.cancel).setOnClickListener(view -> dialog.dismiss());
        dialog.findViewById(R.id.parent).setOnClickListener(view -> dialog.dismiss());
        dialog.findViewById(R.id.confirm).setOnClickListener(view -> {
            if (TextUtils.isEmpty(etName.getText().toString())) {
                showMessage("请输入昵称");
                return;
            }
            presenter.changeNickName(etName.getText().toString());
            dialog.dismiss();
        });
    }

    @Override
    public void accept(String permission) {
        switch (permission) {
            case Manifest.permission.CAMERA:
                getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, this);
                break;
            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                openCamera();
                break;
            case Manifest.permission.READ_EXTERNAL_STORAGE:
                enterAlbum();
                break;
        }
    }

    private String photoPath;

    private void openCamera() {
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                ContentValues values = new ContentValues(1);
                File mOutPhotoFile = new File(MainApp.getCacheImagePath(), DateUtil.getDateString(DateUtil.mFormatTimeCamara) + ".png");
                photoPath = mOutPhotoFile.getAbsolutePath();
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
                values.put(MediaStore.Images.Media.DATA, photoPath);
                uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            File mOutPhotoFile = new File(MainApp.getCacheImagePath(), DateUtil.getDateString(DateUtil.mFormatTimeCamara) + ".png");
            photoPath = mOutPhotoFile.getAbsolutePath();
            uri = Uri.fromFile(mOutPhotoFile);

        }
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intentCamera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(intentCamera, Constant.RESULT_CAMERA);


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
    public void uploadImageSuccess(String path) {
        presenter.getUserInfo();
        showMessage("修改头像成功");
    }

    @Override
    protected void submitDataSuccess(Object data) {
        super.submitDataSuccess(data);
        userInfoTo = userInfoHelp.getUserInfo();
        Glide.with(this).load(userInfoTo.getHeadimg()).into(headImage);
        userName.setText(userInfoTo.getNickname());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.RESULT_SDCARD:
                    Uri uri = data.getData();
                    String mPhotoPath = FileUtil.getPath(this, uri);
                    if (!TextUtils.isEmpty(mPhotoPath)) {
                        beginCrop(uri);
                    }

                    break;
                case Constant.RESULT_CAMERA:

                    Uri uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

                    if (uri2 != null && photoPath != null) {
                        Uri uri1 = Uri.fromFile(new File(photoPath));
                        beginCrop(uri1);

                    }
                    break;
                case Crop.REQUEST_CROP:
                    handleCrop(data);
                    break;

            }
        }
    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), DateUtil.getDateString(DateUtil.mFormatDateShort1) + "croppedd.jpg"));

        Crop.of(source, destination).asSquare().start(this);

    }
    File file;
    private void handleCrop(Intent result) {

        try {
             file = new File(new URI( Crop.getOutput(result).toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        presenter.modifyImage(file, this);

    }
}
