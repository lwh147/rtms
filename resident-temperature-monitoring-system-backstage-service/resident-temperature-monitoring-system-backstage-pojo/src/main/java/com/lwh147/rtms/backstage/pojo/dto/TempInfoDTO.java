package com.lwh147.rtms.backstage.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@ApiModel(description = "TempInfoDTO")
@NoArgsConstructor
@AllArgsConstructor
public class TempInfoDTO {
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 检测时间
     */
    @ApiModelProperty("检测时间，格式：yyyy-MM-dd HH:mm:ss")
    private Date time;

    /**
     * 检测结果，温度摄氏度
     */
    @ApiModelProperty("检测结果，温度摄氏度，只保留一位小数")
    private Float temp;

    /**
     * 外键
     */
    @ApiModelProperty("外键")
    private Long residentId;
}