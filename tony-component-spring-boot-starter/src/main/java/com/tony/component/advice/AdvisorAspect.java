/*
 *       Copyright© (2020).
 */
package com.tony.component.advice;

import com.tony.common.utils.spring.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;


/**
 * @author tony
 * @create 2021-12-26
 * @description:
 */
@Aspect
@Slf4j
public class AdvisorAspect {


    @Before(value = "@annotation(before)")
    public void beforeAdvice(JoinPoint jp, com.tony.component.advice.Before before) {
        if (before.adviceUsing() != Void.class) {
            try {
                Advisor advisor = (Advisor) SpringUtils.getInstance(before.adviceUsing());
                advisor.before(jp);
            } catch (Exception e) {
                log.error(e.toString(), e);
            }
        }
    }

    @Before(value = "@annotation(befores)")
    public void beforeAdvice(JoinPoint jp, com.tony.component.advice.Befores befores) {
        Arrays.stream(befores.value()).forEach(before -> beforeAdvice(jp, before));
    }

    @Around(value = "@annotation(around)")
    public Object aroundAdvice(ProceedingJoinPoint pjp, com.tony.component.advice.Around around) throws Throwable {
        if (around.adviceUsing() != Void.class) {
            Advisor advisor = (Advisor) SpringUtils.getInstance(around.adviceUsing());
            return advisor.around(pjp);
        }
        return null;
    }

    @Around(value = "@annotation(arounds)")
    public Object aroundAdvice(ProceedingJoinPoint pjp, com.tony.component.advice.Arounds arounds) throws Throwable {
        Object obj = null;
        for (com.tony.component.advice.Around around: arounds.value()) {
            obj = aroundAdvice(pjp, around);
        }
        return obj;
    }



        @After(value = "@annotation(after)")
    public void afterAdvice(JoinPoint jp, com.tony.component.advice.After after) {
        if (after.adviceUsing() != Void.class) {
            try {
                Advisor advisor = (Advisor) SpringUtils.getInstance(after.adviceUsing());
                advisor.after(jp);
            } catch (Exception e) {
                log.error(e.toString(), e);
            }
        }
    }

    @After(value = "@annotation(afters)")
    public void afterAdvice(JoinPoint jp, com.tony.component.advice.Afters afters) {
        Arrays.stream(afters.value()).forEach(after -> afterAdvice(jp , after));
    }

    @AfterReturning(value = "@annotation(afterReturning)", returning = "retVal")
    public void afterReturningAdvice(JoinPoint jp, Object retVal, com.tony.component.advice.AfterReturning afterReturning) {
        if (afterReturning.adviceUsing() != Void.class) {
            try {
                Advisor advisor = (Advisor) SpringUtils.getInstance(afterReturning.adviceUsing());
                advisor.afterReturning(jp, retVal);
            } catch (Exception e) {
                log.error(e.toString(), e);
            }
        }
    }

    @AfterReturning(value = "@annotation(afterReturnings)", returning = "retVal")
    public void afterReturningsAdvice(JoinPoint jp, Object retVal, AfterReturnings afterReturnings) {
        for (com.tony.component.advice.AfterReturning afterReturning : afterReturnings.value()) {
            afterReturningAdvice(jp, retVal, afterReturning);
        }
    }

    @AfterThrowing(value = "@annotation(throwing)", throwing = "ex")
    public void afterThrowingAdvice(JoinPoint jp, Throwable ex, com.tony.component.advice.AfterThrowing throwing) {
        if (throwing.adviceUsing() != Void.class) {
            try {
                Advisor advisor = (Advisor) SpringUtils.getInstance(throwing.adviceUsing());
                advisor.afterThrowing(jp, ex);
            } catch (Exception e) {
                log.error(e.toString(), e);
            }
        }
    }

    @AfterThrowing(value = "@annotation(throwings)", throwing = "ex")
    public void afterThrowingsAdvice(JoinPoint jp, Throwable ex, com.tony.component.advice.AfterThrowings throwings) {
        Arrays.stream(throwings.value()).forEach(throwing -> afterThrowingAdvice(jp, ex, throwing));
    }
}
