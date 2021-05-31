package com.example.yule.member.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yule.R;
import com.example.yule.databinding.ItemTicketRecordBinding;
import com.fskj.applibrary.base.adapter.BaseAdapter;
import com.fskj.applibrary.base.adapter.BindingHolder;
import com.fskj.applibrary.domain.TicketRecordTo;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/7 14:42
 * Email:635768909@qq.com
 */
public class TicketRecordAdapter extends BaseAdapter<TicketRecordTo.DataBean, ItemTicketRecordBinding> {
    public TicketRecordAdapter(Activity context) {
        super(context);
    }

    @NonNull
    @Override
    public BindingHolder<ItemTicketRecordBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemTicketRecordBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_ticket_record, parent, false);
        BindingHolder<ItemTicketRecordBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<ItemTicketRecordBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        ItemTicketRecordBinding binding = holder.getBinding();
        TicketRecordTo.DataBean model = mList.get(position);
//        model.getTicket_use_at().substring(0, 10)
        if(model.getUse_type()==2){
            binding.refund.setVisibility(View.VISIBLE);
            if(!TextUtils.isEmpty(model.getAdmin_name())){
                binding.roomName.setText(model.getAdmin_name());
            }
        }else{
            binding.refund.setVisibility(View.GONE);
            if(!TextUtils.isEmpty(model.getAdmin_name())&&!TextUtils.isEmpty(model.getRoom_name())){
                binding.roomName.setText(model.getAdmin_name()+"—"+model.getRoom_name());
            }
        }

        binding.name.setText(model.getMid_to_name());
        binding.buyTime.setText( model.getCreated_at());
        binding.useTime.setText( model.getTicket_use_at());
    }
}