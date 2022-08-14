package com.tony.boot.component.test;


import java.lang.annotation.*;

/**
 * 枚举key -> 枚举
 *
 * @author tony
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BindEnum {

    Class<?> enumClass() default Void.class;

    String keyField() default "";

    String key() default "";


}
