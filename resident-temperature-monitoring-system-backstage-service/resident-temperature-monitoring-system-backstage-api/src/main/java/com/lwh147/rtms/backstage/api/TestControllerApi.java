package com.lwh147.rtms.backstage.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @description: 测试控制器类接口
 * @author: lwh
 * @create: 2021/4/29 14:07
 * @version: v1.0
 **/
@Api(tags = "测试控制器")
public interface TestControllerApi {
    @ApiOperation(value = "测试方法")
    String test();

    @ApiOperation(value = "测试方法2")
    String test2(String mark);
}
