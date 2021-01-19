package com.example.yule.company;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;

import com.example.yule.R;
import com.example.yule.company.presenter.CompanyPresenter;
import com.example.yule.login.LoginActivity;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.domain.com.ComInfoTo;
import com.fskj.applibrary.util.StatueBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/4 13:05
 * Email:635768909@qq.com
 */
public class CompanyLoginActivity extends BaseActivity {
    private CompanyPresenter presenter;
    boolean isShowPwd = false;// 是否显示密码
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.et_password)
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_login_com);
        StatueBarUtil.setStatueBarTransparent(getWindow());
        StatueBarUtil.setStatueBarTextBlack(getWindow());
        ButterKnife.bind(this);
        initData();

    }

    private void initData() {
        presenter = new CompanyPresenter(this);
    }

    @OnClick({R.id.swift_login, R.id.see_password, R.id.login,})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.swift_login:
                intent = new Intent(CompanyLoginActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                goToAnimation(2);
                break;
            case R.id.login:
                if (TextUtils.isEmpty(phone.getText().toString())) {
                    showMessage("请输入账号!");
                    return;
                }
                if (TextUtils.isEmpty(etPassword.getText().toString())) {
                    showMessage("请输入密码");
                    return;
                }
                presenter.login(phone.getText().toString(), etPassword.getText().toString());
                break;
            case R.id.see_password:
                if (!isShowPwd)
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                else
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                isShowPwd = !isShowPwd;
                etPassword.setSelection(etPassword.getText().toString().length());
                break;
        }
    }

    @Override
    protected void submitDataSuccess(Object data) {
        ComInfoTo model=(ComInfoTo)data;
        Intent intent = new Intent(CompanyLoginActivity.this, ComMainActivity.class);
        intent.putExtra("comName", model.getName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        goToAnimation(1);
    }
}
