package com.example.yule.main.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yule.R;
import com.example.yule.login.FillInformationActivity;
import com.example.yule.login.LoginActivity;
import com.example.yule.main.presenter.UpdatePresenter;
import com.example.yule.mine.BalanceActivity;
import com.example.yule.mine.ClockRecordActivity;
import com.example.yule.mine.InvitationCodeActivity;
import com.example.yule.mine.IssueTicketsActivity;
import com.example.yule.mine.ServiceActivity;
import com.example.yule.mine.UserInfoActivity;
import com.example.yule.util.AutoUpdateUI;
import com.fskj.applibrary.base.ActivityManager;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.BaseFragment;
import com.fskj.applibrary.base.SpUtil;
import com.fskj.applibrary.domain.UpdateInfo;
import com.fskj.applibrary.impl.PermissionListener;

import com.fskj.applibrary.util.TipsDialog;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observable;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/22 20:25
 * Email:635768909@qq.com
 * 我的
 */
public class MineFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.manage_name)
    TextView manageName;
    @BindView(R.id.company_name)
    TextView companyName;
    @BindView(R.id.invitation_layout)
    RelativeLayout invitationLayout;
    @BindView(R.id.balance_layout)
    RelativeLayout balanceLayout;
    @BindView(R.id.clock_layout)
    RelativeLayout clockLayout;
    @BindView(R.id.issue_ticket)
    RelativeLayout issueTicket;
    @BindView(R.id.head_image)
    ImageView headImage;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.version)
    TextView versionName;
    UpdatePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = View.inflate(appContext, R.layout.fragment_mine, null);
        unbinder = ButterKnife.bind(this, mView);
        userInfoTo = userInfoHelp.getUserInfo();
        initView();
        return mView;
    }

    private void initView() {
        setView();
        presenter = new UpdatePresenter(this);
        versionName.setText("版本号：V"+getVersion());
    }

    int useType;


    public void setView() {
        userInfoTo = userInfoHelp.getUserInfo();
        useType = userInfoTo.getM_type();
        if (useType == 0) {
            name.setText(userInfoTo.getNickname());
            issueTicket.setVisibility(View.GONE);

            if (userInfoTo.getCompany() != null && userInfoTo.getCompany().getName() != null) {
                companyName.setText("公司：" + userInfoTo.getCompany().getName());
            } else {
                companyName.setText("公司：" + "暂未绑定");
            }
            if (userInfoTo.getFid_user() != null && !(TextUtils.isEmpty(userInfoTo.getFid_user().getNickname()))) {
                manageName.setText("组长：" + userInfoTo.getFid_user().getNickname());
            } else {
                manageName.setText("组长：" + "暂未绑定");
            }
        } else if (useType == 1) {//妈咪
            name.setText(userInfoTo.getNickname());
            companyName.setVisibility(View.GONE);
            if (userInfoTo.getCompany() != null && userInfoTo.getCompany().getName() != null) {
                manageName.setText("公司：" + userInfoTo.getCompany().getName());
            }else{
                manageName.setText("公司：暂无");

            }
            invitationLayout.setVisibility(View.GONE);
            balanceLayout.setVisibility(View.GONE);
//            issueTicket.setVisibility(View.GONE);

        } else {
            titleName.setText("我的");
            name.setText(userInfoTo.getNickname());
            companyName.setVisibility(View.GONE);
            if (userInfoTo.getCompany() != null && userInfoTo.getCompany().getName() != null) {
                manageName.setText("公司：" + userInfoTo.getCompany().getName());
            }else{
                manageName.setText("公司：暂无" );

            }
            issueTicket.setVisibility(View.GONE);
            invitationLayout.setVisibility(View.GONE);
            balanceLayout.setVisibility(View.GONE);
            clockLayout.setVisibility(View.GONE);
        }
        Glide.with(getActivity()).load(userInfoTo.getHeadimg()).placeholder(R.mipmap.use_image).into(headImage);
    }

    @OnClick({R.id.logout, R.id.service_layout, R.id.issue_ticket, R.id.set_layout, R.id.balance_layout, R.id.invitation_layout, R.id.clock_layout, R.id.update_layout,})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.logout:
                showLogOutDialog();
//                timeDialog();
                break;
            case R.id.update_layout:
                presenter.getUpdateInfo();
                break;
            case R.id.clock_layout:
                intent = new Intent(getActivity(), ClockRecordActivity.class);
//                intent = new Intent(getActivity(), FillInformationActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
                case R.id.issue_ticket:
                intent = new Intent(getActivity(), IssueTicketsActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.balance_layout:
                intent = new Intent(getActivity(), BalanceActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.set_layout:
                intent = new Intent(getActivity(), UserInfoActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.invitation_layout:
                intent = new Intent(getActivity(), InvitationCodeActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;

            case R.id.service_layout:
                intent = new Intent(getActivity(), ServiceActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
        }
    }

    @Override
    public void onResume() {
        setView();
        super.onResume();
    }


    private void showLogOutDialog() {
        NiftyDialogBuilder dialog = TipsDialog.show(getActivity(), "确定退出登录");
        TextView confirm = dialog.findViewById(R.id.confirm);
        TextView cancel = dialog.findViewById(R.id.cancel);
        confirm.setText("取消");
        cancel.setText("退出");
        confirm.setOnClickListener(v -> dialog.dismiss());
        cancel.setOnClickListener(v1 -> {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            SpUtil.put("Token", "");
            userInfoHelp.saveUserInfo(null);
            Observable.from(ActivityManager.activityList).subscribe((BaseActivity baseActivity) -> baseActivity.finish());
            startActivity(intent);
            goToAnimation(2);
            dialog.dismiss();
        });
    }


    @Override
    protected void submitDataSuccess(Object data) {
        super.submitDataSuccess(data);
        UpdateInfo mode = (UpdateInfo) data;
        if (!getVersion().equals(mode.getVersion())) {
            getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, new PermissionListener() {
                @Override
                public void accept(String permission) {
                    AutoUpdateUI.instance(getActivity()).executeUpdateUI(mode);

                }

                @Override
                public void refuse(String permission) {

                }
            });

        } else {
            showMessage("已是最新版本，无需更新");
        }

    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = getActivity().getPackageManager();
            PackageInfo info = manager.getPackageInfo(getActivity().getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "无法获取到版本号";
        }
    }
}
