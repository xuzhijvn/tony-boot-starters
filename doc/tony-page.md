`tony-page`为应用提供无感知的分页功能，开发者不需要关注分页。

`tony-page`基于**[PageHelper](https://pagehelper.github.io/)**

## 1 引入依赖

```XML
<dependency>
    <groupId>com.tony.boot</groupId>
    <artifactId>tony-boot-starter-page</artifactId>
    <version>1.0.6-SNAPSHOT</version>
</dependency>
```

## 2 使用

### 2.1 极简使用

一个 `@Pagination` 注解搞定

默认 pageNo = 1, pageSize = 10

例如下面easyopen接口：

```Java
@Api(name = "get.user.list")
@Pagination
public Object userList(){
    return userMapper.listAll();
}
```

返回：

```JSON
{
    "data": {
        "data": [
           ...//省略
        ],
        "page_no": 1,
        "page_size": 10,
        "total": 71
    },
    "ret": 0
}
```

如果需要修改默认值，参考如下：

```http
http://localhost:8080/api/get.user.list?pageNo=2&pageSize=5
```

### 2.2 自定义分页参数

假设分页请求是：

```http
http://localhost:8080/api/get.user.list?myPageNo=2&myPageNo=5
```

设置@Pagination的pageNo和pageSize属性即可

```java
@Api(name = "get.user.list")
@Pagination(pageNo = "myPageNo", pageSize = "myPageSize")
public Object userList(){
    return userMapper.listAll();
}
```

### 2.3 完整响应

因为easyopen帮我们包装了`ret, msg, code`等响应，所以我们不需要关注。

但是，如果没有使用easyopen，可以设置 `pureMode = false` 返回完整响应

```java
@GetMapping("/getUserList")
@Pagination(pureMode = false)
public Object userList(){
    return userMapper.listAll();
}
```

返回：

```json
{
    "data": [
         ...//省略
    ],
    "code": 200,
    "msg": "操作成功",
    "total": 13,
    "pageNo": 1,
    "pageSize": 10
}
```

