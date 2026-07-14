--liquibase formatted sql

--changeset ggeorgiev:0019 splitStatements:false
alter table ip_objects.ip_single_design add column st13 varchar(22);
alter table ip_objects.ip_mark add column st13 varchar(17);
CREATE INDEX mark_st13_idx ON ip_objects.ip_mark (st13);
CREATE INDEX single_design_st13_idx ON ip_objects.ip_single_design (st13);
