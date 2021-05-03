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
    CONTROLLER_ARGUMENT_VO_EMPTUY_ERROR(2001, "VO对象为空"),
    CONTROLLER_ARGUMENT_ID_EMPTUY_ERROR(2002, "id为空"),
    CONTROLLER_BEAN_COPY_ERROR(2003, "VO与DTO转换出错"),
    CONTROLLER_ARGUMENT_QUERY_EMPTY_ERROR(2004, "query对象为空"),

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
