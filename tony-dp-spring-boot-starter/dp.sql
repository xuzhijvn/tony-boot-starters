CREATE TABLE `sys_data_permission_resource`
(
    `id`         bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `type`       tinyint(6) NOT NULL DEFAULT '0' COMMENT '数据权限类型：1-常规审查，2-作弊审查，3-申述',
    `key`        tinyint(6) NOT NULL DEFAULT '0' COMMENT '数据权限key',
    `value`      varchar(100) NOT NULL DEFAULT '' COMMENT '数据权限value',
    `op_user`    varchar(32)  NOT NULL DEFAULT '' COMMENT '操作人',
    `updated_at` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `created_at` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY          `ix_created_at` (`created_at`),
    KEY          `ix_updated_at` (`updated_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据权限资源表';

INSERT INTO `sys_data_permission_resource` VALUES (1, 1, 1, '待一审（还需二审）', 'system', '2021-09-02 15:25:01', '2021-09-02 15:22:57');
INSERT INTO `sys_data_permission_resource` VALUES (2, 1, 2, '待一审（直接通过）', 'system', '2021-09-02 15:25:02', '2021-09-02 15:24:02');
INSERT INTO `sys_data_permission_resource` VALUES (3, 1, 3, '重传待一审（还需二审）', 'system', '2021-09-02 15:25:05', '2021-09-02 15:24:35');
INSERT INTO `sys_data_permission_resource` VALUES (4, 1, 4, '重传待一审（直接通过）', 'system', '2021-09-02 15:27:26', '2021-09-02 15:25:19');
INSERT INTO `sys_data_permission_resource` VALUES (5, 1, 5, '待二审（AI通过）', 'system', '2021-09-02 15:27:27', '2021-09-02 15:26:14');
INSERT INTO `sys_data_permission_resource` VALUES (6, 1, 6, '待二审（一审通过）', 'system', '2021-09-02 15:27:28', '2021-09-02 15:26:38');
INSERT INTO `sys_data_permission_resource` VALUES (7, 1, 7, '重传待二审（AI通过）', 'system', '2021-09-02 15:27:29', '2021-09-02 15:26:53');
INSERT INTO `sys_data_permission_resource` VALUES (8, 1, 8, '重传待二审（一审通过）', 'system', '2021-09-02 15:27:30', '2021-09-02 15:27:07');
INSERT INTO `sys_data_permission_resource` VALUES (9, 2, 1, '待一审（还需二审）', 'system', '2021-09-02 15:31:32', '2021-09-02 15:29:32');
INSERT INTO `sys_data_permission_resource` VALUES (10, 2, 2, '待一审（直接通过）', 'system', '2021-09-02 15:31:34', '2021-09-02 15:29:45');
INSERT INTO `sys_data_permission_resource` VALUES (11, 2, 3, '重传待一审（还需二审）', 'system', '2021-09-02 15:31:35', '2021-09-02 15:30:02');
INSERT INTO `sys_data_permission_resource` VALUES (12, 2, 4, '重传待一审（直接通过）', 'system', '2021-09-02 15:31:36', '2021-09-02 15:30:14');
INSERT INTO `sys_data_permission_resource` VALUES (13, 2, 5, '待二审', 'system', '2021-09-02 15:31:37', '2021-09-02 15:30:40');
INSERT INTO `sys_data_permission_resource` VALUES (14, 2, 6, '重传待二审', 'system', '2021-09-02 15:31:39', '2021-09-02 15:30:55');
INSERT INTO `sys_data_permission_resource` VALUES (15, 3, 1, '常规申诉', 'system', '2021-09-02 15:31:40', '2021-09-02 15:31:10');
INSERT INTO `sys_data_permission_resource` VALUES (16, 3, 2, '作弊申诉', 'system', '2021-09-02 15:31:41', '2021-09-02 15:31:21');

CREATE TABLE `sys_data_permission_role`
(
    `id`         bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `name`       varchar(100) NOT NULL DEFAULT '' COMMENT '角色名称',
    `op_user`    varchar(32)  NOT NULL DEFAULT '' COMMENT '操作人',
    `updated_at` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `created_at` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY          `ix_created_at` (`created_at`),
    KEY          `ix_updated_at` (`updated_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

CREATE TABLE `sys_data_permission_role_resource`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `role_id`     bigint(20) NOT NULL DEFAULT 0 COMMENT '角色ID',
    `resource_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '资源ID',
    `op_user`     varchar(32) NOT NULL DEFAULT '' COMMENT '操作人',
    `updated_at`  datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `created_at`  datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY uk_role_id_resource_id(`role_id`,`resource_id`),
    KEY           `ix_created_at` (`created_at`),
    KEY           `ix_updated_at` (`updated_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源表';


CREATE TABLE `sys_data_permission_user_role`
(
    `id`         bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `user_id`    bigint(20) NOT NULL DEFAULT 0 COMMENT '用户ID',
    `role_id`    bigint(20) NOT NULL DEFAULT 0 COMMENT '角色ID',
    `op_user`    varchar(32) NOT NULL DEFAULT '' COMMENT '操作人',
    `updated_at` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `created_at` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY uk_role_id_user_id(`role_id`,`user_id`),
    KEY          `ix_created_at` (`created_at`),
    KEY          `ix_updated_at` (`updated_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';