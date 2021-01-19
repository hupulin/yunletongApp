package com.example.yule.inspection.presenter;

import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.InspectionApi;
import com.fskj.applibrary.base.BaseFragment;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.domain.PageParam;
import com.fskj.applibrary.domain.inspection.InspectionRecordTo;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/11 17:24
 * Email:635768909@qq.com
 */
public class InspectionListPresenter extends BasePresenter {
    public InspectionListPresenter(BaseFragment activity) {
        initContext(activity);
    }
List<InspectionRecordTo.DataBean> list= new ArrayList<>();
    public void getInspectionRecord() {
        showLoadingDialog();
        PageParam param = new PageParam();
        param.setPage(recyclePageIndex);
        ApiClient.create(InspectionApi.class).getInspectionRecord(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo<InspectionRecordTo>>(this) {
            @Override
            public void onNext(MessageTo<InspectionRecordTo> msg) {
                if (recyclePageIndex != 1) {
                    list.addAll(msg.getData().getData());
                } else {
                    list = msg.getData().getData();
                }
                setRecycleList(list);
            }
        });
    }
//    public void getInspectionChildList(int Id) {
//        showLoadingDialog();
//        InspectionIdParam param = new InspectionIdParam();
//        param.setPage(recyclePageIndex);
//        param.setInspectionId(Id);
//        ApiClient.create(InspectionApi.class).getInspectionChildList(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo<InspectionRecordTo>>(this) {
//            @Override
//            public void onNext(MessageTo<InspectionRecordTo> msg) {
//                if (recyclePageIndex != 1) {
//                    list.addAll(msg.getData().getData());
//                } else {
//                    list = msg.getData().getData();
//                }
//                setRecycleList(list);
//            }
//        });
//    }
    @Override
    public void recycleViewRefresh() {
        super.recycleViewRefresh();
        getInspectionRecord();
    }

    @Override
    public void recycleViewLoadMore() {
        super.recycleViewLoadMore();
        getInspectionRecord();

    }
}
