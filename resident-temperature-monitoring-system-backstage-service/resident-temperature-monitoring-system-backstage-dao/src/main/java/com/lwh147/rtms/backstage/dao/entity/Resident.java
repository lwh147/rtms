package com.lwh147.rtms.backstage.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@ApiModel("")
@Table(name = "resident")
public class Resident {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 姓名
     */
    @Column(name = "name")
    @ApiModelProperty("姓名")
    private String name;

    /**
     * 性别（0：男，1：女）
     */
    @Column(name = "sex")
    @ApiModelProperty("性别（0：男，1：女）")
    private Byte sex;

    /**
     * 楼号
     */
    @Column(name = "building")
    @ApiModelProperty("楼号")
    private Byte building;

    /**
     * 单元号
     */
    @Column(name = "entrance")
    @ApiModelProperty("单元号")
    private Byte entrance;

    /**
     * 房间号
     */
    @Column(name = "room")
    @ApiModelProperty("房间号")
    private Byte room;

    /**
     * 联系电话
     */
    @Column(name = "phone")
    @ApiModelProperty("联系电话")
    private String phone;
}