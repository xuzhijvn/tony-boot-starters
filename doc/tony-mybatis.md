tony-mybatis是对mybatis的增强。

## 1 使用方式

### 1.1 导入依赖

```XML
<dependency>
    <groupId>com.tony</groupId>
    <artifactId>tony-boot-starter-mybatis</artifactId>
    <version>${tony-starter.version}</version>
</dependency>
```

## 2 功能

### 2.1 枚举注解

例如，你只想在数据库里面存jobid，但是查询的时候自动注入jobid对应的 “岗位名称” 可以使用 `@BindEnumValue` 注解，或者自动注解jobid对应的岗位枚举可以使用 `@BindEnum`

```Java
/**
 * 系统用户
 *
 * @TableName user
 */
@TableName(value = "user")
public class User implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 岗位名称
     */
    private Long jobId;
    /**
     * 枚举value (返给前端)
     */
    @TableField(exist = false)
    @BindEnumValue(key = "jobId", enumClass = Job.class)
    private String jobText;
    /**
     * 枚举
     */
    @TableField(exist = false)
    @BindEnum(key = "jobId", enumClass = Job.class)
    private Job jobEnum;

}
```

其中Job枚举如下：

```java
@Getter
@AllArgsConstructor
@ToString
public enum Job {

    CODER(8, "码农"),
    AS(11, "空姐"),
    TEACHER(12, "老师"),
    KILLER(13, "杀手");

    private int key;
    private String value;

}
```

