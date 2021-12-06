/*
 *       Copyright© (2020) TONY Co., Ltd.
 */
package com.tony.component.handler;

import com.tony.common.utils.spring.SpringUtils;
import com.tony.component.annotation.Lark;
import com.tony.component.template.LarkTemplate;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * @author tony
 * @create 2021-07-25
 * @description:
 */
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LarkAspect {

    @Pointcut("@annotation(com.tony.component.annotation.Lark)")
    public void lark() {
    }

    @Around("lark()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 目标方法
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Object[] args = point.getArgs();
        Lark lark = method.getAnnotation(Lark.class);
        Object result;
        try {
            result = point.proceed();
        } catch (Throwable ex) {
            SpringUtils.getBean(LarkTemplate.class).sendAsync(lark.titleName(), ex, method, args, lark.color());
            throw ex;
        }
        return result;
    }
}