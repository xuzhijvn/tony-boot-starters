package com.tony.data.permission.annotation.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tony
 * @ClassName: AroundLog
 * @Description:记录around日志
 * @date 2019年4月27日 下午3:36:29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AroundLog {

    String name() default "";
}
