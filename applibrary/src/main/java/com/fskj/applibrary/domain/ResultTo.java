package com.fskj.applibrary.domain;

import lombok.Data;

/**
 * Created by 1ONE on 2019/5/27.
 */
@Data
public class ResultTo<T> {
    private MessageTo<T> Result;
}
