/*
 *       Copyright© (2020).
 */
package com.tony.boot.tools;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tony
 * @create 2021-08-31
 * @description:
 */
public class DefaultContext {

    private static final ThreadLocal<Map<Object, Object>> THREAD_LOCAL_CACHE = new ThreadLocal<>();

    public static void set(Object obj) {
        if (obj == null) {
            return;
        }
        Map<Object, Object> cache = THREAD_LOCAL_CACHE.get();
        if (cache == null) {
            cache = new HashMap<>(16);
        }
        cache.put(obj.getClass(), obj);
        THREAD_LOCAL_CACHE.set(cache);
    }

    public static Object get(Class<?> clazz) {
        Map<Object, Object> cache = THREAD_LOCAL_CACHE.get();
        return cache == null ? null : cache.get(clazz);
    }

    public static void removeCache() {
        THREAD_LOCAL_CACHE.remove();
    }

}
