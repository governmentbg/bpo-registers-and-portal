--liquibase formatted sql

--changeset ggeorgiev:0054 splitStatements:false
alter table ip_objects.ip_single_design add column design_number int;
update ip_objects.ip_single_design set design_number = split_part(st13, '-', 2)::int;
alter table ip_objects.ip_single_design alter column design_number set not null;