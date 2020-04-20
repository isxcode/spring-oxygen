
-- h2 删除表操作 drop table if exists file

-- 文件管理表
create table file
(
    file_uuid          varchar(50) primary key ,-- comment '文件uuid',
    file_name          varchar(100) not null,-- comment '文件名称',
    file_suffix        varchar(100) not null, --comment '文件后缀',
    file_size          long         not null, --comment '文件大小(KB)',
    file_status        varchar(50)  not null, --comment '文件状态',
    business_type      varchar(50), --comment '业务类型',
    business_id        varchar(50), --comment '业务标识',
    created_by         varchar(50)  not null, --comment '创建者',
    created_date       datetime     not null, --comment '创建时间',
    last_modified_by   varchar(50)  not null, --comment '更新者',
    last_modified_date datetime     not null, --comment '更新时间',
    version            integer      not null --comment '版本好'
);

-- 用户表
create table user
(
    user_uuid          varchar(50) primary key,-- comment '用户uuid',
    account            varchar(100) not null,-- comment '用户账号',
    password           varchar(100) not null,  --comment '用户密码',
    authority          varchar(100) not null,  --comment '用户权限',
    phone              varchar(50)  not null,  --comment '用户手机号',
    created_by         varchar(50)  not null,  --comment '创建者',
    created_date       datetime     not null,  --comment '创建时间',
    last_modified_by   varchar(50)  not null,  --comment '更新者',
    last_modified_date datetime     not null,  --comment '更新时间',
    version            integer      not null   --comment '版本好'
);

-- 日志表
create table log
(
    log_uuid           varchar(50) primary key,-- comment '日志uuid',
    api_name           varchar(100) not null,-- comment '接口名称',
    request_body       longtext,               --comment '请求体',
    response_body      longtext,               --comment '响应体',
    execute_date       datetime,               --comment '执行日期',
    execute_time       long,                   --comment '执行时间',
    created_by         varchar(50)  not null,  --comment '创建者',
    created_date       datetime     not null,  --comment '创建时间',
    last_modified_by   varchar(50)  not null,  --comment '更新者',
    last_modified_date datetime     not null,  --comment '更新时间',
    version            integer      not null   --comment '版本好'
);





