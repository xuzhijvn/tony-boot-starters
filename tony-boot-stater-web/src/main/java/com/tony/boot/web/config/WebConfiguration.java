/*
 *       Copyright© (2020).
 */
package com.tony.boot.web.config;

import com.tony.boot.tools.utils.spring.SpringUtils;
import com.tony.boot.web.WebFilter;
import com.tony.boot.web.WebProperties;
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
@EnableConfigurationProperties(WebProperties.class)
public class WebConfiguration {

    @Bean(name = "tonyFilterRegistrationBean")
    public FilterRegistrationBean filterRegistrationBean(WebProperties webProperties) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        WebFilter filter = new WebFilter();
        registration.setFilter(filter);
        String[] urlPatterns = Arrays.stream(webProperties.getUrlPatterns().split(",")).map(String::trim).toArray(String[]::new);
        registration.addUrlPatterns(urlPatterns);
        registration.addInitParameter("current-user", Optional.ofNullable(webProperties.getCurrentUser()).orElse(false).toString());
        registration.setName("tony-web-filter");
        registration.setOrder(2);
        return registration;
    }

}
