package com.lwh147;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description: 启动类
 * @author: lwh
 * @create: 2021/4/27 21:49
 * @version: v1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ResidentTemperatureMonitoringSystemGatewayExecuteApp {
    public static void main(String[] args) {
        SpringApplication.run(ResidentTemperatureMonitoringSystemGatewayExecuteApp.class,args);
    }

}
