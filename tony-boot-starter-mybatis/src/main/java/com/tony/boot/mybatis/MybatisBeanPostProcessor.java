/*
 *       Copyright© (2020).
 */
package com.tony.boot.mybatis;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author tony
 * @create 2021-11-26
 * @description:
 */
public class MybatisBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("sqlSessionFactory".equals(beanName)) {
            if (bean instanceof MybatisSqlSessionFactoryBean) {
                MybatisSqlSessionFactoryBean sqlSessionFactory = (MybatisSqlSessionFactoryBean) bean;
                sqlSessionFactory.getConfiguration().addInterceptor(new ResultSetPlugin());
            } else {
                SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) bean;
                sqlSessionFactory.getConfiguration().addInterceptor(new ResultSetPlugin());
            }
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
