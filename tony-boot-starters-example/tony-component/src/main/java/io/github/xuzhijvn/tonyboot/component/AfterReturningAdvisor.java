/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.component;

import com.tony.boot.component.advice.AbstractAfterReturningAdvisor;
import org.aspectj.lang.JoinPoint;

import java.util.Arrays;

/**
 * @author tony
 * @create 2021-12-26
 * @description:
 */
public class AfterReturningAdvisor extends AbstractAfterReturningAdvisor {
    @Override
    public void afterReturning(JoinPoint jp, Object retVal) {
        System.out.println("afterReturning 返回参数：" + retVal);
        System.out.println("afterReturning ***** 接收参数 *****");
        Arrays.stream(jp.getArgs()).parallel().forEach(System.out::println);
    }
}
