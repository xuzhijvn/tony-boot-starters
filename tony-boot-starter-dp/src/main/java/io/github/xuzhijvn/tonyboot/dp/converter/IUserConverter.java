/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.dp.converter;

import io.github.xuzhijvn.tonyboot.dp.model.Permission;
import io.github.xuzhijvn.tonyboot.web.model.User;

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
