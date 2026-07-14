--liquibase formatted sql

--changeset ggeorgiev:0104 splitStatements:false
update ip_objects.ip_object set alternate_key = substring(id, 6, 4) || case when length(substring(id, 11)) >= 6 then substring(id, 11) else lpad(substring(id, 11), 6, '0') end ||substring(id, 4, 1)
where object_type not in ('T');
alter table ip_objects.ip_object add unique (alternate_key);