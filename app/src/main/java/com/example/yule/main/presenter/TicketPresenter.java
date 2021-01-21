package com.example.yule.main.presenter;

import com.example.yule.ticket.fragment.TicketListFragment;
import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.TicketApi;
import com.fskj.applibrary.base.BaseFragment;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.domain.main.AdminIdParam;
import com.fskj.applibrary.domain.main.CompanyTo;
import com.fskj.applibrary.domain.main.TicketParam;
import com.fskj.applibrary.domain.main.TicketTo;
import com.fskj.applibrary.domain.main.TicketTotal;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/22 13:44
 * Email:635768909@qq.com
 */
public class TicketPresenter extends BasePresenter {
  private   String type;
    public TicketPresenter(BaseFragment activity,String type) {
        initContext(activity);
        this.type=type;
    }

    public void getTicketTotal(int adminId ){
        showLoadingDialog();
        AdminIdParam param =new AdminIdParam();
        param.setAdminId(adminId);
        ApiClient.create(TicketApi.class).getTicketTotal(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo<TicketTotal>>(this) {
            @Override
            public void onNext(MessageTo<TicketTotal> msg) {
                dismissLoadingDialog();
                    if(msg.getError_code()==0){
                        submitDataSuccess(msg.getData());
                }
            }
        });
    }
    public void getTicketCom(){
        showLoadingDialog();
        ApiClient.create(TicketApi.class).getTicketCom().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo<List<CompanyTo>>>(this) {
            @Override
            public void onNext(MessageTo<List<CompanyTo>> msg) {
                dismissLoadingDialog();
                    if(msg.getError_code()==0){
                        getDataSuccess(msg.getData());

                }
            }
        });
    }
    private  int adminId;
    public void getTicket(int adminId) {
        this.adminId=adminId;
        TicketParam param=new TicketParam();
        param.setSelectType(type);//
        param.setAdminId(adminId);//
        showLoadingDialog();
        ApiClient.create(TicketApi.class).getTicket(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo<List<TicketTo>>>(this) {
            @Override
            public void onNext(MessageTo<List<TicketTo>> msg) {
                dismissLoadingDialog();
                    if(msg.getError_code()==0){
                        setRecycleList(msg.getData());
                }
            }
        });
    }

    @Override
    public void recycleViewRefresh() {
        super.recycleViewRefresh();
        getTicket(adminId);
        ((TicketListFragment)mFragment).recycleViewRefresh();
    }
}
