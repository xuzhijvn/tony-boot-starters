/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.notify;


import io.github.xuzhijvn.tonyboot.notify.lark.LarkTemplate;

/**
 * @author tony
 * @create 2021-07-29
 * @description:
 */
@FunctionalInterface
public interface LarkCustomizer {
    void customize(LarkTemplate larkTemplate);
}
