/*
 *       Copyright© (2020).
 */
package com.tony.boot.notify;


import com.tony.boot.notify.lark.LarkTemplate;

/**
 * @author tony
 * @create 2021-07-29
 * @description:
 */
@FunctionalInterface
public interface LarkCustomizer {
    void customize(LarkTemplate larkTemplate);
}
