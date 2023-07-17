/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.component;

import com.tony.boot.component.advice.AbstractAroundAdvisor;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author tony
 * @create 2021-12-26
 * @description:
 */
public class AroundAdvisor extends AbstractAroundAdvisor {
    @Override
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        return pjp.proceed() + " the best";
    }
}
