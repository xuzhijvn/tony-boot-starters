`dp` 是 `数据权限（data permission）`的缩写

**需求**： `不同的用户` 对 `同一个数据`有 `不同的权限`

**举例1**： a, b用户都能查看数据 D，但是只有a用户能操作数据D

**举例2**： a, b用户都能操作数据D，a操作完成后流转到状态S1，b操作完成后流转到状态S2

## 1 引入依赖

```XML
<dependency>
    <groupId>com.tony.boot</groupId>
    <artifactId>tony-boot-starter-dp</artifactId>
    <version>${tony-starter.version}</version>
</dependency>
```

## 2 初始化表

```SQL
CREATE TABLE `sys_data_permission_resource` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `type` tinyint(6) NOT NULL DEFAULT '0' COMMENT '数据权限类型',
  `key` tinyint(6) NOT NULL DEFAULT '0' COMMENT '数据权限key',
  `value` varchar(100)  NOT NULL DEFAULT '' COMMENT '数据权限value',
  `op_user` varchar(32)  NOT NULL DEFAULT '' COMMENT '操作人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `ix_created_at` (`created_at`),
  KEY `ix_updated_at` (`updated_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';



CREATE TABLE `sys_data_permission_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `name` varchar(100)  NOT NULL DEFAULT '' COMMENT '角色名称',
  `op_user` varchar(32)  NOT NULL DEFAULT '' COMMENT '操作人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_name` (`name`),
  KEY `ix_created_at` (`created_at`),
  KEY `ix_updated_at` (`updated_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';



CREATE TABLE `sys_data_permission_role_resource` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `role_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '角色ID',
  `resource_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '资源ID',
  `op_user` varchar(32)  NOT NULL DEFAULT '' COMMENT '操作人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY uk_role_id_resource_id(`role_id`,`resource_id`),
  KEY `ix_created_at` (`created_at`),
  KEY `ix_updated_at` (`updated_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源表';



CREATE TABLE `sys_data_permission_user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '角色ID',
  `op_user` varchar(32)  NOT NULL DEFAULT '' COMMENT '操作人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY uk_role_id_user_id(`role_id`,`user_id`),
  KEY `ix_created_at` (`created_at`),
  KEY `ix_updated_at` (`updated_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';
```

## 3 使用

### 3.1 加数据权限校验

operate 接口将被进行数据权限校验

`permissionHandlerUsing` 不是必须的，不指定会使用默认 `DefaultPermissionHandler`

```java
@DataPermission(dataConvertUsing = CheckDataConverter.class, permissionHandlerUsing = CheckPermissionHandler.class)
public StickerCheck operate(@DataConvert CheckOperateRo checkOperateRo) {
    // do something
    return null;
}
```

### 3.2 实现 `IDataConverter<T>`  接口

```java
public class CheckDataConverter implements IDataConverter<CheckOperateRo> {
    @Override
    public Permission convert(CheckOperateRo ro) {
        // 在这里将ro -> 对应数据需要的权限
        return null;
    }
}
```

### 3.3 实现 `IPermissionHandler` 接口

```java
public class CheckPermissionHandler implements IPermissionHandler {
    @Override
    public boolean hasPermission(Set<Permission> userPermissions, Permission permission) {
        //自定义权限校验规则
        return true;
    }
}
```