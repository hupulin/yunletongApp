package com.example.yule.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yule.R;
import com.example.yule.login.presenter.RegisterPresenter;
import com.example.yule.web.StaticWebViewActivity;
import com.fskj.applibrary.base.ActivityManager;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.SpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/25 13:23
 * Email:635768909@qq.com
 * <p>
 * 注销
 */
public class CancellationActivity extends BaseActivity {
    @BindView(R.id.select)
    View select;
    @BindView(R.id.send_code)
    TextView sendCode;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.phone)
    TextView phone;
    RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_cancellation);
        ButterKnife.bind(this);
        initData();

    }

    private void initData() {
        setTitleName("注销");
        presenter = new RegisterPresenter(this);
        phone.setText(userInfoTo.getPhone_number());
    }

    boolean isSelect = true;

    @OnClick({R.id.confirm, R.id.select_check, R.id.to_agreement, R.id.send_code})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.confirm:
                if (!isSelect) {
                    showMessage("请先阅读并同意账号注销协议条款");
                    return;
                }
                if (TextUtils.isEmpty(etCode.getText().toString())) {
                    showMessage("请输入验证码");
                    return;

                }
                presenter.cancellation(etCode.getText().toString());
                break;
            case R.id.select_check:
                if (isSelect) {
                    select.setBackground(getResources().getDrawable(R.mipmap.select_no));
                } else {
                    select.setBackground(getResources().getDrawable(R.mipmap.select_on));
                }
                isSelect = !isSelect;
                break;
            case R.id.to_agreement:
                intent = new Intent(this, StaticWebViewActivity.class);
                intent.putExtra("Title", "注销协议");
                intent.putExtra("Url", "user/cancellationAgreement");
                startActivity(intent);
                break;
            case R.id.send_code:
                presenter.sendSMS(userInfoTo.getPhone_number());
                break;
        }
    }

    @Override
    protected void submitDataSuccess(Object data) {
        super.submitDataSuccess(data);
        sendCodeSuccess();

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

    public void cancellationApp() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        SpUtil.put("Token", "");
        SpUtil.put("shopToken", "");
        userInfoHelp.saveUserInfo(null);
        Observable.from(ActivityManager.activityList).subscribe((BaseActivity baseActivity) -> baseActivity.finish());
        startActivity(intent);
        goToAnimation(2);
    }
}
