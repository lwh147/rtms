package com.lwh147.rtms.backstage.serve.exception.code;

import com.lwh147.rtms.backstage.common.exception.code.CommonExceptionCode;
import lombok.AllArgsConstructor;

/**
 * @description: 业务处理异常枚举类
 * @author: lwh
 * @create: 2021/4/30 17:24
 * @version: v1.0
 **/
@AllArgsConstructor
public enum BusinessExceptionCode implements CommonExceptionCode {
    /**
     * 编号从3000开始到3999，3000保留
     **/
    BUSINESS_BEAN_COPY_ERROR(3001, "DTO与ENTITY转换出错"),
    BUSINESS_QUERY_RESULT_SET_NULL_ERROR(3002, "查询结果集为null"),

    ;

    /**
     * 错误码
     **/
    private final Integer code;
    /**
     * 错误信息
     **/
    private final String message;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
