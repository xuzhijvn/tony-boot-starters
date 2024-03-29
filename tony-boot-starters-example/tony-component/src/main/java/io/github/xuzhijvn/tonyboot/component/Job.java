/*
 *       Copyright© (2020) Lalamove Co., Ltd.
 */
package io.github.xuzhijvn.tonyboot.component;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author tony
 * @create 2021-07-01
 * @description: 职业
 */
@Getter
@AllArgsConstructor
public enum Job {

    CODER(1001, "码农"),
    AS(1002, "空姐"),
    TEACHER(1003, "老师"),
    KILLER(1004, "杀手");

    private int key;
    private String value;

}
