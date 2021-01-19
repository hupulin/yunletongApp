package com.example.yule.inspection.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.example.yule.R;
import com.example.yule.databinding.ItemInspectionBinding;
import com.example.yule.databinding.ItemInspectionGridBinding;
import com.example.yule.inspection.InspectionChildDetailActivity;
import com.fskj.applibrary.base.adapter.BaseAdapter;
import com.fskj.applibrary.base.adapter.BindingHolder;
import com.fskj.applibrary.domain.inspection.InspectionRecordTo;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/11 17:44
 * Email:635768909@qq.com
 */
public class InspectionAdapter extends BaseAdapter<InspectionRecordTo.DataBean, ItemInspectionBinding> {

    public InspectionAdapter(Activity context) {
        super(context);
    }

    @NonNull
    @Override
    public BindingHolder<ItemInspectionBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemInspectionBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_inspection, parent, false);
        BindingHolder<ItemInspectionBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);
        holder.setIsRecyclable(false);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<ItemInspectionBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        ItemInspectionBinding binding = holder.getBinding();
        InspectionRecordTo.DataBean model = mList.get(position);
        binding.startTime.setText("开始巡查时间："+model.getInspection_start_time());
        if(!TextUtils.isEmpty(model.getInspection_end_time())){
            binding.endTime.setText("结束巡查时间："+model.getInspection_end_time());
        }else{
            binding.endTime.setText("结束巡查时间：暂无");
        }
        if(model.getBox_info().size()>0){
            binding.grid.setVisibility(model.isFold() ? View.GONE : View.VISIBLE);

        }
        binding.arrow.setBackground(mContext.getResources().getDrawable(model.isFold()? R.mipmap.arrow_up: R.mipmap.arrow_down));
        binding.normalNum.setText("巡查正常  "+ model.getTrue_normal()+"条");
        binding.abnormalNum.setText("巡查异常  "+ model.getTrue_normal()+"条");
        binding.itemClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setFold(!model.isFold());
                notifyDataSetChanged();
            }
        });
        binding.itemClick.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
if(listener!=null){
   listener.itemLongClick(model,position);
}
                return false;
            }
        });
        binding.grid.removeAllViews();
        if(model.getBox_info().size()>0){
            for (int i = 0; i <model.getBox_info().size() ; i++) {
                View mView = View.inflate(mContext, R.layout.item_inspection_grid, null);
                ItemInspectionGridBinding bind = DataBindingUtil.bind(mView);
                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                layoutParams.width = (int) (750.0 / 750 * getScreenWidth());
                bind.floor.setText("巡查楼层："+model.getBox_info().get(i).getFloor_name() );
                bind.result.setText("巡查结果："+model.getBox_info().get(i).getType_name() );
                mView.setLayoutParams(layoutParams);
                int finalI = i;
                bind.parent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(mContext,InspectionChildDetailActivity.class);
                        intent.putExtra("model",model.getBox_info().get(finalI));
                        mContext.startActivity(intent);
                    }
                });
                binding.grid.addView(mView);
            }
        }

    }
    protected OnItemLongClickListener listener;

    public void setOnItemClickListener(OnItemLongClickListener listener) {
        this.listener = listener;

    }
    public interface OnItemLongClickListener {
        void itemLongClick(InspectionRecordTo.DataBean mode, int position);
    }

}
