/*
 *       Copyright© (2020) TONY Co., Ltd.
 */
package com.tony.component.handler;

import com.tony.component.annotation.Feishu;
import com.tony.component.util.BeanUtil;
import com.tony.component.template.FeishuTemplate;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author tony
 * @create 2021-07-25
 * @description:
 */
@Aspect
public class FeishuAspect {

    @Pointcut("@annotation(com.tony.component.annotation.Feishu)")
    public void Feishu() {
    }

    @Around("Feishu()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 目标方法
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Feishu feishu = method.getAnnotation(Feishu.class);
        Object result;
        try {
            result = point.proceed();
        } catch (Throwable ex) {
            BeanUtil.getBean(FeishuTemplate.class).send(feishu.titleName(), ex, ex.getMessage(), feishu.color());
            throw ex;
        }
        return result;
    }
}