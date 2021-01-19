package com.example.yule.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.example.yule.R;
import com.example.yule.company.ComMainActivity;
import com.example.yule.login.presenter.LoginPresenter;
import com.example.yule.main.MainActivity;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.SpUtil;
import com.fskj.applibrary.util.StatueBarUtil;

import butterknife.ButterKnife;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/7/4 14:20
 * Email:635768909@qq.com
 */
public class SplashActivity extends BaseActivity {
    private ImageView splash;
    boolean isDestroy = false;
    //    @BindView(R.id.skipBtn)
//    RTextView skipBtn;
    private boolean isClick = false;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        StatueBarUtil.setStatueBarTransparent(getWindow());
        countDown();
        LoginPresenter presenter = new LoginPresenter(this);
        presenter.getUserInfo();
    }

    private int countTime;
    private Thread thread;

    private void countDown() {
        thread = new Thread(() -> {
            for (int i = 3; i >= 0; i--) {
                countTime = i;
                runOnUiThread(() -> {
                    if (countTime == 1) {
                        if (!TextUtils.isEmpty(SpUtil.getString("Token"))) {
                            //登录态

                                if (SpUtil.getBoolean("isCom")) {
                                    startActivity(new Intent(this, ComMainActivity.class));
                                    goToAnimation(1);
                                    finish();
                                } else {
                                    startActivity(new Intent(this, MainActivity.class));
                                    goToAnimation(1);
                                    finish();
                                }



                        } else {
                            startActivity(new Intent(this, LoginActivity.class));
                            goToAnimation(1);
                            finish();
                        }
                    }
                });
                if (countTime != 0) {
                    SystemClock.sleep(1000);
                }

            }
        });
        thread.start();
    }

    @Override
    protected void onDestroy() {
        thread.interrupt();
        super.onDestroy();
    }

    boolean isGetInfo;

    @Override
    protected void submitDataSuccess(Object data) {
        super.submitDataSuccess(data);
        isGetInfo = true;
    }
}
