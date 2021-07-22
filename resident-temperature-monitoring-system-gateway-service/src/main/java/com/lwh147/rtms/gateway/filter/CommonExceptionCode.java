package com.lwh147.rtms.gateway.filter;

/**
 * @description: 自定义异常错误码枚举类接口，如果需要进一步自定义必须实现该接口
 * @author: lwh
 * @create: 2021/5/1 23:48
 * @version: v1.0
 **/
public interface CommonExceptionCode {
    Integer getCode();

    String getMessage();
}
