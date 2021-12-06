/*
 *       Copyright© (2020) TONY Co., Ltd.
 */
package com.tony.component.annotation;

import com.tony.component.constant.Color;

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
    String titleName() default "";

    Color color() default Color.BLUE;
}