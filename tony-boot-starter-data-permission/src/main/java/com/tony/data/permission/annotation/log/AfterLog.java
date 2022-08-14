package com.tony.data.permission.annotation.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tony
 * @ClassName: AfterLog
 * @Description: 记录后置日志
 * @date 2019年4月27日 下午3:36:29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AfterLog {

    String name() default "";
}
