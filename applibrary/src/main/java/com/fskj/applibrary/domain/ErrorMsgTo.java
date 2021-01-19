package com.fskj.applibrary.domain;

import lombok.Data;

@Data
public class ErrorMsgTo {

    /**
     * code : 1003
     * message : Incorrect Username or Password
     */
    private int code;
    private String message;


}
