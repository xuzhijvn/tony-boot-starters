/*
 *       Copyright© (2020).
 */
package com.tony.boot.dp.converter;

import com.tony.boot.dp.model.Permission;
import com.tony.boot.web.model.User;

import java.util.Set;

/**
 * @author tony
 * @create 2021-09-02
 * @description:
 */
public interface IUserConverter {

    /**
     * convert
     *
     * @param user
     * @return
     */
    Set<Permission> convert(User user);

}
