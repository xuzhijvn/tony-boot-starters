/*
 *       Copyright© (2020).
 */
package com.tony.component.example;

import com.tony.component.annotation.ThreadLocalCache;
import org.springframework.stereotype.Service;

/**
 * @author tony
 * @create 2021-07-29
 * @description:
 */
@Service
public class HelloService {

    @ThreadLocalCache
    public String say(String name) {
        System.out.println("hello " + name);
        return "123";
    }
}
