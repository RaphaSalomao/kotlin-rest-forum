CREATE TABLE public.topic (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  course_id UUID NOT NULL,
  author_id UUID NOT NULL,
  title VARCHAR(255) NOT NULL,
  status VARCHAR(255) NOT NULL DEFAULT 'OPEN',
  message VARCHAR(255) NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);