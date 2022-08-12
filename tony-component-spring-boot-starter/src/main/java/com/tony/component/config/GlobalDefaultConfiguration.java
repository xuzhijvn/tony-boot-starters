/*
 *       Copyright© (2020).
 */
package com.tony.component.config;

import cn.hutool.core.util.ClassUtil;
import com.tony.common.BootUtil;
import com.tony.common.utils.spring.SpringUtils;
import com.tony.component.*;
import com.tony.component.advice.AdvisorAspect;
import com.tony.component.GlobalExceptionMethodInterceptor;
import com.tony.component.ThreadLocalCacheAspect;
import com.tony.component.template.LarkTemplate;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
    @ConditionalOnProperty(prefix = "tony.component.ex-handle.lark", name = "webhooks")
    public LarkTemplate larkTemplate(GlobalDefaultProperties globalDefaultProperties, ObjectProvider<LarkCustomizer> customizers) {
        LarkTemplate larkTemplate = new LarkTemplate(globalDefaultProperties);
        customizers.orderedStream().forEach(customizer -> customizer.customize(larkTemplate));
        return new LarkTemplate(globalDefaultProperties);
    }

//    @Bean
//    @ConditionalOnMissingBean(ExceptionCatchAspect.class)
//    public ExceptionCatchAspect larkAspect() {
//        return new ExceptionCatchAspect();
//    }

    @Bean(name = "globalAspectJExpressionPointcutAdvisor")
    //@ConditionalOnProperty(prefix = "tony.component.ex-handle", name = "pointcut")
    @Order
    public AspectJExpressionPointcutAdvisor pointcutAdvisor(GlobalDefaultProperties globalDefaultProperties) {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        String pointcut = globalDefaultProperties.getPointcut();
        if (StringUtils.isEmpty(pointcut)) {
            String mainClass = Objects.requireNonNull(BootUtil.getMainClass()).getName();
            String pk = mainClass.substring(0, mainClass.lastIndexOf("."));
            pointcut = "execution(* " + pk + "..*.*(..))";
        }
        advisor.setExpression(pointcut);
        Set<Class<?>> classes = ClassUtil.scanPackageBySuper("", ExceptionHandler.class);
        Set<ExceptionHandler> exceptionHandlers = classes.stream()
                .filter(clazz -> !ClassUtil.isAbstract(clazz))
                .map(clazz -> {
                    ExceptionHandler exceptionHandler;
                    try {
                        exceptionHandler = (ExceptionHandler) SpringUtils.getInstance(clazz);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return exceptionHandler;
                }).collect(Collectors.toSet());
        advisor.setAdvice(new GlobalExceptionMethodInterceptor(exceptionHandlers, globalDefaultProperties));
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
