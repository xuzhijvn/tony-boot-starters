/*
 *       Copyright© (2020) Lalamove Co., Ltd.
 */
package com.tony.mybatis.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author tony121.xu@huolala.cn
 * @create 2021-07-01
 * @description: 职业
 */
@Getter
@AllArgsConstructor
public enum Job {

    CODER(8, "码农"),
    AS(11, "空姐"),
    TEACHER(12, "老师"),
    KILLER(13, "杀手");

    private int key;
    private String value;

}
