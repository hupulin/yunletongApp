package com.example.yule.login;

import android.Manifest;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yule.R;
import com.example.yule.login.presenter.AuthenticationPresenter;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.domain.main.CardInfoTo;
import com.fskj.applibrary.impl.PermissionListener;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.wildma.idcardcamera.camera.IDCardCamera;
import com.wildma.idcardcamera.utils.FileUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    public void accept(String permission) {
        switch (permission) {
            case Manifest.permission.CAMERA:
                getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, this);

                break;
            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                getPermission(Manifest.permission.READ_EXTERNAL_STORAGE, this);
                break;
            case Manifest.permission.READ_EXTERNAL_STORAGE:

                if ("back".equals(type)) {
                    portrait();

                } else {
                    national(); //国徽

                }

//                enterAlbum();

                break;

        }

    }

    @OnClick({R.id.national_emblem, R.id.portrait_layout, R.id.confirm,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.national_emblem:
                type = "back";

                getPermission(Manifest.permission.CAMERA, this);
                break;
            case R.id.portrait_layout://头像
                type = "face";

                getPermission(Manifest.permission.CAMERA, this);
                break;
            case R.id.confirm://头像


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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
        name.setText("姓名："+model.getCardInfo().getName());
        code.setText("身份证："+model.getCardInfo().getNum());
        dialog.show();
        dialog.findViewById(R.id.cancel).setOnClickListener(view -> dialog.dismiss());
        dialog.findViewById(R.id.parent).setOnClickListener(view -> dialog.dismiss());
        dialog.findViewById(R.id.confirm).setOnClickListener(view -> {
            //TOdo    去认证页面
            Intent intent = new Intent(AuthenticationActivity.this, FaceVerificationActivity.class);
            startActivity(intent);
            goToAnimation(1);
            dialog.dismiss();
        });
    }

    // 获取到的身份信息
    private CardInfoTo model;

    @Override
    public void loadDataSuccess(Object data) {
        model = (CardInfoTo) data;
        showCardInfo();

    }
}
