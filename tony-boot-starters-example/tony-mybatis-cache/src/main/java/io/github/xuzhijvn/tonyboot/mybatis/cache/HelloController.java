 /*
  *       Copyright© (2020) TONY Co., Ltd.
  */
 package io.github.xuzhijvn.tonyboot.mybatis.cache;


 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RestController;

 /**
  * @author tony老师
  * @create 2021-06-02
  * @description:
  */

 @RestController
 @RequestMapping(value = "hello")
 public class HelloController {

     @GetMapping(value = "say2")
     public void say2() {
         int a = 1 / 0;
     }

 }
