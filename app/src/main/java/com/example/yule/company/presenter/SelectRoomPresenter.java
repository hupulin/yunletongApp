package com.example.yule.company.presenter;

import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.LoginApi;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.domain.com.RoomTo;
import com.fskj.applibrary.domain.com.UseTicketParam;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/5 16:02
 * Email:635768909@qq.com
 */
public class SelectRoomPresenter extends BasePresenter {
    public SelectRoomPresenter(BaseActivity activity) {
        initContext(activity);
    }
    public void getRoom() {
        showLoadingDialog();
        ApiClient.create(LoginApi.class).getRoom().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo<List<RoomTo>>>(this) {
            @Override
            public void onNext(MessageTo<List<RoomTo>> msg) {
                dismissLoadingDialog();
                if (msg.getError_code() == 0) {
                    getDataSuccess(msg.getData());
                } else {
                    showMessage(msg.getData().toString());
                }
            }
        });
    }
    public void useTicket(String code, String id) {
        showLoadingDialog();
        UseTicketParam param = new UseTicketParam();
        param.setCode(code);
        param.setRoomId(id);
        ApiClient.create(LoginApi.class).useTicket(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {
                dismissLoadingDialog();
                if (msg.getError_code() == 0) {
                    showMessage((String) msg.getData());
//                    submitDataSuccess(msg.getData());
                } else {
                    showMessage((String) msg.getData());
                }
            }
        });
    }
}
