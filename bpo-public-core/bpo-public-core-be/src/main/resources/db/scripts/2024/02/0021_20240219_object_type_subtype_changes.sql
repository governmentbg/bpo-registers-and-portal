--liquibase formatted sql

--changeset ggeorgiev:0021 splitStatements:false

alter table ip_objects.ip_object drop constraint object_type_fk;
alter table ip_objects.ip_object drop constraint object_subtype;
alter table ip_objects.ip_single_design drop constraint single_design_subtype;
alter table nomenclatures.object_subtype drop constraint object_subtype_pk;
delete from nomenclatures.object_type where backoffice_code in ('I', 'R', 'B', 'D', 'В', 'Н', 'У', 'Х');

update ip_objects.ip_object set object_type = 'N' where object_type = 'D';

alter table nomenclatures.object_type rename column backoffice_code to code;

delete from nomenclatures.object_subtype where object_type in ('I', 'R', 'B', 'В', 'D', 'Н', 'У', 'Х');
alter table nomenclatures.object_subtype rename column object_subtype to code;
update ip_objects.ip_object set object_subtype = 'П4' where object_subtype = 'Р4' and object_type = 'P';
update ip_objects.ip_object set object_subtype = 'МП' where object_subtype = 'РМ' and object_type = 'P';
update ip_objects.ip_object set object_subtype = 'ПТ' where object_subtype = 'РП' and object_type = 'P';
update ip_objects.ip_object set object_subtype = 'РА' where object_subtype = 'АС' and object_type = 'P';
delete from nomenclatures.object_subtype where object_type = 'P' and code = 'РП';
delete from nomenclatures.object_subtype where object_type = 'P' and code = 'АС';
delete from nomenclatures.object_subtype where object_type = 'P' and code = 'Р4';
delete from nomenclatures.object_subtype where object_type = 'P' and code = 'РМ';
delete from nomenclatures.object_subtype where object_type = 'D';
update ip_objects.ip_object set object_subtype = 'ИМ' where object_subtype = 'РИ' and object_type = 'N';
update ip_objects.ip_object set object_subtype = 'СМ' where object_subtype = 'РС' and object_type = 'N';
update ip_objects.ip_object set object_subtype = 'КМ' where object_subtype = 'РК' and object_type = 'N';
delete from nomenclatures.object_subtype where object_type = 'N' and code in ('РИ', 'РС', 'РК');

update ip_objects.ip_object set object_subtype = 'ИМ' where object_subtype = 'РМ' and object_type in ('N', 'D');

update ip_objects.ip_object set object_subtype = 'ПМ' where object_subtype = 'РУ' and object_type = 'U';
update ip_objects.ip_object set object_subtype = 'ПТ' where object_subtype = 'РТ' and object_type = 'U';
update ip_objects.ip_object set object_subtype = 'КМ' where object_subtype = 'РК' and object_type = 'U';
update ip_objects.ip_object set object_subtype = 'РА' where object_subtype = 'АС' and object_type = 'U';
delete from nomenclatures.object_subtype where object_type = 'U' and code in ('РУ', 'РТ', 'РК', 'АС');

update ip_objects.ip_object set object_subtype = 'ПТПМ' where object_subtype = 'ПТ' and object_type = 'U';
update nomenclatures.object_subtype set code = 'ПТПМ' where code = 'ПТ' and object_type = 'U';




alter table ip_objects.ip_single_design drop column object_type;


alter table nomenclatures.object_subtype
    add constraint object_subtype_pk
        primary key (code);

alter table ip_objects.ip_single_design
    add constraint single_design_subtype
        foreign key (object_subtype) references nomenclatures.object_subtype (code);

alter table ip_objects.ip_object
    add constraint object_type_fk
        foreign key (object_type) references nomenclatures.object_type (code);


alter table ip_objects.ip_object
    add constraint object_subtype
        foreign key (object_subtype) references nomenclatures.object_subtype (code);


