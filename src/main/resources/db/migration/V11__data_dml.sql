-- John -> ADMIN
insert into t_user_roles (t_user_id, roles_id) values ('3ac206cc-1c00-4fac-8200-fdd19dcdbb4c','adb2f996-853c-43e6-b7c4-cb8d8d51d19e');

insert into topic values (uuid_generate_v4(),'c18801e9-83fe-4696-8400-5df1342de08f','3ac206cc-1c00-4fac-8200-fdd19dcdbb4c','Title','OPEN','message',now(), now());
insert into topic values (uuid_generate_v4(),'9164f67a-a47a-4bdc-b567-4de133f758e3','3ac206cc-1c00-4fac-8200-fdd19dcdbb4c','Title','OPEN','message',now(), now());
insert into topic values (uuid_generate_v4(),'1853ecc0-d238-4973-9391-039dc48284e5','3ac206cc-1c00-4fac-8200-fdd19dcdbb4c','Title','OPEN','message',now(), now());
insert into topic values (uuid_generate_v4(),'eda5dbca-59ec-4676-be58-9d8cb3f811da','3ac206cc-1c00-4fac-8200-fdd19dcdbb4c','Title','OPEN','message',now(), now());