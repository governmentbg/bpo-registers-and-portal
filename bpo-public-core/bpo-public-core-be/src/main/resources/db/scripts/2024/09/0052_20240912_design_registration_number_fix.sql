--liquibase formatted sql

--changeset ggeorgiev:0052
alter table ip_objects.ip_single_design_replication_details alter column registration_number type varchar(20);