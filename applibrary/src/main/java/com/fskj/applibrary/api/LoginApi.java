package com.fskj.applibrary.api;

import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.domain.com.ComInfoTo;
import com.fskj.applibrary.domain.com.LoginComParam;
import com.fskj.applibrary.domain.com.RoomTo;
import com.fskj.applibrary.domain.com.UseTicketParam;
import com.fskj.applibrary.domain.login.FaceTo;
import com.fskj.applibrary.domain.login.LoginParam;
import com.fskj.applibrary.domain.login.RegisterParam;
import com.fskj.applibrary.domain.login.UserInfoTo;
import com.fskj.applibrary.domain.main.CardInfoTo;
import com.fskj.applibrary.domain.main.FillInfoParam;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

public interface LoginApi {
    /**
     * 上传图片人脸核身
     */
    @Multipart
    @POST("api/authentication/photoVerification")
    Observable<MessageTo> upLoadPic(@Part MultipartBody.Part file);
    /**
     * 验证码登录
     */
    @POST("api/auth/login_sms")
    Observable<MessageTo> loginSMS(@Body LoginParam param);

    /**
     * 密码登录
     */
    @POST("api/auth/login")
    Observable<MessageTo> loginPsw(@Body LoginParam param);

    /**
     * 销票
     */
    @POST("com/ticket/useTicket")
    Observable<MessageTo> useTicket(@Body UseTicketParam param);


    /**
     *
     * 企业登录
     */
    @POST("com/auth/login")
    Observable<MessageTo> loginPswCom(@Body LoginComParam param);

    /**
     * 获取企业房间
     */
    @POST("com/box/all")
    Observable<MessageTo<List<RoomTo>>> getRoom();

    /**
     * 获取企业信息
     */
    @POST("com/auth/me")
    Observable<MessageTo<ComInfoTo>> getComInfo();

    /**
     * 获取用户信息
     */
    @POST("api/auth/me")
    Observable<UserInfoTo> getUserInfo();

    /**
     * 发送验证码
     */
    @POST("api/auth/send_sms")
    Observable<MessageTo> sendSMS(@Body LoginParam param);

    /**
     * 账号注销
     */
    @POST("api/auth/cancellation")
    Observable<MessageTo> cancellation(@Body LoginParam param);

    /**
     * 注册账号
     */
    @POST("api/u/new")
    Observable<MessageTo> register(@Body RegisterParam param);

    /**
     * 设置密码
     */
    @POST("api/auth/set_pass")
    Observable<MessageTo> setPassword(@Body RegisterParam param);

    /**
     * 上传身份证照片
     */
    @Multipart
    @POST("api/authentication/upLoadIdCard")
    Observable<MessageTo> upLoadIdCard(@Part List<MultipartBody.Part> build);

    /**
     * 获取身份证信息
     */
    @POST("api/authentication/cardIdentification")
    Observable<CardInfoTo> getCardIdentification();
//    static const String FaceCode = "api/authentication/startFace";

    /**
     * 获取人脸code
     */
    @POST("api/authentication/startFace")
    Observable<MessageTo<FaceTo>> getFaceCode();
    /**
     * 获取人脸code
     */
    @POST("/api/u/userInfoSave")
    Observable<MessageTo> fillInfo(@Body FillInfoParam param);

    /**
     * 上传视频人脸比对
     */
    @Multipart
    @POST("api/authentication/startRecognitionFace")
    Observable<MessageTo> faceComparison(@Part List<MultipartBody.Part> build);

}
