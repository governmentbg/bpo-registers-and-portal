--liquibase formatted sql

--changeset ggeorgiev:0016 splitStatements:false
alter table ip_objects.ip_object add column show_persons_flag int not null default 1;
alter table ip_objects.ip_object alter column show_persons_flag drop default;