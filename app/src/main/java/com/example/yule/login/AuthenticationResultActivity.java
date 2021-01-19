package com.example.yule.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.yule.R;
import com.example.yule.login.presenter.LoginPresenter;
import com.example.yule.main.MainActivity;
import com.fskj.applibrary.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/31 17:30
 * Email:635768909@qq.com
 */
public class AuthenticationResultActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_authentication_result);
        ButterKnife.bind(this);
        initData();

    }

    private void initData() {
        setTitleName("认证成功");
        LoginPresenter presenter = new LoginPresenter(this);
        presenter.getUserInfo();
//        if(!TextUtils.isEmpty(getIntent().getStringExtra("phone"))){
//            presenter.login(getIntent().getStringExtra("phone"), getIntent().getStringExtra("pws"));
//        }else{
//          presenter.getUserInfo();
//        }


    }

    @OnClick({R.id.confirm,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
        }
    }

}
