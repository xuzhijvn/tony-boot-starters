/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.dp.handler;

import io.github.xuzhijvn.tonyboot.dp.model.Permission;

import java.util.Set;


/**
 * @author tony
 * @create 2021-09-03
 * @description:
 */
public interface IPermissionHandler {

    boolean hasPermission(Set<Permission> userPermissions, Permission dataPermissionStatus);
}
