package com.example.yule.mine.presenter;

import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.MyselfApi;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.domain.MessageTo;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/25 15:28
 * Email:635768909@qq.com
 */
public class InvitationPresenter extends BasePresenter {
    public InvitationPresenter(BaseActivity activity) {
        initContext(activity);
    }

    public void getMyCode() {
        showLoadingDialog();
        ApiClient.create(MyselfApi.class).getMyCode().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {
                dismissLoadingDialog();
                if (msg.getError_code() == 0) {
                    submitDataSuccess(msg.getData());

                }

            }
        });
    }

}
