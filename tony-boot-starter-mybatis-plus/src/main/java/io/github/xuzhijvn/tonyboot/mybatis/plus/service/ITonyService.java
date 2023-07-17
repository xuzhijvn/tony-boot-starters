/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.mybatis.plus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.xuzhijvn.tonyboot.mybatis.plus.method.InsertIgnore;
import io.github.xuzhijvn.tonyboot.mybatis.plus.method.Replace;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
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
     * 插入（批量），如果中已经存在相同的记录，则忽略当前新数据
     *
     * @param entityList 实体对象集合
     * @return true/false
     */
    @Transactional(rollbackFor = Exception.class)
    default boolean saveIgnoreBatch(Collection<T> entityList) {
        return saveIgnoreBatch(entityList, DEFAULT_BATCH_SIZE);
    }

    /**
     * 插入（批量），如果中已经存在相同的记录，则忽略当前新数据
     *
     * @param entityList 实体对象集合
     * @param batchSize  插入批次数量
     * @return true/false
     */
    boolean saveIgnoreBatch(Collection<T> entityList, int batchSize);

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

