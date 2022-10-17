drop table user if exists;


create table user (
    id bigint auto_increment,
    name varchar(255),
    create_time timestamp,
    update_time timestamp,
    primary key (id)
);



insert into user (name, create_time, update_time) values ('张三', now(), now());
insert into user (name, create_time, update_time) values ('李四', now(), now());
insert into user (name, create_time, update_time) values ('王五', now(), now());
