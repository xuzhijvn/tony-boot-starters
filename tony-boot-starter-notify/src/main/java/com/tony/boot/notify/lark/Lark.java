/*
 *       Copyright© (2020) TONY Co., Ltd.
 */
package com.tony.boot.notify.lark;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tony
 * @create 2021-07-25
 * @description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Lark {

    Class<?> handleUsing() default Void.class;

    Color color() default Color.BLUE;

    String title() default "";

}