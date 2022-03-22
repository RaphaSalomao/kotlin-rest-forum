CREATE TABLE t_user_roles (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    t_user_id UUID NOT NULL REFERENCES t_user(id),
    roles_id UUID NOT NULL REFERENCES role(id)
);