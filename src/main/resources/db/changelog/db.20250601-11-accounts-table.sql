CREATE TABLE accounts
(
    id                UUID         NOT NULL DEFAULT UUID_GENERATE_V4(),
    company_id        UUID         NOT NULL,
    parent_account_id UUID,
    created_at        TIMESTAMP    NOT NULL,
    updated_at        TIMESTAMP    NOT NULL,
    status            VARCHAR(32)  NOT NULL,
    name              VARCHAR(128) NOT NULL,
    type              VARCHAR(32)  NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (company_id) REFERENCES companies (id),
    FOREIGN KEY (parent_account_id) REFERENCES accounts (id)
);