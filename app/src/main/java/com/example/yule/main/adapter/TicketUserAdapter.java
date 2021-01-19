package com.example.yule.main.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.yule.R;
import com.example.yule.databinding.ItemTicketBinding;
import com.fskj.applibrary.base.adapter.BaseAdapter;
import com.fskj.applibrary.base.adapter.BindingHolder;
import com.fskj.applibrary.domain.main.TicketTo;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/22 16:53
 * Email:635768909@qq.com
 */
public class TicketUserAdapter extends BaseAdapter<TicketTo, ItemTicketBinding> {
    private int type;

    public TicketUserAdapter(Activity context, int type) {
        super(context);
        this.type = type;
    }

    @NonNull
    @Override
    public BindingHolder<ItemTicketBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemTicketBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_ticket, parent, false);
        BindingHolder<ItemTicketBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<ItemTicketBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        ItemTicketBinding binding = holder.getBinding();
        TicketTo model = mList.get(position);
        binding.leaderName.setText("组长  " + model.getMid_to_name());
        binding.companyName.setText(model.getAdmin_name());
        binding.account.setText(model.getTotal_fee() / 100.0 + "");
        binding.buyTime.setText("购买日期  " + model.getCreated_at().substring(0, 10));
        if (type == 1) {
            binding.userTime.setText("使用日期  " + model.getTicket_use_at().substring(0, 10));
            binding.viewType.setBackground(mContext.getResources().getDrawable(R.mipmap.ticket_used));
        } else if(type == 2) {
            binding.userTime.setText("退款日期  " + model.getTicket_use_at().substring(0, 10));
            binding.viewType.setBackground(mContext.getResources().getDrawable(R.mipmap.ticket_refund));
        }else{
            binding.userTime.setText("失效日期  " + model.getTicket_use_at().substring(0, 10));
            binding.viewType.setBackground(mContext.getResources().getDrawable(R.mipmap.invalid));
        }
    }

}
