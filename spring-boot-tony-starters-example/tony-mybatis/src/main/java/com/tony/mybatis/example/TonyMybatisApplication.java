package com.tony.mybatis.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.tony.mybatis.example.mapper"})
public class TonyMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(TonyMybatisApplication.class, args);
    }

}
