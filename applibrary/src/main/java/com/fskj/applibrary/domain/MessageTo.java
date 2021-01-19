package com.fskj.applibrary.domain;


import java.io.Serializable;

import lombok.Data;

/**
 * Created by Administrator on 2018/9/1.
 */
@Data
public class MessageTo<T>  implements Serializable {

    /**
     * Code : 000000
     * Msg : 返回成功
     * Time : 2018-08-30 16:08:20
     * ApiUrl : http://xmap18070031.php.hzxmnet.com/api/Sms/sendcode
     * Data : {"mobile":"13738084633","sms_code":"637442"}
     */
    private static final long serialVersionUID = -7124170122394232970L;
    private String errorMsg;
    private String errMsg;
    private int error_code;
    private T data ;
    private String desc ;
    private String result ;
    private String access_token ;
    private String url ;





}
