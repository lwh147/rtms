package com.lwh147.rtms.backstage.common.context;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 存储用户请求上下文信息，为了方便存储更多内容所以ThreadLocal内部建立Map存储
 * @author: lwh
 * @create: 2021/5/2 21:38
 * @version: v1.0
 **/
public class BaseContextHolder {
    private static final ThreadLocal<Map<String, String>> THREAD_LOCAL_MAP = new ThreadLocal<>();

    /**
     * 静态类，不能实例化
     **/
    private BaseContextHolder() {
    }

    /**
     * 获取map
     *
     * @return java.util.Map<java.lang.String, java.lang.String>
     **/
    public static Map<String, String> getMap() {
        Map<String, String> map = THREAD_LOCAL_MAP.get();
        if (map == null) {
            map = new HashMap<>(10);
            THREAD_LOCAL_MAP.set(map);
        }
        return map;
    }

    /**
     * 删除线程独享变量map
     **/
    public static void remove() {
        THREAD_LOCAL_MAP.remove();
    }

    /**
     * 设置上下文信息
     *
     * @param key
     * @param value
     * @return void
     **/
    public static void set(String key, String value) {
        Map<String, String> map = getMap();
        map.put(key, value);
    }

    /**
     * 获取上下文信息
     *
     * @param key
     * @return java.lang.String
     **/
    public static String get(String key) {
        Map<String, String> map = getMap();
        return map.getOrDefault(key, null);
    }

    /**
     * 设置分页信息
     *
     * @param pageInfo PageInfo<T>类型使用json序列化后的字符串
     **/
    public static void setPageInfo(String pageInfo) {
        set(BaseContextConstants.PAGE_INFO, pageInfo);
    }

    /**
     * 获取分页信息
     **/
    public static String getPageInfo() {
        return get(BaseContextConstants.PAGE_INFO);
    }
}
