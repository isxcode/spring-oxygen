-- DROP TABLE META_DATA_T;

CREATE TABLE META_DATA_T
(
    id                 VARCHAR2(100) PRIMARY KEY,
    is_delete          NUMBER        NOT NULL,
    created_by         VARCHAR2(100) NOT NULL,
    created_date       TIMESTAMP     NOT NULL,
    last_modified_by   VARCHAR2(100) NOT NULL,
    last_modified_date TIMESTAMP     NOT NULL,
    version            INT           NOT NULL,
    a_int              INT           NOT NULL,
    an_int             NUMBER        NOT NULL,
    a_double           NUMBER        NOT NULL,
    an_double          NUMBER        NOT NULL,
    a_long             NUMBER        NOT NULL,
    an_Long            NUMBER        NOT NULL,
    a_boolean          NUMBER        NOT NULL,
    an_boolean         NUMBER        NOT NULL,
    a_short            NUMBER        NOT NULL,
    an_short           NUMBER        NOT NULL,
    a_float            NUMBER        NOT NULL,
    an_float           NUMBER        NOT NULL,
    a_byte             RAW(50)       NOT NULL,
    an_byte            RAW(50)       NOT NULL,
    a_char             CHAR          NOT NULL,
    an_string          VARCHAR(100)  NOT NULL,
    a_date             TIMESTAMP     NOT NULL,
    an_local_date      DATE          NOT NULL,
    an_local_date_time TIMESTAMP     NOT NULL,
    an_big_decimal     NUMBER        NOT NULL
);
