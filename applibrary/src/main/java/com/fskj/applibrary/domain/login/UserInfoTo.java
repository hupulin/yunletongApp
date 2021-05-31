package com.fskj.applibrary.domain.login;


import com.fskj.applibrary.domain.MessageTo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class UserInfoTo extends MessageTo {

    /**
     * id : 999
     * nickname : hu
     * headimg : https://yule-app-name.oss-cn-hangzhou.aliyuncs.com/head_img/2020-12-03/BU7WEphpl8keWs1606978869201203up.png
     * type : 2
     * account : 252
     * frozen_account : 0
     * unsettle_account : 0
     * days_account : 0
     * days_after_account : 0
     * name : 胡普林
     * sex : 1
     * phone_number : 18758168587
     * fid : 994
     * is_manager : 1
     * is_active : 1
     * is_face : 1
     * is_gov : 0
     * admin_id : 223
     * admin_class_id : 0
     * room_at : 18
     * room_at_name : 305
     * take_order_at : 2020-09-03 16:47:56
     * created_at : 2020-08-25 15:06:50
     * updated_at : 2020-12-22 14:02:23
     * deleted_at : null
     * default_alipay : null
     * real_status : 1
     * up_picture_card : citizen_id_photo/2020-08-25/H8Cjnphp1tyhxX1598339512200825face.png
     * down_picture_card : citizen_id_photo/2020-08-25/zcmIrphpJJfpn51598339298200825back.png
     * card_id : 420117199112085531
     * card_birthday : 19911208
     * card_nationality : 汉
     * card_residence : 武汉市新洲区仓埠街胡彰村胡彰湾四组104号
     * card_start_effective_date : 1559145600
     * card_end_effective_date : 2190297600
     * card_issue : 武汉市公安局新洲分局
     * origin_prov : null
     * origin_city : null
     * origin_dist : null
     * checkin_begin : null
     * checkin_end : null
     * national : 汉
     * fid_user : {"nickname":""}
     * company : {"name":"测试企业","cost_per_day":30,"least_buy_day":3,"class_name":"默认管理费"}
     * is_password : 1
     */

    private int id;
    private String nickname;
    private String headimg;
    private int type;
    private int account;
    private int frozen_account;
    private int unsettle_account;
    private int days_account;
    private int days_after_account;
    private String name;
    private int sex;
    private String phone_number;
    private int fid;
    private int is_manager;
    private int is_active;
    private int is_face;
    private int is_gov;
    private int admin_id;
    private int admin_class_id;
    private int room_at;
    private String created_at;
    private String updated_at;
    private int real_status;
    private String up_picture_card;
    private String down_picture_card;
    private String card_id;
    private String card_birthday;
    private String card_nationality;
    private String card_residence;
//    private int card_start_effective_date;
//    private long card_end_effective_date;
    private String card_issue;
    private String national;
    private FidUserBean fid_user;
    private CompanyBean company;
    private int is_password;
    private int m_type;   //0  普通  1小姐  2 巡查

   @Data
    public static class FidUserBean {
        /**
         * nickname :
         */

        private String nickname;


    }
    @Data
    public static class CompanyBean {
        /**
         * name : 测试企业
         * cost_per_day : 30
         * least_buy_day : 3
         * class_name : 默认管理费
         */

        private String name;
        private int cost_per_day;
        private int least_buy_day;
        private String class_name;


    }
}
