package com.example.yule.main.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yule.R;
import com.example.yule.main.adapter.CompanyListAdapter;
import com.example.yule.main.presenter.TicketPresenter;
import com.example.yule.ticket.fragment.TicketListFragment;
import com.example.yule.util.FragmentAdapter;
import com.fskj.applibrary.base.BaseFragment;
import com.fskj.applibrary.base.view.RecycleViewHeadView;
import com.fskj.applibrary.domain.main.CompanyTo;
import com.fskj.applibrary.domain.main.TicketTotal;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/22 12:38
 * 票 列表
 * //
 * Email:635768909@qq.com
 */
public class TicketFragment extends BaseFragment {
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.un_use)
    TextView unUse;
    @BindView(R.id.used)
    TextView used;
    @BindView(R.id.refund)
    TextView refund;
    @BindView(R.id.invalid)
    TextView invalid;
    Unbinder unbinder;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.move_line)
    RelativeLayout moveLine;
    private List<Fragment> fragmentList = new ArrayList<>();
    TicketPresenter presenter;

    public TicketFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = View.inflate(appContext, R.layout.fragment_ticket, null);
        unbinder = ButterKnife.bind(this, mView);
        initView();
        return mView;
    }

    private void initView() {
        presenter = new TicketPresenter(this, "");
        presenter.getTicketTotal(0);
        presenter.getTicketCom();
        initFragment();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void submitDataSuccess(Object data) {
        TicketTotal model = (TicketTotal) data;
        unUse.setText("未用" + model.getSelect_new() + "张");
        used.setText("已用" + model.getSelect_used() + "张");
        refund.setText("已退" + model.getSelect_refund() + "张");
        invalid.setText("失效" + model.getSelect_cancle() + "张");
    }

    TicketListFragment Fragment1;
    TicketListFragment Fragment2;
    TicketListFragment Fragment3;
    TicketListFragment Fragment4;

    private void initFragment() {
        FragmentPagerAdapter adapter = new FragmentAdapter(getChildFragmentManager(), fragmentList);
        Fragment1 = new TicketListFragment(0);
        Fragment2 = new TicketListFragment(1);
        Fragment3 = new TicketListFragment(2);
        Fragment4 = new TicketListFragment(3);

        fragmentList.add(Fragment1);
        fragmentList.add(Fragment2);
        fragmentList.add(Fragment3);
        fragmentList.add(Fragment4);
        viewPager.setOffscreenPageLimit(4);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float v, @Px int i1) {
                moveLine.setX(getScreenWidth() / 4 * position + i1 / 4);
            }

            @Override
            public void onPageSelected(int position) {
                unUse.setTextColor(position == 0 ? Color.parseColor("#7165E3") : Color.parseColor("#303030"));
                used.setTextColor(position == 1 ? Color.parseColor("#7165E3") : Color.parseColor("#303030"));
                refund.setTextColor(position == 2 ? Color.parseColor("#7165E3") : Color.parseColor("#303030"));
                invalid.setTextColor(position == 3 ? Color.parseColor("#7165E3") : Color.parseColor("#303030"));
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        viewPager.setAdapter(adapter);

    }

    @OnClick({R.id.un_use, R.id.used, R.id.refund, R.id.select, R.id.invalid})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.un_use:
                viewPager.setCurrentItem(0, false);
                break;
            case R.id.used:
                viewPager.setCurrentItem(1, false);
                break;
            case R.id.refund:
                viewPager.setCurrentItem(2, false);
                break;
            case R.id.invalid:
                viewPager.setCurrentItem(3, false);
                break;
            case R.id.select:
                showList();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getTicketTotal(0);
        presenter.getTicketCom();

    }

    List<CompanyTo> modeList = new ArrayList<>();

    @Override
    public void loadDataSuccess(Object data) {
        modeList = (List<CompanyTo>) data;
    }

    private String selectName = "全部公司";
    private int selectId = 0;

    public void showList() {
        List<CompanyTo> list = new ArrayList<>();
        CompanyTo mode = new CompanyTo();
        mode.setCheck(false);
        mode.setName("全部公司");
        list.add(mode);
        list.addAll(modeList);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setCheck(false);
            if (selectName.equals(list.get(i).getName())) {
                list.get(i).setCheck(true);
            }
        }
        NiftyDialogBuilder dialog = NiftyDialogBuilder.getInstance(getActivity());
        dialog.setContentView(R.layout.dialog_show_company);
        LRecyclerView recycleView = dialog.findViewById(R.id.recycler_view);
        dialog.findViewById(R.id.parent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setRefreshHeader(new RecycleViewHeadView(appContext));
        CompanyListAdapter adapter = new CompanyListAdapter(getActivity());
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        recycleView.setAdapter(lRecyclerViewAdapter);
        recycleView.setLoadMoreEnabled(false);
        recycleView.setPullRefreshEnabled(false);
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                selectName = list.get(position).getName();
                selectId = list.get(position).getId();
                Fragment1.getData(selectId);
                Fragment2.getData(selectId);
                Fragment3.getData(selectId);
                Fragment3.getData(selectId);
                presenter.getTicketTotal(selectId);
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setCheck(false);
                }
                rightTitle.setText(selectName);
                list.get(position).setCheck(true);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        adapter.setList(list);
        adapter.notifyDataSetChanged();
        dialog.show();
    }

}

