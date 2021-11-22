/*
 *       Copyright© (2020).
 */
package com.tony.component.handler;

import com.tony.component.annotation.Lark;
import com.tony.component.template.LarkTemplate;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

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
            if (Arrays.stream(larkTemplate.getGlobalDefaultProperties().getExcludeException().split(",")).anyMatch(e -> e.equals(ex.getClass().getName()))) {
                throw ex;
            }
            Lark lark = methodInvocation.getMethod().getAnnotation(Lark.class);
            if (lark == null) {
                larkTemplate.sendIfAbsent("全局异常", ex.getClass(), ex.getMessage());
            }
            throw ex;
        }
    }
}
