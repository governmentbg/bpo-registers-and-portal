--liquibase formatted sql

--changeset iborisov:0102 splitStatements:false
alter table common.attachment alter column content_type type varchar(100);
