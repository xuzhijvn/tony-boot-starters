/*
 *       Copyright© (2020).
 */
package com.tony.mybatis.plus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tony.mybatis.plus.method.InsertIgnore;
import com.tony.mybatis.plus.method.Replace;

import java.util.List;

/**
 * 自定义基础Service继承IService及实现类
 *
 * @author tony
 */
public interface ITonyService<T> extends IService<T> {
    /**
     * 插入数据，如果中已经存在相同的记录，则忽略当前新数据
     * {@link InsertIgnore}
     *
     * @param entity 实体类
     * @return 影响条数
     */
    int saveIgnore(T entity);

    /**
     * 批量插入数据，如果中已经存在相同的记录，则忽略当前新数据
     * {@link InsertIgnore}
     *
     * @param entityList 实体类列表
     * @return 影响条数
     */
    int saveIgnoreBatch(List<T> entityList);

    /**
     * 替换数据
     * replace into表示插入替换数据，需求表中有PrimaryKey，或者unique索引，如果数据库已经存在数据，则用新数据替换，如果没有数据效果则和insert into一样；
     * {@link Replace}
     *
     * @param entity 实体类
     * @return 影响条数
     */
    int replace(T entity);
}

