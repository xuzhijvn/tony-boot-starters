/*
 *       Copyright© (2020).
 */
package com.tony.component.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

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

    /**
     * 得到方法
     *
     * @param t
     * @param <T>
     * @return
     */
    default <T extends JoinPoint> Method getMethod(T t) {
        MethodSignature signature = (MethodSignature) t.getSignature();
        return signature.getMethod();
    }

    /**
     * 是否同一个方法
     *
     * @param t
     * @param method
     * @param <T>
     * @return
     */
    default <T extends JoinPoint> boolean equalMethod(T t, Method method) {
        return getMethod(t).equals(method);
    }
}
