package com.lwh147.rtms.backstage.common.aop;

import com.alibaba.fastjson.JSON;
import com.lwh147.rtms.backstage.common.context.BaseContextHolder;
import com.lwh147.rtms.backstage.common.response.CommonPage;
import com.lwh147.rtms.backstage.common.response.CommonResponse;
import com.lwh147.rtms.backstage.common.response.CommonResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

/**
 * @description: 增强controller，统一应答报文
 * @author: lwh
 * @create: 2021/5/2 1:09
 * @version: v1.0
 **/
@Slf4j
@RestControllerAdvice(basePackages = "com.lwh147")
public class CommonResponseAdvice implements ResponseBodyAdvice<Object> {
    /**
     * 对controller中的方法进行过滤
     *
     * @param methodParameter
     * @param aClass
     * @return boolean
     **/
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 全部都走这个Advice，也可以自己过滤
        return true;
    }

    /**
     * 写入应答体之前进行的操作，对返回数据进行统一封装
     *
     * @param o
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return java.lang.Object
     **/
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 如果是布尔返回值，直接根据操作结果返回对应状态的无返回数据response
        if (o instanceof Boolean) {
            log.info("【统一应答Advice】检测到Boolean应答");
            if ((Boolean) o) {
                return CommonResponseUtil.success();
            } else {
                return CommonResponseUtil.failed();
            }
        }

        // 如果返回值已经是CommonResponse类型，说明发生了异常，直接返回
        // 因为发生异常时全局异常处理Advice优先执行，此时返回结果已经封装为统一应答类型
        if (o instanceof CommonResponse || o instanceof CommonPage.PageInfo) {
            log.info("【统一应答Advice】已经封装，直接返回");
            return o;
        }

        // 检查是否为分页应答
        String pageInfoStr = BaseContextHolder.getPageInfo();
        if (pageInfoStr != null && o instanceof List) {
            log.info("【统一应答Advice】检测到分页信息，封装分页应答并返回");
            // 只从原始分页数据对象中提取分页信息，因为原始的list还是实体list
            CommonPage.PageInfo pageInfo = JSON.parseObject(pageInfoStr, CommonPage.PageInfo.class);
            // 已经提取分页信息，清空BaseContextHolder中的分页信息
            BaseContextHolder.setPageInfo(null);
            // 构建分页应答
            return CommonResponseUtil.success(pageInfo, (List<?>) o);
        }

        log.info("【统一应答Advice】其他数据，正常封装返回");
        // 其他类型正常构建带返回数据的成功应答
        return CommonResponseUtil.success(o);
    }
}
