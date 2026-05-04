--liquibase formatted sql

--changeset ggeorgiev:0036 splitStatements:false
alter table ip_object_recordals.details drop constraint recordal_id_fk;
alter table ip_object_recordals.recordals drop constraint recordal_id_fk;
alter table ip_object_recordals.recordals drop constraint recordals_pkey;

alter table ip_object_recordals.recordals alter column id type varchar(30);
alter table ip_object_recordals.recordals alter column recordal_id type varchar(30);
alter table ip_object_recordals.details alter column recordal_id type varchar(30);
alter table ip_object_recordals.recordals
    add primary key (id);
alter table ip_object_recordals.recordals
    add constraint recordal_id_fk
        foreign key (recordal_id) references ip_object_recordals.recordals;
alter table ip_object_recordals.details
    add constraint recordal_id_fk
        foreign key (recordal_id) references ip_object_recordals.recordals;
alter table ip_object_recordals.recordals alter column id drop default;
