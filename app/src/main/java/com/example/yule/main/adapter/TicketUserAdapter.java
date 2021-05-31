package com.example.yule.main.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yule.R;
import com.example.yule.databinding.ItemTicketBinding;
import com.example.yule.login.LoginActivity;
import com.fskj.applibrary.base.adapter.BaseAdapter;
import com.fskj.applibrary.base.adapter.BindingHolder;
import com.fskj.applibrary.domain.main.TicketTo;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/22 16:53
 * Email:635768909@qq.com
 */
public class TicketUserAdapter extends BaseAdapter<TicketTo, ItemTicketBinding> {
    private int type;


    /**
     * type 1  使用 type 2 已退 type 2 失效
     * */
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
        binding.account.setText(model.getTotal_fee() / 100.0 + "");
        binding.buyTime.setText("购买日期  " + model.getCreated_at().substring(0, 10));
        if (type == 1) {
            binding.userTime.setText("使用日期  " + model.getTicket_use_at().substring(0, 10));
            binding.companyName.setText(model.getAdmin_name()+"  "+model.getRoom_name()+"房间已使用");
            binding.viewType.setBackground(mContext.getResources().getDrawable(R.mipmap.ticket_used));
        } else if(type == 2) {
            binding.companyName.setText(model.getAdmin_name());

            binding.userTime.setText("退款日期  " + model.getTicket_use_at().substring(0, 10));
            binding.viewType.setBackground(mContext.getResources().getDrawable(R.mipmap.ticket_refund));
        }else{
            binding.companyName.setText(model.getAdmin_name());

            binding.userTime.setText("失效日期  " + model.getTicket_use_at().substring(0, 10));
            binding.viewType.setBackground(mContext.getResources().getDrawable(R.mipmap.invalid));
            binding.result.setVisibility(View.VISIBLE);
            binding.result.setText("失效原因  "+ model.getSat_reason_type());
            binding.remark.setVisibility(View.VISIBLE);
            binding.remark.setOnClickListener(v -> {
                NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mContext);
                dialogBuilder.setContentView(R.layout.dialog_text);
                dialogBuilder.show();
                dialogBuilder.findViewById(R.id.parent).setOnClickListener(v1 -> dialogBuilder.dismiss());
                TextView view = dialogBuilder.findViewById(R.id.text);
                view.setText(model.getReason());
            });
        }
    }

}
