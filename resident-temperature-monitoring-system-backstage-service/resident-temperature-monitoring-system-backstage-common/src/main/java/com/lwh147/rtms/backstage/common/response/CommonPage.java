package com.lwh147.rtms.backstage.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: 含有分页信息的应答data类型
 * @author: lwh
 * @create: 2021/5/2 23:09
 * @version: v1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "分页数据结构：分页请求应答的data结构即为此结构")
public class CommonPage<T> {
    /**
     * 分页信息
     **/
    @ApiModelProperty("分页信息")
    private PageInfo pageInfo;
    /**
     * 数据
     **/
    @ApiModelProperty("分页数据")
    private List<T> list;

    /**
     * @description: 分页信息类
     * @author: lwh
     * @create: 2021/5/2 23:09
     * @version: v1.0
     **/
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "分页信息结构")
    public static class PageInfo {
        /**
         * 总记录数
         */
        @ApiModelProperty("总记录数")
        private Long total;
        /**
         * 当前页号
         */
        @ApiModelProperty("当前页号，默认为1")
        private Integer pageNum;
        /**
         * 每页数量
         */
        @ApiModelProperty("每页数量，默认为10")
        private Integer pageSize;
        /**
         * 总页数
         */
        @ApiModelProperty("总页数")
        private Integer pages;
        /**
         * 当前返回的数据个数
         */
        @ApiModelProperty("当前返回的数据个数")
        private Integer size;
        /**
         * 当前页展示的数据的起始行
         */
        @ApiModelProperty("当前页展示的数据的起始行")
        private Long startRow;
        /**
         * 当前页展示的数据的结束行
         */
        @ApiModelProperty("当前页展示的数据的结束行")
        private Long endRow;
        /**
         * 前一页页码
         */
        @ApiModelProperty("前一页页码")
        private Integer prePage;
        /**
         * 后一页页码
         */
        @ApiModelProperty("后一页页码")
        private Integer nextPage;
        /**
         * 是否为第一页
         */
        @ApiModelProperty("是否为第一页")
        private Boolean isFirstPage;
        /**
         * 是否为最后一页
         */
        @ApiModelProperty("是否为最后一页")
        private Boolean isLastPage;
        /**
         * 是否有前一页
         */
        @ApiModelProperty("是否有前一页")
        private Boolean hasPreviousPage;
        /**
         * 是否有下一页
         */
        @ApiModelProperty("是否有下一页")
        private Boolean hasNextPage;
        /**
         * 导航页码数，所谓导航页码数，就是在页面进行展示的那些1.2.3.4...
         * 比如一共有分为两页数据的话，则将此值设置为2
         */
        @ApiModelProperty("导航页码数：一共有分为两页数据的话，则将此值设置为2")
        private Integer navigatePages;
        /**
         * 所有导航页号，一共有两页的话则为[1,2]
         */
        @ApiModelProperty("所有导航页号，一共有两页的话则为[1,2]")
        private Integer[] navigatepageNums;
        /**
         * 导航条上的第一页页码值
         */
        @ApiModelProperty("导航条上的第一页页码值")
        private Integer navigateFirstPage;
        /**
         * 导航条上的最后一页页码值
         */
        @ApiModelProperty("导航条上的最后一页页码值")
        private Integer navigateLastPage;
    }
}
