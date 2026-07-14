--liquibase formatted sql

--changeset ggeorgiev:0070
create table nomenclatures.single_design_type (
    code varchar(50) not null primary key ,
    description varchar(255) not null,
    description_en varchar(255) not null
);
INSERT INTO nomenclatures.single_design_type (code, description, description_en) VALUES ('ГС', 'Графичен символ', 'Графичен символ');
INSERT INTO nomenclatures.single_design_type (code, description, description_en) VALUES ('И', 'Изделие', 'Изделие');
INSERT INTO nomenclatures.single_design_type (code, description, description_en) VALUES ('К', 'Комплект', 'Комплект');
INSERT INTO nomenclatures.single_design_type (code, description, description_en) VALUES ('КЗ', 'Композиция', 'Композиция');
INSERT INTO nomenclatures.single_design_type (code, description, description_en) VALUES ('О', 'Опаковка', 'Опаковка');
INSERT INTO nomenclatures.single_design_type (code, description, description_en) VALUES ('ПШ', 'Печатен Шрифт', 'Печатен Шрифт');
INSERT INTO nomenclatures.single_design_type (code, description, description_en) VALUES ('ЧИ', 'Част от изделие', 'Част от изделие');

create table nomenclatures.backoffice_single_design_type_map (
    backoffice_application_subtype varchar(50) not null primary key,
    single_design_type varchar(50) not null
);
INSERT INTO nomenclatures.backoffice_single_design_type_map (backoffice_application_subtype, single_design_type) VALUES ('ГС', 'ГС');
INSERT INTO nomenclatures.backoffice_single_design_type_map (backoffice_application_subtype, single_design_type) VALUES ('И', 'И');
INSERT INTO nomenclatures.backoffice_single_design_type_map (backoffice_application_subtype, single_design_type) VALUES ('К', 'К');
INSERT INTO nomenclatures.backoffice_single_design_type_map (backoffice_application_subtype, single_design_type) VALUES ('КЗ', 'КЗ');
INSERT INTO nomenclatures.backoffice_single_design_type_map (backoffice_application_subtype, single_design_type) VALUES ('О', 'О');
INSERT INTO nomenclatures.backoffice_single_design_type_map (backoffice_application_subtype, single_design_type) VALUES ('ПШ', 'ПШ');
INSERT INTO nomenclatures.backoffice_single_design_type_map (backoffice_application_subtype, single_design_type) VALUES ('ЧИ', 'ЧИ');

alter table nomenclatures.backoffice_single_design_type_map
    add constraint backoffice_single_design_type_map_single_design_type_fk
        foreign key (single_design_type) references nomenclatures.single_design_type;



alter table ip_objects.ip_single_design rename column object_subtype to single_design_type;
alter table ip_objects.ip_single_design
    drop constraint single_design_subtype;

alter table ip_objects.ip_single_design
    add constraint single_design_type_fk
        foreign key (single_design_type) references nomenclatures.single_design_type;
delete from nomenclatures.backoffice_object_subtype_map where backoffice_application_type = 'ЕД';
delete from nomenclatures.backoffice_object_type_map where backoffice_application_type = 'ЕД';
delete from nomenclatures.object_subtype where object_type = 'Е';
delete from nomenclatures.object_type where code = 'Е';




