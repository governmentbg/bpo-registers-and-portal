--liquibase formatted sql

--changeset ggeorgiev:0100 splitStatements:false
alter table emails.email_notification add column uuid uuid;
alter table emails.email_notification add unique (uuid);