--liquibase formatted sql

--changeset iborisov:0059 splitStatements:false

alter table repr_eservices.xml alter column portal_user type varchar(40);
alter table repr_eservices.change_partnership alter column created_user_id type varchar(40);
alter table repr_eservices.change_physical_person alter column created_user_id type varchar(40);
alter table repr_eservices.course alter column created_user_id type varchar(40);
alter table repr_eservices.exam alter column created_user_id type varchar(40);
alter table repr_eservices.register_partnership alter column created_user_id type varchar(40);
alter table repr_eservices.register_physical_person alter column created_user_id type varchar(40);
