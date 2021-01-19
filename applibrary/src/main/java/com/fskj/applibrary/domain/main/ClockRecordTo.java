package com.fskj.applibrary.domain.main;

import java.util.List;

import lombok.Data;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/29 11:24
 * Email:635768909@qq.com
 */
@Data
public class ClockRecordTo  {

    /**
     * current_page : 1
     * data : [{"id":567,"order_no":"2020102219130024674","created_at":"2020-10-22 19:13:00","updated_at":"2020-10-22 19:13:00","deleted_at":null,"mid":2512,"mid_name":null,"admin_id":223,"longitude":"120.264354","latitude":"30.221759","address":"浙江省杭州市萧山区永晖路杭州湾智慧谷","type":0,"mod_ticket":null,"ticket_code":null,"cost_per_day":null,"class_id":null,"class_name":null,"class_remarks":null,"fixed_commission":null},{"id":566,"order_no":"2020102219123723337","created_at":"2020-10-22 19:12:37","updated_at":"2020-10-22 19:12:37","deleted_at":null,"mid":2512,"mid_name":null,"admin_id":223,"longitude":"120.264352","latitude":"30.221755","address":"浙江省杭州市萧山区永晖路杭州湾智慧谷","type":1,"mod_ticket":null,"ticket_code":null,"cost_per_day":null,"class_id":null,"class_name":null,"class_remarks":null,"fixed_commission":null},{"id":565,"order_no":"2020102219122676056","created_at":"2020-10-22 19:12:26","updated_at":"2020-10-22 19:12:26","deleted_at":null,"mid":2512,"mid_name":null,"admin_id":223,"longitude":"120.264351","latitude":"30.221753","address":"浙江省杭州市萧山区永晖路杭州湾智慧谷","type":0,"mod_ticket":null,"ticket_code":null,"cost_per_day":null,"class_id":null,"class_name":null,"class_remarks":null,"fixed_commission":null},{"id":564,"order_no":"2020102219120797304","created_at":"2020-10-22 19:12:07","updated_at":"2020-10-22 19:12:07","deleted_at":null,"mid":2512,"mid_name":null,"admin_id":223,"longitude":"120.264351","latitude":"30.221753","address":"浙江省杭州市萧山区永晖路杭州湾智慧谷","type":1,"mod_ticket":null,"ticket_code":null,"cost_per_day":null,"class_id":null,"class_name":null,"class_remarks":null,"fixed_commission":null},{"id":563,"order_no":"2020102219032316486","created_at":"2020-10-22 19:03:23","updated_at":"2020-10-22 19:03:23","deleted_at":null,"mid":2512,"mid_name":null,"admin_id":223,"longitude":"120.264350","latitude":"30.221752","address":"浙江省杭州市萧山区永晖路杭州湾智慧谷","type":0,"mod_ticket":null,"ticket_code":null,"cost_per_day":null,"class_id":null,"class_name":null,"class_remarks":null,"fixed_commission":null},{"id":562,"order_no":"2020102219024815562","created_at":"2020-10-22 19:02:48","updated_at":"2020-10-22 19:02:48","deleted_at":null,"mid":2512,"mid_name":null,"admin_id":223,"longitude":"120.264354","latitude":"30.221759","address":"浙江省杭州市萧山区永晖路杭州湾智慧谷","type":1,"mod_ticket":null,"ticket_code":null,"cost_per_day":null,"class_id":null,"class_name":null,"class_remarks":null,"fixed_commission":null},{"id":561,"order_no":"2020102218493191529","created_at":"2020-10-22 18:49:31","updated_at":"2020-10-22 18:49:31","deleted_at":null,"mid":2512,"mid_name":null,"admin_id":223,"longitude":"120.264353","latitude":"30.221758","address":"浙江省杭州市萧山区永晖路杭州湾智慧谷","type":0,"mod_ticket":null,"ticket_code":null,"cost_per_day":null,"class_id":null,"class_name":null,"class_remarks":null,"fixed_commission":null},{"id":560,"order_no":"2020102215520272592","created_at":"2020-10-22 15:52:02","updated_at":"2020-10-22 15:52:02","deleted_at":null,"mid":2512,"mid_name":null,"admin_id":223,"longitude":"120.264360","latitude":"30.221771","address":"浙江省杭州市萧山区永晖路杭州湾智慧谷","type":1,"mod_ticket":null,"ticket_code":null,"cost_per_day":null,"class_id":null,"class_name":null,"class_remarks":null,"fixed_commission":null},{"id":559,"order_no":"2020102215422786159","created_at":"2020-10-22 15:42:27","updated_at":"2020-10-22 15:42:27","deleted_at":null,"mid":2512,"mid_name":null,"admin_id":223,"longitude":"120.264342","latitude":"30.221710","address":"浙江省杭州市萧山区永晖路杭州湾智慧谷","type":0,"mod_ticket":null,"ticket_code":null,"cost_per_day":null,"class_id":null,"class_name":null,"class_remarks":null,"fixed_commission":null},{"id":558,"order_no":"2020102215413403139","created_at":"2020-10-22 15:41:34","updated_at":"2020-10-22 15:41:34","deleted_at":null,"mid":2512,"mid_name":null,"admin_id":223,"longitude":"120.264399","latitude":"30.221679","address":"浙江省杭州市萧山区永晖路杭州湾智慧谷","type":1,"mod_ticket":null,"ticket_code":null,"cost_per_day":null,"class_id":null,"class_name":null,"class_remarks":null,"fixed_commission":null},{"id":557,"order_no":"2020102215412610065","created_at":"2020-10-22 15:41:26","updated_at":"2020-10-22 15:41:26","deleted_at":null,"mid":2512,"mid_name":null,"admin_id":223,"longitude":"120.264393","latitude":"30.221642","address":"浙江省杭州市萧山区永晖路杭州湾智慧谷","type":0,"mod_ticket":null,"ticket_code":null,"cost_per_day":null,"class_id":null,"class_name":null,"class_remarks":null,"fixed_commission":null},{"id":556,"order_no":"2020102215411643190","created_at":"2020-10-22 15:41:16","updated_at":"2020-10-22 15:41:16","deleted_at":null,"mid":2512,"mid_name":null,"admin_id":223,"longitude":"120.264349","latitude":"30.221750","address":"浙江省杭州市萧山区永晖路杭州湾智慧谷","type":1,"mod_ticket":null,"ticket_code":null,"cost_per_day":null,"class_id":null,"class_name":null,"class_remarks":null,"fixed_commission":null},{"id":555,"order_no":"2020102215361605068","created_at":"2020-10-22 15:36:16","updated_at":"2020-10-22 15:36:16","deleted_at":null,"mid":2512,"mid_name":null,"admin_id":223,"longitude":"120.264351","latitude":"30.221753","address":"浙江省杭州市萧山区永晖路杭州湾智慧谷","type":0,"mod_ticket":null,"ticket_code":null,"cost_per_day":null,"class_id":null,"class_name":null,"class_remarks":null,"fixed_commission":null},{"id":554,"order_no":"2020102215355298582","created_at":"2020-10-22 15:35:52","updated_at":"2020-10-22 15:35:52","deleted_at":null,"mid":2512,"mid_name":null,"admin_id":223,"longitude":"120.264393","latitude":"30.221652","address":"浙江省杭州市萧山区永晖路杭州湾智慧谷","type":1,"mod_ticket":null,"ticket_code":null,"cost_per_day":null,"class_id":null,"class_name":null,"class_remarks":null,"fixed_commission":null},{"id":553,"order_no":"2020102215323882960","created_at":"2020-10-22 15:32:38","updated_at":"2020-10-22 15:32:38","deleted_at":null,"mid":2512,"mid_name":null,"admin_id":223,"longitude":"120.264354","latitude":"30.221759","address":"浙江省杭州市萧山区永晖路钱江世纪城人才专项用房1期","type":0,"mod_ticket":null,"ticket_code":null,"cost_per_day":null,"class_id":null,"class_name":null,"class_remarks":null,"fixed_commission":null}]
     * first_page_url : https://webdev.fangsheng.tech/api/sign_in/records?page=1
     * from : 1
     * last_page : 1
     * last_page_url : https://webdev.fangsheng.tech/api/sign_in/records?page=1
     * next_page_url : null
     * path : https://webdev.fangsheng.tech/api/sign_in/records
     * per_page : 15
     * prev_page_url : null
     * to : 15
     * total : 15
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
        private int mid;
        private int admin_id;
        private String longitude;
        private String latitude;
        private String address;
        private int type;

    }
}
