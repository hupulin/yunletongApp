package com.example.yule.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yule.R;
import com.example.yule.inspection.AddInspectionActivity;
import com.example.yule.inspection.adapter.InspectionChildAdapter;
import com.example.yule.inspection.presenter.InspectionPresenter;
import com.fskj.applibrary.base.BaseFragment;
import com.fskj.applibrary.base.SpUtil;
import com.fskj.applibrary.domain.inspection.InspectionTo;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.ruffian.library.RTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/8 16:17
 * Email:635768909@qq.com
 */
public class InspectionFragment extends BaseFragment {
    @BindView(R.id.title_name)
    TextView title;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.start_inspection)
    RTextView startInspection;
    @BindView(R.id.finish_inspection)
    RTextView finishInspection;
    @BindView(R.id.add_record)
    RTextView addRecord;
    InspectionPresenter presenter;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = View.inflate(appContext, R.layout.fragment_inspection, null);
        unbinder = ButterKnife.bind(this, mView);
        initView();
        return mView;
    }


    private void initView() {
        InspectionChildAdapter adapter = new InspectionChildAdapter(getActivity());
        presenter = new InspectionPresenter(this);
        setRecycleView(adapter, recyclerView, presenter, true, true, noData);
        title.setText("巡查");

    }

    @OnClick({R.id.start_inspection, R.id.finish_inspection, R.id.add_record,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start_inspection:
                presenter.startInspection();

                break;
            case R.id.finish_inspection:
                if (inspectionId != 0) {
                    presenter.finishInspection(inspectionId);
                }
                break;
            case R.id.add_record:

                Intent intent = new Intent(getActivity(), AddInspectionActivity.class);
                intent.putExtra("id",inspectionId);
                startActivity(intent);
                goToAnimation(1);
                break;
        }
    }

    private int inspectionId;
    @BindView(R.id.no_data)
    View noData;
    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;
    @BindView(R.id.recycler_layout)
    RelativeLayout recyclerLayout;
    private  boolean isList;

    @Override
    public void onResume() {
        super.onResume();
        if(isList){
            if(SpUtil.getBoolean("isAdd")){
                presenter.getInspectionChildList(inspectionId);

                SpUtil.put("isAdd",false);
            }
        }
    }

    @Override
    protected void submitDataSuccess(Object data) {
        InspectionTo mode = (InspectionTo) data;
        inspectionId = mode.getInspectionId();
        presenter.getInspectionChildList(inspectionId);

        title.setText("正在巡查");
        isList=true;
        view.setVisibility(View.GONE);
        addRecord.setVisibility(View.VISIBLE);
        finishInspection.setVisibility(View.VISIBLE);
        startInspection.setVisibility(View.GONE);
        presenter.getInspectionChildList(inspectionId);
        recyclerLayout.setVisibility(View.VISIBLE);


    }


    @Override
    public void loadDataSuccess(Object data) {
        recyclerLayout.setVisibility(View.GONE);
        title.setText("巡查");
        isList=false;

        view.setVisibility(View.VISIBLE);
        finishInspection.setVisibility(View.GONE);
        addRecord.setVisibility(View.GONE);
        startInspection.setVisibility(View.VISIBLE);


    }
}
