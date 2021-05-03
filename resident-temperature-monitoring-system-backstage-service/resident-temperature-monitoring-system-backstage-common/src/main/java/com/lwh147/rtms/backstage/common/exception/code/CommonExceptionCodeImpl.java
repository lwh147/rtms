package com.lwh147.rtms.backstage.common.exception.code;

import lombok.AllArgsConstructor;

/**
 * @description: 全局异常错误码枚举类
 * @author: lwh
 * @create: 2021/5/2 22:31
 * @version: v1.0
 **/
@AllArgsConstructor
public enum CommonExceptionCodeImpl implements CommonExceptionCode {
    /**
     * 编号从1000开始到1999，1000保留
     **/
    COMMON_PAGE_AOP_ERROR(1001, "分页切面错误"),

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
