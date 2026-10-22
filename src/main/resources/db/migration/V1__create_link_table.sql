CREATE TABLE link (
    id UUID PRIMARY KEY,
    short_code VARCHAR(255) NOT NULL UNIQUE,
    target_url VARCHAR(255) NOT NULL,
    title VARCHAR(255),
    created_at TIMESTAMP(6) NOT NULL
);