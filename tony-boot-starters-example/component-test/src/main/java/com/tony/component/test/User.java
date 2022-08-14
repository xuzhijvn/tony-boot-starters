/*
 *       Copyright© (2020).
 */
package com.tony.component.test;

import lombok.Data;

/**
 * @author tony
 * @create 2021-11-25
 * @description: 用户
 */

@Data
public class User {

    /**
     * 枚举key (存数据库)
     */
    private Integer job;

    /**
     * 枚举value (返给前端)
     */
    @BindEnumValue(key = "job", enumClass = Job.class, keyField = "key", valueField = "value")
    private String jobText;

    /**
     * 枚举
     */
    @BindEnum(key = "job", enumClass = Job.class)
    private Job jobEnum;

    public User(Integer job) {
        this.job = job;
        BindEnumHandler<User> handler = new BindEnumHandler<>(User.class);
        handler.handle(this);

    }

}
