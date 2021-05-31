package com.example.yule.login;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yule.R;
import com.example.yule.company.CompanyLoginActivity;
import com.example.yule.login.presenter.LoginPresenter;
import com.example.yule.main.MainActivity;
import com.example.yule.web.StaticWebViewActivity;
import com.fskj.applibrary.base.ActivityManager;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.SpUtil;
import com.fskj.applibrary.util.StatueBarUtil;
import com.fskj.applibrary.util.TipsDialog;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/11/2 13:06
 * Email:635768909@qq.com
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.rl_code)
    RelativeLayout rlCode;
    @BindView(R.id.rl_password)
    RelativeLayout rlPassword;
    @BindView(R.id.login_switch)
    TextView loginSwitch;
    @BindView(R.id.send_code)
    TextView sendCode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.psw)
    View psw;
    @BindView(R.id.see_password)
    RelativeLayout seePassword;
    boolean isPswLogin = false;//是否密码登录
    boolean isShowPwd = true;// 是否显示密码
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_login);
        StatueBarUtil.setStatueBarTransparent(getWindow());
        StatueBarUtil.setStatueBarTextBlack(getWindow());
        ButterKnife.bind(this);
        initData();
//        int[] height = {1,8,6,2,5,4,8,3,7};
//        getMax(height);


    }
    public void getMax(int[]  numList) {
        int max = 0;
        for(int i = 0, j = numList.length - 1; i < j ; ){
            // = numList[i] < numList[j] ? numList[i++] : numList[j--];
            // max = Math.max(max, (j - i + 1) * minHeight);
            int minHeight ;
            if( numList[i] < numList[j]){
                minHeight  =numList[i];
                max = Math.max(max, (j - i) * minHeight);
                i++;
            }else{
                minHeight  =numList[j];
                max = Math.max(max, (j - i) * minHeight);
                j--;
            }
        }
        showMessage(max+"");

    }

    private boolean canOut;
    private Handler handler = new Handler();

    private void showDialog() {
        SpannableString str = new SpannableString("请你务必仔细阅读，充分理解“服务协议”和“隐私政策”各条款。\n你可阅读《个人隐私协议和服务条款》了解详细信息。\n" +
                "如你同意，请点击“同意”开始接受我们的服务");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(LoginActivity.this, StaticWebViewActivity.class);
                intent.putExtra("Title", "服务协议及隐私协议");
                intent.putExtra("Url", "user/agreement");
                startActivity(intent);
                goToAnimation(1);
            }
        };
        str.setSpan(clickableSpan, 35, 48, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        NiftyDialogBuilder dialog = TipsDialog.showTitle(this, str, "服务协议和隐私政策");
        TextView confirm = dialog.findViewById(R.id.confirm);
        TextView cancel = dialog.findViewById(R.id.cancel);
        cancel.setText("拒绝");
        confirm.setText("同意");
        cancel.setTextColor(Color.parseColor("#8C8C8C"));
        confirm.setOnClickListener(v1 -> {
            dialog.dismiss();
            SpUtil.put("isSecond",true);
        });
        cancel.setOnClickListener(v1 -> {
            Observable.from(ActivityManager.activityList).subscribe((BaseActivity baseActivity) -> baseActivity.finish());
            finish();
        });
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    return  true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
                showMessage("再返回一次退出云乐通");
                if (canOut) {
                    Observable.from(ActivityManager.activityList).subscribe((BaseActivity baseActivity) -> baseActivity.finish());
                    finish();
                }
                canOut = true;
                handler.postDelayed(() -> canOut = false, 3000);
                return true;


        }
        return super.onKeyDown(keyCode, event);
    }

    private void initData() {
        loginSwitch.getPaint().setAntiAlias(true);
        loginSwitch.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        presenter = new LoginPresenter(this);
        if (!SpUtil.getBoolean("isSecond")) {
            showDialog();
        }
//        codeLogin.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
    }

    @OnClick({R.id.register, R.id.see_password, R.id.company_login, R.id.login_switch, R.id.send_code, R.id.login, R.id.forget_psw,})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.register:
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                goToAnimation(1);

                break;
            case R.id.company_login:
                intent = new Intent(LoginActivity.this, CompanyLoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                goToAnimation(1);

                break;
            case R.id.login_switch:
                if (isPswLogin) {
                    //验证码登录
                    rlPassword.setVisibility(View.GONE);
                    rlCode.setVisibility(View.VISIBLE);
                    loginSwitch.setText(getString(R.string.psw_link));

                } else {//密码登录
                    rlPassword.setVisibility(View.VISIBLE);
                    rlCode.setVisibility(View.GONE);
                    loginSwitch.setText(getString(R.string.code_link));

                }
                isPswLogin = !isPswLogin;
                break;
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
            case R.id.send_code:
                if (TextUtils.isEmpty(phone.getText().toString())) {
                    showMessage("请输入手机号!");
                    return;
                }
                presenter.sendSMS(phone.getText().toString());
                break;
            case R.id.forget_psw:
                Intent intent1 = new Intent(LoginActivity.this, SetPasswordActivity.class);
                intent1.putExtra("type", "1");
                startActivity(intent1);
                goToAnimation(1);
                break;
            case R.id.login:
                if (TextUtils.isEmpty(phone.getText().toString())) {
                    showMessage("请输入手机号!");
                    return;
                }
                if (isPswLogin) {
                    if (TextUtils.isEmpty(etPassword.getText().toString())) {
                        showMessage("请输入密码!");
                        return;
                    }
                    presenter.login(phone.getText().toString(), etPassword.getText().toString());
                } else {
                    if (TextUtils.isEmpty(etCode.getText().toString())) {
                        showMessage("请输入验证码!");
                        return;
                    }
                    presenter.loginSMS(phone.getText().toString(), etCode.getText().toString());
                }
                break;
        }
    }

    @Override
    protected void submitDataSuccess(Object data) {


        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
        goToAnimation(1);

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
    public void loadDataSuccess(Object data) {
        sendCodeSuccess();
    }
}
