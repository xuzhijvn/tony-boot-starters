/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.dp.dto;

import io.github.xuzhijvn.tonyboot.dp.dao.entity.SysDpUserRole;
import lombok.Data;

import java.util.List;

/**
 * @author tony
 * @create 2021-09-07
 * @description:
 */
@Data
public class RoleUserAddDTO {

    private Long roleId;
    List<SysDpUserRole> users;
    private String opUser;
}
