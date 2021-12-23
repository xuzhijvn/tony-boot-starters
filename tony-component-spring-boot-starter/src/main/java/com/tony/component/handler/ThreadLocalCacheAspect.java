package com.tony.component.handler;


import com.alibaba.fastjson.JSON;
import com.tony.component.annotation.ThreadLocalCache;
import com.tony.component.annotation.ThreadLocalCleanAfter;
import com.tony.component.context.ThreadLocalCacheManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tony
 * @create 2021/7/14
 * @description: 线程内缓存
 */
@Aspect
public class ThreadLocalCacheAspect {


    @AfterReturning(value = "@annotation(cleanAfter)")
    public void clean(ThreadLocalCleanAfter cleanAfter){
        //clean thread local
        ThreadLocalCacheManager.removeCache();
    }

    @Around(value = "@annotation(localCache)")
    public Object aroundAdvice(ProceedingJoinPoint joinpoint, ThreadLocalCache localCache) throws Throwable {
        Object[] args = joinpoint.getArgs();
        Method method = ((MethodSignature) joinpoint.getSignature()).getMethod();
        String className = joinpoint.getTarget().getClass().getName();
        String methodName = method.getName();

        String key = parseKey(localCache.key(), method, args, getDefaultKey(className, methodName, args));

        Map cache = ThreadLocalCacheManager.getCache();
        if (cache == null) {
            cache = new HashMap();
        }

        Map finalCache = cache;

        if (finalCache.containsKey(key)) {
            return finalCache.get(key);
        }

        Object result = null;
        try {
            result = joinpoint.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
        finalCache.put(key, result);
        ThreadLocalCacheManager.setCache(finalCache);
        return result;
    }

    private String getDefaultKey(String className, String methodName, Object[] args) {
        String defaultKey = className + "." + methodName;
        if (args != null) {
            defaultKey = defaultKey + "." + JSON.toJSONString(args);
        }
        return defaultKey;
    }

    private String parseKey(String key, Method method, Object[] args, String defaultKey) {
        if (!StringUtils.hasText(key)) {
            return defaultKey;
        }
        LocalVariableTableParameterNameDiscoverer nameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = nameDiscoverer.getParameterNames(method);

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < paraNameArr.length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        try {
            return parser.parseExpression(key).getValue(context, String.class);
        } catch (SpelEvaluationException e) {
            // 解析不出SPEL默认为类名+方法名+参数
            return defaultKey;
        }
    }

}