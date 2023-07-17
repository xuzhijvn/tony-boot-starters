/*
 *       Copyright© (2020) Lalamove Co., Ltd.
 */
package io.github.xuzhijvn.tonyboot.mybatis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author tony
 * @create 2021-07-01
 * @description: 职业
 */
@Getter
@AllArgsConstructor
@ToString
public enum Job {

    CODER(8, "码农"),
    AS(11, "空姐"),
    TEACHER(12, "老师"),
    KILLER(13, "杀手");

    private int key;
    private String value;

}
