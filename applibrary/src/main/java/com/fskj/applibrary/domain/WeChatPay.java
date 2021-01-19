package com.fskj.applibrary.domain;

import lombok.Data;

@Data
public class WeChatPay {

    /**
     * appId : wxbf1099a490cb8b9a
     * sign : 44335341F9C443CA329156352D2A3458
     * prepayId : wx200931196088212841ae488c1666762900
     * partnerId : 1518532941.//商户号
     * nonce : 2df235dd3d384f9e88e5c3c41fbb74d2
     * timestamp : 1568943079
     */
    private String appid;
    private String sign;
    private String prepayid;
//    private String mch_id;
    private String partnerid;
    private String noncestr;
    private String timestamp;

}
