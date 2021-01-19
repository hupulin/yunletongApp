package com.example.yule.ticket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.yule.R;
import com.example.yule.main.MainActivity;
import com.fskj.applibrary.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/28 13:52
 * Email:635768909@qq.com
 */
public class BuyTicketResultActivity extends BaseActivity {
    @BindView(R.id.text_result)
    TextView tvResult;
    @BindView(R.id.img_result)
    View ivResult;
    @BindView(R.id.result_fault)
    TextView tvFault;

    @BindView(R.id.confirm)
    TextView confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_buy_ticket_result);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        String msg = getIntent().getStringExtra("msg");

        if ("".equals(msg)) {
            setTitleName("购票成功");
            tvResult.setText("购票成功");
            ivResult.setBackground(getResources().getDrawable(R.mipmap.pay_success));
            tvFault.setVisibility(View.GONE);
            confirm.setText("返回");
        } else {
            setTitleName("购票失败");
            tvResult.setText("购票失败");
            ivResult.setBackground(getResources().getDrawable(R.mipmap.pay_faild));
            tvFault.setVisibility(View.VISIBLE);
            tvFault.setText(msg);
            confirm.setText("返回");
        }
    }

    @OnClick({R.id.confirm,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                if ("".equals(getIntent().getStringExtra("msg"))) {
                    Intent intent = new Intent(BuyTicketResultActivity.this, MainActivity.class);
                    intent.putExtra("index","1");
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    goToAnimation(2);
                } else {
                    finish();
                    goToAnimation(2);
                }

                break;
        }
    }
}
