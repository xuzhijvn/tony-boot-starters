/*
*       Copyright© (2020).
*/
package com.tony.boot.component.advice;

import java.lang.annotation.*;

/**
* @author tony
* @create 2021-12-26
* @description:
*/

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(AfterReturnings.class)
public @interface AfterReturning {
    Class<?> adviceUsing() default Void.class;
}