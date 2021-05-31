package com.fskj.applibrary.domain;

import java.util.List;

import lombok.Data;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/7 14:43
 * Email:635768909@qq.com
 */
@Data
public class TicketRecordTo {

    /**
     * current_page : 1
     * data : [{"id":2335,"rel_orders_ticket":"2020123118075756888","rel_ticket_use_log":"2021010411054958596","rel_ticket_sat_log":"2021010411054994173","total_fee":42,"planform_fee":20,"admin_fee":22,"manager_fee":0,"code":"0b3b4c3f-2bf4-4d8a-b470-0b2822f8999a","type":0,"use_state":1,"use_type":2,"sat_state":1,"admin_id":223,"admin_name":"测试企业","mid_from":2512,"mid_from_name":"逾","mid_to":999,"mid_to_name":"KTV","class_id":148,"class_name":"A-2","class_remarks":null,"created_at":"2020-12-31 18:07:57","updated_at":"2021-01-04 11:05:49","deleted_at":null,"ticket_use_at":"2021-01-04 11:05:49","ticket_sat_at":"2021-01-04 11:05:49","room_id":null,"room_name":null},{"id":2334,"rel_orders_ticket":"2020123118075756888","rel_ticket_use_log":"2021010516204692286","rel_ticket_sat_log":"2021010516204632233","total_fee":42,"planform_fee":20,"admin_fee":22,"manager_fee":0,"code":"caf9ca4f-151d-4cf8-a247-219397456452","type":0,"use_state":1,"use_type":1,"sat_state":1,"admin_id":223,"admin_name":"测试企业","mid_from":2512,"mid_from_name":"逾","mid_to":999,"mid_to_name":"KTV","class_id":148,"class_name":"A-2","class_remarks":null,"created_at":"2020-12-31 18:07:57","updated_at":"2021-01-05 16:20:46","deleted_at":null,"ticket_use_at":"2021-01-05 16:20:46","ticket_sat_at":"2021-01-05 16:20:46","room_id":3,"room_name":"101"},{"id":2333,"rel_orders_ticket":"2020123118020399155","rel_ticket_use_log":"2021010516242487894","rel_ticket_sat_log":"2021010516242498871","total_fee":42,"planform_fee":20,"admin_fee":22,"manager_fee":0,"code":"b315fa2e-f25d-47df-8a33-51c0d381cb57","type":0,"use_state":1,"use_type":1,"sat_state":1,"admin_id":223,"admin_name":"测试企业","mid_from":2512,"mid_from_name":"逾","mid_to":999,"mid_to_name":"KTV","class_id":148,"class_name":"A-2","class_remarks":null,"created_at":"2020-12-31 18:02:03","updated_at":"2021-01-05 16:24:24","deleted_at":null,"ticket_use_at":"2021-01-05 16:24:24","ticket_sat_at":"2021-01-05 16:24:24","room_id":9,"room_name":"201"},{"id":2332,"rel_orders_ticket":"2020123115210672136","rel_ticket_use_log":"2021010516245499047","rel_ticket_sat_log":"2021010516245458374","total_fee":42,"planform_fee":20,"admin_fee":22,"manager_fee":0,"code":"dc619e93-ab27-471e-9864-3a2310b4b34a","type":0,"use_state":1,"use_type":1,"sat_state":1,"admin_id":223,"admin_name":"测试企业","mid_from":2512,"mid_from_name":"逾","mid_to":999,"mid_to_name":"KTV","class_id":148,"class_name":"A-2","class_remarks":null,"created_at":"2020-12-31 15:21:06","updated_at":"2021-01-05 16:24:54","deleted_at":null,"ticket_use_at":"2021-01-05 16:24:54","ticket_sat_at":"2021-01-05 16:24:54","room_id":9,"room_name":"201"},{"id":2331,"rel_orders_ticket":"2020123114345329469","rel_ticket_use_log":"2020123115202099432","rel_ticket_sat_log":"2020123115202030688","total_fee":42,"planform_fee":20,"admin_fee":22,"manager_fee":0,"code":"aee0bd07-9b05-43ff-8322-d9daee9fe97d","type":0,"use_state":1,"use_type":1,"sat_state":1,"admin_id":223,"admin_name":"测试企业","mid_from":2512,"mid_from_name":"逾","mid_to":999,"mid_to_name":"KTV","class_id":148,"class_name":"A-2","class_remarks":null,"created_at":"2020-12-31 14:34:53","updated_at":"2020-12-31 15:20:20","deleted_at":null,"ticket_use_at":"2020-12-31 15:20:20","ticket_sat_at":"2020-12-31 15:20:20","room_id":3,"room_name":"101"},{"id":2330,"rel_orders_ticket":"2020123114331508669","rel_ticket_use_log":"2020123114332628752","rel_ticket_sat_log":null,"total_fee":42,"planform_fee":20,"admin_fee":22,"manager_fee":0,"code":"7780fddc-682a-4537-9b78-a618f2d60f06","type":0,"use_state":2,"use_type":0,"sat_state":0,"admin_id":223,"admin_name":"测试企业","mid_from":2512,"mid_from_name":"逾","mid_to":999,"mid_to_name":"KTV","class_id":148,"class_name":"A-2","class_remarks":null,"created_at":"2020-12-31 14:33:15","updated_at":"2020-12-31 14:33:26","deleted_at":null,"ticket_use_at":"2020-12-31 14:33:26","ticket_sat_at":null,"room_id":null,"room_name":null},{"id":2329,"rel_orders_ticket":"2020123114313045171","rel_ticket_use_log":"2020123114324646226","rel_ticket_sat_log":"2020123114324674808","total_fee":42,"planform_fee":20,"admin_fee":22,"manager_fee":0,"code":"9fdbcc4b-b82a-49bd-9bc6-566d2c6fb51c","type":0,"use_state":1,"use_type":1,"sat_state":1,"admin_id":223,"admin_name":"测试企业","mid_from":2512,"mid_from_name":"逾","mid_to":999,"mid_to_name":"KTV","class_id":148,"class_name":"A-2","class_remarks":null,"created_at":"2020-12-31 14:31:30","updated_at":"2020-12-31 14:32:46","deleted_at":null,"ticket_use_at":"2020-12-31 14:32:46","ticket_sat_at":"2020-12-31 14:32:46","room_id":3,"room_name":"101"},{"id":2328,"rel_orders_ticket":"2020123114261413992","rel_ticket_use_log":"2020123114350949613","rel_ticket_sat_log":"2020123114350957206","total_fee":42,"planform_fee":20,"admin_fee":22,"manager_fee":0,"code":"f510515a-680b-4cd4-811e-ffa99ea7f48e","type":0,"use_state":1,"use_type":1,"sat_state":1,"admin_id":223,"admin_name":"测试企业","mid_from":2512,"mid_from_name":"逾","mid_to":999,"mid_to_name":"KTV","class_id":148,"class_name":"A-2","class_remarks":null,"created_at":"2020-12-31 14:26:14","updated_at":"2020-12-31 14:35:09","deleted_at":null,"ticket_use_at":"2020-12-31 14:35:09","ticket_sat_at":"2020-12-31 14:35:09","room_id":14,"room_name":"301"}]
     * first_page_url : https://webdev.fangsheng.tech/api/u/ticketUse?page=1
     * from : 1
     * last_page : 1
     * last_page_url : https://webdev.fangsheng.tech/api/u/ticketUse?page=1
     * next_page_url : null
     * path : https://webdev.fangsheng.tech/api/u/ticketUse
     * per_page : 15
     * prev_page_url : null
     * to : 8
     * total : 8
     */

