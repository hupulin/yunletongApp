package com.example.yule.member.presenter;

import android.view.View;

import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.MemberApi;
import com.fskj.applibrary.base.BaseFragment;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.domain.MemberTo;
import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.domain.com.SelectTypeParam;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/6 11:38
 * Email:635768909@qq.com
 */
public class MemberPresenter extends BasePresenter {
    private List<MemberTo> list=new ArrayList<>();
    public MemberPresenter(BaseFragment activity) {
        initContext(activity);
    }
private  String type="";
    public void getMemberList(String type) {
        showLoadingDialog();
        this.type=type;
        SelectTypeParam param =new SelectTypeParam();
        param.setSelectType(type);
        ApiClient.create(MemberApi.class).getMember(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo<List<MemberTo>>>(this) {
            @Override
            public void onNext(MessageTo<List<MemberTo>> msg) {
                dismissLoadingDialog();
                if (msg.getError_code() == 0) {
                    if (recyclePageIndex != 1) {
                        list.addAll(msg.getData());
                    } else {
                        list = msg.getData();
                    }
                    setRecycleList(list);
                    submitDataSuccess(list);
                } else {
                    showMessage(msg.getData().toString());
                }
            }
        });

    }

    @Override
    public void recycleViewRefresh() {
        super.recycleViewRefresh();
        getMemberList( type);
    }

    @Override
    public void recycleItemClick(View view, int position) {
        super.recycleItemClick(view, position);
    }
}
