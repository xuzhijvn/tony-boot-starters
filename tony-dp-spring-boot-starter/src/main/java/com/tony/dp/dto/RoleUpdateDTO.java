/*
 *       Copyright© (2020).
 */
package com.tony.dp.dto;

import lombok.Data;

import java.util.List;

/**
 * @author tony
 * @create 2021-09-07
 * @description:
 */
@Data
public class RoleUpdateDTO {
    private Long roleId;
    private String roleName;
    private List<Long> resourceIds;
    private String opUser;
}
