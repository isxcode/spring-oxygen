IF EXISTS(Select 1 From Sysobjects Where Name='DOGS_T')
    DROP table DOGS_T;

CREATE TABLE DOGS_T
(
    id                    int            not null,
    name                  varchar(100)   not null,
    amount_double         float          not null,
    amount_big_decimal    decimal(10, 5) not null,
    is_alive              bit            not null,
    birth_date            date           not null,
    birth_local_date      date           not null,
    birth_local_date_time datetime       not null
);
