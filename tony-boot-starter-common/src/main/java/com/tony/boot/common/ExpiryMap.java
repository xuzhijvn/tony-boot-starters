/*
 *       Copyright© (2020).
 */
package com.tony.boot.common;


import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title: ExpiryMap 可以设置过期时间的Map
 * @description ExpiryMap继承至HashMap 重写了所有对外的方法，对每个key值都设置了有效期
 * @Author: xx
 * @Version: 1.0
 */
public class ExpiryMap<K, V> extends ConcurrentHashMap<K, V> {

    private static final long serialVersionUID = 1L;
    /**
     * 缓存实例对象
     */
    private volatile static ExpiryMap<Object, Object> instance;

    private final ConcurrentHashMap<K, Long> expiryMap = new ConcurrentHashMap<>();
    /**
     * default expiry time 2s
     */
    private long expiry = 1000 * 2;

    public ExpiryMap() {
        super();
    }

    public ExpiryMap(long defaultExpiryTime) {
        this(1 << 4, defaultExpiryTime);
    }

    public ExpiryMap(int initialCapacity, long defaultExpiryTime) {
        super(initialCapacity);
        this.expiry = defaultExpiryTime;
    }

    /**
     * 采用单例模式获取实例
     *
     * @return
     */
    public static ExpiryMap<Object, Object> getInstance() {
        //第一次判空,提高效率
        if (null == instance) {
            //保证线程安全
            synchronized (ExpiryMap.class) {
                //第二次判空,保证单例对象的唯一性,防止第一次有多个线程进入第一个if判断
                if (null == instance) {
                    instance = new ExpiryMap<>();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) throws InterruptedException {
        ExpiryMap<String, String> map = new ExpiryMap<>();
        map.put("test", "xxx");
        map.put("test2", "ankang", 5000);
        System.out.println("test==" + map.get("test"));
        Thread.sleep(3000);
        System.out.println("test==" + map.get("test"));
        System.out.println("test2==" + map.get("test2"));
        Thread.sleep(3000);
        System.out.println("test2==" + map.get("test2"));
    }

    @Override
    public V put(K key, V value) {
        expiryMap.put(key, System.currentTimeMillis() + expiry);
        return super.put(key, value);
    }

    @Override
    public boolean containsKey(Object key) {
        return !checkExpiry(key, true) && super.containsKey(key);
    }

    /**
     * @param key
     * @param value
     * @param expiryTime 键值对有效期 毫秒
     * @return
     */
    public V put(K key, V value, long expiryTime) {
        expiryMap.put(key, System.currentTimeMillis() + expiryTime);
        return super.put(key, value);
    }

    @Override
    public int size() {
        return entrySet().size();
    }

    @Override
    public boolean isEmpty() {
        return entrySet().size() == 0;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value == null) {
            return Boolean.FALSE;
        }
        Set<Entry<K, V>> set = super.entrySet();
        Iterator<Entry<K, V>> iterator = set.iterator();
        while (iterator.hasNext()) {
            java.util.Map.Entry<K, V> entry = iterator.next();
            if (value.equals(entry.getValue())) {
                if (checkExpiry(entry.getKey(), false)) {
                    iterator.remove();
                    return Boolean.FALSE;
                } else {
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Collection<V> values() {

        Collection<V> values = super.values();

        if (values.size() < 1) {
            return values;
        }

        values.removeIf(next -> !containsValue(next));
        return values;
    }

    @Override
    public V get(Object key) {
        if (key == null) {
            return null;
        }
        if (checkExpiry(key, true)) {
            return null;
        }
        return super.get(key);
    }

    /**
     * @param key
     * @return null:不存在或key为null -1:过期  存在且没过期返回value 因为过期的不是实时删除，所以稍微有点作用
     * @Description: 是否过期
     */
    public Object isInvalid(Object key) {
        if (key == null) {
            return null;
        }
        if (!expiryMap.containsKey(key)) {
            return null;
        }
        long expiryTime = expiryMap.get(key);

        boolean flag = System.currentTimeMillis() > expiryTime;

        if (flag) {
            super.remove(key);
            expiryMap.remove(key);
            return -1;
        }
        return super.get(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            expiryMap.put(e.getKey(), System.currentTimeMillis() + expiry);
        }
        super.putAll(m);
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<java.util.Map.Entry<K, V>> set = super.entrySet();
        set.removeIf(entry -> checkExpiry(entry.getKey(), false));

        return set;
    }

    /**
     * @param key
     * @param isRemoveSuper true super删除
     * @return 是否过期
     */
    private boolean checkExpiry(Object key, boolean isRemoveSuper) {

        if (!expiryMap.containsKey(key)) {
            return Boolean.FALSE;
        }
        long expiryTime = expiryMap.get(key);

        boolean flag = System.currentTimeMillis() > expiryTime;

        if (flag) {
            if (isRemoveSuper) {
                super.remove(key);
            }
            expiryMap.remove(key);
        }
        return flag;
    }
}