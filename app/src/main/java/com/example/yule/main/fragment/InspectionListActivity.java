package com.example.yule.main.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.yule.R;
import com.example.yule.inspection.adapter.InspectionAdapter;
import com.example.yule.inspection.presenter.InspectionListPresenter;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.domain.inspection.InspectionRecordTo;
import com.fskj.applibrary.util.StatueBarUtil;
import com.github.jdsjlzx.recyclerview.LRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/5/25 13:10
 * Email:635768909@qq.com
 */
public class InspectionListActivity extends BaseActivity {
    @BindView(R.id.no_data)
    View noData;
    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;
    InspectionListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_list);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setTitleName("巡查记录");
        presenter = new InspectionListPresenter(this );
        InspectionAdapter adapter = new InspectionAdapter(this);
        adapter.setOnItemClickListener(new InspectionAdapter.OnItemLongClickListener() {
            @Override
            public void itemLongClick(InspectionRecordTo.DataBean mode, int position) {

            }
        });
        setRecycleView(adapter, recyclerView, presenter, true, true, noData);
        presenter.getInspectionRecord();
    }

}
