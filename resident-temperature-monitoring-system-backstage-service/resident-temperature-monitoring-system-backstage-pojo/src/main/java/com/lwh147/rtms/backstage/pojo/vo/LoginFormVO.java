package com.lwh147.rtms.backstage.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 登录表单数据
 * @author: lwh
 * @create: 2021/4/30 11:19
 * @version: v1.0
 **/
@Data
@ApiModel(description = "LoginFormVO")
@NoArgsConstructor
@AllArgsConstructor
public class LoginFormVO {
    /**
     * 账号
     **/
    @ApiModelProperty("账号（10位，字母、数字或者下划线、点）")
    private String account;

    /**
     * 密码
     **/
    @ApiModelProperty("MD5加密后的密码（6位纯数字）")
    private String password;
}




