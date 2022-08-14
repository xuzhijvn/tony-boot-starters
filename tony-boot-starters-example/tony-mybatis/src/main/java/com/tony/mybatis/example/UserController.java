/*
 *       Copyright© (2020).
 */
package com.tony.mybatis.example;


import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.tony.mybatis.example.service.UserService;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author tony
 * @create 2021-10-19
 * @description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 列出所有用户
     *
     * @return
     */
    @GetMapping("/list")
    public Object list() {
        return userService.list();
    }

    @GetMapping("/get")
    public Object get() {
        return userService.getById(1);
    }

}