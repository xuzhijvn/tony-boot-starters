package com.tony.boot.data.permission.annotation.auth;

import java.lang.annotation.*;

/**
 * 数据权限过滤
 *
 * @author tony
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataPermissions {

    /**
     * 需要做数据权限主表
     */
    DataPermission[] value();
}
