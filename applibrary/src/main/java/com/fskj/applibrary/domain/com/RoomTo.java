package com.fskj.applibrary.domain.com;

import java.util.List;

import lombok.Data;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/5 14:14
 * Email:635768909@qq.com
 */
@Data
public class RoomTo {

    /**
     * id : 6
     * admin_id : 223
     * name : 一楼
     * created_at : 2020-07-27 14:10:31
     * updated_at : 2020-07-27 14:10:31
     * deleted_at : null
     * rooms : [{"id":3,"admin_id":223,"team_id":6,"name":"101","created_at":"2020-07-27 14:11:04","updated_at":"2020-07-27 14:11:04","deleted_at":null},{"id":4,"admin_id":223,"team_id":6,"name":"102","created_at":"2020-07-27 14:11:12","updated_at":"2020-07-27 14:11:12","deleted_at":null},{"id":5,"admin_id":223,"team_id":6,"name":"103","created_at":"2020-07-27 14:11:19","updated_at":"2020-07-27 14:11:19","deleted_at":null}]
     */

    private int id;
    private int admin_id;
    private String name;
    private String created_at;
    private String updated_at;
    private List<RoomTo> rooms;


}

