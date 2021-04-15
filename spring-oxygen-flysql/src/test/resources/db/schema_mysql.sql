DROP TABLE IF EXISTS META_DATA_T;

CREATE TABLE META_DATA_T
(
    id                 VARCHAR(100) PRIMARY KEY,
    is_delete          INT          NOT NULL,
    created_by         VARCHAR(100) NOT NULL,
    created_date       TIMESTAMP    NOT NULL,
    last_modified_by   VARCHAR(100) NOT NULL,
    last_modified_date TIMESTAMP    NOT NULL,
    version            INT          NOT NULL,
    an_byte            TINYINT      NOT NULL,
    an_int             INT          NOT NULL,
    an_short           SMALLINT     NOT NULL,
    an_long            BIGINT       NOT NULL,
    an_double          DOUBLE       NOT NULL,
    an_float           FLOAT        NOT NULL,
    an_boolean         BOOLEAN      NOT NULL,
    an_char            CHAR         NOT NULL,
    an_string          VARCHAR(100) NOT NULL,
    an_date            DATE         NOT NULL,
    an_local_date      TIMESTAMP    NOT NULL,
    an_local_date_time DATETIME     NOT NULL,
    an_big_decimal     DECIMAL      NOT NULL
);
