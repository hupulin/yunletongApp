package com.example.yule.inspection.presenter;

import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.InspectionApi;
import com.fskj.applibrary.base.BaseFragment;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.domain.InspectionIdParam;
import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.domain.inspection.InspectionChildTo;
import com.fskj.applibrary.domain.inspection.InspectionTo;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/8 17:14
 * Email:635768909@qq.com
 */
public class InspectionPresenter extends BasePresenter {
    public InspectionPresenter(BaseFragment activity) {
        initContext(activity);
    }

    public void startInspection() {
        showLoadingDialog();
        ApiClient.create(InspectionApi.class).startInspection().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo<InspectionTo>>(this) {
            @Override
            public void onNext(MessageTo<InspectionTo> msg) {
                dismissLoadingDialog();
                if (msg.getError_code() == 0) {
                    submitDataSuccess(msg.getData());

                }

            }
        });
    }

    public void finishInspection(int id) {
        InspectionTo param = new InspectionTo();
        param.setInspectionId(id);
        showLoadingDialog();
        ApiClient.create(InspectionApi.class).finishInspection(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {
                dismissLoadingDialog();
                if (msg.getError_code() == 0) {
                    getDataSuccess(msg.getData());
                } else {
                    showMessage(msg.getData().toString());
                }
            }
        });
    }
    List<InspectionChildTo.InspectionRecordBoxBean> list=new ArrayList<>();

int inspectionId;
    public void getInspectionChildList(int inspectionId) {
    this.inspectionId=inspectionId;
        InspectionIdParam param=new InspectionIdParam();
        param.setPage(recyclePageIndex);//
        param.setInspectionId(inspectionId);//
        showLoadingDialog();
        ApiClient.create(InspectionApi.class).getInspectionChildList(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo<InspectionChildTo>>(this) {
            @Override
            public void onNext(MessageTo<InspectionChildTo> msg) {
                dismissLoadingDialog();
                if(msg.getError_code()==0){
                    if (recyclePageIndex != 1) {
                        list.addAll(msg.getData().getInspectionRecordBox());
                    } else {
                        list = msg.getData().getInspectionRecordBox();
                    }
                    setRecycleList(list);
                }
            }
        });
    }

    @Override
    public void recycleViewRefresh() {
        super.recycleViewRefresh();
        getInspectionChildList(inspectionId);
    }
}
