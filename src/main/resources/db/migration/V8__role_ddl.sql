CREATE TABLE role (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(25) NOT NULL
);

INSERT INTO role (name) VALUES
    ('ADMIN'),
    ('USER'),
    ('GUEST');