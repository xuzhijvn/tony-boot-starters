/*
 *       Copyright© (2020).
 */
package com.tony.boot.dp.model;

import java.util.Objects;

/**
 * @author tony
 * @create 2021-09-02
 * @description:
 */
public class Permission {

    private Integer type;
    private Integer key;
    private String value;

    public Permission() {
    }

    public Permission(Integer type, Integer key) {
        this.type = type;
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "type=" + type +
                ", key=" + key +
                '}';
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Permission permission = (Permission) o;
        return Objects.equals(type, permission.type) && Objects.equals(key, permission.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, key);
    }
}
