package com.tony.boot.data.permission.annotation.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tony
 * @ClassName: BeforeLog
 * @Description: 记录前置日志
 * @date 2019年4月27日 下午3:36:29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface BeforeLog {

    String name() default "";
}
