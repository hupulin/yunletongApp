package com.example.yule.member.presenter;

import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.MemberApi;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.domain.TicketRecordParam;
import com.fskj.applibrary.domain.TicketRecordTo;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/7 14:24
 * Email:635768909@qq.com
 */
public class TicketRecordPresenter extends BasePresenter {
    public TicketRecordPresenter(BaseActivity activity) {
        initContext(activity);
    }

    private List<TicketRecordTo.DataBean> list = new ArrayList<>();
    private int mId = 0;

    public void getTicketRecord(int mId) {
        this.mId = mId;
        TicketRecordParam param = new TicketRecordParam();
        param.setPage(recyclePageIndex);//
        param.setMid(mId);//
        showLoadingDialog();
        ApiClient.create(MemberApi.class).getTicketRecord(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo<TicketRecordTo>>(this) {
            @Override
            public void onNext(MessageTo<TicketRecordTo> msg) {
                dismissLoadingDialog();
                if (msg.getError_code() == 0) {
                    if (recyclePageIndex != 1) {
                        list.addAll(msg.getData().getData());
                    } else {
                        list = msg.getData().getData();
                    }
                    setRecycleList(list);
                }
            }
        });
    }

    @Override
    public void recycleViewRefresh() {
        super.recycleViewRefresh();
        getTicketRecord(mId);
    }

    @Override
    public void recycleViewLoadMore() {
        super.recycleViewLoadMore();
        getTicketRecord(mId);

    }
}
