package com.tony.global.handle.annotation;

import com.tony.global.handle.GlobalDefaultConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tony
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(GlobalDefaultConfiguration.class)
public @interface EnableGlobalDispose {

}
