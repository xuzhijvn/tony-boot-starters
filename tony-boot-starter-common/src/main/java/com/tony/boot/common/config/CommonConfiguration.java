/*
 *       Copyright© (2020).
 */
package com.tony.boot.common.config;

import com.tony.boot.common.CommonFilter;
import com.tony.boot.common.CommonProperties;
import com.tony.boot.common.utils.spring.SpringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author tony
 * @create 2021-07-28
 * @description:
 */
@Configuration
@Import(SpringUtils.class)
@EnableConfigurationProperties(CommonProperties.class)
public class CommonConfiguration {

    @Bean(name = "tonyCommonFilterRegistrationBean")
    public FilterRegistrationBean filterRegistrationBean(CommonProperties commonProperties) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        CommonFilter filter = new CommonFilter();
        registration.setFilter(filter);
        String[] urlPatterns = Arrays.stream(commonProperties.getUrlPatterns().split(",")).map(String::trim).toArray(String[]::new);
        registration.addUrlPatterns(urlPatterns);
        registration.addInitParameter("current-user", Optional.ofNullable(commonProperties.getCurrentUser()).orElse(false).toString());
        registration.setName("tony-common-filter");
        registration.setOrder(2);
        return registration;
    }

}
