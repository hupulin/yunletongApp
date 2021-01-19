package com.fskj.applibrary.domain;

import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by zhangxutong .
 * Date: 16/08/28
 */
@Data
public class MemberTo extends BaseIndexPinyinBean implements Serializable {

    private String nickname;//名字
    private int id;//名字
    private String headimg;//头像
    private String remark_name;//备注
    private int star_num;//星星数
    private String room_at_name;//房间名字
    private int room_at;//房间名字
    private int work_status;//房间名字
    private int use_state_new;//未使用
    private int use_state_used;//已使用
    private boolean isTop=false;//是否是最上面的 不需要被转化成拼音的





    @Override
    public String getTarget() {
        return nickname;
    }

    @Override
    public boolean isNeedToPinyin() {
        return !isTop;
    }


    @Override
    public boolean isShowSuspension() {
        return !isTop;
    }
}
