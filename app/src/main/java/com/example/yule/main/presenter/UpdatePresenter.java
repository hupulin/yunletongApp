package com.example.yule.main.presenter;

import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.MyselfApi;
import com.fskj.applibrary.base.BaseFragment;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.domain.UpdateInfo;
import com.fskj.applibrary.domain.UpdateInfoParam;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/15 17:20
 * Email:635768909@qq.com
 */
public class UpdatePresenter extends BasePresenter {


    public UpdatePresenter(BaseFragment activity) {
        initContext(activity);
    }
    public void getUpdateInfo( ){
        showLoadingDialog();
        UpdateInfoParam param =new UpdateInfoParam();
        param.setApp_type(2);
        ApiClient.create(MyselfApi.class).updateInfo(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo<UpdateInfo>>(this) {
            @Override
            public void onNext(MessageTo<UpdateInfo> msg) {
                dismissLoadingDialog();
                if(msg.getError_code()==0){
                    submitDataSuccess(msg.getData());
                }
            }
        });
    }
}