/*
 *       Copyright© (2020).
 */
package com.tony.boot.dp.dto;

import com.tony.boot.dp.dao.entity.SysDpUserRole;
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
