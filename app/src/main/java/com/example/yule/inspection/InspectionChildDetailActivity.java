package com.example.yule.inspection;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yule.Constant;
import com.example.yule.R;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.PostImageDetailActivity;
import com.fskj.applibrary.domain.inspection.InspectionRecordTo;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/15 10:38
 * Email:635768909@qq.com
 */
public class InspectionChildDetailActivity extends BaseActivity {
    InspectionRecordTo.DataBean.BoxInfoBean model;
    @BindView(R.id.floor)
    TextView floor;
    @BindView(R.id.result)
    TextView result;
    @BindView(R.id.remark)
    TextView remark;
    @BindView(R.id.grid)
    GridLayout grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_inspection_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setTitleName("巡查详情");
        model= (InspectionRecordTo.DataBean.BoxInfoBean) getIntent().getSerializableExtra("model");
        floor.setText("巡查楼层    "+model.getFloor_name());
        result.setText("巡查结果    "+model.getType_name());
        if(!TextUtils.isEmpty(model.getRemarks())){
            remark.setText(model.getRemarks());
        }else{
            remark.setText("暂无");
        }
        if(model.getImg_info().size()>0){
            for (int i = 0; i <model.getImg_info().size() ; i++) {
                String pathList = "";
                ImageView imageView = new ImageView(this);
                pathList = pathList + model.getImg_info().get(i).getImg_url() + ";";
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                String finalPathList = pathList;
//                displayImage(imageView,Constant.IMG_BASE_URL+model.getImg_info().get(i).getImg_url());
                Picasso.get().load(Constant.IMG_BASE_URL+model.getImg_info().get(i).getImg_url()).into(imageView);
                imageView.setTag(model.getImg_info().get(i).getImg_url());
                imageView.setOnClickListener(view -> {
                    Intent intent = new Intent(this, PostImageDetailActivity.class);
                    intent.putExtra("CurrentPath", (String) view.getTag());
                    intent.putExtra("PathList", (Serializable) finalPathList.substring(0, finalPathList.length() - 1));
                    startActivity(intent);
                    goToAnimation(1);
                });
                GridLayout.LayoutParams layoutParam = new GridLayout.LayoutParams();
                layoutParam.width = getScreenWidth() * 103 / 414;
                layoutParam.height = getScreenWidth() * 103 / 414;
                layoutParam.setGravity(Gravity.CENTER);
                layoutParam.leftMargin = 13 * getScreenWidth() / 414;
                layoutParam.rightMargin = 13 * getScreenWidth() / 414;
                layoutParam.bottomMargin = 20 * getScreenWidth() / 414;
                imageView.setLayoutParams(layoutParam);
                grid.addView(imageView);
            }
        }


    }
}
