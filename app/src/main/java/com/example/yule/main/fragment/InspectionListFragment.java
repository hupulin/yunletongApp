package com.example.yule.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yule.R;
import com.example.yule.inspection.adapter.InspectionAdapter;
import com.example.yule.inspection.presenter.InspectionListPresenter;
import com.example.yule.login.LoginActivity;
import com.fskj.applibrary.base.ActivityManager;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.BaseFragment;
import com.fskj.applibrary.base.SpUtil;
import com.fskj.applibrary.domain.inspection.InspectionRecordTo;
import com.fskj.applibrary.util.TipsDialog;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/11 17:03
 * Email:635768909@qq.com
 */
public class InspectionListFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.no_data)
    View noData;
    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;
    InspectionListPresenter presenter;
    @BindView(R.id.title_name)
    TextView title;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = View.inflate(appContext, R.layout.fragment_inspection_list, null);
        unbinder = ButterKnife.bind(this, mView);
        initView();
        return mView;
    }


    private void initView() {
        title.setText("巡查记录");
//        presenter = new InspectionListPresenter(getActivity());
        InspectionAdapter adapter = new InspectionAdapter(getActivity());
        adapter.setOnItemClickListener(new InspectionAdapter.OnItemLongClickListener() {
            @Override
            public void itemLongClick(InspectionRecordTo.DataBean mode, int position) {
//                showMessage("长按删除");
            }
        });
        setRecycleView(adapter, recyclerView, presenter, true, true, noData);
        presenter.getInspectionRecord();

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getInspectionRecord();

    }
    private void showLogOutDialog() {
        NiftyDialogBuilder dialog = TipsDialog.show(getActivity(), "提示");
        TextView confirm = dialog.findViewById(R.id.confirm);
        TextView cancel = dialog.findViewById(R.id.cancel);
        confirm.setText("取消");
        cancel.setText("退出");
        confirm.setOnClickListener(v -> dialog.dismiss());
        cancel.setOnClickListener(v1 -> {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            SpUtil.put("Token", "");
            userInfoHelp.saveUserInfo(null);
            Observable.from(ActivityManager.activityList).subscribe((BaseActivity baseActivity) -> baseActivity.finish());
            startActivity(intent);
            goToAnimation(2);
            dialog.dismiss();
        });
    }
}
