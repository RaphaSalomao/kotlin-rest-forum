CREATE TABLE role (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(25) NOT NULL
);

INSERT INTO role (id, name) VALUES
    ('2ab6eb48-5c73-42a4-a488-411dc522ec71','ADMIN'),
    ('0717f210-b3d9-4ef0-838d-d239e7589569','USER'),
    ('adb2f996-853c-43e6-b7c4-cb8d8d51d19e','GUEST');