--liquibase formatted sql

--changeset ggeorgiev:0088 splitStatements:false
alter table ip_objects.ip_object_priority add column priority_order int not null default 0;
alter table ip_objects.ip_object_priority alter column priority_order drop default;