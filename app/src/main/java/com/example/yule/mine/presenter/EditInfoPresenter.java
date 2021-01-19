package com.example.yule.mine.presenter;

import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.LoginApi;
import com.fskj.applibrary.api.MyselfApi;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.base.image.UploadImageListener;
import com.fskj.applibrary.base.image.UploadImageModel;
import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.domain.login.NickNameParam;
import com.fskj.applibrary.domain.login.UserInfoTo;

import java.io.File;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/24 15:22
 * Email:635768909@qq.com
 */
public class EditInfoPresenter extends BasePresenter {
    public EditInfoPresenter(BaseActivity activity) {
        initContext(activity);
    }
    public void modifyImage(File imagePaths, UploadImageListener listener) {
        UploadImageModel model = new UploadImageModel();
        model.uploadImageHead(imagePaths, listener);
    }
    public void getUserInfo() {
//        showLoadingDialog();
        ApiClient.create(LoginApi.class).getUserInfo().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<UserInfoTo>(this) {
            @Override
            public void onNext(UserInfoTo msg) {
                userInfoHelp.saveUserInfo(msg);
                submitDataSuccess(msg);

            }
        });
    }
    public void changeNickName(String name) {
         NickNameParam param =new NickNameParam();
        param.setNickname(name);
        ApiClient.create(MyselfApi.class).uploadHeadImage(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {
                showMessage("修改昵称成功");
                getUserInfo();
            }
        });
    }
}
