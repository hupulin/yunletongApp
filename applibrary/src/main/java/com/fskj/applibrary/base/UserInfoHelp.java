package com.fskj.applibrary.base;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.fskj.applibrary.domain.login.UserInfoTo;
import com.google.gson.Gson;


/**
 * Created by Administrator on 2018/9/11.
 */

public class UserInfoHelp {




    public void saveUserInfo(UserInfoTo userInfo){

        SpUtil.put("UserInfo", JSON.toJSONString(userInfo));
        if (userInfo==null)
            SpUtil.put("UserInfo", "");
    }

    public UserInfoTo getUserInfo() {
        if (!TextUtils.isEmpty(SpUtil.getString("UserInfo")))
            return new Gson().fromJson(SpUtil.getString("UserInfo"),UserInfoTo.class);
        return new UserInfoTo();
    }
}
