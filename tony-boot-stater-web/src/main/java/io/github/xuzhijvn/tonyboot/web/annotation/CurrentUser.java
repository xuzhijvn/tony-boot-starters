package io.github.xuzhijvn.tonyboot.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tony
 * @ClassName: CurrentUser
 * @Description: 在Controller的方法参数中使用此注解，该方法在映射时会注入当前登录的User对象
 * @date 2019年5月18日 下午11:52:43
 */
@Target({ElementType.PARAMETER, ElementType.METHOD}) // 可用在方法或参数上
@Retention(RetentionPolicy.RUNTIME) // 运行时有效
public @interface CurrentUser {
}