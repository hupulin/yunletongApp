package com.fskj.applibrary.domain;

import lombok.Data;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/15 17:31
 * Email:635768909@qq.com
 */
@Data
public class UpdateInfo {

    /**
     * id : 1
     * version : 1.1.1
     * url : http://web.ifishfun.com/yzl-release.apk
     * is_news : 1
     * remarks : null
     * app_type : 2
     * created_at : 2020-12-09 11:05:05
     * deleted_at : null
     * updated_at : 2020-12-09 11:05:11
     */

    private int id;
    private String version;
    private String url;
    private int is_news;
    private String remarks;
    private int app_type;
    private String created_at;
    private Object deleted_at;
    private String updated_at;


}
