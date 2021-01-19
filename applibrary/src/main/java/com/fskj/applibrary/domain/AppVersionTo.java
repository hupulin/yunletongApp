package com.fskj.applibrary.domain;

import java.io.Serializable;

import lombok.Data;

@Data

public class AppVersionTo implements Serializable {

    /**
     * appVersion : {"appVersionId":"1","upTime":1,"appVersion":"0.0.0","appDefinitionId":"1","addOn":1,"description":"string","fileUrl":"string","state":1,"lastModifyOn":1,"mandatory":true}
     * update : true
     */
    private AppVersionEntity appVersion;
    private boolean update;

    @Data
    public class AppVersionEntity implements Serializable {

        /**
         * appVersionId : 12
         * upTime : 1568000636
         * appVersion : 3.1.1
         * appDefinitionId : 5
         * addOn : 1568000635
         * description : 0909宠小宝APP
         * fileUrl : https://tabaooss.oss-cn-beijing.aliyuncs.com/190909990000299.apk
         * state : 2
         * lastModifyOn : 1568000635
         * mandatory : true
         */
        private String appVersionId;
        private String upTime;
        private String appVersion;
        private String appDefinitionId;
        private String addOn;
        private String description;
        private String fileUrl;
        private int state;
        private String lastModifyOn;
        private boolean mandatory;


    }
}
