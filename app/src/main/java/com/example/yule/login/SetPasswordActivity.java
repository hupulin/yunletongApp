package com.example.yule.login;


import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yule.R;
import com.example.yule.login.presenter.RegisterPresenter;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.util.AlertDialog;
import com.fskj.applibrary.util.StatueBarUtil;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/11/2 16:20
 * Email:635768909@qq.com
 */
public class SetPasswordActivity extends BaseActivity {
    private RegisterPresenter presenter;

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.send_code)
    TextView sendCode;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_password_again)
    EditText etPasswordAgain;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.psw)
    View psw;
    @BindView(R.id.psw_again)
    View pswAgain;
    private String type;//1 忘记密码 不带账号进来(未登录)  2设置密码 带账号进来  3修改密码 (已登录)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_set_password);
        StatueBarUtil.setStatueBarTransparent(getWindow());
        StatueBarUtil.setStatueBarTextBlack(getWindow());
        ButterKnife.bind(this);
        initData();

    }

    private void initData() {
        presenter = new RegisterPresenter(this);
        setTitleName("");
        if ("1".equals(getIntent().getStringExtra("type"))) {
            title.setText("忘记密码");
        } else if ("2".equals(getIntent().getStringExtra("type"))) {
            title.setText("设置密码");
            etPhone.setEnabled(false);
            etPhone.setText(getIntent().getStringExtra("phone"));
        } else {
            title.setText("修改密码");
            etPhone.setEnabled(false);
            etPhone.setText(userInfoTo.getPhone_number());
        }
    }

    private boolean isShowPwd=true;
    private boolean isShowPwdS=true;

    @OnClick({R.id.confirm, R.id.send_code, R.id.see_password, R.id.see_password_again})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.see_password:
                if (!isShowPwd) {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    psw.setBackground(getResources().getDrawable(R.mipmap.see_psw));
                } else {
                    psw.setBackground(getResources().getDrawable(R.mipmap.hide_psw));
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                isShowPwd = !isShowPwd;
                etPassword.setSelection(etPassword.getText().toString().length());
                break;
            case R.id.see_password_again:
                if (!isShowPwdS){
                    pswAgain.setBackground(getResources().getDrawable(R.mipmap.see_psw));
                    etPasswordAgain.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                pswAgain.setBackground(getResources().getDrawable(R.mipmap.hide_psw));
                etPasswordAgain.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
                isShowPwdS = !isShowPwdS;
                etPasswordAgain.setSelection(etPasswordAgain.getText().toString().length());
                break;
            case R.id.confirm:



                if (TextUtils.isEmpty(etPhone.getText().toString())) {
                    showMessage("请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(etCode.getText().toString())) {
                    showMessage("请输入验证码");
                    return;

                }
                if (TextUtils.isEmpty(etPassword.getText().toString())) {
                    showMessage("请输入密码");
                    return;

                }
                if (TextUtils.isEmpty(etPasswordAgain.getText().toString())) {
                    showMessage("请输入密码");
                    return;

                }
                if (!etPassword.getText().toString().equals(etPasswordAgain.getText().toString())) {
                    showMessage("两次密码不一致");
                    return;

                }
                presenter.setPassword(etPhone.getText().toString(), etCode.getText().toString(), etPassword.getText().toString());
                break;

            case R.id.send_code:
                if (TextUtils.isEmpty(etPhone.getText().toString())) {
                    showMessage("请先输入手机号");
                    return;
                }
                presenter.sendSMS(etPhone.getText().toString());
                break;

        }

    }
    private int countTime;

    private void sendCodeSuccess() {
        showMessage("发送验证码成功");
        sendCode.setEnabled(false);
        new Thread(() -> {
            for (int i = 60; i >= 0; i--) {
                countTime = i;
                SystemClock.sleep(1000);
                runOnUiThread(() -> sendCode.setText(countTime == 0 ? "重发验证码" : (countTime + "秒后重发")));
                if (countTime == 0)
                    runOnUiThread(() -> sendCode.setEnabled(true));
            }
        }).start();
    }
    @Override
    protected void submitDataSuccess(Object data) {
        MessageTo msg = (MessageTo) data;
        if (msg.getError_code() == 0) {
                sendCodeSuccess();
        } else if (msg.getError_code() == 1) {
            NiftyDialogBuilder dialog = AlertDialog.show(SetPasswordActivity.this, "账号未注册");
            TextView confirm = dialog.findViewById(R.id.confirm);
            TextView title = dialog.findViewById(R.id.title);
            confirm.setText("去注册");
            confirm.setOnClickListener(v1 -> {
                Intent intent = new Intent(SetPasswordActivity.this, RegisterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                goToAnimation(1);
                dialog.dismiss();
            });
        } else {
            showMessage((String) msg.getData());
        }
    }

    @Override
    public void loadDataSuccess(Object data) {

        if ("1".equals(getIntent().getStringExtra("type"))) {//忘记密码
            showMessage("修改密码成功");
            finish();
            goToAnimation(2);

        } else if ("3".equals(getIntent().getStringExtra("type"))) {
//            Todo  设置密码
            showMessage("修改密码成功");
            finish();
            goToAnimation(2);

        } else {//Todu修改密码 去实名
            Intent  intent = new Intent(this, AuthenticationActivity.class);
            startActivity(intent);
            goToAnimation(1);
            presenter.login( etPhone.getText().toString(),etPassword.getText().toString());
        }
    }
}
