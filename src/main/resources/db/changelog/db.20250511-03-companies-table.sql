CREATE TABLE companies
(
    id           UUID         NOT NULL DEFAULT UUID_GENERATE_V4(),
    created_at   TIMESTAMP    NOT NULL,
    updated_at   TIMESTAMP    NOT NULL,
    status       VARCHAR(32)  NOT NULL,
    name         VARCHAR(128) NOT NULL,
    tax_id       VARCHAR(32)  NOT NULL,
    address      TEXT,
    phone_number VARCHAR(32),
    PRIMARY KEY (id)
);