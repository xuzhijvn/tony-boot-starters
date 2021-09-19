/*
 *       Copyright© (2020).
 */
package com.tony.dp.converter;


import com.tony.dp.ApplicationContextHolder;
import com.tony.dp.dao.entity.SysDpResource;
import com.tony.dp.dao.mapper.SysDpResourceMapper;
import com.tony.dp.model.Permission;
import com.tony.dp.model.User;

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
