package com.fskj.applibrary.domain.main;

import java.util.List;

import lombok.Data;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/3/29 10:58
 * Email:635768909@qq.com
 */
@Data
public class TicketTypeTo {

    private List<TicketBean> ticket;
    private List<UserBean> user;

    @Data

    public static class TicketBean {
        /**
         * class_name : B-1
         * total : 0
         */
        private String class_name;
        private int total;


    }
    @Data

    public static class UserBean {
        /**
         * nickname : 12345678901
         * total : 0
         */

        private String nickname;
        private int total;

        public String getNickname() {
            return nickname;
        }

    }
}
