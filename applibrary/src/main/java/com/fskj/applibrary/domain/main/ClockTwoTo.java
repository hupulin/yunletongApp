package com.fskj.applibrary.domain.main;

import lombok.Data;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/31 15:31
 * Email:635768909@qq.com
 */
@Data
public class ClockTwoTo {


    /**
     * id : 567
     * order_no : 2020102219130024674
     * created_at : 2020-10-22 19:13:00
     * updated_at : 2020-10-22 19:13:00
     * deleted_at : null
     * mid : 2512
     * mid_name : null
     * admin_id : 223
     * longitude : 120.264354
     * latitude : 30.221759
     * address : 浙江省杭州市萧山区永晖路杭州湾智慧谷
     * type : 0
     * mod_ticket : null
     * ticket_code : null
     * cost_per_day : null
     * class_id : null
     * class_name : null
     * class_remarks : null
     * fixed_commission : null
     */

    private int id;
    private String order_no;
    private String created_at;
    private String updated_at;
    private Object deleted_at;
    private int mid;
    private int admin_id;
    private String longitude;
    private String latitude;
    private String address;
    private int type;


}
