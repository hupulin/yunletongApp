package com.example.yule.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.yule.R;
import com.fskj.applibrary.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/25 14:12
 * Email:635768909@qq.com
 */
public class BalanceActivity extends BaseActivity  {
    @BindView(R.id.balance)
    TextView balance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_balance);
        ButterKnife.bind(this);
        initData();

    }

    private void initData() {
        setTitleName("账户余额");
        setRightName("账户明细");
        balance.setText("￥"+userInfoTo.getAccount()/100.0);

    }
    @OnClick({R.id.right_name,})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.right_name:
                Intent intent   = new Intent(this, AccountDetailsActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
        }
    }
}
