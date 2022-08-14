mybatis的二级缓存快速启动器，目前实现了redis和j2cache，未来将提供更多缓存实现

## 1 使用方式

### 1.1 导入依赖

```XML
<dependency>
    <groupId>com.tony</groupId>
    <artifactId>tony-boot-starter-mybatis-cache</artifactId>
    <version>${tony-starter.version}</version>
</dependency>
```

### 1.2 打开mybatis二级缓存开关

```XML
<setting name="cacheEnabled" value="true"/>
```

### 1.3 指定缓存方式

```Java
@CacheNamespace(implementation = RedisCacheAdapter.class, eviction = RedisCacheAdapter.class)
public interface UserMapper extends BaseMapper<User> {

}
```