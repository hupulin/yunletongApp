package com.example.yule.inspection.presenter;

import com.example.yule.inspection.AddInspectionActivity;
import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.InspectionApi;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.base.image.UploadImageListener;
import com.fskj.applibrary.base.image.UploadImageModel;
import com.fskj.applibrary.domain.AddInspectionParam;
import com.fskj.applibrary.domain.InspectionTypeTo;
import com.fskj.applibrary.domain.MessageTo;

import java.io.File;
import java.util.List;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/12 14:10
 * Email:635768909@qq.com
 */
public class AddInspectionPresenter extends BasePresenter {
    public AddInspectionPresenter(BaseActivity activity) {
        initContext(activity);
    }

    public void getAllInspectionType() {
        showLoadingDialog();
        ApiClient.create(InspectionApi.class).getAllInspectionType().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo<List<InspectionTypeTo>>>(this) {
            @Override
            public void onNext(MessageTo<List<InspectionTypeTo>> msg) {
                dismissLoadingDialog();
                if (msg.getError_code() == 0) {
                    ((AddInspectionActivity) activity).getTypeSuccess(msg.getData());
                } else {
                    showMessage(msg.getData().toString());
                }
            }
        });
    }

    public void addInspection(AddInspectionParam param) {
        showLoadingDialog();
        ApiClient.create(InspectionApi.class).addInspection(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {
                dismissLoadingDialog();
                if (msg.getError_code() == 0) {
                    submitDataSuccess(msg);
                } else {
                    showMessage(msg.getData().toString());
                }
            }
        });
    }
    public void addInspection(Map<String, Object> param) {
        showLoadingDialog();
        ApiClient.create(InspectionApi.class).addInspection(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo<List<InspectionTypeTo>>>(this) {
            @Override
            public void onNext(MessageTo<List<InspectionTypeTo>> msg) {
                dismissLoadingDialog();
                if (msg.getError_code() == 0) {
                } else {
                    showMessage(msg.getData().toString());
                }
            }
        });
    }

    public void uploadImage(List<File> imagePath,UploadImageListener listener) {
        showLoadingDialog();
        UploadImageModel model = new UploadImageModel();
        model.uploadImageList(imagePath, listener);
    }
}
