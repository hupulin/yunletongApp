package com.example.yule.mine;

import android.os.Bundle;
import android.view.View;

import com.example.yule.R;
import com.example.yule.mine.adapter.AccountDetailsAdapter;
import com.example.yule.mine.presenter.AccountDetailsPresenter;
import com.fskj.applibrary.base.BaseActivity;
import com.github.jdsjlzx.recyclerview.LRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/29 13:12
 * Email:635768909@qq.com
 */
public class AccountDetailsActivity extends BaseActivity {
    AccountDetailsPresenter presenter;
    @BindView(R.id.no_data)
    View noData;

    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_account_detail);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        setTitleName("账户明细");
        presenter=new AccountDetailsPresenter(this);
        AccountDetailsAdapter adapter=new AccountDetailsAdapter(this);
        setRecycleView(adapter, recyclerView, presenter, true, true, noData);
        presenter.getAccountRecord();
    }

}
