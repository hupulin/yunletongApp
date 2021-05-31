package com.example.yule.mine;

import android.content.ClipData;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yule.R;
import com.example.yule.databinding.ItemFaceCodeBinding;
import com.example.yule.databinding.ItemTicketTypeBinding;
import com.example.yule.mine.presenter.IssueTicketsPresenter;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.domain.login.FaceTo;
import com.fskj.applibrary.domain.main.TicketTypeTo;
import com.fskj.applibrary.util.DateUtil;
import com.fskj.applibrary.util.TimePickerDialog.TimePickerExpect;
import com.fskj.applibrary.util.TimePickerDialog.data.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/3/26 13:59
 * Email:635768909@qq.com
 */
public class IssueTicketsActivity extends BaseActivity {
    @BindView(R.id.grid)
    GridLayout gridLayout;

    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.change_text)
    TextView changeText;
    @BindView(R.id.time_start_text)
    TextView timeStartText;
    @BindView(R.id.time_end_text)
    TextView timeEndText;
    @BindView(R.id.default_num)
    TextView defaultNum;
    IssueTicketsPresenter presenter;
    private String start;
    private String end;
    int totalNum = 0;
    boolean isMember = false;
    TicketTypeTo mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_ticket);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        setTitleName("出票记录");
        presenter = new IssueTicketsPresenter(this);
        start = DateUtil.longToString(System.currentTimeMillis(), DateUtil.mFormatDateString);
        timeStartText.setText(DateUtil.longToString(System.currentTimeMillis(), DateUtil.mTicket));
        end = DateUtil.longToString(System.currentTimeMillis(), DateUtil.mFormatDateString);
        timeEndText.setText(DateUtil.longToString(System.currentTimeMillis(), DateUtil.mTicket));
        presenter.issueTicket(start, end);
    }

    private void setView() {
        gridLayout.removeAllViews();
        totalNum = 0;
        if (isMember) {
            for (int j = 0; j < mode.getUser().size(); j++) {
                View mView = View.inflate(appContext, R.layout.item_ticket_type, null);
                ItemTicketTypeBinding binding = DataBindingUtil.bind(mView);
                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                layoutParams.width = (int) ((getScreenWidth()));
                layoutParams.height = (int) ((getScreenWidth()) * 38 / 414);
                mView.setLayoutParams(layoutParams);
                binding.type.setText(mode.getUser().get(j).getNickname());
                binding.num.setText(mode.getUser().get(j).getTotal() + "");
                totalNum = totalNum + mode.getUser().get(j).getTotal();
                gridLayout.addView(mView);
            }
        } else {
            for (int i = 0; i < mode.getTicket().size(); i++) {
                View mView = View.inflate(appContext, R.layout.item_ticket_type, null);
                ItemTicketTypeBinding binding = DataBindingUtil.bind(mView);
                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                layoutParams.width = (int) ((getScreenWidth()));
                layoutParams.height = (int) ((getScreenWidth()) * 38 / 414);
                mView.setLayoutParams(layoutParams);
                binding.type.setText(mode.getTicket().get(i).getClass_name());
                binding.num.setText(mode.getTicket().get(i).getTotal() + "");
                totalNum = totalNum + mode.getTicket().get(i).getTotal();
                gridLayout.addView(mView);
            }

        }
        total.setText("总计：" + totalNum);


    }

    @Override
    protected void submitDataSuccess(Object data) {
        mode = (TicketTypeTo) data;
        setView();
    }

    private void timeDialog(int type) {
        TimePickerExpect mimePickerExpect = new TimePickerExpect.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setCallBack((timePickerView, millseconds) -> {
                    if (type == 1) {
                        timeStartText.setText(DateUtil.longToString(millseconds, DateUtil.mTicket));
                        start = DateUtil.longToString(millseconds, DateUtil.mFormatDateString);
                        presenter.issueTicket(start, end);
                    } else {
                        timeEndText.setText(DateUtil.longToString(millseconds, DateUtil.mTicket));
                        end = DateUtil.longToString(millseconds, DateUtil.mFormatDateString);
                        presenter.issueTicket(start, end);
                    }
                })
                .setYearText("年")
                .setMonthText("月")
                .setThemeColor(Color.parseColor("#7165E3"))
                .setWheelItemTextSelectorColor(Color.parseColor("#7165E3"))
                .setDayText("日")
                .setWheelItemTextSize(14)
                .setCyclic(false)
                .setMaxMillseconds(System.currentTimeMillis())
                .buildNew();
        mimePickerExpect.show(getSupportFragmentManager(), "year_month_day");

    }

    @OnClick({R.id.time_end, R.id.time_start, R.id.type_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.time_start:
                timeDialog(1);
                break;
            case R.id.time_end:
                timeDialog(2);
                break;
            case R.id.type_change:
                if (isMember) {
                    changeText.setText("人员名称");
                    type.setText("票价类型");
                } else {
                    changeText.setText("票价类型");
                    type.setText("人员名称");

                }
                isMember = !isMember;


                setView();
                break;
        }
    }
}
