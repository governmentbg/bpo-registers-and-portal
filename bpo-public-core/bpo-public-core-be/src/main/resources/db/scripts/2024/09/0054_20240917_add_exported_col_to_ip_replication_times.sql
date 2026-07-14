--liquibase formatted sql

--changeset akehayov:0054
alter table ip_objects.ip_replication_times
    add exported integer default 0;