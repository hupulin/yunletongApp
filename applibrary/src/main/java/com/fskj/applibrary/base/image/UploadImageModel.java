package com.fskj.applibrary.base.image;

import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.InspectionApi;
import com.fskj.applibrary.api.MyselfApi;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.domain.MessageTo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 **/

public class UploadImageModel extends BasePresenter {

    private int uploadCount = 0;
    private String pathKey;
    private List<String> keyRecordList = new ArrayList<>();

    public void uploadImageList(List<File> imagePathList, UploadImageListener listener) {
        pathKey = "";
        for (int i = 0; i < imagePathList.size(); i++) {
            keyRecordList.clear();
            File file = imagePathList.get(i);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("boxImg", file.getName(), requestFile);
            ApiClient.create(InspectionApi.class).uploadImageInspection(body).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                    new MyObserver<MessageTo>(this) {
                        @Override
                        public void onNext(MessageTo msg) {
                            uploadCount++;
                            pathKey = pathKey + msg.getData() + ";";
                            if (uploadCount == imagePathList.size()) {
                                dismissLoadingDialog();
                                listener.uploadImageSuccess(pathKey.substring(0, pathKey.length() - 1));
                            }
                        }
                    }
            );
        }
    }

    public void uploadImageHead(File file, UploadImageListener listener) {
        pathKey = "";
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        builder.addFormDataPart("headming", file.getName(), imageBody);
        List<MultipartBody.Part> parts = builder.build().parts();
        ApiClient.create(MyselfApi.class).uploadHeadImage(parts).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getError_code() == 0)
                            listener.uploadImageSuccess(msg.getUrl());
                    }
                }
        );
    }
}
