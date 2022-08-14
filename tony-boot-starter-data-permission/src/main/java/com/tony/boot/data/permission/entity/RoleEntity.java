/*
 *       Copyright© (2020).
 */
package com.tony.boot.data.permission.entity;

/**
 * @author tony
 * @create 2021-08-25
 * @description:
 */
public class RoleEntity {
    private Integer id;
    private String name;
    private String permission;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
