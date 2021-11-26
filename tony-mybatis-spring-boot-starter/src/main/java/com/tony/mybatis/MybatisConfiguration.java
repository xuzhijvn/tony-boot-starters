/*
 *       Copyright© (2020).
 */
package com.tony.mybatis;


import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author tony
 * @create 2021-07-28
 * @description:
 */
@Configuration
public class MybatisConfiguration {

    @Bean
    public MybatisBeanPostProcessor mybatisBeanPostProcessor() {
        return new MybatisBeanPostProcessor();
    }

//    @Bean
//    public MybatisBeanDefinitionRegistryPostProcessor mybatisFactoryPostProcessor() {
//        return new MybatisBeanDefinitionRegistryPostProcessor();
//    }

//    @Bean
//    @Primary
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        sqlSessionFactoryBean.setPlugins(new Interceptor[]{new ResultSetPlugin()});
//        return sqlSessionFactoryBean.getObject();
//    }

}
