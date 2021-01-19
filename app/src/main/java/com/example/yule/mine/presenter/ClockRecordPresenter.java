package com.example.yule.mine.presenter;

import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.MyselfApi;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.domain.PageParam;
import com.fskj.applibrary.domain.main.ClockRecordTo;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/29 11:05
 * Email:635768909@qq.com
 */
public class ClockRecordPresenter extends BasePresenter {
    public ClockRecordPresenter(BaseActivity activity) {
        initContext(activity);
    }
    private List<ClockRecordTo.DataBean> list = new ArrayList<>();

    public void getClockRecord() {
        showLoadingDialog();
        PageParam param=new PageParam();
        param.setPage(recyclePageIndex);
        ApiClient.create(MyselfApi.class).getClockRecord(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<ClockRecordTo>(this) {
            @Override
            public void onNext(ClockRecordTo msg) {

                if (recyclePageIndex != 1) {
                    list.addAll(msg.getData());
                } else {
                    list = msg.getData();
                }
                setRecycleList(list);
            }
        });
    }

    @Override
    public void recycleViewRefresh() {
        super.recycleViewRefresh();
        getClockRecord();
    }

    @Override
    public void recycleViewLoadMore() {
        super.recycleViewLoadMore();
        getClockRecord();

    }
}
