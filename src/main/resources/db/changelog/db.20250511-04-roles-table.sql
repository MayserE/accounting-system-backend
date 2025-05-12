CREATE TABLE roles
(
    id         UUID        NOT NULL DEFAULT UUID_GENERATE_V4(),
    company_id UUID        NOT NULL,
    created_at TIMESTAMP   NOT NULL,
    updated_at TIMESTAMP   NOT NULL,
    name       VARCHAR(64) NOT NULL,
    status     VARCHAR(32) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (company_id) REFERENCES companies (id)
);