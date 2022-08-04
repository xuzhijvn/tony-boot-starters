/*
 *       Copyright© (2020).
 */
package com.tony.component.handler;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.tony.component.Alert;
import com.tony.component.ExceptionHandler;
import com.tony.component.GlobalDefaultProperties;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;


/**
 * @author tony
 * @create 2021-05-01
 * Description: 全局异常处理
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionMethodInterceptor implements MethodInterceptor {

    private final Set<ExceptionHandler> exceptionHandlers;

    private final GlobalDefaultProperties globalDefaultProperties;

    public GlobalExceptionMethodInterceptor(Set<ExceptionHandler> exceptionHandlers, GlobalDefaultProperties globalDefaultProperties) {
        this.exceptionHandlers = exceptionHandlers;
        this.globalDefaultProperties = globalDefaultProperties;
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

            exceptionHandlers.forEach(exceptionHandler -> exceptionHandler.handle(method, args, ex));
            throw ex;
        }
    }


    private boolean isNeedExclude(Method method) {


        String clazz = method.getDeclaringClass().getName();

        if (Arrays.stream(globalDefaultProperties.getExcludePackages().split(","))
                .filter(StrUtil::isNotBlank)
                .anyMatch(e -> ReUtil.contains(e, clazz))) {
            return true;
        }

        if (StrUtil.isNotBlank(globalDefaultProperties.getLimitPackages()) &&
                Arrays.stream(globalDefaultProperties.getLimitPackages().split(","))
                        .filter(StrUtil::isNotBlank)
                        .noneMatch(e -> ReUtil.contains(e, clazz))
        ) {
            return true;
        }
        return false;
    }

    private boolean isNeedExclude(Throwable ex) {


        String clazz = ex.getClass().getName();

        if (Arrays.asList(globalDefaultProperties.getExcludeException().split(",")).contains(clazz)) {
            return true;
        }
        return false;
    }

}
