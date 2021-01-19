package com.example.yule.member;

import android.os.Bundle;
import android.view.View;

import com.example.yule.R;
import com.example.yule.member.adapter.TicketRecordAdapter;
import com.example.yule.member.presenter.TicketRecordPresenter;
import com.fskj.applibrary.base.BaseActivity;
import com.github.jdsjlzx.recyclerview.LRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/7 14:15
 * Email:635768909@qq.com  核票记录
 */
public class TicketRecordActivity extends BaseActivity {
    TicketRecordPresenter presenter;
    TicketRecordAdapter adapter;
    @BindView(R.id.no_data)
    View noData;
    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_record);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        setTitleName("核票记录");
        presenter = new TicketRecordPresenter(this);
        adapter = new TicketRecordAdapter(this);
        presenter.getTicketRecord(getIntent().getIntExtra("id", 0));
        setRecycleView(adapter, recyclerView, presenter, true, true, noData);

    }

}
