package com.example.yule.company.presenter;

import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.LoginApi;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.base.SpUtil;
import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.domain.com.ComInfoTo;
import com.fskj.applibrary.domain.com.LoginComParam;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/4 13:15
 * Email:635768909@qq.com
 */
public class CompanyPresenter extends BasePresenter {
    public CompanyPresenter(BaseActivity activity) {
        initContext(activity);
    }

    public void login(String phone, String psw) {
        showLoadingDialog();
        LoginComParam param = new LoginComParam();
        param.setUsername(phone);
        param.setPassword(psw);
        ApiClient.create(LoginApi.class).loginPswCom(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {
                dismissLoadingDialog();
                if (msg.getError_code() == 0) {
                    SpUtil.put("Token", msg.getAccess_token());
                    SpUtil.put("isCom", true);
                    getComInfo();
                } else {
                    showMessage((String) msg.getData());
                }
            }
        });
    }



    public void getComInfo() {
        showLoadingDialog();
        ApiClient.create(LoginApi.class).getComInfo().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo<ComInfoTo>>(this) {
            @Override
            public void onNext(MessageTo<ComInfoTo> msg) {
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
