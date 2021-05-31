package com.example.yule.mine.presenter;

import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.MemberApi;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.domain.main.IssueTicketParam;
import com.fskj.applibrary.domain.main.TicketTypeTo;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/3/26 17:21
 * Email:635768909@qq.com
 */
public class IssueTicketsPresenter extends BasePresenter {
    public IssueTicketsPresenter(BaseActivity activity) {
        initContext(activity);
    }

    public void issueTicket(String start,String end) {
        IssueTicketParam param=new IssueTicketParam();
        param.setBegin(start);
        param.setEnd(end);
        showLoadingDialog();
        ApiClient.create(MemberApi.class).issueTicket(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo<TicketTypeTo>>(this) {
            @Override
            public void onNext(MessageTo<TicketTypeTo> msg) {
                dismissLoadingDialog();
                if (msg.getError_code() == 0) {
                    submitDataSuccess(msg.getData());
                }

            }
        });
    }

}