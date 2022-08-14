/*
 *       Copyright© (2020).
 */
package com.tony.component;

import com.tony.component.template.LarkTemplate;

/**
 * @author tony
 * @create 2021-07-29
 * @description:
 */
@FunctionalInterface
public interface LarkCustomizer {
    void customize(LarkTemplate larkTemplate);
}
