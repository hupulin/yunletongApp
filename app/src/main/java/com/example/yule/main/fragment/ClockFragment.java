package com.example.yule.main.fragment;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.yule.R;
import com.example.yule.main.presenter.ClockPresenter;
import com.fskj.applibrary.MainApp;
import com.fskj.applibrary.base.BaseFragment;
import com.fskj.applibrary.base.Constant;
import com.fskj.applibrary.domain.main.ClockTwoTo;
import com.fskj.applibrary.impl.PermissionListener;
import com.fskj.applibrary.util.DateUtil;
import com.fskj.applibrary.util.LoadingDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static android.app.Activity.RESULT_OK;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/22 11:21
 * Email:635768909@qq.com
 * 打卡
 */
public class ClockFragment extends BaseFragment implements PermissionListener {
    @BindView(R.id.update)
    LinearLayout update;
    @BindView(R.id.time_on)
    TextView timeOn;
    @BindView(R.id.time_off)
    TextView timeoff;
    @BindView(R.id.clock)
    TextView clock;
    //    @BindView(R.id.time_on)
//    TextView timeOn;
    @BindView(R.id.address)
    TextView address;
    Unbinder unbinder;
    ClockPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = View.inflate(appContext, R.layout.fragment_clock, null);
        unbinder = ButterKnife.bind(this, mView);
        initView();
        return mView;
    }

    private void initView() {
        presenter = new ClockPresenter(this);
        presenter.clockList();
        getPermission(Manifest.permission.ACCESS_FINE_LOCATION, this);

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
                openCamera();
                break;
            case Manifest.permission.ACCESS_FINE_LOCATION:
                setLocate();
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.RESULT_CAMERA:
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            isCamera=false;
                        }
                    },200);

                    presenter.faceComparison(photoPath, address.getText().toString(), latitude, longitude);
                    break;
            }
        }
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
                uri = getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
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

    @Override
    public void refuse(String permission) {

    }

    @OnClick({R.id.clock, R.id.update,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clock:
                //TODO  打卡逻辑
                getPermission(Manifest.permission.CAMERA, this);
                break;
            case R.id.update:
                //TODO  刷新地址
                showDialog();
                setLocate();
                break;
        }
    }


    LoadingDialog loadingDialog;
    public void showDialog() {
        loadingDialog = new LoadingDialog(getActivity(), "", com.fskj.applibrary.R.drawable.loading_animation);
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.show();
    }

    public void dismissDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }
    public void stopLocation() {
        if(mLocationClient!=null){
            mLocationClient.stopLocation();
        }

    }
    public AMapLocationClientOption mLocationOption = null;
    public AMapLocationClient mLocationClient = null;

    private void setLocate() {
        AMapLocationClientOption option = new AMapLocationClientOption();
        mLocationOption = new AMapLocationClientOption();
        mLocationClient = new AMapLocationClient(getActivity());
        mLocationOption.setOnceLocation(true);
        mLocationClient.setLocationListener(mAMapLocationListener);
        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationOption.setInterval(10000);
        mLocationClient.startLocation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.stopLocation();

    }

    String latitude;
    String longitude;
    Handler handler =new Handler();
    boolean isCamera;
    AMapLocationListener mAMapLocationListener = amapLocation -> {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                latitude = String.valueOf(amapLocation.getLatitude());
                longitude = String.valueOf(amapLocation.getLongitude());
                address.setText(amapLocation.getAddress());
                Log.i("222222", ": "+amapLocation.toStr());
                dismissDialog();
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        if(!isCamera){
            presenter.clockList();
        }
        setLocate();

    }

    List<ClockTwoTo> list = new ArrayList<>();

    @Override
    protected void submitDataSuccess(Object data) {
        list.clear();
        list = (List<ClockTwoTo>) data;
        if (list.size() == 0) {
            //一次未打卡
            timeOn.setText("上班未打卡");
            timeoff.setText("下班未打卡");
            clock.setText("上班打卡");
        } else if (list.size() == 1) {
            //上次打卡是上班卡
            timeOn.setText("上班打卡时间  " + list.get(0).getCreated_at());
            timeoff.setText("下班未打卡");
            clock.setText("下班打卡");
        } else {
            //上次打卡是下班卡
            timeOn.setText("上班打卡时间  " + list.get(1).getCreated_at());
            timeoff.setText("下班打卡时间  " + list.get(0).getCreated_at());
            clock.setText("上班打卡");
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        mLocationClient.stopLocation();

    }

}
