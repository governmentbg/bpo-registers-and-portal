--liquibase formatted sql

--changeset ggeorgiev:0051
alter table ip_objects.ip_object drop column show_persons_flag;