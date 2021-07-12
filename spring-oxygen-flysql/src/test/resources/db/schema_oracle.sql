-- declare
--     num number;
-- begin
--     select count(1) into num from user_tables where table_name = 'DOGS_T';
--     if num > 0 then
--         execute immediate 'drop table DOGS_T';
--     end if;
-- end;
drop table DOGS_T;

CREATE TABLE DOGS_T
(
    id                    INT          NOT NULL,
    name                  VARCHAR(100) NOT NULL,
    amount_double         NUMBER       NOT NULL,
    amount_big_decimal    NUMBER       NOT NULL,
    is_alive              char(1)      NOT NULL,
    birth_date            date         NOT NULL,
    birth_local_date      date         NOT NULL,
    birth_local_date_time date         NOT NULL
);
