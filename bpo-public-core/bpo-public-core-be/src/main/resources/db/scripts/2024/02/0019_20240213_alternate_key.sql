--liquibase formatted sql

--changeset ggeorgiev:0019 splitStatements:false
alter table ip_objects.ip_single_design add column alternate_key varchar(20);
CREATE INDEX single_design_alternate_key ON ip_objects.ip_single_design USING btree (alternate_key);
CREATE INDEX object_alternate_key ON ip_objects.ip_object USING btree (alternate_key);