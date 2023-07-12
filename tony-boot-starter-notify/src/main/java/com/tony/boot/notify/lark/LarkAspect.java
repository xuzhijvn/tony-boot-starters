/*
 *       Copyright© (2020) TONY Co., Ltd.
 */
package com.tony.boot.notify.lark;


import com.tony.boot.tools.utils.spring.SpringUtils;
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

    @Pointcut("@annotation(com.tony.boot.notify.lark.Lark)")
    public void lark() {
    }

    @Around("lark()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 目标方法
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Object[] objects = point.getArgs();
        Lark lark = method.getAnnotation(Lark.class);
        Object result;
        try {
            result = point.proceed();
        } catch (Throwable ex) {
            LarkTemplate larkTemplate = SpringUtils.getBean(LarkTemplate.class);
            Color color = lark.color();
            String title = lark.title();
            String content = LarkRequest.buildDefaultContent(ex.getMessage(), null, ex, method, objects);
            LarkRequest request = LarkRequest.build(title, content, color);
            larkTemplate.notify(request);
            throw ex;
        }
        return result;
    }
}