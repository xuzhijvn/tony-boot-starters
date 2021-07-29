package com.tony.component.annotation;

/**
 * @author tony
 * @create 2021-05-16
 * Description:
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ThreadLocalCache {

    /**
     * 缓存key，支持SPEL表达式
     * @return
     */
    String key() default "";

}