/*
 *       Copyright© (2020).
 */
package com.tony.boot.component.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author tony
 * @create 2021-12-26
 * @description:
 */
public abstract class AbstractAfterThrowingAdvisor implements Advisor {


    @Override
    public void before(JoinPoint jp) {

    }

    @Override
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        return null;
    }

    @Override
    public void after(JoinPoint jp) {

    }

    @Override
    public void afterReturning(JoinPoint jp, Object retVal) {
    }

    /**
     * afterThrowing advice
     *
     * @param jp
     * @param ex
     */
    @Override
    public abstract void afterThrowing(JoinPoint jp, Throwable ex);
}
