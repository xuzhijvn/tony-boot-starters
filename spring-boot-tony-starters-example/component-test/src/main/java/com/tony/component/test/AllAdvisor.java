/*
 *       Copyright© (2020).
 */
package com.tony.component.test;

import com.tony.component.advice.Advisor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.annotation.Order;

/**
 * @author tony
 * @create 2021-12-26
 * @description:
 */
public class AllAdvisor implements Advisor {
    @Override
    public void before(JoinPoint jp) {
        System.out.println("before... " + jp.getSignature());
    }

    @Override
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around... " + pjp.getSignature());
        return pjp.proceed() + " your";
    }

    @Override
    public void after(JoinPoint jp) {

        System.out.println("after... " + jp.getSignature());
    }

    @Override
    public void afterReturning(JoinPoint jp, Object retVal) {
        System.out.println("afterReturning... " + jp.getSignature() + " " + retVal);

    }

    @Override
    public void afterThrowing(JoinPoint jp, Throwable ex) {
        System.out.println("afterThrowing... " + jp.getSignature() + " " + ex);

    }
}
