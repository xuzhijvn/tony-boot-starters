package com.tony.mybatis.cache.autoconfigure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 启动入口
 * @author tony
 *
 */

@Configuration
@Import(J2CacheAutoConfiguration.class)
public class J2CacheMybatisAutoConfiguration {



}
