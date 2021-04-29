package com.lwh147.rtms.backstage.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @description: 利用雪花算法生成id
 * @author: lwh
 * @create: 2021/4/28 22:28
 * @version: v1.0
 **/
@Slf4j
@Component
public class IdGenerator {
    @Value("${snowflake.workerId: 0}")
    private long workerId;
    @Value("${snowflake.dataCenterId: 1}")
    private long dataCenterId;
    /**
     * 雪花算法对象
     **/
    private Snowflake snowflake;

    @PostConstruct
    public void init() {
        try {
            log.info("当前机器的 workerId: {}", workerId);
            snowflake = IdUtil.createSnowflake(workerId, dataCenterId);
        } catch (Exception e) {
            log.error("当前机器的 workerId 获取失败: {}", e.getMessage());
            workerId = NetUtil.getLocalhostStr().hashCode();
            log.error("当前机器 workId: {}", workerId);
        }
    }

    /**
     * 生成id
     *
     * @param
     * @return long 生成的id
     **/
    public synchronized long snowflakeId() {
        return snowflake.nextId();
    }

}
