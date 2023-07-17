## 1. 目前版本的能力

1. 捕获异常并飞书通知
2. 线程变量注解

## 2. 使用方法

### 2.1 引入依赖

```xml
<dependency>
   <groupId>io.github.xuzhijvn</groupId>
   <artifactId>tony-boot-starter-component</artifactId>
   <version>1.0.4-SNAPSHOT</version>
</dependency>
```

### 2. 2 Yaml配置示例

```yaml
risk:
  tony:
    component:
      pointcut: execution(* cn.tony.component.test..*.*(..))
      lark:
        webhooks: https://open.feishu.cn/open-apis/bot/v2/hook/xxxx-xxxxx
        kibana:
          url: https://kibana域名/app/kibana#/discover
          sname: xl-risk-jsticker-svc
          stype: javadaemon
```

> pointcut：要捕获异常的包范围

## 3. 使用方法

### 2.1 飞书通知

1. 捕获全局异常并飞书通知

引入上面依赖，即拥有这个功能，⚠️⚠️**什么都不用做**⚠️⚠️

2. 发送异常自定义飞书通知

```java
@Lark(titleName = "短信发送失败", color = "green")
public void send(){
    //dosomething
}
```

3. 方法内部使用飞书通知

```java
@Resource
LarkTemplate larkTemplate;

public boolean monthlyPay() {
    try {
        //do something
    } catch (Exception e) {
        log.error("失败了");
        larkTemplate.send("每月车贴奖励支付失败", stickerContract, e.toString(), "red");
    }
    return true;
}
```

### 2.2 线程变量

一个注解即可

```Java
@ThreadLocalCache
public DriverBO getDriverBO(long driverId) {
    //do something
}
```

- 如果是web请求，请求链路结束自动清理ThreadLocal（ThreadLocalCacheFilter会执行清理动作）
- 如果是定时任务，没有Filter，则需要指定在哪个方法执行结束之后清理ThreadLocal，可通过

`@ThreadLocalCleaner` 指定：

```TypeScript
@ThreadLocalCleaner
public void handle(){
    System.out.println("do something");
}
```

⚠️ 如果业务中使用了线程池，请慎用 ⚠️  

因为，如果执行 `@ThreadLocalCache注解的方法` 和 `执行 @ThreadLocalCleaner注解的方法` 不是同一个线程，会导致OOM
