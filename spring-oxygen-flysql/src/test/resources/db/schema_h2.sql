drop table if exists dogs;

create table dogs
(
    name          varchar(32)  primary key ,
    age           integer      not null
);