/*
 *       Copyright© (2020).
 */
package com.tony.component.handler;

import com.tony.component.annotation.Feishu;
import com.tony.component.template.FeishuTemplate;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


/**
 * @author tony
 * @create 2021-05-01
 * Description: 全局异常处理
 */
public class GlobalDefaultExceptionHandler implements MethodInterceptor {

    private FeishuTemplate feishuTemplate;

    public GlobalDefaultExceptionHandler(FeishuTemplate feishuTemplate) {
        this.feishuTemplate = feishuTemplate;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        try {
            return methodInvocation.proceed();
        } catch (Throwable ex) {
            Feishu feishu = methodInvocation.getMethod().getAnnotation(Feishu.class);
            if (feishu == null){
                feishuTemplate.send("全局异常", ex, ex.getMessage());
            }
            throw ex;
        }
    }
}
