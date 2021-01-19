package com.example.yule.login;

import android.Manifest;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.yule.R;
import com.example.yule.databinding.ItemFaceCodeBinding;
import com.example.yule.login.presenter.FaceVerificationPresenter;
import com.fskj.applibrary.base.ActivityManager;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.domain.login.FaceTo;
import com.fskj.applibrary.impl.PermissionListener;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/31 11:33
 * Email:635768909@qq.com
 */
public class FaceVerificationActivity extends BaseActivity implements PermissionListener {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.grid)
    GridLayout gridLayout;
    FaceVerificationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_face_verification);
        ButterKnife.bind(this);
        initData();

    }

    @OnClick({R.id.confirm,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                getPermission(Manifest.permission.CAMERA, this);
                break;
        }
    }
    private void initData() {
        setTitleName("实名认证");
        displayImage(image, R.mipmap.face);
        presenter = new FaceVerificationPresenter(this);
        presenter.getFaceCode();
    }
    FaceTo model ;
    @Override
    protected void submitDataSuccess(Object data) {
         model =(FaceTo) data;
        String code = model.getCode();
        char[] ss = code.toCharArray();
        gridLayout.removeAllViews();
        for (char s : ss) {
            View mView = View.inflate(appContext, R.layout.item_face_code, null);
            ItemFaceCodeBinding binding = DataBindingUtil.bind(mView);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.width = (int) ((getScreenWidth()) / 7);
            mView.setLayoutParams(layoutParams);
            binding.text.setText(String.valueOf(s));
            gridLayout.addView(mView);
        }
    }

    private File videoFile;
    String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();


    private void openVideo() {
        Intent intent = new Intent();
        intent.setAction("android.media.action.VIDEO_CAPTURE");
        intent.addCategory("android.intent.category.DEFAULT");
        videoFile = new File(filePath, System.nanoTime() + ".MP4");
        if (videoFile.exists()) {
            videoFile.delete();
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(videoFile));
            startActivityForResult(intent, 30);
        } else {
            try {
                videoFile = File.createTempFile("Zp" + System.nanoTime(), ".mp4", new File(filePath));
                Uri contentUri = FileProvider.getUriForFile(this, "com.fskj.fun", videoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                startActivityForResult(intent, 30);
            } catch (IOException e) {

            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 30:
                    presenter.faceComparison(model.getOrderSn(),videoFile);
                    break;

            }
        }
    }

    @Override
    public void accept(String permission) {
        switch (permission) {
            case Manifest.permission.CAMERA:
                getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, this);

                break;
            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                openVideo();
                break;


        }

    }

    @Override
    public void loadDataSuccess(Object data) {
        Observable.from(ActivityManager.activityList).subscribe((BaseActivity baseActivity) -> baseActivity.finish());
        Intent intent = new Intent(this, AuthenticationResultActivity.class);
        startActivity(intent);
        goToAnimation(1);

    }
}
