/**
 * Copyright (c) 2021-2217, Winter tony (783175223@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tony.mybatis.cache.adapter;

import com.tony.mybatis.cache.ApplicationContextHolder;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.J2Cache;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author tony
 * @create 2021/8/7
 * @description: 实现了 MyBatis 的缓存接口
 */
public class J2CacheAdapter implements Cache {

    private static final String DEFAULT_REGION = "default";

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private CacheChannel cache;

    //private CacheChannel cache = J2Cache.getChannel();

    private String id;

    public J2CacheAdapter(String id) {
        if (id == null) {
            id = DEFAULT_REGION;
        }
        this.id = id;
    }

    private CacheChannel getCacheChannel() {
        if (null == cache) {
            cache = ApplicationContextHolder.getBean("cacheChannel", CacheChannel.class);
        }
        return cache;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        if (id == null) {
            id = DEFAULT_REGION;
        }
        this.id = id;
    }

    @Override
    public void putObject(Object o, Object o1) {
        getCacheChannel().set(this.id, o.toString(), o1);
    }

    @Override
    public Object getObject(Object key) {
        return getCacheChannel().get(this.id, key.toString()).getValue();
    }

    @Override
    public Object removeObject(Object o) {
        Object obj = getCacheChannel().get(this.id, o.toString()).getValue();
        if (obj != null) {
            getCacheChannel().evict(this.id, o.toString());
        }
        return obj;
    }

    @Override
    public void clear() {
        getCacheChannel().clear(this.getId());
    }

    @Override
    public int getSize() {
        Collection<String> keys = getCacheChannel().keys(this.getId());
        return keys != null ? keys.size() : 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }
}
