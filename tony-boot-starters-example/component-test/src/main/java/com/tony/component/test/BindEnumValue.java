package com.tony.component.test;



import java.lang.annotation.*;

/**
 * 枚举key -> 枚举value
 *
 * @author tony
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BindEnumValue {

    Class<?> enumClass() default Void.class;

    String keyField() default "";

    String key() default "";

    String valueField() default "";

}
