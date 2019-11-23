-- 文件表/ 负责记录文件的属性信息
create table file
(
    id          varchar(32)  not null comment '文件uuid'
        primary key,
    file_name   varchar(100) not null comment '文件的名字',
    file_size   varchar(10)  not null comment '文件的大小',
    create_date datetime     not null comment '创建时间',
    create_by   varchar(32)  not null comment '创建人',
    file_status varchar(10)  not null comment '文件状态'
)
    comment '文件表';


-- 日志记录表 / 负责记录接口参数
create table log
(
    id              varchar(32)  not null comment '日志uuid'
        primary key,
    create_by       varchar(32)  not null comment '创建人',
    create_date     datetime     not null comment '创建时间',
    api_name        varchar(500) not null comment '接口名称',
    request_params  longtext     null comment '请求参数',
    response_params longtext     null comment '返回参数',
    start_date      datetime     not null comment '开始时间',
    execute_time    mediumtext   not null comment '执行时间'
)
    comment '日志表';

-- 用户基础信息表 / 负责记录用户的基础信息
create table user
(
    id         varchar(32)  not null comment '用户uuid',
    nick_name  varchar(200) not null comment '用户昵称',
    first_name varchar(100) not null comment '用户姓',
    last_name  varchar(100) not null comment '用户名',
    constraint user_nick_name_uindex
        unique (nick_name)
)
    comment '用户基础信息表';


