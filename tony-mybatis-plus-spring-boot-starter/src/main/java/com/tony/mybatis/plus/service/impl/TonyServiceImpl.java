/*
 *       Copyright© (2020).
 */
package com.tony.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.mybatis.plus.mapper.TonyMapper;
import com.tony.mybatis.plus.service.ITonyService;

import java.util.List;

/**
 * 此处可以写通用的Service
 *
 * @param <M>
 * @param <T>
 * @author tony
 */
public class TonyServiceImpl<M extends TonyMapper<T>, T> extends ServiceImpl<TonyMapper<T>, T> implements ITonyService<T> {

    @Override
    public int saveIgnore(T entity) {
        return baseMapper.insertIgnore(entity);
    }

    @Override
    public int saveIgnoreBatch(List<T> entityList) {
        return baseMapper.insertIgnoreBatch(entityList);
    }

    @Override
    public int replace(T entity) {
        return baseMapper.replace(entity);
    }
}

