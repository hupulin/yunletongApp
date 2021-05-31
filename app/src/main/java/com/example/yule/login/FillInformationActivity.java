package com.example.yule.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yule.R;
import com.example.yule.login.presenter.FillInformationPresenter;
import com.fskj.applibrary.base.BaseActivity;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/3/29 18:16
 * Email:635768909@qq.com
 */
public class FillInformationActivity extends BaseActivity {
    FillInformationPresenter presenter;

    @BindView(R.id.height)
    EditText height;
    @BindView(R.id.weight)
    EditText weight;
    @BindView(R.id.marital_status)
    EditText maritalStatus;
    @BindView(R.id.emergency_contact)
    EditText emergencyContact;
    @BindView(R.id.emergency_contact_phone)
    EditText emergencyContactPhone;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.temporary_residence_num)
    EditText temporaryResidenceNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_fill_info);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        setTitleName("");
        presenter = new FillInformationPresenter(this);
    }

    @OnClick({R.id.confirm,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                presenter.fillInfo(height.getText().toString(), weight.getText().toString(), maritalStatus.getText().toString(), emergencyContact.getText().toString(), emergencyContactPhone.getText().toString(), address.getText().toString(), temporaryResidenceNum.getText().toString());
                break;
        }
    }
}
