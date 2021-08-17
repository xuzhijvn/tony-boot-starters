package com.tony.mybatis.cache.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 启动入口
 * @author tony
 *
 */

@Configuration
@Import(J2CacheAutoConfiguration.class)
@ConditionalOnProperty(value = "j2cache.l2-cache-open", havingValue = "true")
public class J2CacheMybatisAutoConfiguration {

    private String test;


}
