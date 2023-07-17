/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.component.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author tony
 * @create 2021-12-26
 * @description:
 */
public abstract class AbstractAroundAdvisor implements Advisor {


    @Override
    public void before(JoinPoint jp) {

    }

    /**
     * around advice
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Override
    public abstract Object around(ProceedingJoinPoint pjp) throws Throwable;

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
