/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.dp.handler;

import io.github.xuzhijvn.tonyboot.dp.model.Permission;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author tony
 * @create 2021-09-03
 * @description:
 */
@Component
public class DefaultPermissionHandler implements IPermissionHandler {

    @Override
    public boolean hasPermission(Set<Permission> userPermissions, Permission permission) {
        return userPermissions != null && userPermissions.contains(permission);
    }
}
