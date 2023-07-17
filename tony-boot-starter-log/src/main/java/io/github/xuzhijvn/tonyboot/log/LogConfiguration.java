/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.log;


import io.github.xuzhijvn.tonyboot.log.dao.service.ISysOperLogService;
import io.github.xuzhijvn.tonyboot.log.dao.service.SysOperLogServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;


/**
 * @author tony
 * @create 2021-07-28
 * @description:
 */
@Configuration
@MapperScan("com.tony.log.dao.mapper")
public class LogConfiguration {

    @Bean(name = "sysOperLogService")
    @DependsOn({"sysOperLogMapper"})
    public ISysOperLogService sysOperLogService() {
        return new SysOperLogServiceImpl();
    }

    @Bean
    @DependsOn({"sysOperLogService"})
    public LogAspect logAspect() {
        return new LogAspect();
    }
}
