package com.fskj.applibrary.domain;

import lombok.Data;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/12 14:05
 * Email:635768909@qq.com
 */
@Data
public class InspectionTypeTo {

    /**
     * id : 1
     * type : 1
     * name : 运营正常
     * is_normal : 1
     * created_at : 2021-01-12 11:49:45
     * deleted_at : null
     */

    private int id;
    private int type;
    private String name;
    private int is_normal;
    private String created_at;
    private Object deleted_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIs_normal() {
        return is_normal;
    }

    public void setIs_normal(int is_normal) {
        this.is_normal = is_normal;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Object getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Object deleted_at) {
        this.deleted_at = deleted_at;
    }
}
