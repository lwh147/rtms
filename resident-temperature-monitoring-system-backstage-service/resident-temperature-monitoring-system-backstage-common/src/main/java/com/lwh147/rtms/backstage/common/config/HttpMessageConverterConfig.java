package com.lwh147.rtms.backstage.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @description: 应答数据转换器配置
 * @author: lwh
 * @create: 2021/4/29 22:28
 * @version: v1.0
 **/
@Configuration
public class HttpMessageConverterConfig implements WebMvcConfigurer {
    /**
     * 序列换成json时,将所有的long变成string
     * 因为js中得数字类型不能包含所有的java long值
     **/
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(long.class, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        // 将MappingJackson2HttpMessageConverter设置为最高优先级
        // 这样当返回数据为String类型时StringHttpMessageConverter不会被使用
        // 可以避免controller返回数据类型为String时，统一应答报cannot convert to String错误
        converters.add(0, jackson2HttpMessageConverter);
    }
}
