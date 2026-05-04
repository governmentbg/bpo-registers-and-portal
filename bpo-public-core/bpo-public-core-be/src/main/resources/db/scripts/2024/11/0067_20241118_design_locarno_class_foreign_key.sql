--liquibase formatted sql

--changeset ggeorgiev:0067
alter table ip_objects.ip_single_design_locarno
    add CONSTRAINT sdl_locarno_subclass_fk foreign key (locarno_class_code)
references nomenclatures.locarno_subclass;