package com.lwh147.rtms.backstage.api;

import com.lwh147.rtms.backstage.pojo.query.TempInfoQuery;
import com.lwh147.rtms.backstage.pojo.vo.TempInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

/**
 * @description: 体温信息控制器接口
 * @author: lwh
 * @create: 2021/5/3 18:25
 * @version: v1.0
 **/
@Api(tags = "体温信息相关控制器")
public interface TempInfoControllerApi {
    /**
     * 添加体温信息
     **/
    @ApiOperation(value = "添加体温信息")
    Boolean add(@ApiParam(required = true) TempInfoVO tempInfoVO);

    /**
     * 根据综合查询条件查询体温信息
     **/
    @ApiOperation(value = "根据综合查询条件查询体温信息")
    List<TempInfoVO> commonQuery(@ApiParam(required = true) TempInfoQuery tempInfoQuery);

}
