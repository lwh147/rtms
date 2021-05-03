package com.lwh147.rtms.backstage.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(description = "ResidentDTO")
@NoArgsConstructor
@AllArgsConstructor
public class ResidentDTO {
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;

    /**
     * 性别（0：男，1：女）
     */
    @ApiModelProperty("性别（0：男，1：女）")
    private Byte sex;

    /**
     * 楼号
     */
    @ApiModelProperty("楼号")
    private Byte building;

    /**
     * 单元号
     */
    @ApiModelProperty("单元号")
    private Byte entrance;

    /**
     * 房间号
     */
    @ApiModelProperty("房间号")
    private Byte room;

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String phone;
}