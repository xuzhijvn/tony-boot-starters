/*
 *       Copyright© (2020).
 */
package com.tony.boot.component.test;

import com.tony.boot.component.LarkCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tony
 * @create 2021-07-29
 * @description:
 */
@Configuration
public class CustomLarkConfig {

    @Bean
    public LarkCustomizer larkCustomizer() {
        return larkTemplate -> larkTemplate.getGlobalDefaultProperties().setPointcut("tony自定义Pointcut");
    }
}
