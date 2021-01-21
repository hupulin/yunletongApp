package com.example.yule.ticket.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yule.R;
import com.example.yule.main.adapter.TicketUnUserAdapter;
import com.example.yule.main.adapter.TicketUserAdapter;
import com.example.yule.main.fragment.TicketFragment;
import com.example.yule.main.presenter.TicketPresenter;
import com.example.yule.ticket.BuyTicketActivity;
import com.fskj.applibrary.base.BaseFragment;
import com.github.jdsjlzx.recyclerview.LRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/22 13:22
 * Email:635768909@qq.com
 */
@SuppressLint("ValidFragment")
public class TicketListFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.no_data)
    View noData;
    @BindView(R.id.buy_ticket)
    View buyTicket;
    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;
    TicketPresenter presenter;
    int type;
    TicketFragment fragment;

    public TicketListFragment(int type,TicketFragment fragment) {
        this.type = type;
        this.fragment = fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = View.inflate(appContext, R.layout.fragment_ticket_list, null);
        unbinder = ButterKnife.bind(this, mView);
        initView();
        return mView;
    }


    public void recycleViewRefresh(){
        if(fragment!=null){
            fragment.refreshData();
        }
    }

    private void initView() {
        presenter = new TicketPresenter(this, type + "");
        if (type == 0) {
            buyTicket.setVisibility(View.VISIBLE);
            TicketUnUserAdapter adapter = new TicketUnUserAdapter(getActivity());
            setRecycleView(adapter, recyclerView, presenter, true, false, noData);
        } else {
            TicketUserAdapter adapter;
            if (type == 1) {
                adapter = new TicketUserAdapter(getActivity(), 1);
            } else if (type == 2) {
                adapter = new TicketUserAdapter(getActivity(), 2);
            } else {
                adapter = new TicketUserAdapter(getActivity(), 3);

            }
            setRecycleView(adapter, recyclerView, presenter, true, false, noData);
        }

        presenter.getTicket(adminId);
    }

    @OnClick({R.id.buy_ticket,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buy_ticket:
                Intent intent = new Intent(getActivity(), BuyTicketActivity.class);
                startActivity(intent);
                break;

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getTicket(adminId);

    }

    int adminId = 0;
    public void getData(int adminId) {
        this.adminId=adminId;
        presenter.getTicket(adminId);
    }
}
