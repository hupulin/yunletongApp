package com.example.yule.company;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.yule.R;
import com.example.yule.company.presenter.SelectRoomPresenter;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.domain.com.RoomTo;
import com.fskj.applibrary.impl.PermissionListener;
import com.fskj.applibrary.util.StatueBarUtil;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/4 17:43
 * Email:635768909@qq.com
 */
public class SelectRoomActivity extends BaseActivity   implements PermissionListener {
    @BindView(R.id.room_name)
    TextView roomName;
    String roomId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_select_time);
        StatueBarUtil.setStatueBarTransparent(getWindow());
        StatueBarUtil.setStatueBarTextWhite(getWindow());
        ButterKnife.bind(this);
        initView();
    }
    private void showPopWindow() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null) imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        if(model.size()>0){
            SelectRoomPopWindow mSelectRoomPopWindow = new SelectRoomPopWindow(SelectRoomActivity.this,model);
            mSelectRoomPopWindow.showAtLocation(roomName, Gravity.BOTTOM, 0, 0);
            mSelectRoomPopWindow.setAddresskListener((code, roomStr) -> {
                roomName.setText(roomStr);
                roomId=code;
            });

        }else{
           showMessage("请先配置企业房间号   ");
        }

    }
    SelectRoomPresenter presenter;
    private void initView() {
        setTitleName("选择房间");
        presenter = new SelectRoomPresenter(this);
        presenter.getRoom();

    }
    List<RoomTo> model=new ArrayList<>();
    @Override
    public void loadDataSuccess(Object data) {
        model=(  List<RoomTo>)data;
    }

    @Override
    public void accept(String permission) {
        switch (permission) {
            case Manifest.permission.CAMERA:
                Intent intent = new Intent(SelectRoomActivity.this, CaptureActivity.class);
                /*ZxingConfig是配置类  可以设置是否显示底部布局，闪光灯，相册，是否播放提示音  震动等动能
                 * 也可以不传这个参数
                 * 不传的话  默认都为默认不震动  其他都为true
                 * */
                ZxingConfig config = new ZxingConfig();
                //config.setShowbottomLayout(true);//底部布局（包括闪光灯和相册）
                config.setPlayBeep(true);//是否播放提示音
                //config.setShake(true);//是否震动
                config.setShowAlbum(false);//是否显示相册
                config.setShowFlashLight(true);//是否显示闪光灯
                intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
                startActivityForResult(intent, 2);
                break;

        }
    }
    @OnClick({R.id.scan, R.id.select_room,  })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.scan:
                if(TextUtils.isEmpty(roomName.getText().toString())){
                  showMessage("请先选择房间");
                   return;
                }
                getPermission(Manifest.permission.CAMERA, this);
                break;
                case R.id.select_room:
                    showPopWindow();
                    break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == 2 && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(Constant.CODED_CONTENT);
//                roomName.setText("扫描结果为：" + content);
                presenter.useTicket(content,roomId);
            }
        }
    }
}
