package com.example.yule.member.presenter;

import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.MemberApi;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.domain.CodeParam;
import com.fskj.applibrary.domain.MessageTo;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/7 16:41
 * Email:635768909@qq.com
 */
public class AddMemberPresenter extends BasePresenter {
    public AddMemberPresenter(BaseActivity activity) {
        initContext(activity);
    }

    public void addMember(String code) {
        showLoadingDialog();
        CodeParam param = new CodeParam();
        param.setKey(code);

        ApiClient.create(MemberApi.class).addMember(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
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
}
