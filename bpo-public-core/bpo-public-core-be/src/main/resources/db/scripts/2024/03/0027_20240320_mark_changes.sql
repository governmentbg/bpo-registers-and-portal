--liquibase formatted sql

--changeset ggeorgiev:0027 splitStatements:false
alter table ip_objects.ip_mark add column acquired_distinctiveness int;
alter table ip_objects.ip_object_relationship add column rel_scope int;
alter table ip_objects.ip_object_relationship add column rel_text text;