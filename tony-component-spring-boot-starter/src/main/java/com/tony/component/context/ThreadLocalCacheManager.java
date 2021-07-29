package com.tony.component.context;

import java.util.Map;

/**
 * @author tony
 * @create 2021/7/14
 * @description: 线程内缓存管理
 */
public class ThreadLocalCacheManager {

    private static ThreadLocal<Map> threadLocalCache = new ThreadLocal<>();

    public static void setCache(Map value) {
        threadLocalCache.set(value);
    }

    public static Map getCache() {
        return threadLocalCache.get();
    }

    public static void removeCache() {
        threadLocalCache.remove();
    }

    public static void removeCache(String key) {
        Map cache = threadLocalCache.get();
        if (cache != null) {
            cache.remove(key);
        }
    }

}
