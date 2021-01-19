package com.fskj.applibrary.domain.main;

import java.io.Serializable;

import lombok.Data;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/22 16:50
 * Email:635768909@qq.com
 */
@Data
public class TicketTo  implements Serializable {
    private String code;
    private String id;
    private int use_state;
    private int total_fee;
    private String mid_to_name;
    private String admin_name;
    private String created_at;
    private String ticket_use_at;//使用或者退票
    private String ticket_sat_at;
    private String room_name;

}
