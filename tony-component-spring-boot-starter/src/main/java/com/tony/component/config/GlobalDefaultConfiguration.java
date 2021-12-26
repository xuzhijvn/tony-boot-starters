/*
 *       Copyright© (2020).
 */
package com.tony.component.config;

import com.tony.component.GlobalDefaultProperties;
import com.tony.component.LarkCustomizer;
import com.tony.component.advice.AdvisorAspect;
import com.tony.component.ThreadLocalCacheFilter;
import com.tony.component.handler.GlobalDefaultExceptionHandler;
import com.tony.component.handler.LarkAspect;
import com.tony.component.handler.ThreadLocalCacheAspect;
import com.tony.component.template.LarkTemplate;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tony
 * @create 2021-07-28
 * @description:
 */
@Configuration
@EnableConfigurationProperties(GlobalDefaultProperties.class)
public class GlobalDefaultConfiguration {

//    @Bean
//    public GlobalDefaultExceptionHandler globalDefaultExceptionHandler(GlobalDefaultProperties globalDefaultProperties) {
//        return new GlobalDefaultExceptionHandler();
//    }

//    @Bean
//    public BeanUtil beanTemplate() {
//        return new BeanUtil();
//    }

    @Bean
    @ConditionalOnMissingBean(LarkTemplate.class)
    @ConditionalOnProperty(prefix = "risk.tony.component.lark", name = "webhooks")
    public LarkTemplate larkApi(GlobalDefaultProperties globalDefaultProperties, ObjectProvider<LarkCustomizer> customizers) {
        LarkTemplate larkTemplate = new LarkTemplate(globalDefaultProperties);
        customizers.orderedStream().forEach(customizer -> customizer.customize(larkTemplate));
        return new LarkTemplate(globalDefaultProperties);
    }

    @Bean
    @ConditionalOnMissingBean(LarkAspect.class)
    public LarkAspect larkAspect() {
        return new LarkAspect();
    }

    @Bean(name = "globalAspectJExpressionPointcutAdvisor")
    @ConditionalOnProperty(prefix = "risk.tony.component", name = "pointcut")
    public AspectJExpressionPointcutAdvisor pointcutAdvisor(GlobalDefaultProperties globalDefaultProperties) {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression(globalDefaultProperties.getPointcut());
        advisor.setAdvice(new GlobalDefaultExceptionHandler(new LarkTemplate(globalDefaultProperties)));
        return advisor;
    }

    @Bean(name = "threadLocalFilterRegistrationBean")
    public FilterRegistrationBean filterRegistrationBean(GlobalDefaultProperties globalDefaultProperties) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        ThreadLocalCacheFilter filter = new ThreadLocalCacheFilter();
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        //registration.addInitParameter("current-user", Optional.ofNullable(globalDefaultProperties.getCurrentUser()).orElse(false).toString());
        registration.setName("thread-local-cache-filter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    @ConditionalOnMissingBean(ThreadLocalCacheAspect.class)
    public ThreadLocalCacheAspect threadLocalCacheAspect() {
        return new ThreadLocalCacheAspect();
    }

    @Bean
    @ConditionalOnMissingBean(AdvisorAspect.class)
    public AdvisorAspect advisorAspect() {
        return new AdvisorAspect();
    }
}
