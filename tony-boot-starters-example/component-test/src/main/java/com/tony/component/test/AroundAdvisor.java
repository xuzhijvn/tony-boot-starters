/*
 *       Copyright© (2020).
 */
package com.tony.component.test;

import com.tony.component.advice.AbstractAroundAdvisor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.annotation.Order;

/**
 * @author tony
 * @create 2021-12-26
 * @description:
 */
public class AroundAdvisor extends AbstractAroundAdvisor {
    @Override
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        return pjp.proceed() + " the best";
    }
}
