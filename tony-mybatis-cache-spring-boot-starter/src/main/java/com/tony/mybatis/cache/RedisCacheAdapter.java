package com.tony.mybatis.cache;


import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author tony
 * @create 2021/8/7
 * @description:
 */
public class RedisCacheAdapter implements Cache {


    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    /**
     * cache instance id
     */
    private final String id;
    /**
     * 缓存刷新间隔，单位为毫秒
     * flushInterval 参数(自定义cache无法使用默认的flushInterval)
     */
    private long flushInterval = 0L;

    private Integer timeout;

    private RedisTemplate redisTemplate;

    public RedisCacheAdapter(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    public void setFlushInterval(long flushInterval) {
        this.flushInterval = flushInterval;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    private RedisTemplate getRedisTemplate() {
        if (null == redisTemplate) {
            redisTemplate = ApplicationContextHolder.getBean("redisTemplate", RedisTemplate.class);
        }
        return redisTemplate;
    }

    @Override
    public String getId() {
        return id;
    }

    /**
     * Put query result to redis
     */
    @Override
    @SuppressWarnings("unchecked")
    public void putObject(Object key, Object value) {
        getRedisTemplate().opsForHash().put(getId(), key.toString(), value);
        if (timeout != null && timeout > 0) {
            getRedisTemplate().expire(getId(), timeout, TimeUnit.MILLISECONDS);
            return;
        }
        if (flushInterval > 0L) {
            getRedisTemplate().expire(getId(), flushInterval, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * Get cached query result from redis
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object getObject(Object key) {
        return getRedisTemplate().opsForHash().get(getId(), key.toString());
    }

    /**
     * Remove cached query result from redis
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object removeObject(Object key) {
        Object obj = getRedisTemplate().opsForHash().get(getId(), key.toString());
        if (obj != null) {
            getRedisTemplate().opsForHash().delete(getId(), key.toString());
        }
        return obj;
    }

    /**
     * Clears this cache instance
     */
    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        getRedisTemplate().delete(getId());
    }

    /**
     * This method is not used
     */
    @Override
    @SuppressWarnings("unchecked")
    public int getSize() {
        return getRedisTemplate().opsForHash().size(getId()).intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
}
