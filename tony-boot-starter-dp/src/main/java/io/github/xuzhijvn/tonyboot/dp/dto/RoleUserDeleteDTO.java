/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.dp.dto;

import lombok.Data;

/**
 * @author tony
 * @create 2021-09-07
 * @description:
 */
@Data
public class RoleUserDeleteDTO {

    private Long roleId;
    private Long userId;
    private String opUser;
}
