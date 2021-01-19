package com.example.yule.member;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yule.R;
import com.example.yule.member.adapter.MemberAdapter;
import com.example.yule.member.presenter.MemberPresenter;
import com.fskj.applibrary.base.BaseFragment;
import com.fskj.applibrary.domain.MemberTo;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;
import com.ruffian.library.RTextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/5 16:49
 * Email:635768909@qq.com
 */
public class MemberFragment extends BaseFragment {
    @BindView(R.id.no_data)
    View noData;
    @BindView(R.id.recycler_view)
    LRecyclerView mRv;
    @BindView((R.id.tvSideBarHint))//显示指示器DialogTex
            TextView mTvSideBarHint;
    @BindView((R.id.clock))
    RTextView clock;
    @BindView((R.id.un_clock))
    RTextView unClock;
    @BindView(R.id.indexBar)
    IndexBar mIndexBar;// 右侧边栏导航区域
    Unbinder unbinder;
    private MemberAdapter mAdapter;
    private LinearLayoutManager mManager;
    private SuspensionDecoration mDecoration;
    private List<MemberTo> mDatas = new ArrayList<>();
    private MemberPresenter presenter;
    boolean isClock = true;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = View.inflate(appContext, R.layout.fragment_member, null);
        unbinder = ButterKnife.bind(this, mView);
        initView();
        return mView;
    }
private String type="1";
    private void initView() {
        presenter = new MemberPresenter(this);
        presenter.getMemberList(type);
        mAdapter = new MemberAdapter(getActivity(),type);
        setRecycleView(mAdapter, mRv, presenter, true, false, noData);
//        mRv.setAdapter(mAdapter);
        mDecoration= new SuspensionDecoration(getActivity(), mDatas);
        mRv.addItemDecoration(mDecoration );
        //如果add两个，那么按照先后顺序，依次渲染。
//        mRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        //使用indexBar
//        mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);//HintTextView
//        mIndexBar = (IndexBar) findViewById(R.id.indexBar);//IndexBar

        //indexbar初始化
        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager);//设置RecyclerView的LayoutManager

        //模拟线上加载数据
//        initDatas(getResources().getStringArray(R.array.provinces));
    }

    /**
     * 组织数据源
     *
     * @param data
     * @return
     */

    @Override
    protected void submitDataSuccess(Object data) {
        super.submitDataSuccess(data);
        mDatas.clear();
        mDatas = (List<MemberTo>) data;
        //indexbar初始化
        mIndexBar.setNeedRealIndex(true);//设置需要真实的索引
        mIndexBar.setmSourceDatas(mDatas)//设置数据
               .invalidate();
        mDecoration.setmDatas(mDatas);
    }

    @OnClick({R.id.add_member, R.id.clock, R.id.un_clock})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_member:
                Intent intent = new Intent(getActivity(), AddMemberActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.clock:
                if (!isClock) {
                    isClock = !isClock;
                    clock.setTextColor(Color.parseColor("#ffffff"));
                    clock.setBackgroundColorNormal(Color.parseColor("#7165E3"));
                    unClock.setTextColor(Color.parseColor("#7165E3"));
                    unClock.setBorderColorNormal(Color.parseColor("#7165E3"));
                    unClock.setBackgroundColorNormal(Color.parseColor("#ffffff"));
                    type="1";
                    presenter.getMemberList(type);
                    mAdapter.setType(type);

                }
                break;
            case R.id.un_clock:
                if (isClock) {
                    isClock = !isClock;
                    type="0";
                    presenter.getMemberList("0");
                    mAdapter.setType(type);
                    unClock.setTextColor(Color.parseColor("#ffffff"));
                    unClock.setBackgroundColorNormal(Color.parseColor("#7165E3"));
                    clock.setBackgroundColorNormal(Color.parseColor("#ffffff"));
                    clock.setTextColor(Color.parseColor("#7165E3"));
                    clock.setBorderColorNormal(Color.parseColor("#7165E3"));

                }
                break;
        }
    }

    @Override
    public void recycleItemClick(View view, int position, Object data) {
        super.recycleItemClick(view, position, data);
        MemberTo model =(MemberTo)data;
        Intent intent = new Intent(getActivity(), MemberDetailActivity.class);
        intent.putExtra("model", (Serializable) model);
        startActivity(intent);
        goToAnimation(1);

    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
