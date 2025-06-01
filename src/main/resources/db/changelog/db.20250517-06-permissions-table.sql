CREATE TABLE permissions
(
    id          UUID         NOT NULL DEFAULT UUID_GENERATE_V4(),
    code        VARCHAR(128) NOT NULL,
    description VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);