package com.example.yule.member;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.yule.R;
import com.example.yule.databinding.ItemCodeBinding;
import com.example.yule.member.presenter.AddMemberPresenter;
import com.fskj.applibrary.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/7 15:52
 * Email:635768909@qq.com
 */
public class AddMemberActivity extends BaseActivity {
    @BindView(R.id.grid)
    GridLayout gridLayout;
    @BindView(R.id.et_grid)
    EditText etGrid;
    List<TextView> mTextList = new ArrayList<>();
    AddMemberPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        setTitleName("添加人员");
        presenter=new AddMemberPresenter(this);
        createTextView();
        etGrid.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence chars, int arg1, int arg2, int arg3) {
            }

            @Override
            public void beforeTextChanged(CharSequence chars, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String temp = etGrid.getText().toString().trim();
                char[] ss = temp.toCharArray();
                for (int i = 0; i <mTextList.size() ; i++) {
                    mTextList.get(i).setText("");
                }
                for (int i = 0; i <ss.length ; i++) {
                    Log.i("2222", "afterTextChanged: "+String.valueOf(ss[i]));
                    mTextList.get(i).setText(String.valueOf(ss[i]));
                }

            }
        });
    }


    private void createTextView() {
        for (int i = 0; i < 6; i++) {
            View mView = View.inflate(appContext, R.layout.item_code, null);
            ItemCodeBinding binding = DataBindingUtil.bind(mView);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.width = (int) ((getScreenWidth()) / 7);
            mView.setLayoutParams(layoutParams);
            mTextList.add(binding.text);
            gridLayout.addView(mView);
        }

    }
    @OnClick({R.id.confirm,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                if(etGrid.getText().toString().trim().length()<6){
                   showMessage("请输入邀请码");
                    return;

                }
                presenter.addMember(etGrid.getText().toString().trim());

                break;
        }
    }

    @Override
    protected void submitDataSuccess(Object data) {
        showMessage("添加成功");
        finish();
        goToAnimation(2);
    }
}
