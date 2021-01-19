package com.example.yule.member.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.yule.Constant;
import com.example.yule.R;
import com.example.yule.databinding.ItemMemberBinding;
import com.fskj.applibrary.base.adapter.BaseAdapter;
import com.fskj.applibrary.base.adapter.BindingHolder;
import com.fskj.applibrary.domain.MemberTo;

/**
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class MemberAdapter extends BaseAdapter<MemberTo, ItemMemberBinding> {
    private String type;

    public MemberAdapter(Activity context, String type) {
        super(context);
        this.type = type;
    }

    @NonNull
    @Override
    public BindingHolder<ItemMemberBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMemberBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_member, parent, false);
        BindingHolder<ItemMemberBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setIsRecyclable(false);
        holder.setBinding(binding);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<ItemMemberBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        ItemMemberBinding binding = holder.getBinding();
        MemberTo model = mList.get(position);
        binding.name.setText(model.getNickname());
        Log.i("2222222222", "onBindViewHolder: " + type);
        if ("0".equals(type)) {
            binding.roomName.setVisibility(View.GONE);
            binding.green.setVisibility(View.GONE);
        } else {
            if (model.getRoom_at() == 0) {
                binding.roomName.setVisibility(View.GONE);
                binding.green.setVisibility(View.GONE);
            } else {
                binding.roomName.setVisibility(View.VISIBLE);
                binding.green.setVisibility(View.VISIBLE);
                binding.roomName.setText(model.getRoom_at_name());
            }
        }
        binding.ratingbar.setSelectedNumber(model.getStar_num());
        binding.ratingbar.setCanTouch(false);
        Glide.with(mContext).load(Constant.IMG_BASE_URL + model.getHeadimg()).placeholder(R.mipmap.use_image).into(binding.headImage);
//        binding.isCheck.setVisibility(model.isCheck()?View.VISIBLE:View.GONE);
//        binding . ratingbar.setOnRatingChangeListener(
//                new RatingBar.OnRatingChangeListener() {
//                    @Override
//                    public void onRatingChange(float RatingCount) {
//                        Toast.makeText(mContext, "the fill star is" + RatingCount, Toast.LENGTH_SHORT).show();
//                    }
//                }
//        );
    }

    public void setType(String type) {
        this.type = type;
    }
}
