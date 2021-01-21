package com.example.yule.company;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.yule.R;
import com.fskj.applibrary.domain.com.RoomTo;
import com.fskj.applibrary.util.wheelview.OnWheelChangedListener;
import com.fskj.applibrary.util.wheelview.OnWheelScrollListener;
import com.fskj.applibrary.util.wheelview.WheelView;
import com.fskj.applibrary.util.wheelview.adapter.AbstractWheelTextAdapter1;

import java.util.ArrayList;
import java.util.List;

public class SelectRoomPopWindow extends PopupWindow implements View.OnClickListener {

    private WheelView wvFloor;
    private WheelView wvRoom;

    private View lyChangeAddress;
    private View lyChangeAddressChild;
    private TextView btnSure;
    private TextView btnCancel;


    /**
     * key - 市 values - 区s
     */


    private ArrayList<RoomTo> mProvinceDatas = new ArrayList<>();
    private ArrayList<RoomTo> mCitisDatasMap = new ArrayList<>();


    private ArrayList<String> arrProvinces = new ArrayList<String>();
    private ArrayList<String> arrCitys = new ArrayList<String>();

    private AddressTextAdapter provinceAdapter;
    private AddressTextAdapter cityAdapter;
    private OnAddressCListener onAddressCListener;


    private int selectProvinceIndex = 0;
    private int selectCityIndex = 0;

    private String strFloor= "";
    private String strRoom = "";


    private String code = "";

    private int maxsize = 22;
    private int minsize = 16;
    List<RoomTo> mList = new ArrayList<>();

    public SelectRoomPopWindow(final Context context, List<RoomTo> list) {
        super(context);
        this.mList = list;
        View view = View.inflate(context, R.layout.room_select_pop_layout, null);
        wvFloor = (WheelView) view.findViewById(R.id.wv_floor);
        wvRoom = (WheelView) view.findViewById(R.id.wv_room);
        lyChangeAddress = view.findViewById(R.id.ly_myinfo_changeaddress);
        lyChangeAddressChild = view.findViewById(R.id.ly_myinfo_changeaddress_child);
        btnSure = (TextView) view.findViewById(R.id.btn_myinfo_sure);
        btnCancel = (TextView) view.findViewById(R.id.btn_myinfo_cancel);


        //设置SelectPicPopupWindow的View
        this.setContentView(view);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
//		this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

        lyChangeAddressChild.setOnClickListener(this);
        lyChangeAddress.setOnClickListener(this);
        btnSure.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        initData();
        initProvinces();
        provinceAdapter = new AddressTextAdapter(context, arrProvinces, 0);
        wvFloor.setVisibleItems(3);
        wvFloor.setViewAdapter(provinceAdapter);
        wvFloor.setCurrentItem(0);

        initCitys(mProvinceDatas.get(0).getRooms());
        cityAdapter = new AddressTextAdapter(context, arrCitys, 0);
        wvRoom.setVisibleItems(3);
        wvRoom.setViewAdapter(cityAdapter);
        wvRoom.setCurrentItem(0);
        code = mList.get(0).getRooms().get(0).getId() + "";
        strRoom = mList.get(0).getRooms().get(0).getName() + "";

        wvFloor.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) provinceAdapter.getItemText(wheel.getCurrentItem());
                strFloor = currentText;
                selectProvinceIndex = newValue;
//                strRoom = mProvinceDatas.get(newValue).getRooms().get(0).getName();
                strRoom = mList.get(newValue).getRooms().get(0).getName();
                code = mList.get(selectProvinceIndex).getRooms().get(0).getId() + "";
                setTextviewSize(currentText, provinceAdapter);
                selectProvinceIndex = newValue;
                initCitys(mProvinceDatas.get(newValue).getRooms());
                cityAdapter = new AddressTextAdapter(context, arrCitys, 0);
                wvRoom.setVisibleItems(10);
                wvRoom.setViewAdapter(cityAdapter);
                wvRoom.setCurrentItem(0);

            }
        });

        wvFloor.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub

            }
        });

        wvRoom.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) cityAdapter.getItemText(wheel.getCurrentItem());
                strRoom = currentText;
                selectCityIndex = newValue;
                setTextviewSize(currentText, cityAdapter);
                code = mList.get(selectProvinceIndex).getRooms().get(newValue).getId() + "";
                //根据市，地区联动


            }
        });

        wvRoom.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
            }
        });

    }

    public void setAddresskListener(OnAddressCListener onAddressCListener) {
        this.onAddressCListener = onAddressCListener;
    }


    private class AddressTextAdapter extends AbstractWheelTextAdapter1 {
        ArrayList<String> list;

        protected AddressTextAdapter(Context context, ArrayList<String> list, int currentItem) {
            super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem);
            this.list = list;
            setItemTextResource(R.id.tempValue);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            return list.get(index) + "";
        }
    }

    /**
     * 设置字体大小
     *
     * @param curriteItemText
     * @param adapter
     */
    public void setTextviewSize(String curriteItemText, AddressTextAdapter adapter) {

        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();

            if (curriteItemText.equals(currentText)) {
                textvew.setTextSize(22);
            } else {
                textvew.setTextSize(16);
            }
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v == btnSure) {
            if (onAddressCListener != null) {
                onAddressCListener.onClick(code, strRoom);
            }
        } else if (v == btnCancel) {

        } else if (v == lyChangeAddressChild) {
            return;
        }
        else if (v == lyChangeAddress) {
            dismiss();
        } else {
//			dismiss();
        }
        dismiss();
    }

    /**
     * 回调接口
     *
     * @author Administrator
     */
    public interface OnAddressCListener {
        public void onClick(String code, String city);
    }

    /**
     * 获取数据
     */
    private void initData() {
        for (int i = 0; i < mList.size(); i++) {
            mProvinceDatas.add(mList.get(i));
            for (int j = 0; j < mList.get(i).getRooms().size(); j++) {
                mCitisDatasMap.add(mList.get(i).getRooms().get(j));
            }
        }
    }

    /**
     * 初始化省会
     */
    public void initProvinces() {
        arrProvinces.clear();
        for (int i = 0; i < mProvinceDatas.size(); i++) {
            arrProvinces.add(mProvinceDatas.get(i).getName());
        }

    }

    /**
     * 根据省会，生成该省会的所有城市
     */
    public void initCitys(List<RoomTo> cityList) {
        arrCitys.clear();
        for (int i = 0; i < cityList.size(); i++) {
            arrCitys.add(cityList.get(i).getName());
        }
    }


}