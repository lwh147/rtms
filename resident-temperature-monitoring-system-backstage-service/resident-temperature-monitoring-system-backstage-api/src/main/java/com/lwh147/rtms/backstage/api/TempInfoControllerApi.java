package com.lwh147.rtms.backstage.api;

import com.lwh147.rtms.backstage.pojo.query.TempInfoQuery;
import com.lwh147.rtms.backstage.pojo.vo.TempInfoVO;
import com.lwh147.rtms.backstage.pojo.vo.TempInfoWithFaceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;
import java.util.Map;

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
     * 添加体温信息(带人脸图片)
     **/
    @ApiOperation(value = "添加体温信息(带人脸图片)，picture参数为图片的base64编码字符串")
    Boolean add(@ApiParam(required = true, value = "添加体温信息(带人脸图片base64编码字符串)") TempInfoWithFaceVO tempInfoWithFace);

    /**
     * 根据综合查询条件查询体温信息
     **/
    @ApiOperation(value = "根据综合查询条件查询体温信息")
    List<TempInfoVO> commonQuery(@ApiParam(required = true) TempInfoQuery tempInfoQuery);

    /**
     * 获取检测的总人次，或者体温异常的总人次
     **/
    @ApiOperation(value = "查询15天内的检测总人数，以normal为查询条件")
    Integer total(@ApiParam(required = true) TempInfoQuery tempInfoQuery);

    /**
     * 获取表格数据，根据时间和是否是异常体温
     **/
    @ApiOperation(value = "获取表格数据，根据时间和是否是异常体温")
    List<Map<String, Object>> charData(@ApiParam(required = true) TempInfoQuery tempInfoQuery);

    /**
     * 获取某个人的体温数据
     **/
    @ApiOperation(value = "获取某个人最近几天的体温数据")
    List<Map<String, Object>> getTempByResidentId(@ApiParam(required = true) TempInfoQuery tempInfoQuery);

    /**
     * 获取15天内所有记录
     **/
    @ApiOperation(value = "获取15天内所有记录")
    List<Map<String, Object>> getTempOf15();
}
