--liquibase formatted sql

--changeset ggeorgiev:0075 splitStatements:false
alter table ip_objects.ip_object add column date_updated date;
update ip_objects.ip_object o set date_updated = (select max(date_start) from ip_objects.ip_replication_times rt join ip_objects.ip_replication_details rd on rd.replication_time_id = rt.id where rd.object_id = o.id );
update ip_objects.ip_object set date_updated = '1970-01-01' where date_updated is null;
alter table ip_objects.ip_object alter column date_updated set not null;