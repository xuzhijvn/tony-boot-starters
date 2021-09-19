/*
*       Copyright© (2020).
*/
package com.tony.dp.converter;

import com.tony.dp.model.Permission;
import com.tony.dp.model.User;

import java.util.Set;

/**
* @author tony
* @create 2021-09-02
* @description:
*/
public interface IUserConverter {

    /**
     * convert
     * @param user
     * @return
     */
    Set<Permission> convert(User user);

}
