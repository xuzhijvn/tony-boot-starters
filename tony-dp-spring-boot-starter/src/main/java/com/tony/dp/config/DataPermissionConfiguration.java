/*
 *       Copyright© (2020).
 */
package com.tony.dp.config;

import com.tony.dp.ApplicationContextHolder;
import com.tony.dp.DataPermissionFilter;
import com.tony.dp.DataPermissionProperties;
import com.tony.dp.converter.DefaultUserConverter;
import com.tony.dp.converter.IUserConverter;
import com.tony.dp.dao.service.DataPermissionService;
import com.tony.dp.dao.service.impl.DataPermissionServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

import java.util.Optional;

/**
 * @author tony
 * @create 2021-07-28
 * @description:
 */
@Configuration
@Import(ApplicationContextHolder.class)
@EnableConfigurationProperties(DataPermissionProperties.class)
public class DataPermissionConfiguration {


    @Bean(name = "dpFilterRegistrationBean")
    public FilterRegistrationBean filterRegistrationBean(DataPermissionProperties dataPermissionProperties) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        DataPermissionFilter filter = new DataPermissionFilter();
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.addInitParameter("current-user", Optional.ofNullable(dataPermissionProperties.getCurrentUser()).orElse(false).toString());
        registration.setName("data-permission-filter");
        registration.setOrder(2);
        return registration;
    }

    @Bean
    @DependsOn({"sysDpRoleMapper", "sysDpResourceMapper", "sysDpUserRoleMapper", "sysDpRoleResourceMapper"})
    @ConditionalOnMissingBean(DataPermissionService.class)
    public DataPermissionService dataPermissionService() {
        return new DataPermissionServiceImpl();
    }

    @Bean(name = "defaultUserConverter")
    @DependsOn({"sysDpResourceMapper"})
    public IUserConverter userConverter() {
        return new DefaultUserConverter();
    }
}
