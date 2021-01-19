package com.example.yule.ticket;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.yule.R;
import com.example.yule.ticket.presenter.BuyTicketPresenter;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.domain.PayResult;
import com.fskj.applibrary.domain.WeChatPay;
import com.fskj.applibrary.util.Event;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/25 17:12
 * Email:635768909@qq.com
 */
public class BuyTicketActivity extends BaseActivity {
    @BindView(R.id.ali_select)
    View aliSelect;
    @BindView(R.id.total)
    TextView tvtTotal;
    @BindView(R.id.pay_num)
    TextView tvPayNum;
    @BindView(R.id.unit_price)
    TextView tvUnitPrice;//单价
    @BindView(R.id.balance)
    TextView balance;//余额
    @BindView(R.id.company_name)
    TextView tvCompanyName;//公司名称
    @BindView(R.id.manage_name)
    TextView tvManageName;//管理者
    @BindView(R.id.weChat_select)
    View weChatSelect;
    @BindView(R.id.balance_select)
    View balanceSelect;
    int payType = 1;
    int payNum = 1;
    double unitPrice;
    double total;
    BuyTicketPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_buy_ticket);
        ButterKnife.bind(this);
        initData();
    }

    @Override
    public void loadDataSuccess(Object data) {
        super.loadDataSuccess(data);
        balance.setText("￥" + userInfoTo.getAccount() / 100.0);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    private void initData() {
        EventBus.getDefault().register(this);
        setTitleName("购票");
        presenter = new BuyTicketPresenter(this);
        presenter.getUserInfo();
        unitPrice = userInfoTo.getCompany().getCost_per_day() / 100.0;
        tvUnitPrice.setText("￥" + unitPrice);
        total = (((double) payNum) * (double) userInfoTo.getCompany().getCost_per_day()) / 100;
        tvtTotal.setText("￥" + String.valueOf(total));
        tvCompanyName.setText(userInfoTo.getCompany().getName());
        tvManageName.setText(userInfoTo.getFid_user().getNickname());


    }

    @OnClick({R.id.ali_layout, R.id.weChat_layout, R.id.balance_layout, R.id.reduce, R.id.add, R.id.confirm,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ali_layout:
                if (payType != 1) {
                    payType = 1;
                    aliSelect.setVisibility(View.VISIBLE);
                    weChatSelect.setVisibility(View.GONE);
                    balanceSelect.setVisibility(View.GONE);
                }

                break;
            case R.id.weChat_layout:
                if (payType != 2) {
                    payType = 2;
                    aliSelect.setVisibility(View.GONE);
                    weChatSelect.setVisibility(View.VISIBLE);
                    balanceSelect.setVisibility(View.GONE);
                }
                break;
            case R.id.balance_layout:
                if (payType != 3) {
                    payType = 3;
                    aliSelect.setVisibility(View.GONE);
                    weChatSelect.setVisibility(View.GONE);
                    balanceSelect.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.reduce:
                if (payNum > 1) {
                    payNum--;
                    tvPayNum.setText(payNum + "");
                    total = (((double) payNum) * (double) userInfoTo.getCompany().getCost_per_day()) / 100;
                    tvtTotal.setText("￥" + String.valueOf(total));
                } else {
                    showMessage("最少购买张数为1");
                }

                break;
            case R.id.add:
                payNum++;
                tvPayNum.setText(payNum + "");
                total = (((double) payNum) * (double) userInfoTo.getCompany().getCost_per_day()) / 100;
                tvtTotal.setText("￥" + String.valueOf(total));
                break;
            case R.id.confirm:
                presenter.getOrder(payType + "", payNum);
                break;

        }
    }

    @Override
    protected void submitDataSuccess(Object data) {
        if (payType == 1) {
            String payInfo = (String) data;
            payMoney(payInfo);
        } else if (payType == 2) {
            WeChatPay payTo = (WeChatPay) data;
            IWXAPI api = WXAPIFactory.createWXAPI(this, "wx8a37cef248b6fb6a");
            PayReq request = new PayReq();
            request.appId = payTo.getAppid();
            request.partnerId = payTo.getPartnerid();
            request.prepayId = payTo.getPrepayid();
            request.nonceStr = payTo.getNoncestr();
            request.timeStamp = payTo.getTimestamp();
            request.packageValue = "Sign=WXPay";
            request.sign = payTo.getSign();
            api.sendReq(request);
        } else if (payType == 3) {
            Intent intent = new Intent(BuyTicketActivity.this, BuyTicketResultActivity.class);
            Toast.makeText(appContext, "支付成功", Toast.LENGTH_SHORT).show();
            intent.putExtra("msg", "");
            startActivity(intent);
        }

    }

    protected void payMoney(String url) {
        final String payPartner = url;
        Runnable payRunnable = () -> {
            PayTask aliPay = new PayTask(this);
            Map<String, String> result = aliPay.payV2(payPartner, true);
            Message msg1 = new Message();
            msg1.what = 1;
            msg1.obj = result;
            Bundle bundle = new Bundle();
            bundle.putString("OlderSid", url);
            msg1.setData(bundle);
            mHandler.sendMessage(msg1);
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }

    //支付宝回调
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {
                    if (payType == 1) {
                        Bundle bundle = msg.getData();
                        PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                        Log.i("2222222", "payResult: " + payResult.toString());
                        // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                        String resultStatus = payResult.getResultStatus();
                        Intent intent = new Intent(BuyTicketActivity.this, BuyTicketResultActivity.class);
                        if (TextUtils.equals(resultStatus, "9000")) {//支付成功
                            Toast.makeText(appContext, "支付成功", Toast.LENGTH_SHORT).show();
                            intent.putExtra("msg", "");
                            goToAnimation(1);
                            startActivity(intent);
                        } else {//支付失败
                            if (TextUtils.equals(resultStatus, "6001")) {
                                showMessage("用户取消支付");
                            } else if (TextUtils.equals(resultStatus, "6002")) {
                                intent.putExtra("msg", "网络连接出错");
                                goToAnimation(1);
                                startActivity(intent);
                            } else {
                                intent.putExtra("msg", "支付失败");
                                goToAnimation(1);
                                startActivity(intent);
                            }

                        }

                        break;
                    }
                }
                default:
                    break;
            }
        }
    };

    //微信支付回调
    @Subscribe
    public void wxPaySuccess(Event event) {
        if ("PayResultDataWX".equals(event.getType())) {
            int state = (int) event.getData();

            Intent intent = new Intent(BuyTicketActivity.this, BuyTicketResultActivity.class);

                if (state == 10) {//支付成功
                    Toast.makeText(appContext, "支付成功", Toast.LENGTH_SHORT).show();
                    intent.putExtra("msg", "");
                    goToAnimation(1);
                    startActivity(intent);


                } else if(state == 9){//支付取消
//                    intent.putExtra("msg", "支付失败");
//                    goToAnimation(1);
//                    startActivity(intent);

                }else{//支付失败
                    intent.putExtra("msg", "支付失败");
                    goToAnimation(1);
                    startActivity(intent);
                }
        }
    }
}
