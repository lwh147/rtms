package com.lwh147.rtms.backstage.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(description = "AdminDTO")
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 管理员名（姓名）
     */
    @ApiModelProperty("管理员名（姓名）")
    private String name;

    /**
     * 账号（10位，字母、数字或者下划线、点）
     */
    @ApiModelProperty("账号（10位，字母、数字或者下划线、点）")
    private String account;

    /**
     * MD5加密后的密码（6位纯数字）
     */
    @ApiModelProperty("MD5加密后的密码（6位纯数字）")
    private String password;

    /**
     * 联系电话（手机号）
     */
    @ApiModelProperty("联系电话（手机号）")
    private String phone;

    /**
     * 手机客户端管理员还是后台管理员（0：后台，1：客户端）
     */
    @ApiModelProperty("手机客户端管理员还是后台管理员（0：后台，1：客户端）")
    private Byte type;
}