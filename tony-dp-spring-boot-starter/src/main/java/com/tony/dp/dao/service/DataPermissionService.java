/*
*       Copyright© (2020).
*/
package com.tony.dp.dao.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tony.dp.dao.entity.SysDpResource;
import com.tony.dp.dao.entity.SysDpRole;
import com.tony.dp.dao.entity.SysDpUserRole;
import com.tony.dp.dto.*;

import java.util.List;

/**
* @author tony
* @create 2021-09-07
* @description:
*/
public interface DataPermissionService {


    IPage<SysDpRole> rolePages(RolePagesDTO dto);


    List<SysDpResource> getResources();

    boolean addRole(RoleAddDTO dto);

    boolean updateRole(RoleUpdateDTO dto);

    List<SysDpUserRole> getRoleUser(RoleUserGetDTO dto);

    boolean deleteRoleUser(RoleUserDeleteDTO dto);

    boolean addRoleUser(RoleUserAddDTO dto);

}
