/*
 *       Copyright© (2020).
 */
package com.tony.mybatis;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

/**
 * @author tony
 * @create 2021-11-26
 * @description:
 */
public class EnumObjectFactory extends DefaultObjectFactory {
    /**
     * 这个时候还只是创建了entity对象，还没有属性赋值
     *
     * @param type
     * @param <T>
     * @return
     */
    @Override
    public <T> T create(Class<T> type) {
        T t = super.create(type);
        new BindEnumHandler<>(type).handle(t);
        return t;
    }
}
