package com.example.yule.member;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yule.Constant;
import com.example.yule.R;
import com.example.yule.member.presenter.MemberDetailPresenter;
import com.example.yule.util.RatingBar;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.domain.MemberTo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/6 13:32
 * Email:635768909@qq.com
 */
public class MemberDetailActivity extends BaseActivity {
    @BindView(R.id.head_image)
    ImageView headImage;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.un_used)
    TextView unUsed;
    @BindView(R.id.used)
    TextView used;
    @BindView(R.id.remark)
    TextView remark;
    MemberTo model;
    MemberDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_detail);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        model = (MemberTo) getIntent().getSerializableExtra("model");
        setTitleName(model.getNickname());
        presenter = new MemberDetailPresenter(this);
        Glide.with(this).load(Constant.IMG_BASE_URL+ model.getHeadimg()) .placeholder(R.mipmap.use_image).into(headImage);
        userName.setText(model.getNickname());
        ratingBar.setSelectedNumber(model.getStar_num());
        ratingBar.setCanTouch(false);
        ratingBar.setStartTotalNumber(model.getStar_num());
        unUsed.setText(model.getUse_state_new() + "");
        used.setText(model.getUse_state_used() + "");
        if (TextUtils.isEmpty(model.getRemark_name())) {
            remark.setText("暂无备注");
        } else {
            remark.setText(model.getRemark_name());
        }
    }


    @OnClick({R.id.confirm,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                Intent intent = new Intent(this, TicketRecordActivity.class);
                intent.putExtra("id", model.getId());
                startActivity(intent);
                goToAnimation(1);
                break;
        }
    }
}
