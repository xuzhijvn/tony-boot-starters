/*
*       Copyright© (2020).
*/
package io.github.xuzhijvn.tonyboot.component.advice;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @author tony
* @create 2021-12-26
* @description:
*/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Befores {

    Before[] value();

}
