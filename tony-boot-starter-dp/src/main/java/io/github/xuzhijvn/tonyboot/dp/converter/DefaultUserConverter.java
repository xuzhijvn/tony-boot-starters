/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.dp.converter;


import io.github.xuzhijvn.tonyboot.dp.ApplicationContextHolder;
import io.github.xuzhijvn.tonyboot.dp.dao.entity.SysDpResource;
import io.github.xuzhijvn.tonyboot.dp.dao.mapper.SysDpResourceMapper;
import io.github.xuzhijvn.tonyboot.dp.model.Permission;
import io.github.xuzhijvn.tonyboot.web.model.User;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author tony
 * @create 2021-09-02
 * @description:
 */
public class DefaultUserConverter implements IUserConverter {

    @Override
    public Set<Permission> convert(User user) {
        Set<SysDpResource> resources = ApplicationContextHolder.getBean(SysDpResourceMapper.class).getResourceByUserId(Long.parseLong(user.getId()));
        return resources.stream().map(e -> {
            Permission permission = new Permission();
            permission.setType(e.getType());
            permission.setKey(e.getKey());
            return permission;
        }).collect(Collectors.toSet());
    }
}
