package com.example.yule.company;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.yule.R;
import com.example.yule.login.LoginActivity;
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
 * Created hpl on 2021/1/4 13:43
 * Email:635768909@qq.com
 */
public class ComMainActivity extends BaseActivity {
    @BindView(R.id.com_name)
    TextView comName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_com_main);
        StatueBarUtil.setStatueBarTransparent(getWindow());
        StatueBarUtil.setStatueBarTextWhite(getWindow());
        ButterKnife.bind(this);
        comName.setText(getIntent().getStringExtra("comName"));
    }

    private boolean canOut;
    private Handler handler = new Handler();

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

    @OnClick({R.id.logout, R.id.scan,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.logout:
                showLogOutDialog();
                break;
            case R.id.scan:
                Intent intent = new Intent(ComMainActivity.this, SelectRoomActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
        }
    }

    private void showLogOutDialog() {
        NiftyDialogBuilder dialog = TipsDialog.show(this, "确定退出登录");
        TextView confirm = dialog.findViewById(R.id.confirm);
        TextView cancel = dialog.findViewById(R.id.cancel);
        confirm.setText("取消");
        cancel.setText("退出");
        confirm.setOnClickListener(v -> dialog.dismiss());
        cancel.setOnClickListener(v1 -> {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            SpUtil.put("Token", "");
            SpUtil.put("isCom", false);
            userInfoHelp.saveUserInfo(null);
            Observable.from(ActivityManager.activityList).subscribe((BaseActivity baseActivity) -> baseActivity.finish());
            startActivity(intent);
            goToAnimation(2);
            dialog.dismiss();
        });
    }
}
