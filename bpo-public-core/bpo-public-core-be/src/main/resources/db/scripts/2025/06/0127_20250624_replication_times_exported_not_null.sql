--liquibase formatted sql

--changeset ggeorgiev:0127
update ip_objects.ip_replication_times set exported = 0 where exported is null;
alter table ip_objects.ip_replication_times alter column  exported set not null;