package com.lwh147.rtms.backstage.common.exception;

import com.lwh147.rtms.backstage.common.exception.code.CommonExceptionCode;
import lombok.Getter;

/**
 * @description: 自定义异常抛出类
 * @author: lwh
 * @create: 2021/4/30 17:38
 * @version: v1.0
 **/
@Getter
public class CommonException extends RuntimeException {
    private final Integer code;
    private final String message;

    public CommonException(CommonExceptionCode commonExceptionCode) {
        super(commonExceptionCode.getMessage());
        this.code = commonExceptionCode.getCode();
        this.message = commonExceptionCode.getMessage();
    }

    public CommonException(CommonExceptionCode commonExceptionCode, Throwable throwable) {
        super(commonExceptionCode.getMessage(), throwable);
        this.code = commonExceptionCode.getCode();
        this.message = commonExceptionCode.getMessage();
    }

    public CommonException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }


    public CommonException(Integer code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
        this.message = message;
    }
}