    private int current_page;
    private String first_page_url;
    private int from;
    private int last_page;
    private String last_page_url;
    private String path;
    private int per_page;
    private int to;
    private int total;
    private List<DataBean> data;

   @Data
    public static class DataBean {
        /**
         * id : 2335
         * rel_orders_ticket : 2020123118075756888
         * rel_ticket_use_log : 2021010411054958596
         * rel_ticket_sat_log : 2021010411054994173
         * total_fee : 42
         * planform_fee : 20
         * admin_fee : 22
         * manager_fee : 0
         * code : 0b3b4c3f-2bf4-4d8a-b470-0b2822f8999a
         * type : 0
         * use_state : 1
         * use_type : 2
         * sat_state : 1
         * admin_id : 223
         * admin_name : 测试企业
         * mid_from : 2512
         * mid_from_name : 逾
         * mid_to : 999
         * mid_to_name : KTV
         * class_id : 148
         * class_name : A-2
         * class_remarks : null
         * created_at : 2020-12-31 18:07:57
         * updated_at : 2021-01-04 11:05:49
         * deleted_at : null
         * ticket_use_at : 2021-01-04 11:05:49
         * ticket_sat_at : 2021-01-04 11:05:49
         * room_id : null
         * room_name : null
         */

        private int id;
        private String rel_orders_ticket;
        private String rel_ticket_use_log;
        private String rel_ticket_sat_log;
        private int total_fee;
        private int planform_fee;
        private int admin_fee;
        private int manager_fee;
        private String code;
        private int type;
        private int use_state;
        private int use_type;
        private int sat_state;
        private int admin_id;
        private String admin_name;
        private int mid_from;
        private String mid_from_name;
        private int mid_to;
        private String mid_to_name;
        private int class_id;
        private String class_name;
        private Object class_remarks;
        private String created_at;
        private String updated_at;
        private Object deleted_at;
        private String ticket_use_at;
        private String ticket_sat_at;
        private Object room_id;
        private String room_name;


    }
}
