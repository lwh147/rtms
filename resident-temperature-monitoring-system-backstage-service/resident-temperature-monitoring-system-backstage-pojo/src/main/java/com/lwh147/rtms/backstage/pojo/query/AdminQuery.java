package com.lwh147.rtms.backstage.pojo.query;

import com.lwh147.rtms.backstage.common.pojo.query.BasicQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @description: 管理员查询条件封装类
 * @author: lwh
 * @create: 2021/5/2 17:49
 * @version: v1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("AdminQuery")
public class AdminQuery extends BasicQuery {
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
