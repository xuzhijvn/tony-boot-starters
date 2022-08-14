 /*
  *       Copyright© (2020) TONY Co., Ltd.
  */
 package com.tony.boot.notify;

 import com.tony.boot.notify.lark.Color;
 import com.tony.boot.notify.lark.Lark;
 import com.tony.boot.notify.lark.LarkTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
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

     @Autowired
     private LarkTemplate larkTemplate;

     @GetMapping(value = "say2")
     @Lark(title = "飞书注解测试", color = Color.GREEN)
     public void say2() {
         System.out.println("hello tony");
         larkTemplate.notify("hello tony");
     }


     @GetMapping(value = "say3")
     @Lark(title = "飞书注解测试", color = Color.GREEN)
     public void say3() {
         System.out.println("hello tony");
         int a = 1 / 0;
     }

 }
