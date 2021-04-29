package com.lwh147.rtms.backstage.controller;

import com.lwh147.rtms.backstage.api.TestControllerApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 测试控制器
 * @author: lwh
 * @create: 2021/4/29 9:13
 * @version: v1.0
 **/
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/test")
public class TestController implements TestControllerApi {
    @Override
    @GetMapping("/testMethod")
    public String test() {
        log.info("测试日志输出");
        return "hello world";
    }

    @Override
    @GetMapping("/testMethod2")
    public String test2(@RequestParam("mark") String mark) {
        return "hello world";
    }
}
