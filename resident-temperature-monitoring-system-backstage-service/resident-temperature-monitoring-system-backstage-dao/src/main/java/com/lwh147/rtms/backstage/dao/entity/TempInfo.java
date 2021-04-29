package com.lwh147.rtms.backstage.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@ApiModel("")
@Table(name = "temp_info")
public class TempInfo {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 检测时间
     */
    @Column(name = "time")
    @ApiModelProperty("检测时间")
    private Date time;

    /**
     * 检测结果，温度摄氏度
     */
    @Column(name = "temp")
    @ApiModelProperty("检测结果，温度摄氏度")
    private Float temp;

    /**
     * 外键
     */
    @Column(name = "resident_id")
    @ApiModelProperty("外键")
    private Long residentId;
}