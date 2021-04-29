package com.lwh147.rtms.backstage.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@ApiModel("")
@Table(name = "admin")
public class Admin {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 管理员名（姓名）
     */
    @Column(name = "name")
    @ApiModelProperty("管理员名（姓名）")
    private String name;

    /**
     * 账号（10位，字母、数字或者下划线、点）
     */
    @Column(name = "account")
    @ApiModelProperty("账号（10位，字母、数字或者下划线、点）")
    private String account;

    /**
     * MD5加密后的密码（6位纯数字）
     */
    @Column(name = "password")
    @ApiModelProperty("MD5加密后的密码（6位纯数字）")
    private String password;

    /**
     * 联系电话（手机号）
     */
    @Column(name = "phone")
    @ApiModelProperty("联系电话（手机号）")
    private String phone;

    /**
     * 手机客户端管理员还是后台管理员（0：后台，1：客户端）
     */
    @Column(name = "type")
    @ApiModelProperty("手机客户端管理员还是后台管理员（0：后台，1：客户端）")
    private Byte type;
}