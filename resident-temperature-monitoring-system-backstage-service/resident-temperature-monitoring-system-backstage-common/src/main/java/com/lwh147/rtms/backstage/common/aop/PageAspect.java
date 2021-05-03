package com.lwh147.rtms.backstage.common.aop;

import com.github.pagehelper.PageHelper;
import com.lwh147.rtms.backstage.common.exception.CommonException;
import com.lwh147.rtms.backstage.common.exception.code.CommonExceptionCodeImpl;
import com.lwh147.rtms.backstage.common.pojo.query.BasicQuery;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @description: 分页切面
 * @author: lwh
 * @create: 2021/5/2 22:13
 * @version: v1.0
 **/
@Aspect
@Component
@Slf4j
public class PageAspect {
    /**
     * 切入点，打了@Page注解的方法
     **/
    @Pointcut("@annotation(com.lwh147.rtms.backstage.common.annotation.Page)")
    public void pointcut() {
    }

    /**
     * 环绕织入
     *
     * @param proceedingJoinPoint
     * @return java.lang.Object
     **/
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("【分页AOP】获取分页参数开始分页");
        // 获取参数
        Object[] args = proceedingJoinPoint.getArgs();
        // 查询参数必须继承自BasicQuery，设置分页信息
        BasicQuery basicQuery = (BasicQuery) args[0];
        PageHelper.startPage(basicQuery.getPageNum(), basicQuery.getPageSize());
        try {
            // 返回正常执行结果，在统一应答advice中处理
            return proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throw new CommonException(CommonExceptionCodeImpl.COMMON_PAGE_AOP_ERROR);
        }
    }

}
