DROP TABLE IF EXISTS DOGS_T;

CREATE TABLE DOGS_T
(
    id                    INT          NOT NULL,
    name                  VARCHAR(100) NOT NULL,
    amount_double         DOUBLE       NOT NULL,
    amount_big_decimal    DECIMAL      NOT NULL,
    is_alive              BOOLEAN      NOT NULL,
    birth_date            DATE         NOT NULL,
    birth_local_date      DATE         NOT NULL,
    birth_local_date_time DATETIME     NOT NULL
);
