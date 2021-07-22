package com.lwh147.rtms.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class RedisUtils {

    private final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    public RedisUtils(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            logger.error("redis cache expire error", e);
            return false;
        }
    }

    /**
     * scan模糊匹配key值
     *
     * @param redisKey
     * @param hashKeyPattern
     * @return
     */
    public <T> List<Map<String, T>> scanHashKey(String redisKey, String hashKeyPattern) {
        List<Map<String, T>> resultList = new LinkedList<>();
        Cursor<Map.Entry<Object, Object>> cursor = null;
        try {
            cursor = redisTemplate.opsForHash().scan(redisKey,
                    ScanOptions.scanOptions().match(hashKeyPattern).count(10000).build());
            while (cursor.hasNext()) {
                Map.Entry<Object, Object> entry = cursor.next();
                Map<String, T> cacheMap = new HashMap<>();
                Object key = entry.getKey();
                Object valueObj = entry.getValue();
                cacheMap.put(String.valueOf(key), (T) valueObj);
                resultList.add(cacheMap);
            }
            //关闭cursor
            cursor.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                try { //关闭cursor
                    cursor.close();
                } catch (IOException e) {
                }
            }
        }
        return resultList;
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param
     * @return
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 批量写入缓存（针对string存储类型）
     *
     * @param map e.g. "{"key1": Object1, "key2": Object2}"
     * @return
     */
    public boolean multiSet(Map<String, Object> map) {
        try {
            redisTemplate.opsForValue().multiSet(map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 批量获取缓存（针对string存储类型）
     *
     * @param keys
     * @return
     */
    public List<Object> multiGet(Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * 写入带失效周期的缓存
     * 时间单位为秒
     *
     * @param key
     * @param
     * @return
     */
    public boolean expireSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 哈希获取数据
     *
     * @param key
     * @param hashKey
     * @return
     */
    public Object hmGet(String key, Object hashKey) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }

    /**
     * 哈希多个hash key获取数据 (这个是hash获取多个)
     *
     * @param key
     * @param hashKeys
     * @return
     */
    public List multiGet(String key, Collection hashKeys) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.multiGet(key, hashKeys);
    }

    /**
     * 哈希获取数据
     *
     * @param key
     * @return
     */
    public Object hmGetAll(String key) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.entries(key);
    }

    /**
     * 哈希 添加
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public void hmSet(String key, Object hashKey, Object value) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key, hashKey, value);
    }

    /**
     * 哈希 删除
     *
     * @param key
     * @param hashKey
     */
    public void hmDel(String key, Object hashKey) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.delete(key, hashKey);
    }

    /**
     * 哈希 添加
     *
     * @param key
     * @param values
     */
    public void hmSetAll(String key, Map values) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.putAll(key, values);
    }

    /**
     * 哈希 判断是否存在
     */
    public boolean hmHaskey(String key, Object hashKey) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.hasKey(key, hashKey);
    }

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        logger.debug("Redis pattern:" + pattern + " clean:" + keys.size());
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 列表添加
     *
     * @param k
     * @param v
     */
    public void listPush(String k, Object v) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(k, v);
    }

    /**
     * 列表批量添加
     *
     * @param k
     * @param v
     */
    public void listPushAll(String k, List v) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPushAll(k, v);
    }

    /**
     * 左弹出数列
     *
     * @param key
     * @return
     */
    public Object listPop(String key) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.leftPop(key);
    }

    /**
     * 左弹出数列
     *
     * @param key
     * @return
     */
    public Long listSize(String key) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.size(key);
    }

    /**
     * 列表获取
     *
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public List<Object> lRange(String k, long l, long l1) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(k, l, l1);
    }

    /**
     * 集合添加
     *
     * @param key
     * @param value
     */
    public void add(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key, value);
    }

    /**
     * 集合获取
     *
     * @param key
     * @return
     */
    public Set<Object> setMembers(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * 将数据放入set缓存与缓存
     *
     * @param key 键
     * @param
     * @return
     */
    public boolean zAdd(String key, Object value, double score, long time) {
        try {
            redisTemplate.opsForZSet().add(key, value, score);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            logger.error("redis cache zAdd error", e);
            return false;
        }
    }

    /**
     * 有序集合添加没有缓存
     *
     * @param key
     * @param value
     */
    public boolean zAdd(String key, Object value, double score) {
        try {
            return redisTemplate.opsForZSet().add(key, value, score);
        } catch (Exception e) {
            logger.error("redis cache zAdd error", e);
            return false;
        }
    }

    /**
     * 获取变量指定区间的元素
     *
     * @param key 键
     * @param
     * @return 成功个数
     */
    public Set<Object> zRange(String key, long start, long end) {
        try {
            return redisTemplate.opsForZSet().range(key, start, end);
        } catch (Exception e) {
            logger.error("redis cache zRange error", e);
            return Collections.singleton("");
        }
    }

    /**
     * 获取区间值的个数
     *
     * @param key 键
     * @param
     * @return 成功个数
     */
    public long count(String key, double min, double max) {
        try {
            return redisTemplate.opsForZSet().count(key, min, max);
        } catch (Exception e) {
            logger.error("redis cache sSet error", e);
            return 0;
        }
    }

    /**
     * 获取变量中元素的个数
     *
     * @param key 键
     * @param
     * @return 成功个数
     */
    public long zCard(String key) {
        try {
            return redisTemplate.opsForZSet().zCard(key);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long zRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForZSet().remove(key, values);
            return count;
        } catch (Exception e) {
            logger.error("redis cache setRemove error", e);
            return 0;
        }
    }

    /**
     * 根据索引值移除区间元素
     *
     * @return 移除的个数
     */
    public long zRemoveRange(String key, long start, long end) {
        try {
            return redisTemplate.opsForZSet().removeRange(key, start, end);
        } catch (Exception e) {
            logger.error("redis cache setRemove error", e);
            return 0;
        }
    }

    /**
     * 获取索引
     *
     * @return 移除的个数
     */
    public long zRank(String key, Object o) {
        try {
            return redisTemplate.opsForZSet().rank(key, o);
        } catch (Exception e) {
            logger.error("redis cache zRank error", e);
            return 0;
        }
    }

    /**
     * 获取分数
     *
     * @return
     */
    public double zScore(String key, Object o) {
        try {
            return redisTemplate.opsForZSet().score(key, o);
        } catch (Exception e) {
            logger.error("redis cache zRank error", e);
            return Double.valueOf(0);
        }
    }

}