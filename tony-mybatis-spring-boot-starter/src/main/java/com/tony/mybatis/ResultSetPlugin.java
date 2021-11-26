/*
 *       Copyright© (2020).
 */
package com.tony.mybatis;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.sql.Statement;
import java.util.Collection;

/**
 * @author tony
 * @create 2021-11-26
 * @description:
 */
@Intercepts({
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})})
public class ResultSetPlugin implements Interceptor{
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object result = invocation.proceed();
        if (result instanceof Collection) {
            Collection<Object> objList = (Collection) result;
            //List<Object> resultList = new ArrayList<>();
            for (Object obj : objList) {
                new BindEnumHandler<>(obj.getClass()).handle(obj);
            }
            return objList;
        } else {
            new BindEnumHandler<>(result.getClass()).handle(result);
            return result;
        }
    }
}
