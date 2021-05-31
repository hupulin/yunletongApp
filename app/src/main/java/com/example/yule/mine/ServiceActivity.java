package com.example.yule.mine;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.yule.R;
import com.fskj.applibrary.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/28 15:11
 * Email:635768909@qq.com
 */
public class ServiceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);
        initData();
    }
    ClipboardManager clipboardManager;

    private void initData() {
        setTitleName("联系客服");
        clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

    }
    @OnClick({ R.id.copy})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.copy:
                    ClipData mClipData = ClipData.newPlainText("Label", "3122469463@qq.com");
                    clipboardManager.setPrimaryClip(mClipData);
                    showMessage("已复制邮箱到剪切板");
                break;
        }
    }

}
