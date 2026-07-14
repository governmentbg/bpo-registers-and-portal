--liquibase formatted sql

--changeset ggeorgiev:0022 splitStatements:false

delete from nomenclatures.object_subtype where object_type = 'A';
delete from nomenclatures.object_subtype where object_type = 'N' and code = 'РМ';
INSERT INTO nomenclatures.object_subtype (object_type, code, description, description_en) VALUES ('P', 'АС', 'Авторско свидетелство', 'Авторско свидетелство');
update ip_objects.ip_object set object_subtype = 'АС' where object_subtype = 'РА' and object_type = 'P';
delete from nomenclatures.object_subtype where code = 'РА' and object_type = 'P';

update ip_objects.ip_object set object_subtype = 'ПД' where object_subtype = 'РД' and object_type = 'Д';
delete from nomenclatures.object_subtype where code = 'РД' and object_type = 'Д';

alter table nomenclatures.object_subtype
    add constraint object_subtype_object_type_fk
        foreign key (object_type) references nomenclatures.object_type (code);




create table nomenclatures.backoffice_object_type_map
(
    backoffice_application_type           varchar(50) not null
        primary key,
    object_type                           varchar(50) not null
);
alter table nomenclatures.backoffice_object_type_map
    add constraint backoffice_object_type_map_object_type_fk
        foreign key (object_type) references nomenclatures.object_type (code);

insert into nomenclatures.backoffice_object_type_map(backoffice_application_type, object_type) VALUES('SPC','S');
insert into nomenclatures.backoffice_object_type_map(backoffice_application_type, object_type) VALUES('АСД','P');
insert into nomenclatures.backoffice_object_type_map(backoffice_application_type, object_type) VALUES('ГО','Г');
insert into nomenclatures.backoffice_object_type_map(backoffice_application_type, object_type) VALUES('ЕВ','T');
insert into nomenclatures.backoffice_object_type_map(backoffice_application_type, object_type) VALUES('ЕД','Е');
insert into nomenclatures.backoffice_object_type_map(backoffice_application_type, object_type) VALUES('НМ','N');
insert into nomenclatures.backoffice_object_type_map(backoffice_application_type, object_type) VALUES('ПД','Д');
insert into nomenclatures.backoffice_object_type_map(backoffice_application_type, object_type) VALUES('ПМ','U');
insert into nomenclatures.backoffice_object_type_map(backoffice_application_type, object_type) VALUES('ПТ','P');
insert into nomenclatures.backoffice_object_type_map(backoffice_application_type, object_type) VALUES('РД','Д');
insert into nomenclatures.backoffice_object_type_map(backoffice_application_type, object_type) VALUES('РМ','N');
insert into nomenclatures.backoffice_object_type_map(backoffice_application_type, object_type) VALUES('РНМ','N');
insert into nomenclatures.backoffice_object_type_map(backoffice_application_type, object_type) VALUES('СП','С');


create table nomenclatures.backoffice_object_subtype_map
(
    backoffice_application_type           varchar(50) not null,
    backoffice_application_subtype           varchar(50) not null,
    object_subtype                           varchar(50) not null,
    constraint backoffice_object_subtype_map_pk primary key (backoffice_application_type, backoffice_application_subtype)
);


alter table nomenclatures.backoffice_object_subtype_map
    add constraint backoffice_object_subtype_map_object_subtype_fk
        foreign key (object_subtype) references nomenclatures.object_subtype (code);

insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('SPC','ЛП', 'ЛП');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('SPC','РЗ', 'РЗ');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('АСД','АС', 'АС');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('АСД','ДО', 'ДО');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('АСД','РА', 'АС');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ГО','ГУ', 'ГУ');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ГО','НП', 'НП');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ЕВ','ЕВ', 'ЕВ');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ЕД','ГС', 'ГС');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ЕД','И', 'И');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ЕД','К', 'К');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ЕД','КЗ', 'КЗ');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ЕД','О', 'О');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ЕД','ПШ', 'ПШ');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ЕД','ЧИ', 'ЧИ');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('НМ','ИМ', 'ИМ');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('НМ','КМ', 'КМ');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('НМ','СМ', 'СМ');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ПД','ПД', 'ПД');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ПМ','TП', 'TП');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ПМ','ММ', 'ММ');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ПМ','ПЕ', 'ПЕ');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ПМ','ПМ', 'ПМ');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ПМ','ПП', 'ПП');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ПМ','ПС', 'ПС');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ПМ','ПТ', 'ПТПМ');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ПМ','РТ', 'ПТПМ');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ПМ','РУ', 'ПМ');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ПМ','ТМ', 'ТМ');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ПТ','МП', 'МП');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ПТ','П4', 'П4');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ПТ','ПТ', 'ПТ');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ПТ','Р4', 'П4');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ПТ','РМ', 'МП');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('ПТ','РП', 'ПТ');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('РД','РД', 'ПД');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('РМ','РМ', 'ИМ');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('РНМ','РИ', 'ИМ');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('РНМ','РК', 'КМ');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('РНМ','РС', 'СМ');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('СП','ДЛ', 'ДЛ');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('СП','ПЖ', 'ПЖ');
insert into nomenclatures.backoffice_object_subtype_map(backoffice_application_type, backoffice_application_subtype, object_subtype) VALUES('СП','СР', 'СР');