package com.lwh147.rtms.backstage.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 分页注解
 * @author: lwh
 * @create: 2021/5/2 22:12
 * @version: v1.0
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Page {
}
