package com.lwh147.rtms.gateway.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 统一封装返回数据格式
 * @author: lwh
 * @create: 2021/4/30 23:54
 * @version: v1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> {
    /**
     * 状态码或者错误码
     **/
    private Integer code;

    /**
     * 信息
     **/
    private String message;

    /**
     * 要返回的数据
     **/
    private T data;
}
