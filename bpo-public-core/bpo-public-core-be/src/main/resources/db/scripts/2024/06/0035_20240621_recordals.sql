--liquibase formatted sql

--changeset ggeorgiev:0035 splitStatements:false
alter table ip_object_recordals.recordals drop column recordal_date;
alter table ip_object_recordals.recordals add column registration_date date;
alter table ip_object_recordals.recordals rename column requested_date to filing_date;
alter table ip_object_recordals.recordals add column status_code varchar(10);
alter table ip_object_recordals.recordals
    add constraint recordal_status_code_fk
        foreign key (status_code) references nomenclatures.backoffice_status_map;