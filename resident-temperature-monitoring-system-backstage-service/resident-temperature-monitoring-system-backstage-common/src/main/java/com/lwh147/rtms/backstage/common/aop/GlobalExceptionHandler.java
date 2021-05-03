package com.lwh147.rtms.backstage.common.aop;

import com.lwh147.rtms.backstage.common.exception.CommonException;
import com.lwh147.rtms.backstage.common.response.CommonResponse;
import com.lwh147.rtms.backstage.common.response.CommonResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description: 自定义全局异常处理
 * @author: lwh
 * @create: 2021/4/30 17:31
 * @version: v1.0
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    public static final String OTHER_EXCEPTION_PREFIX = "其他异常：";

    /**
     * 捕获自定义异常
     *
     * @param commonException
     * @return com.lwh147.rtms.backstage.common.response.CommonResponse<?>
     **/
    @ExceptionHandler(CommonException.class)
    public CommonResponse<?> handleBusinessException(CommonException commonException) {
        log.error("【全局自定义异常处理】发生自定义异常：{}", commonException.getMessage());
        commonException.printStackTrace();
        return CommonResponseUtil.failed(commonException.getCode(), commonException.getMessage());
    }

    /**
     * 保证异常都会被捕获
     */
    @ExceptionHandler(Exception.class)
    public CommonResponse<?> illegalArgumentException(Exception exception) {
        log.error("【全局自定义异常处理】发生其他异常：{}", exception.getMessage());
        exception.printStackTrace();
        return CommonResponseUtil.failed(OTHER_EXCEPTION_PREFIX + exception.getMessage());
    }
}
