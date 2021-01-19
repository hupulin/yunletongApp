package com.example.yule.main.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yule.R;
import com.example.yule.databinding.ItemCompanyBinding;
import com.fskj.applibrary.base.adapter.BaseAdapter;
import com.fskj.applibrary.base.adapter.BindingHolder;
import com.fskj.applibrary.domain.main.CompanyTo;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/30 13:52
 * Email:635768909@qq.com
 */
public class CompanyListAdapter extends BaseAdapter<CompanyTo, ItemCompanyBinding> {

    public CompanyListAdapter(Activity context) {
        super(context);
    }

    @NonNull
    @Override
    public BindingHolder<ItemCompanyBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCompanyBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_company, parent, false);
        BindingHolder<ItemCompanyBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<ItemCompanyBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        ItemCompanyBinding binding = holder.getBinding();
        CompanyTo model = mList.get(position);
        binding.companyName.setText(model.getName());
        binding.isCheck.setVisibility(model.isCheck()?View.VISIBLE:View.GONE);
    }

}

