package com.lwh147.rtms.backstage.common.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 日志切面
 * @author: lwh
 * @create: 2021/5/1 18:28
 * @version: v1.0
 **/
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * 切入点，com.lwh147包及其子包下所有以Controller结尾的类的所有public方法
     **/
    @Pointcut("execution(public * com.lwh147..*Controller.*(..))")
    public void pointcut() {
    }

    /**
     * 执行方法前织入，打印请求信息
     *
     * @param joinPoint
     * @return void
     **/
    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        // 开始打印请求处理相关信息
        log.info(" <============ 收到请求");

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        // 打印请求url
        log.info(" <== 请求路径: {}", request.getRequestURL().toString());
        // 打印请求的方式
        log.info(" <== 请求方式: {}", request.getMethod());
        // 打印调用的controller类全路径及方法
        log.info(" <== 响应方法: {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求ip
        log.info(" <== 请求者IP: {}", request.getRemoteAddr());
        // 打印请求参数
        log.info(" <== 请求参数: {}", JSON.toJSONString(joinPoint.getArgs()));
        // 分割
        log.info(" === 开始处理:");
    }

    /**
     * 环绕织入，记录耗时
     *
     * @param proceedingJoinPoint
     * @return java.lang.Object
     **/
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 分割
        log.info(" === 处理结束:");
        // 打印应答内容
        log.info(" ==> 应答内容: {}", JSON.toJSONString(result));
        // 执行耗时
        log.info(" ==> 耗费时间: {} ms", System.currentTimeMillis() - startTime);
        // 结束
        log.info(" ============> 完成响应");
        return result;
    }

}
