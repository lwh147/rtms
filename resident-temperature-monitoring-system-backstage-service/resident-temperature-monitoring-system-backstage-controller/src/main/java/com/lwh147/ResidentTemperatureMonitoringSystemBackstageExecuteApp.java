package com.lwh147;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @description: 启动类
 * @author: lwh
 * @create: 2021/4/27 21:42
 * @version: v1.0
 **/
@SpringBootApplication
@EnableOpenApi
@EnableCaching
public class ResidentTemperatureMonitoringSystemBackstageExecuteApp {
    public static void main(String[] args) {
        SpringApplication.run(ResidentTemperatureMonitoringSystemBackstageExecuteApp.class, args);
    }

}
