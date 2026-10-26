-- Create tables.

CREATE TABLE roles (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP(6) NOT NULL
);

CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255),
    created_at TIMESTAMP(6) NOT NULL
);

CREATE TABLE users_roles (
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,

    PRIMARY KEY (user_id, role_id),

    CONSTRAINT fk_users_roles_user FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_users_roles_role FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE links (
    id UUID PRIMARY KEY,
    short_code VARCHAR(255) NOT NULL UNIQUE,
    target_url VARCHAR(255) NOT NULL,
    title VARCHAR(255),
    created_at TIMESTAMP(6) NOT NULL,
    user_id UUID NOT NULL,

    CONSTRAINT fk_links_user FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Populate default values.

INSERT INTO roles (id, name, created_at)
VALUES
    (gen_random_uuid(), 'ROLE_USER', CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'ROLE_ADMIN', CURRENT_TIMESTAMP);