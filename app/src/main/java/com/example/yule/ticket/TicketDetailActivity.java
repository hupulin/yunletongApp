package com.example.yule.ticket;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yule.R;
import com.example.yule.ticket.presenter.TicketDetailPresenter;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.domain.main.TicketTo;
import com.fskj.applibrary.util.AlertDialog;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.yzq.zxinglibrary.encode.CodeCreator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/28 16:26
 * Email:635768909@qq.com
 */
public class TicketDetailActivity extends BaseActivity {
    @BindView(R.id.img_code)
    ImageView imgCode;
    @BindView(R.id.head_image)
    ImageView headImage;
    @BindView(R.id.admin_name)
    TextView adminName;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.right_name)
    TextView rightName;
    @BindView(R.id.result)
    RelativeLayout result;
    @BindView(R.id.code)
    RelativeLayout code;
    TicketTo model;
    TicketDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_ticket_detail);
        ButterKnife.bind(this);
        initData();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        presenter = new TicketDetailPresenter(this);
        setTitleName("电子票");
//        rightName.setText("退票");
        model = (TicketTo) getIntent().getSerializableExtra("mode");
        Glide.with(appContext).load(userInfoHelp.getUserInfo().getHeadimg()).placeholder(R.mipmap.use_image).into(headImage);
        userName.setText(userInfoHelp.getUserInfo().getNickname());
        imgCode.setBackground(new BitmapDrawable(getResources(), Create2DCode(model.getCode())));
        adminName.setText(model.getAdmin_name() + "  " + model.getMid_to_name());
        presenter.getTicketStatus(model.getCode());
    }

    @OnClick({R.id.right_name, R.id.result,})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.right_name:
//                presenter.refundTicket(model.getCode());
                showDialog();
                break;
            case R.id.result:
//                presenter.refundTicket(model.getCode());
                onBackPressed();
//                showDialog();
                break;
        }
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
    };

    public void getStatusSuccess(String status) {
        if ("1".equals(status)) {
            handler.removeCallbacksAndMessages(null);
            code.setVisibility(View.GONE);
            result.setVisibility(View.VISIBLE);
            rightName.setVisibility(View.GONE);
        } else {
            handler.postDelayed(() -> presenter.getTicketStatus(model.getCode()), 4000);
            result.setVisibility(View.GONE);
            code.setVisibility(View.VISIBLE);
        }
    }

    NiftyDialogBuilder dialog;

    public void showDialog() {
        dialog = AlertDialog.show(TicketDetailActivity.this, "票款金额会退回到账户余额");
        TextView confirm = dialog.findViewById(R.id.confirm);
        confirm.setOnClickListener(v1 -> {
            presenter.refundTicket(model.getCode());
        });
    }

    @Override
    protected void submitDataSuccess(Object data) {
        dialog.dismiss();
        finish();
    }

    public Bitmap Create2DCode(String str) {
        return CodeCreator.createQRCode(str, 400, 400, null);
        //生成二维矩阵,编码时要指定大小,
        //不要生成了图片以后再进行缩放,以防模糊导致识别失败
//        try {
//            BitMatrix matrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, 278, 308);
//            int width = matrix.getWidth();
//            int height = matrix.getHeight();
//            //  二维矩阵转为一维像素数组（一直横着排）
//            int[] pixels = new int[width * height];
//            for (int y = 0; y < height; y++) {
//                for (int x = 0; x < width; x++) {
//                    if (matrix.get(x, y)) {
//                        pixels[y * width + x] = 0xff000000;
//                    }
//                }
//            }
//            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//            // 通过像素数组生成bitmap, 具体参考api
//            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
//            return bitmap;
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }
//        return null;
    }

}
