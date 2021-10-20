/*
 *       Copyright© (2020).
 */
package com.tony.page;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分页注解
 *
 * @author tony
 * @create 2021-10-19
 * @description:
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Pagination {

    // 第几页的请求参数名称 通过获取参数名称获取真正的pageNo
    String pageNo() default "pageNo";

    // 分页大小的请求参数名称
    String pageSize() default "pageSize";

    boolean pureMode() default true;

}