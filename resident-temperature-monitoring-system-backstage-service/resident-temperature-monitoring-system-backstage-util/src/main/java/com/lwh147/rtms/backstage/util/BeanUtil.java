package com.lwh147.rtms.backstage.util;

import cn.hutool.core.bean.BeanException;
import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description: bean操作工具类
 * @author: lwh
 * @create: 2021/4/28 22:28
 * @version: v1.0
 **/
public class BeanUtil {

    /**
     * 将bean转化为另一种bean实体
     *
     * @param object
     * @param entityClass
     * @return
     */
    public static <T> T convertBean(Object object, Class<T> entityClass) throws BeanException {
        if (object == null) {
            throw new BeanException("BeanUtil: 源对象不能为空");
        }
        return JSON.parseObject(JSON.toJSONString(object), entityClass);
    }

    /**
     * 对象属性拷贝，生成新对象
     *
     * @param source 原对象
     * @param target 目标对象的类
     * @return
     */
    public static <T> T copy(Object source, Class<T> target) throws BeanException {
        if (source == null) {
            throw new BeanException("BeanUtil: 源对象不能为空");
        }
        T targetInstance;
        try {
            targetInstance = target.newInstance();
        } catch (Exception e) {
            throw new BeanException("BeanUtil: 实例化对象出错", e);
        }
        BeanUtils.copyProperties(source, targetInstance);
        return targetInstance;
    }


    /**
     * 对象属性拷贝，已有目标对象
     *
     * @param source 源对象
     * @param target 目标对象
     * @return void
     **/
    public static <T> void copy(Object source, Object target) throws BeanException {
        if (source == null) {
            throw new BeanException("BeanUtil: 源对象不能为空");
        }
        if (target == null) {
            throw new BeanException("BeanUtil: 目标对象不能为空");
        }
        try {
            BeanUtils.copyProperties(source, target);
        } catch (Exception e) {
            throw new BeanException("BeanUtil: 拷贝属性时出错", e);
        }
    }

    /**
     * 空对象不拷贝，如果目标空 源不空则可以考虑，否则执行基础拷贝
     *
     * @param source
     * @param target
     * @param targetClass
     * @param <T>
     */
    public static <T> void copy(Object source, Object target, Class<T> targetClass) throws BeanException {
        if (source == null) {
            throw new BeanException("BeanUtil: 源对象不能为空");
        }
        try {
            if (target == null) {
                target = copy(source, targetClass);
                return;
            }
            BeanUtils.copyProperties(source, target);
        } catch (Exception e) {
            throw new BeanException("BeanUtil: 拷贝属性时出错", e);
        }

    }

    /**
     * List对象拷贝
     *
     * @param list   原list对象
     * @param target 目标list元素对象类型
     * @return 可能为空
     */
    public static <T, E> List<T> copyList(List<E> list, Class<T> target) throws BeanException {
        if (list == null) {
            throw new BeanException("BeanUtil: 源对象列表不能为空");
        }
        List<T> targetList = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            return targetList;
        }
        for (E e : list) {
            T object = copy(e, target);
            targetList.add(object);
        }
        return targetList;
    }

    /**
     * 允许用户自定义深度拷贝
     */
    public interface CopyCallback {
        /**
         * 该接口在拷贝过程被回调
         *
         * @param source 原对象
         * @param target 目标对象
         */
        void copy(Object source, Object target);
    }

    /**
     * List对象深度拷贝
     *
     * @param list         原列表对象
     * @param target       目标对象元素类型
     * @param copyCallback 链表内部对象的自定义拷贝方式
     * @param <T>
     * @param <E>
     * @return 可能为空
     */
    public static <T, E> List<T> copyList(List<E> list, Class<T> target, CopyCallback copyCallback) throws BeanException {
        if (list == null) {
            throw new BeanException("BeanUtil: 源对象列表不能为空");
        }
        List<T> targetList = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            return targetList;
        }
        for (E e : list) {
            T object = copy(e, target);
            targetList.add(object);
            if (copyCallback != null)
                copyCallback.copy(e, object);
        }
        return targetList;
    }

    /**
     * map转化为对象
     *
     * @param map
     * @param t
     * @return T
     **/
    public static <T> T mapToObject(Map<String, Object> map, Class<T> t) throws BeanException {
        T instance;
        try {
            instance = t.newInstance();
        } catch (Exception e) {
            throw new BeanException("BeanUtil: 实例化对象出错", e);
        }
        // 时间数据格式对象
        DateConverter converter = new DateConverter();
        // 一组时间格式
        String[] pattern = new String[3];
        pattern[0] = "yyyy-MM-dd HH:mm:ss";
        pattern[1] = "yyyy-MM-dd";
        pattern[2] = "yyyy/MM/dd";
        converter.setPatterns(pattern);
        // 注册Date时间对象格式
        // 之后可String --> Date
        ConvertUtils.register(converter, Date.class);

        IntegerConverter integerConverter = new IntegerConverter(null);
        ConvertUtils.register(integerConverter, Integer.class);

        //开始复制数据信息
        //遍历map<key, value>中的key，如果bean中有这个属性，就把这个key对应的value值赋给bean的属性
        try {
            org.apache.commons.beanutils.BeanUtils.populate(instance, map);
        } catch (Exception e) {
            throw new BeanException("BeanUtil: map转换为指定对象出错出错", e);
        }

        return instance;
    }

    /**
     * 方法说明：对象转化为Map
     *
     * @param object
     * @return
     */
    public static Map<?, ?> objectToMap(Object object) throws BeanException {
        if (object == null) {
            throw new BeanException("BeanUtil: 源对象不能为空");
        }
        return convertBean(object, Map.class);
    }
}

