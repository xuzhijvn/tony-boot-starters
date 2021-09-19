/*
*       Copyright© (2020).
*/
package com.tony.component.test;

import com.tony.component.FeishuConstomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* @author tony
* @create 2021-07-29
* @description:
*/
@Configuration
public class CustomFeishuConfig {

    @Bean
    public FeishuConstomizer feishuConstomizer(){
        return feishuTemplate -> feishuTemplate.getGlobalDefaultProperties().setPointcut("tony自定义Pointcut");
    }
}
