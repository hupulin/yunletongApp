package com.example.yule.login.presenter;

import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.LoginApi;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.domain.login.FaceTo;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/31 13:15
 * Email:635768909@qq.com
 */
public class FaceVerificationPresenter extends BasePresenter {
    public FaceVerificationPresenter(BaseActivity activity) {
        initContext(activity);
    }

    public void getFaceCode() {
        showLoadingDialog();
        ApiClient.create(LoginApi.class).getFaceCode().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo<FaceTo>>(this) {
            @Override
            public void onNext(MessageTo<FaceTo> msg) {
                dismissLoadingDialog();
                if (msg.getError_code() == 0) {
                    submitDataSuccess(msg.getData());
                } else {
                    showMessage(msg.getData().toString());
                }
            }
        });
    }

    public void faceComparison(String orderSn, File file) {
        showLoadingDialog();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        builder.addFormDataPart("video", file.getName(), imageBody);
        builder.addFormDataPart("orderSn", orderSn);
        List<MultipartBody.Part> parts = builder.build().parts();
        ApiClient.create(LoginApi.class).faceComparison(parts).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {
                dismissLoadingDialog();
                if (msg.getError_code() == 0) {
                    getDataSuccess(msg);
                } else {
                    showMessage(msg.getData().toString());
                }
            }
        });
    }

}
