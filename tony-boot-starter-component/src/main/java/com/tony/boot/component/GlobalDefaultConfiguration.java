/*
 *       Copyright© (2020).
 */
package com.tony.boot.component;

import cn.hutool.core.util.ClassUtil;
import com.tony.boot.common.BootUtil;
import com.tony.boot.common.utils.spring.SpringUtils;
import com.tony.boot.component.advice.AdvisorAspect;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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

    @Bean(name = "tonyGlobalAspectJExpressionPointcutAdvisor")
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

    @Bean(name = "tonyThreadLocalFilterRegistrationBean")
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