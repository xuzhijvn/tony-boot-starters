/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.component;

import com.tony.boot.component.annotation.ThreadLocalCleanAfter;
import org.springframework.stereotype.Service;

/**
 * @author tony
 * @create 2021-07-29
 * @description:
 */
@Service
public class HelloService2 {

    @ThreadLocalCleanAfter
    public void handle(){
        System.out.println("定时处理");
    }

    public void say11(){
        System.out.println("hello service2:");
        int a = 1/0;
    }
}
