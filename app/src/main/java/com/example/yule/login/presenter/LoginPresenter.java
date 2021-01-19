package com.example.yule.login.presenter;

import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.LoginApi;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.base.SpUtil;
import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.domain.login.LoginParam;
import com.fskj.applibrary.domain.login.UserInfoTo;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/11/26 14:32
 * Email:635768909@qq.com
 */
public class LoginPresenter extends BasePresenter {

    public LoginPresenter(BaseActivity activity) {
        initContext(activity);
    }

    //    {"error_code":0,"access_token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3dlYmRldi5mYW5nc2hlbmcudGVjaC9hcGkvYXV0aC9sb2dpbiIsImlhdCI6MTYwODUzMzgyOCwiZXhwIjoyMDI4NTMzODI4LCJuYmYiOjE2MDg1MzM4MjgsImp0aSI6Ikt2M1BDblBqSEJXajJyWkIiLCJzdWIiOjk5OSwicHJ2IjoiYWZkMGZkOWJkZDlhYzcyZmYzOTgzNDFmMWQ2Mjg0MGNiZjRjNzE2NyIsImlkIjo5OTksIm5hbWUiOiJcdTgwZTFcdTY2NmVcdTY3OTciLCJwaG9uZV9udW1iZXIiOiIxODc1ODE2ODU4NyJ9.UPlJ5wxKSpRJ9hrCfITCbOyqPuVrg9UyQ1nxOV4WCkU","token_type":"bearer","expires_in":4200000000}
    public void login(String phone, String psw) {
        showLoadingDialog();
        LoginParam param = new LoginParam();
        param.setPhone_number(phone);
        param.setPassword(psw);
        ApiClient.create(LoginApi.class).loginPsw(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {
                dismissLoadingDialog();
                if (msg.getError_code() == 0) {
                    SpUtil.put("Token", msg.getAccess_token());
                    getUserInfo();
                } else {
                    showMessage((String) msg.getData());
                }
            }
        });
    }

    public void getUserInfo() {
//        showLoadingDialog();
        ApiClient.create(LoginApi.class).getUserInfo().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<UserInfoTo>(this) {
            @Override
            public void onNext(UserInfoTo msg) {
//                dismissLoadingDialog();
                userInfoHelp.saveUserInfo(msg);
                submitDataSuccess(msg);

            }
        });
    }

    public void loginSMS(String phone, String sms) {
        showLoadingDialog();
        LoginParam param = new LoginParam();
        param.setPhone_number(phone);
        param.setCode(sms);
        ApiClient.create(LoginApi.class).loginSMS(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {
                dismissLoadingDialog();
                if (msg.getError_code() == 0) {
                    SpUtil.put("Token", msg.getAccess_token());
                    getUserInfo();
                } else {
                    showMessage((String) msg.getData());
                }
            }
        });
    }

    public void sendSMS(String phone) {
        LoginParam param = new LoginParam();
        param.setPhone_number(phone);
        showLoadingDialog();
        ApiClient.create(LoginApi.class).sendSMS(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {
                dismissLoadingDialog();
                if (msg.getError_code() == 0) {
                    getDataSuccess(msg);
                } else {
                    showMessage((String) msg.getData());
                }
            }
        });
    }


}