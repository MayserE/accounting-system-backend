CREATE TABLE role_permissions
(
    id            UUID      NOT NULL DEFAULT UUID_GENERATE_V4(),
    role_id       UUID      NOT NULL,
    permission_id UUID      NOT NULL,
    created_at    TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (role_id) REFERENCES roles (id),
    FOREIGN KEY (permission_id) REFERENCES permissions (id)
);