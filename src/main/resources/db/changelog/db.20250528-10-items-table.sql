CREATE TABLE items
(
    id          UUID           NOT NULL DEFAULT UUID_GENERATE_V4(),
    company_id  UUID           NOT NULL,
    created_at  TIMESTAMP      NOT NULL,
    updated_at  TIMESTAMP      NOT NULL,
    status      VARCHAR(32)    NOT NULL,
    name        VARCHAR(128)   NOT NULL,
    description VARCHAR(256),
    price       DECIMAL(10, 2) NOT NULL CHECK ( price >= 0 ),
    PRIMARY KEY (id),
    FOREIGN KEY (company_id) REFERENCES companies (id)
);