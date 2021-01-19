package com.example.yule.ticket.presenter;

import android.util.Log;

import com.example.yule.ticket.TicketDetailActivity;
import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.TicketApi;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.domain.RefundTicketParam;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/28 17:25
 * Email:635768909@qq.com
 */
public class TicketDetailPresenter extends BasePresenter {
    public TicketDetailPresenter(BaseActivity activity) {
        initContext(activity);
    }

    public void refundTicket(String code) {
        RefundTicketParam param = new RefundTicketParam();
        param.setCode(code);
        showLoadingDialog();
        ApiClient.create(TicketApi.class).refundTicket(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {
                dismissLoadingDialog();
                if (msg.getError_code() == 0) {
                    showMessage("退票成功");
                    submitDataSuccess(msg);
                }
            }
        });
    }
    public void getTicketStatus(String code) {
        RefundTicketParam param = new RefundTicketParam();
        param.setCode(code);
        ApiClient.create(TicketApi.class).getTicketStatus(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo<String>>(this) {
            @Override
            public void onNext(MessageTo<String> msg) {
                if (msg.getError_code() == 0) {
                    ((TicketDetailActivity)activity).getStatusSuccess(msg.getData());
                }
            }
        });
    }
}
