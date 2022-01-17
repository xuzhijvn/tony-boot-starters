/*
 *       Copyright© (2020).
 */
package com.example.tony.mp;


import com.example.tony.mp.entity.User;
import com.example.tony.mp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


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
    @GetMapping("/saveIgnore")
    public Object saveIgnore(@RequestParam String email) {
        User user = new User();
        user.setUsername("tony");
        user.setEmail(email);
        return userService.saveIgnore(user);
    }

    @GetMapping("/saveIgnoreBatch")
    public Object saveIgnoreBatch(@RequestParam String name, @RequestParam String email) {

        User user1 = new User();
        user1.setUsername("tony");
        user1.setEmail("783175223@qq.com");

        User user2 = new User();
        user2.setUsername(name);
        user2.setEmail(email);

        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);

        return userService.saveIgnoreBatch(list);
    }

    @GetMapping("/replace")
    public Object saveIgnoreBatch(@RequestParam String email) {

        User user = new User();
        user.setUsername("tony");
        user.setEmail(email);
        return userService.replace(user);
    }

}