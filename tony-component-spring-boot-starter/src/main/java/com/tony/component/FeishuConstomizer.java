/*
 *       Copyright© (2020).
 */
package com.tony.component;

import com.tony.component.template.FeishuTemplate;

/**
 * @author tony
 * @create 2021-07-29
 * @description:
 */
@FunctionalInterface
public interface FeishuConstomizer {
    void customize(FeishuTemplate feishuTemplate);
}
