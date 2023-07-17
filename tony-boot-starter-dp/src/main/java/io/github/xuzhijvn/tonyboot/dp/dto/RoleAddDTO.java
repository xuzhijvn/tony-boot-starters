/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.dp.dto;

import lombok.Data;

import java.util.List;

/**
 * @author tony
 * @create 2021-09-07
 * @description:
 */
@Data
public class RoleAddDTO {
    private String roleName;
    private String opUser;
    private List<Long> resourceIds;
}
