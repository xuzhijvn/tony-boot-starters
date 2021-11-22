/*
 *       Copyright© (2020).
 */
package com.tony.dp.annotation;

import java.lang.annotation.*;

/**
 * @author tony
 * @create 2021-09-02
 * @description:
 */

@Target(ElementType.PARAMETER) // 可用在方法的参数上
@Retention(RetentionPolicy.RUNTIME) // 运行时有效
@Documented
public @interface DataConvert {
}
