--liquibase formatted sql

--changeset ggeorgiev:0073
create schema common;
alter table ip_objects.ip_attachment set schema common;
alter table ip_objects.ip_attachment_deletes set schema common;
alter table common.ip_attachment rename to attachment;
alter table common.ip_attachment_deletes rename to attachment_deletes;