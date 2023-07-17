/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.web.model;


/**
 * @author tony
 * @create 2021-08-31
 * @description: 当前用户
 */

public class User {

    private String name;
    private String id;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
