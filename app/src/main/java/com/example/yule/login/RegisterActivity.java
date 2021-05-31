package com.example.yule.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.yule.R;
import com.example.yule.login.presenter.RegisterPresenter;
import com.example.yule.web.StaticWebViewActivity;
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
 * Created hpl on 2020/11/2 15:14
 * Email:635768909@qq.com
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.nickName)
    TextView nickName;
    @BindView(R.id.phone)
    TextView phone;

    @BindView(R.id.select)
    View select;
    private boolean isAgress = false;

    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_register);
        StatueBarUtil.setStatueBarTransparent(getWindow());
        StatueBarUtil.setStatueBarTextBlack(getWindow());
        ButterKnife.bind(this);
        initData();

    }

    private void initData() {
        setTitleName("");
        presenter = new RegisterPresenter(this);

//        codeLogin.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
    }

    boolean isSelect = false;

    @OnClick({R.id.login, R.id.register, R.id.select_check, R.id.agreement, R.id.to_agreement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                goToAnimation(2);
                break;
                case R.id.agreement:
                case R.id.to_agreement:
                    intent = new Intent(this, StaticWebViewActivity.class);
                    intent.putExtra("Title", "服务协议及隐私协议");
                    intent.putExtra("Url", "user/agreement");
                    startActivity(intent);
                break;
            case R.id.select_check:
                if (isSelect) {
                    select.setBackground(getResources().getDrawable(R.mipmap.select_no));
                } else {
                    select.setBackground(getResources().getDrawable(R.mipmap.select_on));
                }
                isSelect = !isSelect;
                break;
            case R.id.register:


                if (TextUtils.isEmpty(nickName.getText().toString())) {
                    showMessage("请输入昵称!");
                    return;
                }
                if (TextUtils.isEmpty(phone.getText().toString())) {
                    showMessage("请输入手机号!");
                    return;
                }if (phone.getText().toString().length()!=11) {
                    showMessage("请输入11位手机号码");
                    return;
                }
                if (isSelect) {
                    presenter.register(nickName.getText().toString(), phone.getText().toString());
                } else {
                    showMessage("请先阅读并勾选协议");
                }
                break;
        }

    }

    @Override
    protected void submitDataSuccess(Object data) {
        MessageTo msg = (MessageTo) data;
        if (msg.getError_code() == 0) {
            Intent intent1 = new Intent(RegisterActivity.this, SetPasswordActivity.class);
            intent1.putExtra("type", "2");
            intent1.putExtra("phone", phone.getText().toString());
            startActivity(intent1);
            goToAnimation(1);
        } else if (msg.getError_code() == 1) {
            NiftyDialogBuilder dialog = AlertDialog.show(RegisterActivity.this, "账号已注册");
            TextView confirm = dialog.findViewById(R.id.confirm);
            confirm.setText("去登录");
            confirm.setOnClickListener(v1 -> {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                goToAnimation(2);
                dialog.dismiss();
            });
        } else {
            showMessage((String) msg.getData());
        }

    }
}