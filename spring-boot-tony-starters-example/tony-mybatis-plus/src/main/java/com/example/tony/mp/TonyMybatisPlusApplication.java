package com.example.tony.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = {"com.example.tony.mp.mapper"})
@SpringBootApplication
public class TonyMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(TonyMybatisPlusApplication.class, args);
    }

}
