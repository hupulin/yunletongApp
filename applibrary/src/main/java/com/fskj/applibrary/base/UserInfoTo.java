package com.fskj.applibrary.base;


import com.fskj.applibrary.domain.login.LoginTo;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by Administrator on 2018/9/11.
 */
@Data
public class UserInfoTo implements Serializable {


    /**
     * nickname :
     * headimg :
     * unique_id :
     * integral :
     */

    private String name;
    private String headimg;
    private String mobile;
    private int balance;//余额
    private int integral;//积分

    private String token;
    private String province;
    private String city;
    private String birth;
    private int sex;//性别 可选值:1(男),2(女),3(迷)
    private int age;//
    private String signature;//签名
    private int level;//类型 可选值:1(普通)
    private String levelname;//等级名称
    private boolean isverified;//是否实名
    private boolean isinvited;//是否邀请
    private boolean isvip;//是否vip
    private boolean validity;//vip失效时间
    private String invitationcode;//vip失效时间



}
