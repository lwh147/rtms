package com.lwh147.rtms.backstage.common.api;

import com.lwh147.rtms.backstage.common.response.CommonPage;
import com.lwh147.rtms.backstage.common.response.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @description: 用于展示统一应答数据结构的控制器接口
 * @author: lwh
 * @create: 2021/5/3 10:31
 * @version: v1.0
 **/
@Api(tags = "用于展示统一应答数据结构的控制器接口")
public interface CommonDataShowControllerApi {
    @ApiOperation("统一应答基础结构")
    CommonResponse<Object> commonResponse();

    @ApiOperation("带有分页信息的统一应答结构")
    CommonResponse<CommonPage<Object>> commonResponsePage();
}
