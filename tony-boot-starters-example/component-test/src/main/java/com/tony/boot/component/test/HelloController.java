 /*
  *       Copyright© (2020) TONY Co., Ltd.
  */
 package com.tony.boot.component.test;

 import com.tony.boot.component.annotation.ThreadLocalCache;
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
     HelloService2 helloService2;

//     @Autowired
//     private LarkTemplate larkTemplate;

     @GetMapping(value = "say")
     public void say() {
         //larkTemplate.sendAsync("测试", new RuntimeException("ddd"), "测试异常");
     }

     @GetMapping(value = "say2")
     public void say2(String name, Integer age) {
         helloService.say2(name);
     }

     @GetMapping(value = "say3")
     //@ExceptionCatch(title = "飞书注解测试", color = Color.GREEN)
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
             helloService2.handle();
         }
         return res;
     }

     @ThreadLocalCache
     private String mySay() {
         System.out.println("mySay");
         return "123";
     }

     @ThreadLocalCache
     public String mySay2() {
         System.out.println("mySay2");
         return "123";
     }

     @GetMapping(value = "say5")
     public String say5() {
         throw new BizException("hello biz");
     }

     @GetMapping(value = "say6")
     public String say6() {
         throw new BizException2("hello biz2");
     }

     @GetMapping(value = "say8")
     public String say8() {
         return helloService.say8();
     }

     @GetMapping(value = "say9")
     public String say9() {
         return helloService.say9("ff");
     }

     @GetMapping(value = "say10")
     public String say10() {
         return helloService.say11("tony");
     }

//     @GetMapping(value = "say7")
//     @CurrentUser
//     public String say7(@CurrentUser User user1){
//         User user = (User) TonyContext.get(User.class);
//         return user.toString();
//     }

     @GetMapping(value = "say11")
     public void say11() {
          helloService.say11();
     }

 }
