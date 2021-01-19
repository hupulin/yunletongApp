package com.example.yule.mine;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.yule.R;
import com.example.yule.databinding.ItemCodeBinding;
import com.example.yule.mine.presenter.InvitationPresenter;
import com.fskj.applibrary.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/25 14:40
 * Email:635768909@qq.com
 */
public class InvitationCodeActivity extends BaseActivity {
    @BindView(R.id.grid)
    GridLayout gridLayout;
    @BindView(R.id.refresh)
    TextView refresh;
    @BindView(R.id.copy)
    TextView copy;
    InvitationPresenter presenter;
    String code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_invitation_code);
        ButterKnife.bind(this);
        initData();
    }

    ClipboardManager clipboardManager;

    private void initData() {
        setTitleName("邀请码");
        presenter = new InvitationPresenter(this);
        presenter.getMyCode();
        clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @OnClick({R.id.refresh, R.id.copy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.refresh:
                presenter.getMyCode();
                break;
            case R.id.copy:
                if (!TextUtils.isEmpty(code)) {
                    ClipData mClipData = ClipData.newPlainText("Label", code);
                    clipboardManager.setPrimaryClip(mClipData);
                    showMessage("已复制邀请码到剪切板");
                }
                break;
        }
    }

    @Override
    protected void submitDataSuccess(Object data) {
        code = (String) data;
        char[] ss = code.toCharArray();
        gridLayout.removeAllViews();
        for (char s : ss) {
            View mView = View.inflate(appContext, R.layout.item_code, null);
            ItemCodeBinding binding = DataBindingUtil.bind(mView);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.width = (int) ((getScreenWidth()) / 7);
            mView.setLayoutParams(layoutParams);
            binding.text.setText(String.valueOf(s));
            gridLayout.addView(mView);
        }
    }
}
