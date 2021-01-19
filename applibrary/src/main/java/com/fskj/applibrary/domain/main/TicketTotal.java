package com.fskj.applibrary.domain.main;

import lombok.Data;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/29 17:32
 * Email:635768909@qq.com
 */
@Data
public class TicketTotal {

    /**
     * select_new : 3
     * select_used : 14
     * select_refund : 53
     */

    private int select_new;
    private int select_used;
    private int select_refund;
    private int select_cancle;

}
