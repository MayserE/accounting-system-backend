CREATE TABLE vendors
(
    id           UUID         NOT NULL DEFAULT UUID_GENERATE_V4(),
    company_id   UUID         NOT NULL,
    created_at   TIMESTAMP    NOT NULL,
    updated_at   TIMESTAMP    NOT NULL,
    status       VARCHAR(32)  NOT NULL,
    name         VARCHAR(128) NOT NULL,
    email        VARCHAR(256) NOT NULL,
    phone_number VARCHAR(32),
    PRIMARY KEY (id),
    FOREIGN KEY (company_id) REFERENCES companies (id)
);