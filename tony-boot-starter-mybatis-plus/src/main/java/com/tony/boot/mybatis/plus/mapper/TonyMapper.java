/*
 *       Copyright© (2020).
 */
package com.tony.boot.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tony.boot.mybatis.plus.method.InsertIgnore;
import com.tony.boot.mybatis.plus.method.Replace;

import java.util.List;

/**
 * 自定义基础Mapper继承BaseMapper
 *
 * @author tony
 */
public interface TonyMapper<T> extends BaseMapper<T> {
    /**
     * 插入数据，如果中已经存在相同的记录，则忽略当前新数据
     * {@link InsertIgnore}
     *
     * @param entity 实体类
     * @return 影响条数
     */
    int insertIgnore(T entity);

    /**
     * 批量插入数据，如果中已经存在相同的记录，则忽略当前新数据
     * <p>
     * 注意：这种实现方式要 特别注意数据库SQL语句的长度限制，在进行数据合并在同一SQL中务必不能超过SQL长度限制，通过 max_allowed_packet 配置可以修改，默认是1M。
     * <p/>
     * {@link InsertIgnore}
     *
     * @param entityList 实体类列表
     * @return 影响条数
     */
    @Deprecated
    int insertIgnoreBatch(List<T> entityList);

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

