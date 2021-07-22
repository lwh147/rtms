package com.lwh147.rtms.gateway.filter;

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
    COMMON_NOT_LOGIN_ERROR(1002, "没有登陆"),
    COMMON_AUTH_ERROR(1003, "鉴权失败"),
    COMMON_PERMISSION_ERROR(1004, "权限不够"),

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
