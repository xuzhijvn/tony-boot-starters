/*
 *       Copyright© (2020).
 */
package com.tony.boot.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.boot.mybatis.plus.TonySqlMethod;
import com.tony.boot.mybatis.plus.mapper.TonyMapper;
import com.tony.boot.mybatis.plus.service.ITonyService;

import java.util.Collection;
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
    public int insertIgnore(T entity) {
        return baseMapper.insertIgnore(entity);
    }

    @Override
    public int insertIgnoreBatch(List<T> entityList) {
        if (entityList == null || entityList.size() == 0) {
            return 0;
        }
        return baseMapper.insertIgnoreBatch(entityList);
    }

    @Override
    public boolean saveIgnoreBatch(Collection<T> entityList, int batchSize) {
        String sqlStatement = mapperClass.getName() + StringPool.DOT + TonySqlMethod.INSERT_IGNORE_ONE.getMethod();
        return executeBatch(entityList, batchSize, (sqlSession, entity) -> sqlSession.insert(sqlStatement, entity));
    }

    @Override
    public int replace(T entity) {
        return baseMapper.replace(entity);
    }
}

