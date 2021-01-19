package com.fskj.applibrary.api;

import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.domain.RefundTicketParam;
import com.fskj.applibrary.domain.WeChatPay;
import com.fskj.applibrary.domain.main.AdminIdParam;
import com.fskj.applibrary.domain.main.ClockParam;
import com.fskj.applibrary.domain.main.ClockTwoTo;
import com.fskj.applibrary.domain.main.CompanyTo;
import com.fskj.applibrary.domain.main.TicketOrderParam;
import com.fskj.applibrary.domain.main.TicketParam;
import com.fskj.applibrary.domain.main.TicketTo;
import com.fskj.applibrary.domain.main.TicketTotal;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/22 13:49
 * Email:635768909@qq.com
 */
public interface TicketApi {
    /**
     * 票列表(未使用 已使用  已退票 已失效)
     */
    @POST("api/u/ticketSelect")
    Observable<MessageTo<List<TicketTo>>> getTicket(@Body TicketParam param);
    /**
     * 票总数(Tab栏上显示的)
     */
    @POST("api/u/ticketSelectCount")
    Observable<MessageTo<TicketTotal>> getTicketTotal(@Body AdminIdParam param);
    /**
     * 买过票的公司  右上角选择公司搜索票
     */
    @POST("api/u/ticketComs")
    Observable<MessageTo<List<CompanyTo>>> getTicketCom();
    /**
     * 退票
     */
    @POST("api/ticket/refund")
    Observable<MessageTo> refundTicket(@Body RefundTicketParam param);
    /**
     * 获取当前票的状态 api/ticket/status
     */
    @POST("api/ticket/status")
    Observable<MessageTo<String>> getTicketStatus(@Body RefundTicketParam param);
    /**
     * 支付宝支付
     */
    @POST("api/pay/ticketOrder")
    Observable<MessageTo> getTicketOrder(@Body TicketOrderParam param);
    /**
     * 微信支付
     */
    @POST("api/pay/ticketOrder")
    Observable<MessageTo<WeChatPay>> getTicketOrderWx(@Body TicketOrderParam param);
//    /**
//     * 打卡记录
//     */
//    @POST("api/sign_in/records")
//    Observable<ClockTo> getClockList();
    /**
     *  首页两条打卡记录
     */
    @POST("api/sign_in/records_team")
    Observable<MessageTo<List<ClockTwoTo>>> getClockTowList();
    /**
     * 上传图片人脸比对
     */
    @Multipart
    @POST("api/authentication/faceComparison")
    Observable<MessageTo> faceComparison(@Part MultipartBody.Part file);
    /**
     * 打卡
     */
    @POST("api/sign_in/do")
    Observable<MessageTo> clockOn(@Body ClockParam param);

}
