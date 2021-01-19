package com.example.yule.mine.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.yule.R;
import com.example.yule.databinding.ItemAccountRecordBinding;
import com.fskj.applibrary.base.adapter.BaseAdapter;
import com.fskj.applibrary.base.adapter.BindingHolder;
import com.fskj.applibrary.domain.main.AcooutRecordTo;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/29 13:17
 * Email:635768909@qq.com
 */
public class AccountDetailsAdapter extends BaseAdapter<AcooutRecordTo.DataBean, ItemAccountRecordBinding> {

    public AccountDetailsAdapter(Activity context) {
        super(context);
    }

    @NonNull
    @Override
    public BindingHolder<ItemAccountRecordBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemAccountRecordBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_account_record, parent, false);
        BindingHolder<ItemAccountRecordBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<ItemAccountRecordBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        ItemAccountRecordBinding binding = holder.getBinding();
        AcooutRecordTo.DataBean model = mList.get(position);
//        binding.address.setText(model.getAddress());
        binding.time.setText(model.getCreated_at());
        binding.balance.setText("余额￥" + model.getAccount_after() / 100.0);
//        binding.account.setText("-￥" + model.getFee() / 100.0);
        if (model.getType() == 1) {
            binding.type.setText("买票");
            String text=  Double.toString(model.getFee() / 100.0);
            binding.account.setText("-￥" +text.replace("-",""));

//            binding.account.setText("-￥" + model.getFee() / 100.0);
            binding.account.setTextColor(mContext.getResources().getColor(R.color.text_red)
            );
        } else if (model.getType() == 10) {
            binding.type.setText("退票");
            binding.account.setText("+￥" + model.getFee() / 100.0);
            binding.account.setTextColor(mContext.getResources().getColor(R.color.app_color));
        } else {
            binding.type.setText("其他");
            binding.account.setText("￥" + model.getFee() / 100.0);
            binding.account.setTextColor(mContext.getResources().getColor(R.color.text_gray_step));
        }
    }
}

