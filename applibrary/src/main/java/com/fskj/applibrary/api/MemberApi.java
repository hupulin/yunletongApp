package com.fskj.applibrary.api;

import com.fskj.applibrary.domain.CodeParam;
import com.fskj.applibrary.domain.MemberTo;
import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.domain.TicketRecordParam;
import com.fskj.applibrary.domain.TicketRecordTo;
import com.fskj.applibrary.domain.com.SelectTypeParam;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/6 11:41
 * Email:635768909@qq.com
 */
public interface MemberApi {
//    @POST("api/management/addressBook")


    /**
     * 获取人员列表
     */
    @POST("api/management/userList")
    Observable<MessageTo<List<MemberTo>>> getMember(@Body SelectTypeParam param);
    /**
     * 获取人员核票记录
     */
    @POST("api/u/ticketUse")
    Observable<MessageTo<TicketRecordTo> >getTicketRecord(@Body TicketRecordParam param);

    /**
     * 获取票类型
     */
    @POST("api/u/getClassInfo")
    Observable<MessageTo> getTicketType();

    /**
     * 添加人员
     */
    @POST("api/u/add_from_key")
    Observable<MessageTo> addMember(@Body CodeParam param) ;
}
