/*
 *       Copyright© (2020).
 */
package com.tony.boot.dp.config;

import com.tony.boot.dp.ApplicationContextHolder;
import com.tony.boot.dp.DataPermissionAspect;
import com.tony.boot.dp.converter.DefaultUserConverter;
import com.tony.boot.dp.converter.IUserConverter;
import com.tony.boot.dp.dao.service.DataPermissionService;
import com.tony.boot.dp.dao.service.impl.DataPermissionServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

/**
 * @author tony
 * @create 2021-07-28
 * @description:
 */
@Configuration
@Import(ApplicationContextHolder.class)
//@EnableConfigurationProperties(DataPermissionProperties.class)
//@AutoConfigureAfter(WebMvcConfiguration.class)
public class DataPermissionConfiguration {

//    @Bean(name = "dpFilterRegistrationBean")
//    public FilterRegistrationBean filterRegistrationBean(DataPermissionProperties dataPermissionProperties) {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        DataPermissionFilter filter = new DataPermissionFilter();
//        registration.setFilter(filter);
//        String[] urlPatterns = Arrays.stream(dataPermissionProperties.getUrlPatterns().split(",")).map(String::trim).toArray(String[]::new);
//        registration.addUrlPatterns(urlPatterns);
//        registration.addInitParameter("current-user", Optional.ofNullable(dataPermissionProperties.getCurrentUser()).orElse(false).toString());
//        registration.setName("data-permission-filter");
//        registration.setOrder(2);
//        return registration;
//    }

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

    @Bean
    @DependsOn({"defaultUserConverter"})
    public DataPermissionAspect dataPermissionAspect(IUserConverter defaultUserConverter) {
        return new DataPermissionAspect(defaultUserConverter);
    }
}
