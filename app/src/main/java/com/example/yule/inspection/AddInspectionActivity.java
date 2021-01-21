package com.example.yule.inspection;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import com.coder.zzq.smartshow.dialog.ChooseListDialog;
import com.coder.zzq.smartshow.dialog.ChooseResult;
import com.coder.zzq.smartshow.dialog.DialogBtnClickListener;
import com.coder.zzq.smartshow.dialog.SmartDialog;
import com.example.yule.R;
import com.example.yule.inspection.presenter.AddInspectionPresenter;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.SpUtil;
import com.fskj.applibrary.base.image.UploadImageListener;
import com.fskj.applibrary.base.photopick.PhotoPickerActivity;
import com.fskj.applibrary.base.photopick.PhotoPreviewActivity;
import com.fskj.applibrary.domain.AddInspectionParam;
import com.fskj.applibrary.domain.InspectionTypeTo;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/8 17:47
 * Email:635768909@qq.com
 */
public class AddInspectionActivity extends BaseActivity implements UploadImageListener {
    @BindView(R.id.image_layout)
    GridLayout imageLayout;
    @BindView(R.id.et_floor)
    EditText etFloor;
    @BindView(R.id.content)
    EditText content;
    @BindView(R.id.tv_normal)
    TextView tvNormal;
    @BindView(R.id.tv_abnormal)
    TextView tvAbnormal;
    int inspectionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_add_inspection);
        ButterKnife.bind(this);
        initView();
    }

    AddInspectionPresenter presenter;

    private void initView() {
        setTitleName("添加巡查记录");
        inspectionId = getIntent().getIntExtra("id", 0);
        presenter = new AddInspectionPresenter(this);
        presenter.getAllInspectionType();
        setPostImageLayout(imageLayout, getScreenWidth() * 308 / 3 / 414, 9);

    }

    AddInspectionParam param = new AddInspectionParam();

    private void createParam() {
        param.setFloorName(etFloor.getText().toString().trim());
        param.setInspectionResult("你好");
        if (!TextUtils.isEmpty(content.getText().toString().trim())) {
            param.setRemarks(content.getText().toString().trim());
        }
        param.setBox_involve(intString.substring(0, intString.length() - 1));
        param.setInspectionId(getIntent().getIntExtra("id", 0));
    }

    @Override
    public void uploadImageSuccess(String path) {
//
        createParam();
        param.setBoxImg(Arrays.asList(path.split(";")));
        presenter.addInspection(param);
    }

    @Override
    protected void submitDataSuccess(Object data) {
        showMessage("添加成功");
        finish();
        SpUtil.put("isAdd",true);
        goToAnimation(2);
    }

    List<File> fileList = new ArrayList<>();

    @OnClick({R.id.inspection_normal, R.id.inspection_abnormal, R.id.confirm,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.inspection_normal:
                showNormalType();
                break;
            case R.id.inspection_abnormal:
                showAbnormalType();
                break;
            case R.id.confirm:
                if (TextUtils.isEmpty(etFloor.getText().toString().trim())) {
                    showMessage("请先输入楼层");
                    return;
                }
                if (TextUtils.isEmpty(tvNormal.getText().toString().trim()) && TextUtils.isEmpty(tvAbnormal.getText().toString().trim())) {
                    showMessage("请先选择巡查结果");
                    return;
                }
                if (imagePaths.size() > 0) {
                    for (int i = 0; i < imagePaths.size(); i++) {
                        fileList.add(new File(String.valueOf(imagePaths.get(i))));
                    }
                    presenter.uploadImage(fileList, this);
                } else {
                    createParam();
                    presenter.addInspection(param);

                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 10:// 选择照片
                    imagePaths = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    pictureLayout.setTag(R.id.select, imagePaths);
                    if (pictureLayout != null) {
                        setPostImageLayout(imageLayout, getScreenWidth() * 338 / 3 / 414, 9);
                    }
                    break;
                case 20:// 预览
                    imagePaths = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                    pictureLayout.setTag(R.id.select, imagePaths);
                    if (pictureLayout != null)
                        setPostImageLayout(imageLayout, getScreenWidth() * 338 / 3 / 414, 9);
                    break;
            }
        }
    }

    private List<String> typeString = new ArrayList<>();
    private List<String> typeStringNormal = new ArrayList<>();
    private List<String> typeStringAbnormal = new ArrayList<>();
    private List<InspectionTypeTo> list;

    public void getTypeSuccess(List<InspectionTypeTo> mList) {
        this.list = mList;
        for (int i = 0; i < list.size(); i++) {
            typeString.add(list.get(i).getName());
            if (list.get(i).getIs_normal() == 0) {
                typeStringAbnormal.add(list.get(i).getName());
            } else {
                typeStringNormal.add(list.get(i).getName());
            }
        }

    }

    private boolean isNormal;
    String intString;

    private void showNormalType() {
        new ChooseListDialog()
                .title("巡查结果")
//                .defaultChoosePos(position)
                .checkMarkPos(Gravity.LEFT)
                .checkMarkColorRes(R.color.app_color)
                .choiceMode(ChooseListDialog.CHOICE_MODE_MULTIPLE)
                .keepChosenPosByLast(true)
                .items(typeStringNormal)
                .confirmBtn("确定", new DialogBtnClickListener() {
                    @Override
                    public void onBtnClick(SmartDialog dialog, int which, Object data) {
                        isNormal = true;
                        ChooseResult chooseResult = (ChooseResult) data;
                        String chooseData = Arrays.toString(chooseResult.getChooseItems()).replace("[", "").replace("]", "");
                        tvNormal.setText(chooseData);
                        intString = "";
                        String[] normalList = chooseData.replaceAll("\\s*", "").split(",");
//                        String[] normalList = chooseData.split(",");
                        for (int i = 0; i < normalList.length; i++) {
                            for (int j = 0; j < list.size(); j++) {
                                if (normalList[i].equals(list.get(j).getName())) {
                                    intString = intString + list.get(j).getId() + ",";
                                }
                            }
                        }
                        tvAbnormal.setHint("点击选择");
                        tvAbnormal.setText("");
                        dialog.dismiss();
                    }
                })
                .showInActivity(this);
    }

    private void showAbnormalType() {
        new ChooseListDialog()
                .title("巡查结果")
//               .defaultChoosePos(position)
                .checkMarkPos(Gravity.LEFT)
                .checkMarkColorRes(R.color.app_color)
                .choiceMode(ChooseListDialog.CHOICE_MODE_MULTIPLE)
                .keepChosenPosByLast(true)
                .items(typeStringAbnormal)
                .confirmBtn("确定", new DialogBtnClickListener() {
                    @Override
                    public void onBtnClick(SmartDialog dialog, int which, Object data) {
                        isNormal = false;
                        ChooseResult chooseResult = (ChooseResult) data;
                        String chooseData = Arrays.toString(chooseResult.getChooseItems()).replace("[", "").replace("]", "");
                        tvNormal.setText(chooseData);
                        intString = "";
                        String[] normalList = chooseData.replaceAll("\\s*", "").split(",");
                        for (int i = 0; i < normalList.length; i++) {
                            for (int j = 0; j < list.size(); j++) {
                                if (normalList[i].equals(list.get(j).getName())) {
                                    intString = intString + list.get(j).getId() + ",";
                                }
                            }
                        }
                        tvAbnormal.setText(chooseData);
                        tvNormal.setHint("点击选择");
                        tvNormal.setText("");
                        dialog.dismiss();
                    }
                })
                .showInActivity(this);
    }


}

