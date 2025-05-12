CREATE TABLE users
(
    id             UUID         NOT NULL DEFAULT UUID_GENERATE_V4(),
    created_at     TIMESTAMP    NOT NULL,
    updated_at     TIMESTAMP    NOT NULL,
    email          VARCHAR(256) NOT NULL UNIQUE,
    password       TEXT         NOT NULL,
    is_super_admin BOOLEAN      NOT NULL,
    status         VARCHAR(32)  NOT NULL,
    first_name     VARCHAR(128) NOT NULL,
    last_name      VARCHAR(128) NOT NULL,
    phone_number   VARCHAR(32),
    PRIMARY KEY (id)
);