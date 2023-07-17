/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.dp.dao.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.xuzhijvn.tonyboot.dp.dao.entity.SysDpResource;
import io.github.xuzhijvn.tonyboot.dp.dao.entity.SysDpRole;
import io.github.xuzhijvn.tonyboot.dp.dao.entity.SysDpUserRole;
import io.github.xuzhijvn.tonyboot.dp.dto.*;

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

    boolean deleteRole(RoleDeleteDTO dto);

}
