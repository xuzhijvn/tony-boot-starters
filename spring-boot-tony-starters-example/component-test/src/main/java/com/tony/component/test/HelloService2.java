/*
 *       Copyright© (2020).
 */
package com.tony.component.test;

import com.tony.component.annotation.ThreadLocalCache;
import com.tony.component.annotation.ThreadLocalCleaner;
import org.springframework.stereotype.Service;

/**
 * @author tony
 * @create 2021-07-29
 * @description:
 */
@Service
public class HelloService2 {

    @ThreadLocalCleaner
    public void handle(){
        System.out.println("定时处理");
    }
}
