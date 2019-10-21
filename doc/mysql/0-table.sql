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

