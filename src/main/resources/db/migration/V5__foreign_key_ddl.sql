alter table if exists answer add constraint answer_author_pk foreign key (author_id) references t_user;
alter table if exists answer add constraint answer_topic_pk foreign key (topic_id) references topic;
alter table if exists topic add constraint topic_author_pk foreign key (author_id) references t_user;
alter table if exists topic add constraint topic_course_pk foreign key (course_id) references course;