package com.example.yule.mine.presenter;

import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.MyselfApi;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.domain.PageParam;
import com.fskj.applibrary.domain.main.AcooutRecordTo;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/29 13:16
 * Email:635768909@qq.com
 */
public class AccountDetailsPresenter extends BasePresenter {
    public AccountDetailsPresenter(BaseActivity activity) {
        initContext(activity);
    }
    private List<AcooutRecordTo.DataBean> list = new ArrayList<>();

    public void getAccountRecord() {
        showLoadingDialog();
        PageParam param=new PageParam();
        param.setPage(recyclePageIndex);
        ApiClient.create(MyselfApi.class).getAccountRecord(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<AcooutRecordTo>(this) {
            @Override
            public void onNext(AcooutRecordTo msg) {
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
        getAccountRecord();
    }

    @Override
    public void recycleViewLoadMore() {
        super.recycleViewLoadMore();
        getAccountRecord();

    }
}
