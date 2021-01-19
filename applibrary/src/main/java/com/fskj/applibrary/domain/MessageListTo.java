package com.fskj.applibrary.domain;


import java.util.List;

import lombok.Data;

/**
 * Created by Administrator on 2018/9/1.
 */
@Data
public class MessageListTo<T> {

    private List<T> list;


}
