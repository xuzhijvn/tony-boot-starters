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
public abstract class AbstractAfterAdvisor implements Advisor {


    @Override
    public void before(JoinPoint jp) {

    }

    @Override
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        return null;
    }

    /**
     * after advice
     *
     * @param jp
     */
    @Override
    public abstract void after(JoinPoint jp);

    @Override
    public void afterReturning(JoinPoint jp, Object retVal) {
    }

    @Override
    public void afterThrowing(JoinPoint jp, Throwable ex) {

    }
}
