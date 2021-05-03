package com.lwh147.rtms.backstage.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "统一应答结构")
public class CommonResponse<T> {
    /**
     * 状态码或者错误码
     **/
    @ApiModelProperty("状态码，或出现错误时的错误码")
    private Integer code;

    /**
     * 信息
     **/
    @ApiModelProperty("提示信息")
    private String message;

    /**
     * 要返回的数据
     **/
    @ApiModelProperty("要返回的数据")
    private T data;
}
