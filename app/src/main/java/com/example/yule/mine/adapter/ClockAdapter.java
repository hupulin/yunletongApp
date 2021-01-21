package com.example.yule.mine.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.yule.R;
import com.example.yule.databinding.ItemClockToBinding;
import com.fskj.applibrary.base.adapter.BaseAdapter;
import com.fskj.applibrary.base.adapter.BindingHolder;
import com.fskj.applibrary.domain.main.ClockRecordTo;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/29 11:15
 * Email:635768909@qq.com
 */
public class ClockAdapter extends BaseAdapter<ClockRecordTo.DataBean, ItemClockToBinding> {

    public ClockAdapter(Activity context) {
        super(context);
    }

    @NonNull
    @Override
    public BindingHolder<ItemClockToBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemClockToBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_clock_to, parent, false);
        BindingHolder<ItemClockToBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<ItemClockToBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        ItemClockToBinding binding = holder.getBinding();
        ClockRecordTo.DataBean model = mList.get(position);
        binding.address.setText(model.getAddress());
        binding.clockTime.setText(model.getCreated_at());
        if (model.getType() == 1) {
            binding.viewType.setBackground(mContext.getResources().getDrawable(R.mipmap.work_off));
        } else {
            binding.viewType.setBackground(mContext.getResources().getDrawable(R.mipmap.work_on));

        }

    }
}
