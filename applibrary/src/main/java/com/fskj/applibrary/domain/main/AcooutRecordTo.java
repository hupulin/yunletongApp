package com.fskj.applibrary.domain.main;

import java.util.List;

import lombok.Data;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/29 13:25
 * Email:635768909@qq.com
 */
@Data
public class AcooutRecordTo {

    /**
     * current_page : 1
     * data : [{"id":5567,"created_at":"2020-12-29 13:23:19","updated_at":"2020-12-29 13:23:19","deleted_at":null,"mid":2512,"type":10,"rel_order_no":null,"fee":42,"account_before":9698,"account_after":9740,"comment":null,"type_word":"票费退款"},{"id":5566,"created_at":"2020-12-28 19:00:47","updated_at":"2020-12-28 19:00:47","deleted_at":null,"mid":2512,"type":1,"rel_order_no":"2020122819004748715","fee":-42,"account_before":9740,"account_after":9698,"comment":null,"type_word":"支付票费"},{"id":5565,"created_at":"2020-12-28 18:19:40","updated_at":"2020-12-28 18:19:40","deleted_at":null,"mid":2512,"type":10,"rel_order_no":null,"fee":30,"account_before":9710,"account_after":9740,"comment":null,"type_word":"票费退款"},{"id":5564,"created_at":"2020-12-28 15:56:21","updated_at":"2020-12-28 15:56:21","deleted_at":null,"mid":2512,"type":1,"rel_order_no":"2020122815562153776","fee":-30,"account_before":9740,"account_after":9710,"comment":null,"type_word":"支付票费"},{"id":5563,"created_at":"2020-12-28 15:55:31","updated_at":"2020-12-28 15:55:31","deleted_at":null,"mid":2512,"type":1,"rel_order_no":"2020122815553127064","fee":-30,"account_before":9770,"account_after":9740,"comment":null,"type_word":"支付票费"},{"id":5562,"created_at":"2020-12-28 15:54:26","updated_at":"2020-12-28 15:54:26","deleted_at":null,"mid":2512,"type":1,"rel_order_no":"2020122815542681634","fee":-30,"account_before":9800,"account_after":9770,"comment":null,"type_word":"支付票费"},{"id":5561,"created_at":"2020-12-28 15:54:18","updated_at":"2020-12-28 15:54:18","deleted_at":null,"mid":2512,"type":1,"rel_order_no":"2020122815541887472","fee":-30,"account_before":9830,"account_after":9800,"comment":null,"type_word":"支付票费"},{"id":5560,"created_at":"2020-12-28 15:53:15","updated_at":"2020-12-28 15:53:15","deleted_at":null,"mid":2512,"type":1,"rel_order_no":"2020122815531561012","fee":-30,"account_before":9860,"account_after":9830,"comment":null,"type_word":"支付票费"},{"id":5559,"created_at":"2020-12-28 15:50:27","updated_at":"2020-12-28 15:50:27","deleted_at":null,"mid":2512,"type":1,"rel_order_no":"2020122815502780760","fee":-30,"account_before":9890,"account_after":9860,"comment":null,"type_word":"支付票费"},{"id":5558,"created_at":"2020-12-28 15:40:04","updated_at":"2020-12-28 15:40:04","deleted_at":null,"mid":2512,"type":1,"rel_order_no":"2020122815400436561","fee":-30,"account_before":9920,"account_after":9890,"comment":null,"type_word":"支付票费"},{"id":5557,"created_at":"2020-12-28 14:06:39","updated_at":"2020-12-28 14:06:39","deleted_at":null,"mid":2512,"type":1,"rel_order_no":"2020122814063977125","fee":-80,"account_before":10000,"account_after":9920,"comment":null,"type_word":"支付票费"},{"id":5556,"created_at":"2020-12-28 14:06:13","updated_at":"2020-12-28 14:06:13","deleted_at":null,"mid":2512,"type":1,"rel_order_no":"2020122814061398978","fee":-80,"account_before":10080,"account_after":10000,"comment":null,"type_word":"支付票费"},{"id":5555,"created_at":"2020-12-28 13:58:55","updated_at":"2020-12-28 13:58:55","deleted_at":null,"mid":2512,"type":10,"rel_order_no":null,"fee":80,"account_before":10000,"account_after":10080,"comment":null,"type_word":"票费退款"},{"id":5554,"created_at":"2020-12-28 13:58:44","updated_at":"2020-12-28 13:58:44","deleted_at":null,"mid":2512,"type":1,"rel_order_no":"2020122813584493128","fee":-80,"account_before":10080,"account_after":10000,"comment":null,"type_word":"支付票费"},{"id":5553,"created_at":"2020-12-28 13:45:43","updated_at":"2020-12-28 13:45:43","deleted_at":null,"mid":2512,"type":1,"rel_order_no":"2020122813454301413","fee":-80,"account_before":10160,"account_after":10080,"comment":null,"type_word":"支付票费"}]
     * first_page_url : https://webdev.fangsheng.tech/api/u/account_records?page=1
     * from : 1
     * last_page : 9
     * last_page_url : https://webdev.fangsheng.tech/api/u/account_records?page=9
     * next_page_url : https://webdev.fangsheng.tech/api/u/account_records?page=2
     * path : https://webdev.fangsheng.tech/api/u/account_records
     * per_page : 15
     * prev_page_url : null
     * to : 15
     * total : 123
     */

    private int current_page;
    private String first_page_url;
    private int from;
    private int last_page;
    private String last_page_url;
    private String next_page_url;
    private String path;
    private int per_page;
    private int to;
    private int total;
    private List<DataBean> data;


    @Data
    public static class DataBean {
        /**
         * id : 5567
         * created_at : 2020-12-29 13:23:19
         * updated_at : 2020-12-29 13:23:19
         * deleted_at : null
         * mid : 2512
         * type : 10
         * rel_order_no : null
         * fee : 42
         * account_before : 9698
         * account_after : 9740
         * comment : null
         * type_word : 票费退款
         */

        private int id;
        private String created_at;
        private String updated_at;
        private Object deleted_at;
        private int mid;
        private int type;
        private int fee;
        private int account_before;
        private int account_after;
        private String type_word;


    }
}
