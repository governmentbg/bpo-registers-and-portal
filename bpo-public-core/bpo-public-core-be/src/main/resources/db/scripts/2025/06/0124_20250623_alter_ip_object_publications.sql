--liquibase formatted sql

--changeset mnakova:0124 splitStatements:false
alter table ip_objects.ip_object_publication add is_public boolean;