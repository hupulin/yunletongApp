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
import com.example.yule.login.LoginActivity;
import com.fskj.applibrary.base.ActivityManager;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.BaseFragment;
import com.fskj.applibrary.base.SpUtil;
import com.fskj.applibrary.domain.inspection.InspectionTo;
import com.fskj.applibrary.util.TipsDialog;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.ruffian.library.RTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observable;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/8 16:17
 * Email:635768909@qq.com
 */
public class InspectionFragment extends BaseFragment {
    @BindView(R.id.title_name)
    TextView title;
    @BindView(R.id.right_name)
    TextView rightName;
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
        rightName.setText("巡查记录");

    }

    @OnClick({R.id.start_inspection, R.id.finish_inspection, R.id.add_record, R.id.right_name,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start_inspection:
                presenter.startInspection();

                break;
            case R.id.finish_inspection:
                if (inspectionId != 0) {
                    showDialog();
                }
                break;
            case R.id.add_record:

                Intent intent = new Intent(getActivity(), AddInspectionActivity.class);
                intent.putExtra("id", inspectionId);
                startActivity(intent);
                goToAnimation(1);
                break;
                case R.id.right_name:
                Intent intent1 = new Intent(getActivity(), InspectionListActivity.class);
                startActivity(intent1);
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
    private boolean isList;

    @Override
    public void onResume() {
        super.onResume();
        if (isList) {
            if (SpUtil.getBoolean("isAdd")) {
                presenter.getInspectionChildList(inspectionId);

                SpUtil.put("isAdd", false);
            }
        }
    }

    @Override
    protected void submitDataSuccess(Object data) {
        InspectionTo mode = (InspectionTo) data;
        inspectionId = mode.getInspectionId();
        presenter.getInspectionChildList(inspectionId);

        title.setText("正在巡查");
        isList = true;
        view.setVisibility(View.GONE);
        addRecord.setVisibility(View.VISIBLE);
        finishInspection.setVisibility(View.VISIBLE);
        startInspection.setVisibility(View.GONE);
        presenter.getInspectionChildList(inspectionId);
        recyclerLayout.setVisibility(View.VISIBLE);


    }

    private void showDialog() {
        NiftyDialogBuilder dialog = TipsDialog.show(getActivity(), "结束本次巡查");
        TextView confirm = dialog.findViewById(R.id.confirm);
        TextView cancel = dialog.findViewById(R.id.cancel);
        cancel.setText("取消");
        confirm.setText("结束");
        confirm.setOnClickListener(v1 -> {
            dialog.dismiss();
            presenter.finishInspection(inspectionId);
        });
    }

    @Override
    public void loadDataSuccess(Object data) {
        recyclerLayout.setVisibility(View.GONE);
        title.setText("巡查");
        isList = false;

        view.setVisibility(View.VISIBLE);
        finishInspection.setVisibility(View.GONE);
        addRecord.setVisibility(View.GONE);
        startInspection.setVisibility(View.VISIBLE);


    }
}
