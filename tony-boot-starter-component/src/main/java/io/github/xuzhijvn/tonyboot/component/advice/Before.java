/*
*       Copyright© (2020).
*/
package io.github.xuzhijvn.tonyboot.component.advice;

import java.lang.annotation.*;

/**
* @author tony
* @create 2021-12-26
* @description:
*/

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Befores.class)
public @interface Before {
    Class<?> adviceUsing() default Void.class;
}
