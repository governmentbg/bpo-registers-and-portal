--liquibase formatted sql

--changeset murlev:0033
ALTER TABLE ip_object_recordals.recordals ALTER COLUMN group_type type varchar(50);
alter table ip_object_recordals.recordals
    add constraint recordal_group_type_fk
        foreign key (group_type) references ip_object_recordals.recordal_group_type;