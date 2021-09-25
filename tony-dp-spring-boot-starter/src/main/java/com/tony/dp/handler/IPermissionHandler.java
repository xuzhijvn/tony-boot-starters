/*
*       Copyright© (2020).
*/
package com.tony.dp.handler;

import com.tony.dp.model.Permission;

import java.util.Set;


/**
* @author tony
* @create 2021-09-03
* @description:
*/
public interface IPermissionHandler {

    boolean hasPermission(Set<Permission> userPermissions, Permission dataPermissionStatus);
}
