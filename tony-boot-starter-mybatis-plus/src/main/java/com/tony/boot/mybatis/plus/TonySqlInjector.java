/*
 *       Copyright© (2020).
 */
package com.tony.boot.mybatis.plus;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.tony.boot.mybatis.plus.method.InsertIgnore;
import com.tony.boot.mybatis.plus.method.InsertIgnoreBatch;
import com.tony.boot.mybatis.plus.method.Replace;

import java.util.List;

/**
 * 自定义sql注入器，增加通用方法
 *
 * @author tony
 */
public class TonySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        // 插入数据，如果中已经存在相同的记录，则忽略当前新数据
        methodList.add(new InsertIgnore());
        // 批量插入数据，如果中已经存在相同的记录，则忽略当前新数据
        methodList.add(new InsertIgnoreBatch());
        // 替换数据，如果中已经存在相同的记录，则覆盖旧数据
        methodList.add(new Replace());
        return methodList;
    }
}
