package com.lwh147.rtms.backstage.api;

import com.lwh147.rtms.backstage.pojo.query.AdminQuery;
import com.lwh147.rtms.backstage.pojo.vo.AdminVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

/**
 * @description: 管理员相关控制器接口
 * @author: lwh
 * @create: 2021/4/30 10:55
 * @version: v1.0
 **/
@Api(tags = "管理员相关控制器")
public interface AdminControllerApi {
    @ApiOperation(value = "添加管理员")
    Boolean add(@ApiParam(required = true) AdminVO adminVO);

    @ApiOperation(value = "根据id删除管理员")
    Boolean delete(@ApiParam(required = true, value = "管理员id") Long id);

    @ApiOperation(value = "根据id查询管理员")
    AdminVO queryById(@ApiParam(required = true, value = "管理员id") Long id);

    @ApiOperation(value = "根据综合查询条件查询管理员")
    List<AdminVO> commonQuery(@ApiParam(required = true) AdminQuery adminQuery);

    @ApiOperation(value = "更改管理员信息")
    Boolean update(@ApiParam(required = true) AdminVO adminVO);

    // @ApiOperation(value = "登录验证")
    // Boolean login(@ApiParam(required = true) LoginFormVO loginFormVO);
}
