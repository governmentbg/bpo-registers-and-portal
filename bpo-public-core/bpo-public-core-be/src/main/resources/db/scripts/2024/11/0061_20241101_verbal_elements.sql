--liquibase formatted sql

--changeset ggeorgiev:0061
alter table ip_objects.ip_single_design add column verbal_element text;
alter table ip_objects.ip_single_design add column verbal_element_en text;