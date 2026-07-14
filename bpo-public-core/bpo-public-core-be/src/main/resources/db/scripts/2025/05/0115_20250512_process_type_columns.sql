--liquibase formatted sql

--changeset ggeorgiev:0115 splitStatements:false
alter table nomenclatures.backoffice_object_type_map add column process_type varchar(4);
update nomenclatures.backoffice_object_type_map set process_type = 2 where object_type = 'N';
update nomenclatures.backoffice_object_type_map set process_type = 12 where object_type = 'S';
update nomenclatures.backoffice_object_type_map set process_type = 30 where backoffice_application_type = 'АСД';
update nomenclatures.backoffice_object_type_map set process_type = 10 where backoffice_application_type = 'ПТ';
update nomenclatures.backoffice_object_type_map set process_type = 13 where object_type = 'Г';
update nomenclatures.backoffice_object_type_map set process_type = 14 where object_type = 'T';
update nomenclatures.backoffice_object_type_map set process_type = 15 where object_type = 'Д';
update nomenclatures.backoffice_object_type_map set process_type = 11 where object_type = 'U';
update nomenclatures.backoffice_object_type_map set process_type = 16 where object_type = 'С';
alter table nomenclatures.backoffice_object_type_map alter column process_type set not null;
alter table nomenclatures.backoffice_status_map add column process_type varchar(4);
update nomenclatures.backoffice_status_map set process_type = split_part(code, '-', 1);
alter table nomenclatures.backoffice_status_map alter column process_type set not null;