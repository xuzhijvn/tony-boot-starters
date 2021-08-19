 /*
  *       Copyright© (2020) TONY Co., Ltd.
  */
 package com.tony.component.test;

 import com.tony.component.annotation.Feishu;
 import com.tony.component.annotation.ThreadLocalCache;
 import com.tony.component.template.FeishuTemplate;
 import org.slf4j.MDC;
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
     HelloService helloService;

     @Autowired
     private FeishuTemplate feishuTemplate;

     @GetMapping(value = "say")
     public void say() {
         feishuTemplate.send("测试", "测试内容", "测试异常");
     }

     @GetMapping(value = "say2")
     public void say2() {
         int a = 1 / 0;
     }

     @GetMapping(value = "say3")
     @Feishu(titleName = "飞书注解测试", color = "green")
     public void say3() {
         String hllTid = MDC.get("HLL_TID");
         String TRACE_ID = MDC.get("TRACE_ID");
         int a = 1 / 0;
     }

     @GetMapping(value = "say4")
     public String say4() {
         System.out.println("say4");
         String res = "";
         for (int i = 0; i < 100; i++) {
             res = helloService.say("tony");
             mySay();
             mySay2();

         }
         return res;
     }

     @ThreadLocalCache
     private String mySay(){
         System.out.println("mySay");
         return "123";
     }

     @ThreadLocalCache
     public String mySay2(){
         System.out.println("mySay2");
         return "123";
     }

     @GetMapping(value = "say5")
     public String say5(){
         throw new BizException("hello biz");
     }

     @GetMapping(value = "say6")
     public String say6(){
         throw new BizException2("hello biz2");
     }

 }
