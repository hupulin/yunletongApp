package com.fskj.applibrary.domain.inspection;

import java.util.List;

import lombok.Data;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/13 19:45
 * Email:635768909@qq.com
 */
@Data
public class InspectionChildTo {


    /**
     * inspectionRecord : {"id":88,"member_id":1107,"inspection_status":0,"inspection_start_time":"2021-01-14 17:01:25","inspection_end_time":null,"admin_id":0,"created_at":"2021-01-14 17:01:25","updated_at":"2021-01-14 17:01:25","deleted_at":null,"member_name":"巡查员"}
     * inspectionRecordBox : [{"id":32,"member_id":1107,"box_id":null,"inspection_id":88,"box_number":null,"box_involve":"8,9","admin_id":0,"created_at":"2021-01-14 17:01:48","updated_at":"2021-01-14 17:01:48","deleted_at":null,"inspection_result":"你好","remarks":"有图片","floor_name":"这也是有图片的","img_info":[{"id":86,"img_url":"inspection_img/2021-01-14/lyU2gphpb9Np3z1610614908210114up.jpg","img_type":1,"business_id":32,"created_at":"2021-01-14 17:01:48","deleted_at":null,"updated_at":"2021-01-14 17:01:48","business_type":null},{"id":87,"img_url":"inspection_img/2021-01-14/ClLpfphpOskSJe1610614908210114up.png","img_type":1,"business_id":32,"created_at":"2021-01-14 17:01:48","deleted_at":null,"updated_at":"2021-01-14 17:01:48","business_type":null}],"is_normal":true,"type_name":"逃生楼道堆放杂物,有醉酒人员"}]
     */

    private InspectionRecordBean inspectionRecord;
    private List<InspectionRecordBoxBean> inspectionRecordBox;

    @Data
    public static class InspectionRecordBean {
        /**
         * id : 88
         * member_id : 1107
         * inspection_status : 0
         * inspection_start_time : 2021-01-14 17:01:25
         * inspection_end_time : null
         * admin_id : 0
         * created_at : 2021-01-14 17:01:25
         * updated_at : 2021-01-14 17:01:25
         * deleted_at : null
         * member_name : 巡查员
         */

        private int id;
        private int member_id;
        private int inspection_status;
        private String inspection_start_time;
        private Object inspection_end_time;
        private int admin_id;
        private String created_at;
        private String updated_at;
        private Object deleted_at;
        private String member_name;
    }
        @Data
    public static class InspectionRecordBoxBean {
        /**
         * id : 32
         * member_id : 1107
         * box_id : null
         * inspection_id : 88
         * box_number : null
         * box_involve : 8,9
         * admin_id : 0
         * created_at : 2021-01-14 17:01:48
         * updated_at : 2021-01-14 17:01:48
         * deleted_at : null
         * inspection_result : 你好
         * remarks : 有图片
         * floor_name : 这也是有图片的
         * img_info : [{"id":86,"img_url":"inspection_img/2021-01-14/lyU2gphpb9Np3z1610614908210114up.jpg","img_type":1,"business_id":32,"created_at":"2021-01-14 17:01:48","deleted_at":null,"updated_at":"2021-01-14 17:01:48","business_type":null},{"id":87,"img_url":"inspection_img/2021-01-14/ClLpfphpOskSJe1610614908210114up.png","img_type":1,"business_id":32,"created_at":"2021-01-14 17:01:48","deleted_at":null,"updated_at":"2021-01-14 17:01:48","business_type":null}]
         * is_normal : true
         * type_name : 逃生楼道堆放杂物,有醉酒人员
         */

        private int id;
        private boolean fold=true;
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
        private boolean is_normal;
        private String type_name;
        private List<ImgInfoBean> img_info;


            @Data

        public static class ImgInfoBean {
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