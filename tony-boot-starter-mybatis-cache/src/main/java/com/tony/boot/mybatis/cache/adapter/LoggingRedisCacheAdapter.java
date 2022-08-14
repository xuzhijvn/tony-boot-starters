package com.tony.boot.mybatis.cache.adapter;


import org.apache.ibatis.cache.decorators.LoggingCache;

/**
 * @author tony
 * @create 2021/8/7
 * @description: redis cache decorators with logging
 */
public class LoggingRedisCacheAdapter extends LoggingCache {

    /**
     * 构造函数，二级缓存必须提供id的构造函数
     *
     * @param id 构造函数
     */
    public LoggingRedisCacheAdapter(String id) {
        super(new RedisCacheAdapter(id));
    }

}
