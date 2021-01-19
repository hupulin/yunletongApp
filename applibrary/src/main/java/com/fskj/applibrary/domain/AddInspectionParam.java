package com.fskj.applibrary.domain;

import java.util.List;

import lombok.Data;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2021/1/13 12:39
 * Email:635768909@qq.com
 */
@Data
public class AddInspectionParam {
    private  String floorName;
    private  String remarks;
    private  String box_involve;
    private  String inspectionResult;
    private List<String> boxImg;
    private  int inspectionId;
}
