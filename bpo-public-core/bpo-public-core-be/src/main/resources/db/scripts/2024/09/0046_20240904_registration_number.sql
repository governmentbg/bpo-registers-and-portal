--liquibase formatted sql

--changeset ggeorgiev:0046 splitStatements:false
alter table ip_objects.ip_object add column registration_number_new varchar(20);
update ip_objects.ip_object set registration_number_new = registration_number||coalesce(registration_dup, '') where object_type = 'N';
update ip_objects.ip_object set registration_number_new = registration_number||coalesce('-'||registration_dup, '') where object_type = 'Г';
alter table ip_objects.ip_object drop column registration_dup;
alter table ip_objects.ip_object drop column registration_number;
alter table ip_objects.ip_object rename column registration_number_new to registration_number;
