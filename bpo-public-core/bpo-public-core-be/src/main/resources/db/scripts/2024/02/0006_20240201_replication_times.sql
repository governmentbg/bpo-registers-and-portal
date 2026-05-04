--liquibase formatted sql

--changeset ggeorgiev:0006 splitStatements:false

alter table ip_objects.ip_replication_times add column date_end timestamp;
alter table ip_objects.ip_replication_times rename column exec_time to date_start;