package com.fskj.applibrary.domain.inspection;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/11 17:37
 * Email:635768909@qq.com
 */
@Data
public class InspectionRecordTo  implements Serializable{

    /**
     * current_page : 1
     * data : [{"id":2,"member_id":1107,"inspection_status":1,"inspection_start_time":"2020-12-18 16:09:15","inspection_end_time":"2020-12-18 16:09:16","admin_id":223,"created_at":"2020-12-18 16:09:15","updated_at":"2020-12-18 16:09:16","deleted_at":null,"member_name":"巡查员"},{"id":3,"member_id":1107,"inspection_status":0,"inspection_start_time":"2021-01-08 17:27:51","inspection_end_time":null,"admin_id":0,"created_at":"2021-01-08 17:27:51","updated_at":"2021-01-08 17:27:51","deleted_at":null,"member_name":"巡查员"},{"id":4,"member_id":1107,"inspection_status":0,"inspection_start_time":"2021-01-08 17:32:12","inspection_end_time":null,"admin_id":0,"created_at":"2021-01-08 17:32:12","updated_at":"2021-01-08 17:32:12","deleted_at":null,"member_name":"巡查员"},{"id":5,"member_id":1107,"inspection_status":0,"inspection_start_time":"2021-01-08 17:35:08","inspection_end_time":null,"admin_id":0,"created_at":"2021-01-08 17:35:08","updated_at":"2021-01-08 17:35:08","deleted_at":null,"member_name":"巡查员"},{"id":6,"member_id":1107,"inspection_status":0,"inspection_start_time":"2021-01-08 17:42:39","inspection_end_time":null,"admin_id":0,"created_at":"2021-01-08 17:42:39","updated_at":"2021-01-08 17:42:39","deleted_at":null,"member_name":"巡查员"},{"id":7,"member_id":1107,"inspection_status":0,"inspection_start_time":"2021-01-08 17:44:13","inspection_end_time":null,"admin_id":0,"created_at":"2021-01-08 17:44:13","updated_at":"2021-01-08 17:44:13","deleted_at":null,"member_name":"巡查员"},{"id":8,"member_id":1107,"inspection_status":0,"inspection_start_time":"2021-01-08 17:44:17","inspection_end_time":null,"admin_id":0,"created_at":"2021-01-08 17:44:17","updated_at":"2021-01-08 17:44:17","deleted_at":null,"member_name":"巡查员"},{"id":9,"member_id":1107,"inspection_status":1,"inspection_start_time":"2021-01-08 17:45:54","inspection_end_time":"2021-01-08 17:46:01","admin_id":0,"created_at":"2021-01-08 17:45:54","updated_at":"2021-01-08 17:46:01","deleted_at":null,"member_name":"巡查员"},{"id":10,"member_id":1107,"inspection_status":1,"inspection_start_time":"2021-01-11 10:27:59","inspection_end_time":"2021-01-11 10:31:17","admin_id":0,"created_at":"2021-01-11 10:27:59","updated_at":"2021-01-11 10:31:17","deleted_at":null,"member_name":"巡查员"},{"id":11,"member_id":1107,"inspection_status":1,"inspection_start_time":"2021-01-11 10:34:54","inspection_end_time":"2021-01-11 10:35:20","admin_id":0,"created_at":"2021-01-11 10:34:54","updated_at":"2021-01-11 10:35:20","deleted_at":null,"member_name":"巡查员"}]
     * first_page_url : https://webdev.fangsheng.tech/api/inspection/getHistoryRecord?page=1
     * from : 1
     * last_page : 3
     * last_page_url : https://webdev.fangsheng.tech/api/inspection/getHistoryRecord?page=3
     * next_page_url : https://webdev.fangsheng.tech/api/inspection/getHistoryRecord?page=2
     * path : https://webdev.fangsheng.tech/api/inspection/getHistoryRecord
     * per_page : 10
     * prev_page_url : null
     * to : 10
     * total : 27
     */

    private int current_page;
    private String first_page_url;
    private int from;
    private int last_page;
    private String last_page_url;
    private String next_page_url;
    private String path;
    private int per_page;
    private Object prev_page_url;
    private int to;
    private int total;
    private List<DataBean> data;

    @Data
    public static class DataBean  implements Serializable {


        /**
         * id : 90
         * member_id : 1107
         * inspection_status : 0
         * inspection_start_time : 2021-01-14 17:15:39
         * inspection_end_time : null
         * admin_id : 0
         * created_at : 2021-01-14 17:15:39
         * updated_at : 2021-01-14 17:15:39
         * deleted_at : null
         * member_name : 巡查员
         * true_normal : 1
         * false_normal : 1
         * box_info : [{"id":34,"member_id":1107,"box_id":null,"inspection_id":90,"box_number":null,"box_involve":"1,7,8,9","admin_id":0,"created_at":"2021-01-14 17:16:04","updated_at":"2021-01-14 17:16:04","deleted_at":null,"inspection_result":"你好","remarks":"有图片","floor_name":"哦哦哦"},{"id":35,"member_id":1107,"box_id":null,"inspection_id":90,"box_number":null,"box_involve":"2,3,4,6","admin_id":0,"created_at":"2021-01-14 17:17:11","updated_at":"2021-01-14 17:17:11","deleted_at":null,"inspection_result":"你好","remarks":"有图片","floor_name":"异常"}]
         */

        private int id;
        private int member_id;
        private boolean fold=true;
        private int inspection_status;
        private String inspection_start_time;
        private String inspection_end_time;
        private int admin_id;
        private String created_at;
        private String updated_at;
        private Object deleted_at;
        private String member_name;
        private int true_normal;
        private int false_normal;
        private List<BoxInfoBean> box_info;


        @Data
        public static class BoxInfoBean implements Serializable {
            /**
             * id : 34
             * member_id : 1107
             * box_id : null
             * inspection_id : 90
             * box_number : null
             * box_involve : 1,7,8,9
             * admin_id : 0
             * created_at : 2021-01-14 17:16:04
             * updated_at : 2021-01-14 17:16:04
             * deleted_at : null
             * inspection_result : 你好
             * remarks : 有图片
             * floor_name : 哦哦哦
             */

            private int id;
            private int member_id;
            private Object box_id;
            private int inspection_id;
            private Object box_number;
            private String box_involve;
            private int admin_id;
            private String created_at;
            private String updated_at;
            private Object deleted_at;
            private String inspection_result;
            private String remarks;
            private String floor_name;
            private String type_name;
            private List<ImgInfoBean> img_info;

            @Data
            public static class ImgInfoBean implements Serializable {
                /**
                 * id : 86
                 * img_url : inspection_img/2021-01-14/lyU2gphpb9Np3z1610614908210114up.jpg
                 * img_type : 1
                 * business_id : 32
                 * created_at : 2021-01-14 17:01:48
                 * deleted_at : null
                 * updated_at : 2021-01-14 17:01:48
                 * business_type : null
                 */

                private int id;
                private String img_url;
                private int img_type;
                private int business_id;
                private String created_at;
                private Object deleted_at;
                private String updated_at;
                private Object business_type;


            }
        }
    }
}
