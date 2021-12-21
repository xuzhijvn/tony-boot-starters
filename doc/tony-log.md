对于一些敏感操作，我们希望对其记录，便于日后审计等。

## 1 引入依赖

```XML
<dependency>
    <groupId>com.tony</groupId>
    <artifactId>tony-log-spring-boot-starter</artifactId>
    <version>${tony-starter.version}</version>
</dependency>
```

## 2 初始化表

```SQL
create table `sys_oper_log` (
  `id`                bigint(20)      not null auto_increment    comment '日志主键',
  `title`             varchar(50)     NOT NULL default ''                 comment '模块标题',
  `business_type`     tinyint(4)      NOT NULL default 0                  comment '业务类型（0其它 1新增 2修改 3删除）',
  `method`            varchar(100)    NOT NULL default ''                 comment '方法名称',
  `request_method`    varchar(10)     NOT NULL default ''                 comment '请求方式',
  `operator_type`     tinyint(4)      NOT NULL default 0                  comment '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_url`          varchar(255)    NOT NULL default ''                 comment '请求URL',
  `oper_ip`           varchar(128)    NOT NULL default ''                 comment '主机地址',
  `oper_location`     varchar(255)    NOT NULL default ''                 comment '操作地点',
  `oper_param`        varchar(2000)   NOT NULL default ''                 comment '请求参数',
  `json_result`       varchar(2000)   NOT NULL default ''                 comment '返回参数',
  `status`            tinyint(4)      NOT NULL default 0                  comment '操作状态（0正常 1异常）',
  `error_msg`         varchar(2000)   NOT NULL default ''                 comment '错误消息',
  `dept_name`         varchar(50)     NOT NULL default ''                 comment '部门名称',
  `op_user`           varchar(32)     NOT NULL DEFAULT ''                 COMMENT '操作人',
  `updated_at`        datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_at`        datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
  primary key (`id`) USING BTREE,
  KEY `ix_created_at` (`created_at`),
  KEY `ix_updated_at` (`updated_at`)
) engine=innodb DEFAULT CHARSET=utf8 comment = '操作日志记录';
```

## 3 使用

```Java
@Log(title = "XXX标题", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
public CommonVO check(CheckParam param) {

    //do something

    return null;

}
```