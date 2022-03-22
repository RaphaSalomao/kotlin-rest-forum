CREATE TABLE public.answer (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  topic_id UUID NOT NULL,
  author_id UUID NOT NULL,
  solution boolean NOT NULL,
  message VARCHAR(255) NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);