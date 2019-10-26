# 文件表/ 负责记录文件的属性信息
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


# 日志记录表
create table log
(
    id              varchar(32)  not null comment '日志uuid'
        primary key,
    create_by       varchar(32)  not null comment '创建人',
    create_date     datetime     not null comment '创建时间',
    api_name        varchar(500) not null comment '接口名称',
    request_params  varchar(500) null comment '请求参数',
    response_params varchar(500) null comment '返回参数',
    end_date        datetime     not null comment '结束时间',
    start_date      datetime     not null comment '开始时间'
)
    comment '日志表';

