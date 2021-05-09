package com.lwh147.rtms.backstage.controller.exception.code;

import com.lwh147.rtms.backstage.common.exception.code.CommonExceptionCode;
import lombok.AllArgsConstructor;

/**
 * @description: 控制层异常错误码
 * @author: lwh
 * @create: 2021/5/1 18:09
 * @version: v1.0
 **/
@AllArgsConstructor
public enum ControllerExceptionCode implements CommonExceptionCode {
    /**
     * 编号从2000开始到2999，2000保留
     **/
    CONTROLLER_ARGUMENT_VO_EMPTY_ERROR(2001, "VO对象为空"),
    CONTROLLER_ARGUMENT_ID_EMPTY_ERROR(2002, "id为空"),
    CONTROLLER_BEAN_COPY_ERROR(2003, "VO与DTO转换出错"),
    CONTROLLER_ARGUMENT_QUERY_EMPTY_ERROR(2004, "query对象为空"),
    CONTROLLER_ARGUMENT_VO_PROPERTY_LOSE_ERROR(2005, "要添加的对象的必需字段存在缺失"),
    CONTROLLER_ARGUMENT_LOSE_ERROR(2006, "请求参数丢失"),
    CONTROLLER_ARGUMENT_NOT_VALID_ERROR(2007, "请求参数非法"),
    /**
     * 登陆相关
     **/
    CONTROLLER_LOGIN_ARGUMENT_EMPTY_ERROR(2500, "登录请求参数为空"),
    CONTROLLER_LOGIN_ARGUMENT_LOSE_ERROR(2501, "用户名或密码为空"),
    CONTROLLER_LOGIN_FAIL(2502, "用户名或密码错误"),
    CONTROLLER_LOGIN_HAS_NO_TOKEN_ERROR(2503, "丢失token，拒绝服务"),

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
