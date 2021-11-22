/*
 *       Copyright© (2020).
 */
package com.tony.page;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tony
 * @create 2021-10-19
 * @description:
 */

@Configuration
public class PageConfiguration {

    @Bean
    public PaginationAspect paginationAspect() {
        return new PaginationAspect();
    }
}
