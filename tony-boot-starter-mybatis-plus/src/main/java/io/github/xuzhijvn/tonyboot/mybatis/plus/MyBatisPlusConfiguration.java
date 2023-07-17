/*
*       Copyright© (2020).
*/
package io.github.xuzhijvn.tonyboot.mybatis.plus;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* @author tony
* @create 2022-01-17
* @description:
*/
@Configuration
public class MyBatisPlusConfiguration {

    @Bean
    public ISqlInjector iSqlInjector() {
        return new TonySqlInjector();
    }
}
