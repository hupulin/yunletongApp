package com.fskj.applibrary.domain.com;

import lombok.Data;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/4 14:49
 * Email:635768909@qq.com
 */
@Data
public class ComInfoTo {

    /**
     * id : 223
     * username : 测试
     * name : 测试企业
     * is_active : 1
     * mod_ticket : 1
     * fixed_commission : 20
     * ticket_admin_fee : 0.00
     * ticket_service_fee : 100.00
     * cycle : 3
     * avatar : null
     * created_at : 2020-07-20 10:33:24
     * updated_at : 2021-01-04 14:01:48
     * deleted_at : null
     * account : 1463
     * province_id : 330000
     * city_id : 330100
     * district_id : 330109
     * address : 杭州市萧山区宁围街道永晖路233号杭州湾智慧谷
     * longitude : 120.265547
     * latitude : 30.216266
     * enterprise_type : 1
     * mobile : 16657042369
     * frozen_account : 400
     * least_buy_day : 3
     * cost_per_day : 10
     * business_license_img : business_license/2020-08-08zhizhao/jF7qFphp13B8.tmp1596873138200808.png
     * legal_person_card_up_img : business_license/2020-08-08zhizhao/r5dHmphp13C9.tmp1596873138200808.jpg
     * legal_person_card_down_img : business_license/2020-08-08zhizhao/6wuHQphp13CA.tmp1596873138200808.jpg
     * store_img : business_license/2020-08-08zhizhao/XZfuaphp13CB.tmp1596873138200808.jpg
     * com_password : $2y$10$Sipsn6URsHyjB0XUa/c0LuZgRYmt2zOWYeEpZ260HGipIFyHlDcpW
     * checkin_start_time : 22:07
     * checkin_end_time : null
     * checkin_range : 1000
     */

    private int id;
    private String username;
    private String name;
    private int is_active;
    private int mod_ticket;
    private int fixed_commission;
    private String ticket_admin_fee;
    private String ticket_service_fee;
    private int cycle;
    private String created_at;
    private String updated_at;
    private int account;
    private String province_id;
    private String city_id;
    private String district_id;
    private String address;
    private String longitude;
    private String latitude;
    private int enterprise_type;
    private String mobile;
    private int frozen_account;
    private int least_buy_day;
    private int cost_per_day;
    private String business_license_img;
    private String legal_person_card_up_img;
    private String legal_person_card_down_img;
    private String store_img;
    private String com_password;
    private String checkin_start_time;
    private String checkin_range;


}
