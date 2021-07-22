package com.lwh147.rtms.backstage.api;

import com.lwh147.rtms.backstage.pojo.query.AdminQuery;
import com.lwh147.rtms.backstage.pojo.vo.AdminVO;
import com.lwh147.rtms.backstage.pojo.vo.LoginFormVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;
import java.util.Map;

/**
 * @description: 管理员相关控制器接口
 * @author: lwh
 * @create: 2021/4/30 10:55
 * @version: v1.0
 **/
@Api(tags = "管理员相关控制器")
public interface AdminControllerApi {
    /**
     * 添加管理员
     **/
    @ApiOperation(value = "添加管理员")
    Boolean add(@ApiParam(required = true) AdminVO adminVO);

    /**
     * 根据id删除管理员
     **/
    @ApiOperation(value = "根据id删除管理员")
    Boolean delete(@ApiParam(required = true, value = "管理员id") Long id);

    /**
     * 根据id查询管理员
     **/
    @ApiOperation(value = "根据id查询管理员")
    AdminVO queryById(@ApiParam(required = true, value = "管理员id") Long id);

    /**
     * 根据综合查询条件查询管理员
     **/
    @ApiOperation(value = "根据综合查询条件查询管理员")
    List<AdminVO> commonQuery(@ApiParam(required = true) AdminQuery adminQuery);

    /**
     * 更改管理员信息
     **/
    @ApiOperation(value = "更改管理员信息")
    Boolean update(@ApiParam(required = true) AdminVO adminVO);

    /**
     * 登陆验证
     **/
    @ApiOperation(value = "客户端管理员登录验证")
    AdminVO loginFromApp(@ApiParam(required = true) LoginFormVO loginFormVO);

    /**
     * 登陆验证
     **/
    @ApiOperation(value = "后台管理员登录验证")
    Map<String, Object> loginFromBackstage(@ApiParam(required = true) LoginFormVO loginFormVO);

    /**
     * 获取管理员信息
     **/
    @ApiOperation(value = "获取管理员信息")
    Map<?, ?> getAdminInfo(@ApiParam(required = true) String token);

    /**
     * 退出登录
     **/
    @ApiOperation(value = "退出登录")
    Boolean logout();

}
