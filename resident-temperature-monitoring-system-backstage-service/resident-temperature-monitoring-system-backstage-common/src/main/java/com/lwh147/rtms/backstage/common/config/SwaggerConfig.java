package com.lwh147.rtms.backstage.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @description: swagger配置类
 * @author: lwh
 * @create: 2021/4/29 13:39
 * @version: v1.0
 **/
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lwh147.rtms.backstage.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("居民体温监测系统后台接口")
                .description("居民体温监测系统后台的接口文档")
                .contact(new Contact("吝旺豪", "https://gitee.com/lwh147", "1479351399@qq.com"))
                .version("1.0.0")
                .build();
    }

}
