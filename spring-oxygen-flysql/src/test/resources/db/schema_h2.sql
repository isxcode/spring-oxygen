DROP TABLE IF EXISTS DOG_T;

CREATE TABLE DOG_T
(
    id                 INT          NOT NULL,
    name               VARCHAR(100) NOT NULL,
    amountDouble       DOUBLE       NOT NULL,
    amountBigDecimal   DECIMAL      NOT NULL,
    isAlive            BOOLEAN      NOT NULL,
    birthDate          TIMESTAMP    NOT NULL,
    birthLocalDate     TIMESTAMP    NOT NULL,
    birthLocalDateTime DATETIME     NOT NULL
);
