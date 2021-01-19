package com.example.yule.ticket.presenter;

import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.LoginApi;
import com.fskj.applibrary.api.TicketApi;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.domain.WeChatPay;
import com.fskj.applibrary.domain.login.UserInfoTo;
import com.fskj.applibrary.domain.main.TicketOrderParam;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/28 10:47
 * Email:635768909@qq.com
 */
public class BuyTicketPresenter extends BasePresenter {
    public BuyTicketPresenter(BaseActivity activity) {
        initContext(activity);
    }

    public void getOrder(String type,int num) {
        showLoadingDialog();
        TicketOrderParam param = new TicketOrderParam();
        param.setDays(num);
        param.setPayType(type);
        if("1".equals(type)||"3".equals(type)){
            ApiClient.create(TicketApi.class).getTicketOrder(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
                @Override
                public void onNext(MessageTo msg) {
                    dismissLoadingDialog();
                    if (msg.getError_code() == 0) {
                        submitDataSuccess(msg.getData());
                        if("3".equals(type)){
                            getUserInfo();
                        }
                    }else{
                        showMessage(msg.getData().toString());
                    }

                }
            });
        }else{
            ApiClient.create(TicketApi.class).getTicketOrderWx(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo<WeChatPay>>(this) {
                @Override
                public void onNext(MessageTo<WeChatPay> msg) {
                    dismissLoadingDialog();
                    if (msg.getError_code() == 0) {
                        submitDataSuccess(msg.getData());
                    }

                }
            });
        }
    }
    public void getUserInfo() {
//        showLoadingDialog();
        ApiClient.create(LoginApi.class).getUserInfo().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<UserInfoTo>(this) {
            @Override
            public void onNext(UserInfoTo msg) {
                userInfoHelp.saveUserInfo(msg);
                getDataSuccess(msg);
            }
        });
    }

}
