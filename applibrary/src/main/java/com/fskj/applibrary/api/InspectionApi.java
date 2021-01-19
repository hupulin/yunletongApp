package com.fskj.applibrary.api;

import com.fskj.applibrary.domain.AddInspectionParam;
import com.fskj.applibrary.domain.InspectionIdParam;
import com.fskj.applibrary.domain.InspectionTypeTo;
import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.domain.PageParam;
import com.fskj.applibrary.domain.inspection.InspectionChildTo;
import com.fskj.applibrary.domain.inspection.InspectionRecordTo;
import com.fskj.applibrary.domain.inspection.InspectionTo;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/9 17:17
 * Email:635768909@qq.com
 */
public interface InspectionApi {
    /**
     * 开始巡查
     */
    @POST("api/inspection/startInspection")
    Observable<MessageTo<InspectionTo>> startInspection();

    /**
     * 结束巡查
     */
    @POST("api/inspection/endInspection")
    Observable<MessageTo> finishInspection(@Body InspectionTo param);

    /**
     * 获取巡查类型
     */
    @POST("api/inspection/getAllInspectionType")
    Observable<MessageTo<List<InspectionTypeTo>>> getAllInspectionType();
    /**
     * 某一次巡查添加巡查记录
     */
    @POST("api/inspection/floorInspection")
    Observable<MessageTo> addInspection(@Body AddInspectionParam param);
    /**
     * 某一次巡查添加巡查记录
     */
    @POST("api/inspection/floorInspection")
    Observable<MessageTo<List<InspectionTypeTo>>> addInspection(@Body Map<String, Object> queryParams);
    /**
     * 上传巡查记录图片
     */
    @Multipart
    @POST("api/inspection/upInspectionImg")
    Observable<MessageTo<String>> uploadImageInspection(@Part MultipartBody.Part file);
    /**
     * 获取巡查记录
     */
    @POST("api/inspection/getHistoryRecord")
    Observable<MessageTo<InspectionRecordTo>> getInspectionRecord(@Body PageParam param);

    /**
     * 获取单个巡查记录的巡查列表
     */
    @POST("api/inspection/getInspectionRecord")
    Observable<MessageTo<InspectionChildTo>>getInspectionChildList(@Body InspectionIdParam param);

}
