

DROP DATABASE IF EXISTS `rbac1`;
CREATE DATABASE  `rbac1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
SET NAMES utf8mb4;

USE `rbac1`;

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
    `role`      varchar(50) NOT NULL,
    create_by         varchar(50)     default ''                 comment '创建者',
    create_time       datetime        default sysdate()          comment '创建时间',
    update_by         varchar(50)     default ''                 comment '更新者',
    update_time       datetime                                   comment '更新时间',
    PRIMARY KEY (`role`)
) engine=innodb comment = '角色表';

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `username`  varchar(50)  NOT NULL,
    `password`  varchar(500) NOT NULL,
    create_by         varchar(50)     default ''                 comment '创建者',
    create_time       datetime        default sysdate()          comment '创建时间',
    update_by         varchar(50)     default ''                 comment '更新者',
    update_time       datetime                                   comment '更新时间',
    PRIMARY KEY (username)
) engine=innodb comment = '用户表';

DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
    `role`      varchar(50) NOT NULL,
    `username`  varchar(50)  NOT NULL,
    PRIMARY KEY (`role`, `username`)
) engine=innodb comment = '角色用户关联表';

-- 在取用户所有权限时，后端会根据 resource_type 决定如何去取对应的资源，如果 resource_type 是 menu 那就去 menu 表找
-- 前端在拿到用户 permission 后，会查看那些 permission 的 resource_type 是 menu 的
-- 前端将 type 为 menu 的值作为对于菜单栏页面显隐的依据
-- 如需要更高性能，应当分离 permission 与 resource，但此处并不需要多高的性能
-- permission 与 resource 是 1:N 的关系
-- 特别的，按钮是跟后端几乎毫无关系的，只能由前端实现权限控制，因此 按钮 不会有实际的资源
-- 特别的，对于持有 page crud 但是不持有该页面父级 menu 的情况，由于前端不会渲染出这种菜单树，因此不会发生越权访问
-- 注意，对于前后端分离开发而言，页面层次语义的解释权交由前端进行诠释，数据层面语义的解释权交由后端诠释
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `expression` varchar(50) DEFAULT NULL COMMENT '权限表达式，代表可以对这些资源做什么操作，核心是最后的 operation 操作符，前面的只是标识符。' ||
        '如 menu:page:btn:cru 的语义就是给这个 btn 按钮 create、read、update 权限，但是没有 delete 的权限。但是后端只解析最后的 cru 而已，并将 cru 作为访问 resource_ids 所有资源的权限',
    `resource_types` varchar(100) DEFAULT NULL COMMENT '将会 split，menu!@@!page!@@!btn，解析的值将用于请求对应资源的表名，where 条件为 resource_ids 的解析值，或者你也可以称该字段为 resource_tables。',
    `resource_types_sep` varchar(10) DEFAULT '!@@!' COMMENT '分隔符',
    `resource_ids` varchar(10000) DEFAULT NULL COMMENT '此处可存放多种资源，为 map 结构: {type1:[t1r1id, t1r2id], type2:[t2r1id, t2r2id]}',
    create_by         varchar(50)     default ''                 comment '创建者',
    create_time       datetime        default sysdate()          comment '创建时间',
    update_by         varchar(50)     default ''                 comment '更新者',
    update_time       datetime                                   comment '更新时间',
    UNIQUE INDEX `idx_permission` (`id` ASC, `resource_id` ASC) USING BTREE,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB auto_increment=1000 comment = '权限表';

DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
    `role`      varchar(50) NOT NULL,
    `permission_id`  int(11)  NOT NULL,
    PRIMARY KEY (`role`, `permission_id`)
)engine=innodb comment = '角色权限关联表';


-- Rbac0WithHierarchy
-- 邻接表实现的继承关系
DROP TABLE IF EXISTS `role_hierarchy`;
CREATE TABLE `role_hierarchy` (
   `role`      varchar(50) NOT NULL,
   `parent_role_id` varchar(50)  DEFAULT NULL,
   UNIQUE INDEX `idx_user_role` (`role` ASC, `parent_role_id` ASC) USING BTREE
)engine=innodb comment = '父子权限表，parent_role_id 为 null 就代表是根节点，程序只允许管理员的 parent_role_id 为 null。';


-- Rbac0WithGroup
DROP TABLE IF EXISTS `role_group_admin`;
CREATE TABLE `role_group_admin` (
    `group`  varchar(50)  NOT NULL,
    `roles`  varchar(5000)  DEFAULT '采用分隔符分割，比如 admin!@|@!role1!@|@!role2',
    `users`  varchar(5000)  DEFAULT '',
    `separator`    varchar(10) DEFAULT '!@|@!',
    PRIMARY KEY (`group`)
)engine=innodb comment = '角色组表';

DROP TABLE IF EXISTS `user_group_admin`;
CREATE TABLE `user_group_admin` (
    `group`  varchar(50)  NOT NULL,
    `roles`  varchar(5000)  DEFAULT '',
    `users`  varchar(5000)  DEFAULT '',
    `separator`    varchar(10) DEFAULT '!@|@!',

    PRIMARY KEY (`group`)
)engine=innodb comment = '用户组表';


-- ui
-- 页面表，页面的可访问权限由 permission 表管理
-- 所以在添加页面时，要同时添加 permission 和 menu 表
-- 留存前 1000 个 id 作为框架使用
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
    id                bigint(20)      not null auto_increment    comment '菜单ID',
    menu_text         varchar(50)     not null                   comment '菜单显示名称',
    parent_id         bigint(20)      default 0                  comment '父菜单ID',
    order_num         int(4)          default 0                  comment '显示顺序',
    router_link       varchar(200)    default ''                 comment '路由地址',
    create_by         varchar(50)     default ''                 comment '创建者',
    create_time       datetime        default sysdate()          comment '创建时间',
    update_by         varchar(50)     default ''                 comment '更新者',
    update_time       datetime                                   comment '更新时间',
    remark            varchar(500)    default ''                 comment '备注，或有妙用~',
    PRIMARY KEY (`id`)
)engine=innodb auto_increment=1000 comment = '菜单表';

-- 注意，菜单可以嵌套菜单，但是 page 不能嵌套 page，page 的 parent_menu_id 不能是 page
DROP TABLE IF EXISTS `page`;
CREATE TABLE `page` (
    id                bigint(20)      not null auto_increment    comment '菜单ID',
    menu_text         varchar(50)     not null                   comment '菜单显示名称',
    parent_menu_id    bigint(20)      default 0                  comment '父菜单ID，menu 可以嵌套 menu，但是 page 不能嵌套 page，page 的 parent_menu_id 不能是 page',
    order_num         int(4)          default 0                  comment '显示顺序',
    router_link       varchar(200)    default ''                 comment '路由地址',
    component_path    varchar(255)    default null               comment '查找组件的路径，不是操作系统的也不是 web 容器的，而需要交由工程化框架（vite 或 webpack）进行 resolve 的，比如 src/view/xx.vue',
    is_frame          int(1)          default 0                  comment '是否为本地路径（0 是、1 wujie 外链、2 iframe 外链）',
    create_by         varchar(50)     default ''                 comment '创建者',
    create_time       datetime        default sysdate()          comment '创建时间',
    update_by         varchar(50)     default ''                 comment '更新者',
    update_time       datetime                                   comment '更新时间',
    remark            varchar(500)    default ''                 comment '备注，或有妙用~',
    PRIMARY KEY (`id`)
)engine=innodb auto_increment=1000 comment = '页面表';







