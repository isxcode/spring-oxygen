drop table if exists dogs;

create table dogs
(
    name               varchar(32)  primary key ,
    age                integer      not null ,
    is_delete          integer      not null ,
    created_by         varchar(50)  not null ,
    created_date       datetime     not null ,
    last_modified_by   varchar(50)  not null ,
    last_modified_date datetime     not null ,
    version            int          not null
);