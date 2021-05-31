package com.fskj.applibrary.base;

/**
 * Created by xzz on 2018/8/14.
 */

public class Constant {
    public static boolean isOnline = true;
    public static final String IMG_BASE_URL = isOnline ? "https://yule-app-public-prod.oss-cn-hangzhou.aliyuncs.com/" : "https://yule-app-name.oss-cn-hangzhou.aliyuncs.com/";
    public static final String BaseUrl = isOnline ? "https://web.ifishfun.com/" : "https://webdev.fangsheng.tech/";
    //    public static final String BaseUrl = isOnline?"https://web.ifishfun.com/":"http://192.168.0.125:8000";
    public final static int RESULT_CAMERA = 10011;
    public final static int RESULT_SDCARD = 811;

}
