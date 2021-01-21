package com.example.yule.inspection.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.yule.R;
import com.example.yule.databinding.ItemInspectionChildBinding;
import com.fskj.applibrary.base.Constant;
import com.fskj.applibrary.base.PostImageDetailActivity;
import com.fskj.applibrary.base.adapter.BaseAdapter;
import com.fskj.applibrary.base.adapter.BindingHolder;
import com.fskj.applibrary.domain.inspection.InspectionChildTo;

import java.io.Serializable;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/13 19:50
 * Email:635768909@qq.com
 */
public class InspectionChildAdapter extends BaseAdapter<InspectionChildTo.InspectionRecordBoxBean, ItemInspectionChildBinding> {

    public InspectionChildAdapter(Activity context) {
        super(context);
    }

    @NonNull
    @Override
    public BindingHolder<ItemInspectionChildBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemInspectionChildBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_inspection_child, parent, false);
        BindingHolder<ItemInspectionChildBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);
        holder.setIsRecyclable(false);

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<ItemInspectionChildBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        ItemInspectionChildBinding binding = holder.getBinding();
        InspectionChildTo.InspectionRecordBoxBean model = mList.get(position);
        binding.floor.setText("巡查楼层：" + model.getFloor_name());
        binding.result.setText("巡查结果：" + model.getType_name());
        binding.fold.setVisibility(model.isFold() ? View.GONE : View.VISIBLE);
        binding.normal.setVisibility(model.is_normal() ? View.VISIBLE : View.GONE);
        binding.abNormal.setVisibility(model.is_normal() ? View.GONE : View.VISIBLE);
        binding.arrow.setBackground(mContext.getResources().getDrawable(model.isFold()? R.mipmap.arrow_up: R.mipmap.arrow_down));
        if (!TextUtils.isEmpty(model.getRemarks())) {
            binding.content.setText("巡查描述：" + model.getRemarks());
        }else{
            binding.content.setText("巡查描述：无");
        }
        binding.itemClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setFold(!model.isFold());
                notifyDataSetChanged();
            }
        });
        binding.grid.removeAllViews();
        if( model.getImg_info().size()>0){
            for (int i = 0; i < model.getImg_info().size(); i++) {
                String pathList = "";
                ImageView imageView = new ImageView(mContext);
//            Picasso.get().load(mList.get(i).getAttachments().get(j).getFileUrl()).into(imageView);
                disPlayImage(imageView,Constant.IMG_BASE_URL+model.getImg_info().get(i).getImg_url());
                pathList = pathList + model.getImg_info().get(i).getImg_url() + ";";
                imageView.setTag(model.getImg_info().get(i).getImg_url());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                String finalPathList = pathList;
                imageView.setOnClickListener(view -> {
                    Intent intent = new Intent(mContext, PostImageDetailActivity.class);
                    intent.putExtra("CurrentPath", (String) view.getTag());
                    intent.putExtra("PathList", (Serializable) finalPathList.substring(0, finalPathList.length() - 1));
                    mContext.startActivity(intent);
                    goToAnimation(1);
                });
                GridLayout.LayoutParams layoutParam = new GridLayout.LayoutParams();
                layoutParam.width = getScreenWidth() * 212 / 750;
                layoutParam.height = getScreenWidth() * 212 / 750;
                layoutParam.setGravity(Gravity.CENTER);
                layoutParam.leftMargin = 14 * getScreenWidth() / 750;
                layoutParam.rightMargin = 14 * getScreenWidth() / 750;
                layoutParam.topMargin = 20 * getScreenWidth() / 750;
                layoutParam.bottomMargin = 20 * getScreenWidth() / 750;
                imageView.setLayoutParams(layoutParam);
                binding.grid.addView(imageView);
            }
        }


    }
}

