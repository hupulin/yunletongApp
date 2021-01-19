package com.fskj.applibrary.domain.main;

import java.util.List;

import lombok.Data;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/23 13:48
 * Email:635768909@qq.com
 */
@Data
public class ClockTo {

    /**
     * current_page : 1
     * data : [{"id":637,"order_no":"2020122214240665045","created_at":"2020-12-22 14:24:06","updated_at":"2020-12-22 14:24:06","deleted_at":null,"mid":999,"mid_name":"hu","admin_id":223,"longitude":"120.264363","latitude":"30.221357","address":"浙江省杭州市萧山区丰二路231号钱江世纪城人才专项用房1期","type":0,"mod_ticket":1,"ticket_code":null,"cost_per_day":null,"class_id":null,"class_name":null,"class_remarks":null,"fixed_commission":null},{"id":636,"order_no":"2020122214234889599","created_at":"2020-12-22 14:23:48","updated_at":"2020-12-22 14:23:48","deleted_at":null,"mid":999,"mid_name":"hu","admin_id":223,"longitude":"120.264363","latitude":"30.221357","address":"浙江省杭州市萧山区丰二路231号钱江世纪城人才专项用房1期","type":1,"mod_ticket":1,"ticket_code":null,"cost_per_day":null,"class_id":null,"class_name":null,"class_remarks":null,"fixed_commission":null},{"id":635,"order_no":"2020122214232673489","created_at":"2020-12-22 14:23:26","updated_at":"2020-12-22 14:23:26","deleted_at":null,"mid":999,"mid_name":"hu","admin_id":223,"longitude":"120.264363","latitude":"30.221357","address":"浙江省杭州市萧山区丰二路231号钱江世纪城人才专项用房1期","type":0,"mod_ticket":1,"ticket_code":null,"cost_per_day":null,"class_id":null,"class_name":null,"class_remarks":null,"fixed_commission":null}]
     * first_page_url : https://webdev.fangsheng.tech/api/sign_in/records?page=1
     * from : 1
     * last_page : 1
     * last_page_url : https://webdev.fangsheng.tech/api/sign_in/records?page=1
     * next_page_url : null
     * path : https://webdev.fangsheng.tech/api/sign_in/records
     * per_page : 15
     * prev_page_url : null
     * to : 3
     * total : 3
     */

    private int current_page;
    private String first_page_url;
    private int from;
    private int last_page;
    private String last_page_url;
    private Object next_page_url;
    private String path;
    private int per_page;
    private Object prev_page_url;
    private int to;
    private int total;
    private List<DataBean> data;

  @Data
    public static class DataBean {
        /**
         * id : 637
         * order_no : 2020122214240665045
         * created_at : 2020-12-22 14:24:06
         * updated_at : 2020-12-22 14:24:06
         * deleted_at : null
         * mid : 999
         * mid_name : hu
         * admin_id : 223
         * longitude : 120.264363
         * latitude : 30.221357
         * address : 浙江省杭州市萧山区丰二路231号钱江世纪城人才专项用房1期
         * type : 0
         * mod_ticket : 1
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
        private String mid_name;
        private int admin_id;
        private String longitude;
        private String latitude;
        private String address;
        private int type;
        private int mod_ticket;
        private Object ticket_code;
        private Object cost_per_day;
        private Object class_id;
        private Object class_name;
        private Object class_remarks;
        private Object fixed_commission;


    }
}
