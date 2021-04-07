DROP TABLE IF EXISTS META_DATA_T;

CREATE TABLE META_DATA_T
(
    id                 VARCHAR(100) PRIMARY KEY,
    is_delete          INTEGER      NOT NULL,
    created_by         VARCHAR(100) NOT NULL,
    created_date       TIMESTAMP    NOT NULL,
    last_modified_by   VARCHAR(100) NOT NULL,
    last_modified_date TIMESTAMP    NOT NULL,
    version            INT          NOT NULL,
    a_int              INT          NOT NULL,
    an_int             INTEGER      NOT NULL,
    a_double           DOUBLE       NOT NULL,
    an_double          DOUBLE       NOT NULL,
    a_long             BIGINT       NOT NULL,
    an_Long            BIGINT       NOT NULL,
    a_boolean          BOOLEAN      NOT NULL,
    an_boolean         BOOLEAN      NOT NULL,
    a_short            SMALLINT     NOT NULL,
    an_short           SMALLINT     NOT NULL,
    a_float            REAL         NOT NULL,
    an_float           REAL         NOT NULL,
    a_byte             TINYINT      NOT NULL,
    an_byte            TINYINT      NOT NULL,
    a_char             CHAR         NOT NULL,
    an_string          VARCHAR(100) NOT NULL,
    a_date             TIMESTAMP    NOT NULL,
    an_local_date      DATE         NOT NULL,
    an_local_date_time TIMESTAMP    NOT NULL,
    an_big_decimal     DECIMAL      NOT NULL
);
