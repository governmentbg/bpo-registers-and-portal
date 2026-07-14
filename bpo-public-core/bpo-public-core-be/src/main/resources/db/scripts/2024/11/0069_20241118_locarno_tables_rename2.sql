--liquibase formatted sql

--changeset ggeorgiev:0069
alter table nomenclatures.locarno_class rename column class_id to heading_id;
alter table nomenclatures.locarno_class rename column subclass_id to class_id;