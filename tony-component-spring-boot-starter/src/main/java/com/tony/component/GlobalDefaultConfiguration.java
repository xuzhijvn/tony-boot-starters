/*
*       Copyright© (2020).
*/
package com.tony.component;

import com.tony.component.filter.ThreadLocalCacheFilter;
import com.tony.component.handler.FeishuAspect;
import com.tony.component.handler.GlobalDefaultExceptionHandler;
import com.tony.component.handler.ThreadLocalCacheAspect;
import com.tony.component.template.FeishuTemplate;
import com.tony.component.template.BeanTemplate;
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

    @Bean
    public BeanTemplate beanTool(){
        return new BeanTemplate();
    }

    @Bean
    @ConditionalOnMissingBean(FeishuTemplate.class)
    @ConditionalOnProperty(prefix = "risk.tony.component.feishu", name = "webhooks")
    public FeishuTemplate feishuApi(GlobalDefaultProperties globalDefaultProperties, ObjectProvider<FeishuConstomizer> customizers){
        FeishuTemplate feishuTemplate =  new FeishuTemplate(globalDefaultProperties);
        customizers.orderedStream().forEach(customizer -> customizer.customize(feishuTemplate));
        return new FeishuTemplate(globalDefaultProperties);
    }

    @Bean
    @ConditionalOnMissingBean(FeishuAspect.class)
    public FeishuAspect feishuAspect(){
        return new FeishuAspect();
    }

    @Bean(name = "globalAspectJExpressionPointcutAdvisor")
    @ConditionalOnProperty(prefix = "risk.tony.component", name = "pointcut")
    public AspectJExpressionPointcutAdvisor pointcutAdvisor(GlobalDefaultProperties globalDefaultProperties) {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression(globalDefaultProperties.getPointcut());
        advisor.setAdvice(new GlobalDefaultExceptionHandler(new FeishuTemplate(globalDefaultProperties)));
        return advisor;
    }

    @Bean(name = "threadLocalFilterRegistrationBean")
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        ThreadLocalCacheFilter filter = new ThreadLocalCacheFilter();
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("thread-local-cache-filter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    @ConditionalOnMissingBean(ThreadLocalCacheAspect.class)
    public ThreadLocalCacheAspect threadLocalCacheAspect(){
        return new ThreadLocalCacheAspect();
    }
}
