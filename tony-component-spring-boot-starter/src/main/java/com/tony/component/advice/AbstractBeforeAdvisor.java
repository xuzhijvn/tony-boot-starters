/*
 *       Copyright© (2020).
 */
package com.tony.component.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author tony
 * @create 2021-12-26
 * @description:
 */
public abstract class AbstractBeforeAdvisor implements Advisor {

    /**
     * before advice
     *
     * @param jp
     */
    @Override
    public abstract void before(JoinPoint jp);

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

    @Override
    public void afterThrowing(JoinPoint jp, Throwable ex) {

    }
}
