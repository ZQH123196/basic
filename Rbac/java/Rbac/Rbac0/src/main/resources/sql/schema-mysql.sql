

DROP DATABASE IF EXISTS `rbac0`;
CREATE DATABASE  `rbac0` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
SET NAMES utf8mb4;

USE `rbac0`;

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
    `role`      varchar(50) NOT NULL,
    create_time datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`role`)
) engine=innodb comment = '角色表';

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `username`  varchar(50)  NOT NULL,
    `password`  varchar(500) NOT NULL,
    create_time datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (username)
) engine=innodb comment = '用户表';

DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
    `role`      varchar(50) NOT NULL,
    `username`  varchar(50)  NOT NULL,
    PRIMARY KEY (`role`, `username`)
) engine=innodb comment = '角色用户关联表';

DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `expression` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限表达式,模块名称:资源名称:操作(crud)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB auto_increment=1000 comment = '权限表';

DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
    `role`      varchar(50) NOT NULL,
    `permission_id`  int(11)  NOT NULL,
    PRIMARY KEY (`role`, `permission_id`)
)engine=innodb comment = '角色权限关联表';








