package com.example.yule.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.yule.R;
import com.example.yule.login.AuthenticationActivity;
import com.example.yule.main.fragment.ClockFragment;
import com.example.yule.main.fragment.InspectionFragment;
import com.example.yule.main.fragment.InspectionListFragment;
import com.example.yule.main.fragment.MineFragment;
import com.example.yule.main.fragment.TicketFragment;
import com.example.yule.main.presenter.MainActivityPresenter;
import com.example.yule.member.MemberFragment;
import com.fskj.applibrary.base.ActivityManager;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.SpUtil;
import com.fskj.applibrary.util.NoSlideViewPager;
import com.fskj.applibrary.util.StatueBarUtil;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/22 10:37
 * Email:635768909@qq.com
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.view_pager)
    NoSlideViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    @BindView(R.id.clock_text)
    TextView clockText;
    @BindView(R.id.ticket_text)
    TextView ticketText;
    @BindView(R.id.mine_text)
    TextView mineText;
    @BindView(R.id.ticket_icon)
    View ticketIcon;
    @BindView(R.id.clock_icon)
    View clockIcon;
    @BindView(R.id.mine_icon)
    View mineIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatueBarUtil.setStatueBarColor(getWindow(), "#7165E3");
        StatueBarUtil.setStatueBarTextWhite(getWindow());
        ButterKnife.bind(this);
        initView();
        initData();
    }
    MainActivityPresenter presenter;
    private void initView() {
        presenter=new MainActivityPresenter(this);
        presenter.getUserInfo();

    }

    @Override
    protected void submitDataSuccess(Object data) {
        super.submitDataSuccess(data);
        fragmentMine.setView();
    }

    private void showDialog() {
        isShowdialog = true;
        NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(this);
        dialogBuilder.setContentView(com.fskj.applibrary.R.layout.dialog_alert);
        TextView cancel = dialogBuilder.findViewById(com.fskj.applibrary.R.id.cancel);
        TextView confirm = dialogBuilder.findViewById(com.fskj.applibrary.R.id.confirm);
        TextView tipText = dialogBuilder.findViewById(com.fskj.applibrary.R.id.title);

        tipText.setText("用户尚未实名，请先实名");
        dialogBuilder.show();
        confirm.setText("去实名");
        cancel.setText("退出");
        confirm.setOnClickListener(v -> {
            SpUtil.put("isMain", true);
            Intent intent = new Intent(this, AuthenticationActivity.class);
            startActivity(intent);
            goToAnimation(1);
        });
        cancel.setOnClickListener(v1 -> {
            Observable.from(ActivityManager.activityList).subscribe((BaseActivity baseActivity) -> baseActivity.finish());
            finish();
            dialogBuilder.dismiss();
        });
    }

    private void initData() {
        useType = userInfoHelp.getUserInfo().getM_type();
        initFragment();
        if (userInfoHelp.getUserInfo().getIs_face() == 0) {
            showDialog();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        presenter.getUserInfo();

    }

    int useType;
    MineFragment fragmentMine;
    private void initFragment() {

        TicketFragment fragmentTicket = new TicketFragment();
         fragmentMine = new MineFragment();
        MemberFragment fragmentMember = new MemberFragment();
        InspectionFragment fragmentInspection = new InspectionFragment();
        ClockFragment fragmentClock = new ClockFragment();
        InspectionListFragment fragmentInspectionList = new InspectionListFragment();
        if (useType == 0) {
            fragmentList.add(fragmentClock);
            fragmentList.add(fragmentTicket);
            fragmentList.add(fragmentMine);
            ticketText.setText("票");
            ticketIcon.setBackground(getResources().getDrawable(R.mipmap.ticket_un));


        } else if (useType == 1) {
            fragmentList.add(fragmentClock);
            fragmentList.add(fragmentMember);
            fragmentList.add(fragmentMine);
            ticketText.setText("人员");
            ticketIcon.setBackground(getResources().getDrawable(R.mipmap.member_un));

        } else {
            fragmentList.add(fragmentInspection);
            fragmentList.add(fragmentInspectionList);
            fragmentList.add(fragmentMine);
            clockText.setText("巡查");
            ticketText.setText("巡查记录");
            ticketIcon.setBackground(getResources().getDrawable(R.mipmap.record_un));

        }
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()

        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if(position==2){
                    presenter.getUserInfo();

                }
                if (useType == 0) {//普通
                    clockText.setTextColor(Color.parseColor(position == 0 ? "#7165E3" : "#888888"));
                    clockIcon.setBackground(getResources().getDrawable((position == 0 ? R.mipmap.clock : R.mipmap.clock_un)));
                    ticketText.setTextColor(Color.parseColor(position == 1 ? "#7165E3" : "#888888"));
                    ticketIcon.setBackground(getResources().getDrawable((position == 1 ? R.mipmap.ticket : R.mipmap.ticket_un)));
                    mineText.setTextColor(Color.parseColor(position == 2 ? "#7165E3" : "#888888"));
                    mineIcon.setBackground(getResources().getDrawable((position == 2 ? R.mipmap.mine : R.mipmap.mine_un)));
                } else if (useType == 1) {//妈咪
                    clockText.setTextColor(Color.parseColor(position == 0 ? "#7165E3" : "#888888"));
                    clockIcon.setBackground(getResources().getDrawable((position == 0 ? R.mipmap.clock : R.mipmap.clock_un)));
                    ticketText.setTextColor(Color.parseColor(position == 1 ? "#7165E3" : "#888888"));
                    ticketIcon.setBackground(getResources().getDrawable((position == 1 ? R.mipmap.member : R.mipmap.member_un)));
                    mineText.setTextColor(Color.parseColor(position == 2 ? "#7165E3" : "#888888"));
                    mineIcon.setBackground(getResources().getDrawable((position == 2 ? R.mipmap.mine : R.mipmap.mine_un)));
                } else {//巡查
                    clockText.setTextColor(Color.parseColor(position == 0 ? "#7165E3" : "#888888"));
                    clockIcon.setBackground(getResources().getDrawable((position == 0 ? R.mipmap.clock : R.mipmap.clock_un)));
                    ticketText.setTextColor(Color.parseColor(position == 1 ? "#7165E3" : "#888888"));
                    ticketIcon.setBackground(getResources().getDrawable((position == 1 ? R.mipmap.record : R.mipmap.record_un)));
                    mineText.setTextColor(Color.parseColor(position == 2 ? "#7165E3" : "#888888"));
                    mineIcon.setBackground(getResources().getDrawable((position == 2 ? R.mipmap.mine : R.mipmap.mine_un)));
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        if ("1".equals(getIntent().getStringExtra("index"))) {
            viewPager.setCurrentItem(1);
        }

    }

    @OnClick({R.id.clock_layout, R.id.ticket_layout, R.id.mine_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clock_layout:
                viewPager.setCurrentItem(0, false);
                break;
            case R.id.ticket_layout:
                viewPager.setCurrentItem(1, false);
                break;
            case R.id.mine_layout:
                viewPager.setCurrentItem(2, false);
                break;
        }
    }

    FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    };
    private boolean canOut;
    private Handler handler = new Handler();
    private boolean isShowdialog;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isShowdialog) {
                return true;
            } else {
                showMessage("再返回一次退出云乐通");
                if (canOut) {
                    Observable.from(ActivityManager.activityList).subscribe((BaseActivity baseActivity) -> baseActivity.finish());
                    finish();
                }
                canOut = true;
                handler.postDelayed(() -> canOut = false, 3000);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
