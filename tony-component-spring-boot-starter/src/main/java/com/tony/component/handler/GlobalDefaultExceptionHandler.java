/*
 *       Copyright© (2020).
 */
package com.tony.component.handler;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.tony.component.GlobalDefaultProperties;
import com.tony.component.annotation.Lark;
import com.tony.component.template.LarkTemplate;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;
import java.util.Arrays;


/**
 * @author tony
 * @create 2021-05-01
 * Description: 全局异常处理
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalDefaultExceptionHandler implements MethodInterceptor {

    private LarkTemplate larkTemplate;

    public GlobalDefaultExceptionHandler(LarkTemplate larkTemplate) {
        this.larkTemplate = larkTemplate;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        try {
            return methodInvocation.proceed();
        } catch (Throwable ex) {
            if (isNeedExclude(ex)) {
                throw ex;
            }
            Method method = methodInvocation.getMethod();

            if (isNeedExclude(method)) {
                throw ex;
            }

            Object[] args = methodInvocation.getArguments();
            Lark lark = method.getAnnotation(Lark.class);
            if (lark == null) {
                larkTemplate.sendIfAbsent("全局异常", ex, method, args);
            }
            throw ex;
        }
    }


    private boolean isNeedExclude(Method method) {

        GlobalDefaultProperties properties = larkTemplate.getGlobalDefaultProperties();

        String clazz = method.getDeclaringClass().getName();

        if (Arrays.stream(properties.getExcludePackages().split(",")).anyMatch(e -> ReUtil.contains(e, clazz))) {
            return true;
        }

        if (StrUtil.isNotBlank(properties.getLimitPackages()) &&
                Arrays.stream(properties.getLimitPackages().split(",")).noneMatch(e -> ReUtil.contains(e, clazz))
        ) {
            return true;
        }
        return false;
    }

    private boolean isNeedExclude(Throwable ex) {

        GlobalDefaultProperties properties = larkTemplate.getGlobalDefaultProperties();

        String clazz = ex.getClass().getName();

        if (Arrays.stream(properties.getExcludeException().split(",")).anyMatch(e -> e.equals(clazz))) {
            return true;
        }
        return false;
    }

}
