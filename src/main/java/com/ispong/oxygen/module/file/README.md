## file system

集成文件管理系统

1- 可以直接使用自己的数据表

2- 可以自定义自己的sql

通过配置路径  
通过数据库查询指定数据
插入数据库

实现多级附件

主要目的 还是一个连接下载地址实现文件的下载  文件的上传  

必选字段   name  size  type status

```sql
create table file_table
(
    file_uuid          varchar(50)  not null comment '文件id'
        primary key,
    file_name          varchar(200) not null comment '文件名称',
    file_suffix        varchar(20)  not null comment '文件的后缀',
    file_size          mediumtext   not null comment '文件的大小（KB）',
    file_status        varchar(50)  not null comment '文件的状态',
    business_type      varchar(50)  null comment '业务类型',
    business_id        varchar(50)  null comment '业务id',
    created_date       datetime     not null comment '创建时间',
    create_by          varchar(50)  not null comment '创建者',
    last_modified_date datetime     not null comment '更新时间',
    last_modified_by   varchar(50)  not null comment '更新者',
    version            int          not null comment '版本号'
)
    comment '文件表';
```