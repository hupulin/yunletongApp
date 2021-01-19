package com.example.yule.mine;

import android.os.Bundle;
import android.view.View;

import com.example.yule.R;
import com.example.yule.mine.adapter.ClockAdapter;
import com.example.yule.mine.presenter.ClockRecordPresenter;
import com.fskj.applibrary.base.BaseActivity;
import com.github.jdsjlzx.recyclerview.LRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/29 10:55
 * Email:635768909@qq.com
 */
public class ClockRecordActivity extends BaseActivity {
    ClockRecordPresenter presenter;
    @BindView(R.id.no_data)
    View noData;

    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_clock_record);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        setTitleName("打卡记录");
        presenter=new ClockRecordPresenter(this);
        ClockAdapter adapter=new ClockAdapter(this);
        setRecycleView(adapter, recyclerView, presenter, true, true, noData);
        presenter.getClockRecord();
    }

}
