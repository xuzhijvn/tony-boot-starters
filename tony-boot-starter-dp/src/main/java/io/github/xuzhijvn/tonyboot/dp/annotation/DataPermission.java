package io.github.xuzhijvn.tonyboot.dp.annotation;


import io.github.xuzhijvn.tonyboot.dp.handler.DefaultPermissionHandler;

import java.lang.annotation.*;

/**
 * 数据权限过滤
 *
 * @author tony
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(DataPermissions.class)
public @interface DataPermission {

    Class<?> dataConvertUsing() default Void.class;

    Class<?> permissionHandlerUsing() default DefaultPermissionHandler.class;

}
