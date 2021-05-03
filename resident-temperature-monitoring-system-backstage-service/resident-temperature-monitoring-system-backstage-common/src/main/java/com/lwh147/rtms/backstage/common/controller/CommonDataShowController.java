package com.lwh147.rtms.backstage.common.controller;

import com.lwh147.rtms.backstage.common.api.CommonDataShowControllerApi;
import com.lwh147.rtms.backstage.common.response.CommonPage;
import com.lwh147.rtms.backstage.common.response.CommonResponse;
import com.lwh147.rtms.backstage.common.response.CommonResponseUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @description: 用于展示统一应答数据结构的控制器
 * @author: lwh
 * @create: 2021/5/3 10:35
 * @version: v1.0
 **/
@RestController
@CrossOrigin
@RequestMapping("/test")
public class CommonDataShowController implements CommonDataShowControllerApi {
    @Override
    @GetMapping("/commonResponse")
    public CommonResponse<Object> commonResponse() {
        String data = "需要有返回数据时我才有用，否则为null";
        return CommonResponseUtil.success(data);
    }

    @Override
    @GetMapping("/commonResponsePage")
    public CommonResponse<CommonPage<Object>> commonResponsePage() {
        return CommonResponseUtil.success(new CommonPage.PageInfo(), new ArrayList<>());
    }
}
