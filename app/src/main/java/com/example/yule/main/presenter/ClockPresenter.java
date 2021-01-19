package com.example.yule.main.presenter;

import android.graphics.Bitmap;

import com.example.yule.main.fragment.ClockFragment;
import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.TicketApi;
import com.fskj.applibrary.base.BaseFragment;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.domain.main.ClockParam;
import com.fskj.applibrary.domain.main.ClockTwoTo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/23 13:46
 * Email:635768909@qq.com
 */
public class ClockPresenter extends BasePresenter {

    ClockParam param;

    public ClockPresenter(BaseFragment activity) {
        initContext(activity);
    }

    public void clockList() {
//        param.setPassword(psw);
        showLoadingDialog();
        ApiClient.create(TicketApi.class).getClockTowList().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo<List<ClockTwoTo>>>(this) {
            @Override
            public void onNext(MessageTo<List<ClockTwoTo>>  msg) {
                dismissLoadingDialog();
                submitDataSuccess(msg.getData());
            }
        });
    }

    public void clockOn() {
        showLoadingDialog();
        ApiClient.create(TicketApi.class).clockOn(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {
                dismissLoadingDialog();

                if (msg.getError_code() == 0) {
                    showMessage("打卡成功");
                    clockList();

                } else {
                    showMessage((String) msg.getData());
                }
//                if(msg.getError_code()==0){
//                }
            }
        });
    }
    public static File saveBitmapFile(Bitmap bitmap, String filepath) {
        File file = new File(filepath);//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
    public void faceComparison(String imagePath, String address, String latitude, String longitude) {
        ((ClockFragment)mFragment).showDialog();
        showLoadingDialog();
        param = new ClockParam();
        param.setAddress(address);
        param.setLatitude(latitude);
        param.setLongitude(longitude);
        File file = new File(imagePath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("img", file.getName(), requestFile);
        ApiClient.create(TicketApi.class).faceComparison(body).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {
                ((ClockFragment)mFragment).dismissDialog();
                if (msg.getError_code() == 0) {
                    if ((boolean) msg.getData()) {
                        clockOn();
                    } else {
                        showMessage("人脸识别不成功");
                    }
                } else {
                    String ss = (String) msg.getData();
                    showMessage(ss);
                }
            }
        });
    }

}
