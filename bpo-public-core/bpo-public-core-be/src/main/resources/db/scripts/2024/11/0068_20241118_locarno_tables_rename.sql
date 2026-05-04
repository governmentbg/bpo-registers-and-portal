--liquibase formatted sql

--changeset ggeorgiev:0068
alter table nomenclatures.locarno_class rename to locarno_heading;
alter table nomenclatures.locarno_subclass rename to locarno_class;
alter table ip_objects.ip_single_design_locarno
    drop CONSTRAINT sdl_locarno_subclass_fk;
alter table ip_objects.ip_single_design_locarno
    add CONSTRAINT sdl_locarno_class_fk foreign key (locarno_class_code)
        references nomenclatures.locarno_class;