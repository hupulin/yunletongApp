package com.fskj.applibrary.api;


import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.domain.PageParam;
import com.fskj.applibrary.domain.UpdateInfo;
import com.fskj.applibrary.domain.UpdateInfoParam;
import com.fskj.applibrary.domain.login.NickNameParam;
import com.fskj.applibrary.domain.main.AcooutRecordTo;
import com.fskj.applibrary.domain.main.ClockRecordTo;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

public interface MyselfApi {

    /**
     * 上传头像图片
     */

    @Multipart
    @POST("api/u/nickhead")
    Observable<MessageTo> uploadHeadImage(@Part List<MultipartBody.Part> build);

    /**
     * 修改昵称
     */

    @POST("api/u/nickhead")
    Observable<MessageTo> uploadHeadImage(@Body NickNameParam param);
    /**
     * 获取更新信息
     */

    @POST("api/auth/checkApp")
    Observable<MessageTo<UpdateInfo>> updateInfo(@Body UpdateInfoParam param);

    /**
     * 获取邀请码
     */

    @POST("api/u/key")
    Observable<MessageTo> getMyCode();

    /**
     * 打卡记录
     */
    @POST("api/sign_in/records")
    Observable<ClockRecordTo> getClockRecord(@Body PageParam param);

    /**
     * 账户流水
     */
    @POST("api/u/account_records")
    Observable<AcooutRecordTo> getAccountRecord(@Body PageParam param);
}
