/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.component.advice;

import io.github.xuzhijvn.tonyboot.tools.utils.spring.SpringUtils;
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
    public void beforeAdvice(JoinPoint jp, io.github.xuzhijvn.tonyboot.component.advice.Before before) {
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
    public void beforeAdvice(JoinPoint jp, Befores befores) {
        Arrays.stream(befores.value()).forEach(before -> beforeAdvice(jp, before));
    }

    @Around(value = "@annotation(around)")
    public Object aroundAdvice(ProceedingJoinPoint pjp, io.github.xuzhijvn.tonyboot.component.advice.Around around) throws Throwable {
        if (around.adviceUsing() != Void.class) {
            Advisor advisor = (Advisor) SpringUtils.getInstance(around.adviceUsing());
            return advisor.around(pjp);
        }
        return null;
    }

    @Around(value = "@annotation(arounds)")
    public Object aroundAdvice(ProceedingJoinPoint pjp, Arounds arounds) throws Throwable {
        Object obj = null;
        for (io.github.xuzhijvn.tonyboot.component.advice.Around around: arounds.value()) {
            obj = aroundAdvice(pjp, around);
        }
        return obj;
    }



        @After(value = "@annotation(after)")
    public void afterAdvice(JoinPoint jp, io.github.xuzhijvn.tonyboot.component.advice.After after) {
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
    public void afterAdvice(JoinPoint jp, Afters afters) {
        Arrays.stream(afters.value()).forEach(after -> afterAdvice(jp , after));
    }

    @AfterReturning(value = "@annotation(afterReturning)", returning = "retVal")
    public void afterReturningAdvice(JoinPoint jp, Object retVal, io.github.xuzhijvn.tonyboot.component.advice.AfterReturning afterReturning) {
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
        for (io.github.xuzhijvn.tonyboot.component.advice.AfterReturning afterReturning : afterReturnings.value()) {
            afterReturningAdvice(jp, retVal, afterReturning);
        }
    }

    @AfterThrowing(value = "@annotation(throwing)", throwing = "ex")
    public void afterThrowingAdvice(JoinPoint jp, Throwable ex, io.github.xuzhijvn.tonyboot.component.advice.AfterThrowing throwing) {
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
    public void afterThrowingsAdvice(JoinPoint jp, Throwable ex, AfterThrowings throwings) {
        Arrays.stream(throwings.value()).forEach(throwing -> afterThrowingAdvice(jp, ex, throwing));
    }
}
