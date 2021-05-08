package com.lwh147.rtms.backstage.pojo.query;

import com.lwh147.rtms.backstage.common.pojo.query.BasicQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: 体温信息查询条件，继承BasicQuery则必须分页显示
 * @author: lwh
 * @create: 2021/5/3 18:30
 * @version: v1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "TempInfoQuery")
public class TempInfoQuery extends BasicQuery {
    /**
     * 某个居民的体温信息
     **/
    @ApiModelProperty("某个居民的id")
    private Long id;

    /**
     * 查询范围为最近几天的数据，3代表最近三天内的，默认为3，与查询时间区段条件冲突，
     * 同时只能设置一种，以查询时间区段优先
     * 作为图标查询条件时只能取值1、3、7、15
     **/
    @ApiModelProperty("查询范围为最近几天的数据，3代表最近三天内的，与查询时间区段条件冲突，同时只能设置一种，以查询时间区段优先，作为图标查询条件时只能取值1、3、7、15")
    private Integer days = 3;

    /**
     * 查询时间区段，开始时间
     **/
    @ApiModelProperty("查询时间区段，开始时间，格式：yyyy/MM/dd HH:mm:ss")
    private Date startTime;

    /**
     * 查询时间区段，结束时间
     **/
    @ApiModelProperty("查询时间区段，结束时间，格式：yyyy/MM/dd HH:mm:ss")
    private Date endTime;

    /**
     * 查询的是总数据还是体温异常数据
     **/
    @ApiModelProperty("查询的是总数据还是体温异常数据 fasle异常，true总")
    private Boolean normal;
}
