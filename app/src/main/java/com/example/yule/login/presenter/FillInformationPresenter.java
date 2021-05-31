package com.example.yule.login.presenter;

import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.LoginApi;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.domain.login.FaceTo;
import com.fskj.applibrary.domain.main.FillInfoParam;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/3/29 18:22
 * Email:635768909@qq.com
 */
public class FillInformationPresenter extends BasePresenter {
    public FillInformationPresenter(BaseActivity activity) {
            initContext(activity);
        }
    public void fillInfo(String height,String weight,String marriage,String emergencyContact,String emergencyPhone,String temporaryNumber,String currentAddress) {
        FillInfoParam param =new FillInfoParam();
        param.setHeight(height);
        param.setWeight(weight);
        param.setMarriage(marriage);
        param.setEmergencyContact(emergencyContact);
        param.setEmergencyPhone(emergencyPhone);
        param.setCurrentAddress(currentAddress);
        param.setTemporaryNumber(temporaryNumber);
        showLoadingDialog();
        ApiClient.create(LoginApi.class).fillInfo(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {
                dismissLoadingDialog();
                if (msg.getError_code() == 0) {
                    submitDataSuccess(msg.getData());
                } else {
                    showMessage(msg.getData().toString());
                }
            }
        });
    }
    }
