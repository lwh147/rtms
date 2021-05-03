package com.lwh147.rtms.backstage.api;

import com.lwh147.rtms.backstage.pojo.query.ResidentQuery;
import com.lwh147.rtms.backstage.pojo.vo.ResidentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

/**
 * @description: 居民信息控制器接口
 * @author: lwh
 * @create: 2021/5/3 12:08
 * @version: v1.0
 **/
@Api(tags = "居民相关控制器")
public interface ResidentControllerApi {
    /**
     * 添加居民
     **/
    @ApiOperation(value = "添加居民")
    Boolean add(@ApiParam(required = true) ResidentVO residentVO);

    /**
     * 根据id删除居民
     **/
    @ApiOperation(value = "根据id删除居民")
    Boolean delete(@ApiParam(required = true, value = "居民id") Long id);

    /**
     * 根据id查询居民
     **/
    @ApiOperation(value = "根据id查询居民")
    ResidentVO queryById(@ApiParam(required = true, value = "居民id") Long id);

    /**
     * 根据综合查询条件查询居民
     **/
    @ApiOperation(value = "根据综合查询条件查询居民")
    List<ResidentVO> commonQuery(@ApiParam(required = true) ResidentQuery residentQuery);

    /**
     * 更改居民信息
     **/
    @ApiOperation(value = "更改居民信息")
    Boolean update(@ApiParam(required = true) ResidentVO residentVO);

}
