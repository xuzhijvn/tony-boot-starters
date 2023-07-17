/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.component;

import com.tony.boot.component.advice.*;
import com.tony.boot.component.annotation.ThreadLocalCache;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tony
 * @create 2021-07-29
 * @description:
 */
@Service
public class HelloService {

    @Resource
    private HelloService2 helloService2;

    @ThreadLocalCache
    public String say(String name) {
        System.out.println("hello " + name);
        return "123";
    }

    @ThreadLocalCache
    public String say9(String name) {
        throw new RuntimeException("测试Undeclared");
    }

    public String say8() {
        System.out.println("hello say8");
        throw new RuntimeException("rrrr");
    }

    public void say2(String name) {
        int a = 1 / 0;
    }


    /**
     * 注解申明顺序决定执行顺序
     *
     * @param name
     * @return
     */
    @Around(adviceUsing = AroundAdvisor.class)
    @Before(adviceUsing = AllAdvisor.class)
    @Around(adviceUsing = AllAdvisor.class)
    @After(adviceUsing = AllAdvisor.class)
    @AfterReturning(adviceUsing = AllAdvisor.class)
    @AfterThrowing(adviceUsing = AllAdvisor.class)
    @AfterReturning(adviceUsing = AfterReturningAdvisor.class)
    public String say11(String name) {
        System.out.println("测试: " + name);
        return "hello " + name;
    }

    public void say11() {
        System.out.println("hello service:");
        helloService2.say11();
    }
}
