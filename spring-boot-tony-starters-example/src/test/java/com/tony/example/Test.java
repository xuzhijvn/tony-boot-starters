 /*
  *       Copyright© (2020).
  */
 package com.tony.example;

 import java.util.Optional;

 /**
  * @author tony
  * @create 2021-07-29
  * @description:
  */
 public class Test {
     @org.junit.jupiter.api.Test
     public void testNull() {
         String xx = null;
         String a = Optional.ofNullable(xx).orElse("''");
         System.out.println(a);
     }
 }
