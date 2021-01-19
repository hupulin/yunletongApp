package com.example.yule.main.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yule.R;
import com.example.yule.databinding.ItemTicketUnBinding;
import com.example.yule.ticket.TicketDetailActivity;
import com.fskj.applibrary.base.adapter.BaseAdapter;
import com.fskj.applibrary.base.adapter.BindingHolder;
import com.fskj.applibrary.domain.main.TicketTo;

import java.io.Serializable;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/22 16:39
 * Email:635768909@qq.com
 *
 *
 *未使用
 */
public class TicketUnUserAdapter extends BaseAdapter<TicketTo, ItemTicketUnBinding> {



    public TicketUnUserAdapter(Activity context) {
        super(context);
    }
    @NonNull
    @Override
    public BindingHolder<ItemTicketUnBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemTicketUnBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_ticket_un, parent, false);
        BindingHolder<ItemTicketUnBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<ItemTicketUnBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        ItemTicketUnBinding binding = holder.getBinding();
        TicketTo model = mList.get(position);
        binding.leaderName.setText("组长  "+model.getMid_to_name());
        binding.adminName.setText(model.getAdmin_name());
        binding.account.setText(model.getTotal_fee()/100.0+"");
        binding.time.setText("购买日期  "+model.getCreated_at().substring(0,10));
        binding.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,TicketDetailActivity.class);
                intent.putExtra("mode",(Serializable) model);

                mContext.startActivity(intent);
            }
        });
    }

}
