package com.example.yule.login;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

import com.example.yule.R;
import com.fskj.applibrary.base.ActivityManager;
import com.fskj.applibrary.base.BaseActivity;

import butterknife.ButterKnife;
import rx.Observable;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/3/23 10:34
 * Email:635768909@qq.com
 */
public class AuthenticationStateActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_authentication_state);
        ButterKnife.bind(this);
        initData();

    }

    private boolean canOut;
    private Handler handler = new Handler();

    private void initData() {
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
}
