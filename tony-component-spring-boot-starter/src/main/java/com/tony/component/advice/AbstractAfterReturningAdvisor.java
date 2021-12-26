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
public abstract class AbstractAfterReturningAdvisor implements Advisor {


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

    /**
     * afterReturning advice
     *
     * @param jp
     * @param retVal
     */
    @Override
    public abstract void afterReturning(JoinPoint jp, Object retVal);

    @Override
    public void afterThrowing(JoinPoint jp, Throwable ex) {

    }
}
