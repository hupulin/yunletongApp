package com.example.yule.main.presenter;

import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.LoginApi;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.domain.login.UserInfoTo;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/12 16:29
 * Email:635768909@qq.com
 */
public class MainActivityPresenter extends BasePresenter {
    public MainActivityPresenter(BaseActivity activity) {
        initContext(activity);
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
}
