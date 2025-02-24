create database if not exists paperManagement;
use paperManagement;

# 部门表
create table if not exists department
(
    id                  int auto_increment comment '序号',
    school_id           int                                null comment '外键，学校，id',
    department_name     varchar(64)                        null comment '名称',
    department_describe text                               null comment '部门描述',
    creation_time       datetime default current_timestamp null comment '创建时间',
    update_time         datetime default current_timestamp null on update current_timestamp comment '更新时间',
    update_user_id      VARCHAR(128)                       null comment '更新人id',
    delete_flag         boolean  default false             null comment '删除标记',
    constraint department_pk
        primary key (id)
)
    comment '部门表';

insert department(id, school_id, department_name, department_describe)
values (1, 1, '数学与信息工程学院', '待定');
insert department(id, school_id, department_name, department_describe)
values (2, 1, '外国语学院', '待定');
insert department(id, school_id, department_name, department_describe)
values (3, 1, '经济与管理学院', '待定');
insert department(id, school_id, department_name, department_describe)
values (4, 1, '生命科学学院', '待定');
