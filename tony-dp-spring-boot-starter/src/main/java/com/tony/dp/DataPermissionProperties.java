/*
 *       Copyright© (2020).
 */
package com.tony.dp;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tony
 * @create 2021-07-28
 * @description:
 */
@ConfigurationProperties(prefix = "risk.tony.dp")
public class DataPermissionProperties {

    private Boolean currentUser;

    public Boolean getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Boolean currentUser) {
        this.currentUser = currentUser;
    }

}
