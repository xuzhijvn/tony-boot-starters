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
public interface Advisor {

    /**
     * before
     *
     * @param jp
     */
    void before(JoinPoint jp);


    /**
     * around
     * @param pjp
     * @return
     * @throws Throwable
     */
    Object around(ProceedingJoinPoint pjp) throws Throwable;



    /**
     * after
     *
     * @param jp
     */
    void after(JoinPoint jp);


    /**
     * afterReturning
     *
     * @param jp
     * @param retVal
     */
    void afterReturning(JoinPoint jp, Object retVal);

    /**
     * afterThrowing
     * @param jp
     * @param ex
     */
    void afterThrowing(JoinPoint jp, Throwable ex);
}
