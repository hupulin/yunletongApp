package com.example.yule.login.presenter;

import com.example.yule.login.CancellationActivity;
import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.LoginApi;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.base.SpUtil;
import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.domain.login.LoginParam;
import com.fskj.applibrary.domain.login.RegisterParam;
import com.fskj.applibrary.domain.login.UserInfoTo;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/21 15:32
 * Email:635768909@qq.com
 */
public class RegisterPresenter extends BasePresenter {
    public RegisterPresenter(BaseActivity activity) {
        initContext(activity);
    }
    public void register(String nikeName,String phone) {
        showLoadingDialog();
        RegisterParam param=new RegisterParam();
        param.setNickname(nikeName);
        param.setPhone(phone);
        ApiClient.create(LoginApi.class).register(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {
                dismissLoadingDialog();
                submitDataSuccess(msg);
//                if(msg.getError_code()==0){
//                }else{
//                    showMessage((String) msg.getData());
//                }
            }
        });
    }
    public void setPassword(String phone,String code,String password) {
        showLoadingDialog();
        RegisterParam param=new RegisterParam();
        param.setPhone_number(phone);
        param.setCode(code);
        param.setPassword(password);
        ApiClient.create(LoginApi.class).setPassword(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {
                dismissLoadingDialog();
                if(msg.getError_code()==0){
                    getDataSuccess(msg);
                }else{
                    showMessage((String) msg.getData());
                }
            }
        });
    }
    public void sendSMS(String phone) {
        LoginParam param=new LoginParam();
        param.setPhone_number(phone);
        showLoadingDialog();
        ApiClient.create(LoginApi.class).sendSMS(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {
                dismissLoadingDialog();
                submitDataSuccess(msg);

            }
        });
    }

    public void cancellation(String code) {
        LoginParam param=new LoginParam();
        param.setCode(code);
        showLoadingDialog();
        ApiClient.create(LoginApi.class).cancellation(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {
                dismissLoadingDialog();
                if(msg.getError_code()==0){
                    (( CancellationActivity)activity).cancellationApp();
                }else{
                   showMessage(msg.getData().toString());
                }

            }
        });
    }
    public void login(String phone, String psw) {
        LoginParam param = new LoginParam();
        param.setPhone_number(phone);
        param.setPassword(psw);
        ApiClient.create(LoginApi.class).loginPsw(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {
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
        ApiClient.create(LoginApi.class).getUserInfo().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<UserInfoTo>(this) {
            @Override
            public void onNext(UserInfoTo msg) {
                userInfoHelp.saveUserInfo(msg);
            }
        });
    }
}
