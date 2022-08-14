/*
 *       Copyright© (2020).
 */
package com.tony.boot.mybatis;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tony
 * @create 2021-07-28
 * @description:
 */
@Configuration
public class MybatisConfiguration {

    @Bean
    public MybatisBeanPostProcessor mybatisBeanPostProcessor() {
        return new MybatisBeanPostProcessor();
    }
}
