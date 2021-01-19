package com.fskj.applibrary.domain.main;

import lombok.Data;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/31 11:10
 * Email:635768909@qq.com
 */
@Data
public class CardInfoTo {

    /**
     * error_code : 0
     * data : 认证成功
     * cardInfo : {"address":"武汉市新洲区仓埠街胡彰村胡彰湾四组104号","birth":"19911208","card_region":[{"x":24,"y":140},{"x":4460,"y":218},{"x":4581,"y":2972},{"x":0,"y":3000}],"config_str":"{\"side\":\"face\"}","face_rect":{"angle":0,"center":{"x":3612,"y":1511},"size":{"height":1615,"width":1364}},"face_rect_vertices":[{"x":4294,"y":703},{"x":4295,"y":2319},{"x":2930,"y":2319},{"x":2930,"y":704}],"is_fake":false,"name":"胡普林","nationality":"汉","num":"420117199112085531","request_id":"3A688A10-859F-4E34-9FF2-9946C965B488","sex":"男","success":true}
     */

    private int error_code;
    private String data;
    private CardInfoBean cardInfo;

@Data
    public static class CardInfoBean {
        /**
         * address : 武汉市新洲区仓埠街胡彰村胡彰湾四组104号
         * birth : 19911208
         * card_region : [{"x":24,"y":140},{"x":4460,"y":218},{"x":4581,"y":2972},{"x":0,"y":3000}]
         * config_str : {"side":"face"}
         * face_rect : {"angle":0,"center":{"x":3612,"y":1511},"size":{"height":1615,"width":1364}}
         * face_rect_vertices : [{"x":4294,"y":703},{"x":4295,"y":2319},{"x":2930,"y":2319},{"x":2930,"y":704}]
         * is_fake : false
         * name : 胡普林
         * nationality : 汉
         * num : 420117199112085531
         * request_id : 3A688A10-859F-4E34-9FF2-9946C965B488
         * sex : 男
         * success : true
         */

        private String address;
        private String birth;
        private String config_str;
        private boolean is_fake;
        private String name;
        private String nationality;
        private String num;
        private String request_id;
        private String sex;
        private boolean success;




    }
}
