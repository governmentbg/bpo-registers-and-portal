--liquibase formatted sql

--changeset ggeorgiev:0020 splitStatements:false
alter table nomenclatures.object_subtype rename column backoffice_application_type to object_type;
alter table nomenclatures.object_subtype rename column backoffice_application_subtype to object_subtype;

UPDATE nomenclatures.object_subtype set object_type = 'R' where object_type = 'ET';
UPDATE nomenclatures.object_subtype set object_type = 'S' where object_type = 'SPC';
UPDATE nomenclatures.object_subtype set object_type = 'P' where object_type = 'АСД';
UPDATE nomenclatures.object_subtype set object_type = 'Г' where object_type = 'ГО';
UPDATE nomenclatures.object_subtype set object_type = 'В' where object_type = 'ГОВ';
UPDATE nomenclatures.object_subtype set object_type = 'T' where object_type = 'ЕВ';
UPDATE nomenclatures.object_subtype set object_type = 'Е' where object_type = 'ЕД';
UPDATE nomenclatures.object_subtype set object_type = 'У' where object_type = 'ЕМД';
UPDATE nomenclatures.object_subtype set object_type = 'A' where object_type = 'ИСД';
UPDATE nomenclatures.object_subtype set object_type = 'Х' where object_type = 'МД';
UPDATE nomenclatures.object_subtype set object_type = 'A' where object_type = 'МК';
UPDATE nomenclatures.object_subtype set object_type = 'Н' where object_type = 'МНП';
UPDATE nomenclatures.object_subtype set object_type = 'I' where object_type = 'МР';
UPDATE nomenclatures.object_subtype set object_type = 'N' where object_type = 'НМ';
UPDATE nomenclatures.object_subtype set object_type = 'Д' where object_type = 'ПД';
UPDATE nomenclatures.object_subtype set object_type = 'U' where object_type = 'ПМ';
UPDATE nomenclatures.object_subtype set object_type = 'A' where object_type = 'ПОП';
UPDATE nomenclatures.object_subtype set object_type = 'A' where object_type = 'ПРП';
UPDATE nomenclatures.object_subtype set object_type = 'P' where object_type = 'ПТ';
UPDATE nomenclatures.object_subtype set object_type = 'Д' where object_type = 'РД';
UPDATE nomenclatures.object_subtype set object_type = 'D' where object_type = 'РМ';
UPDATE nomenclatures.object_subtype set object_type = 'B' where object_type = 'РММ';
UPDATE nomenclatures.object_subtype set object_type = 'N' where object_type = 'РНМ';
UPDATE nomenclatures.object_subtype set object_type = 'A' where object_type = 'СИГ';
UPDATE nomenclatures.object_subtype set object_type = 'С' where object_type = 'СП';
INSERT INTO nomenclatures.object_subtype (object_type, object_subtype, description, description_en, id) VALUES ('N', 'РМ', 'Разделена Марка', 'Разделена Марка', 99);
INSERT INTO nomenclatures.object_type (backoffice_code, description, description_en) VALUES ('Е', 'Единичен дизайн', 'Единичен дизайн');



alter table ip_objects.ip_object add column object_subtype2 varchar(50);
update ip_objects.ip_object o set object_subtype2 = (select object_subtype from nomenclatures.object_subtype os where os.id = o.object_subtype);
alter table ip_objects.ip_object drop column object_subtype;
alter table ip_objects.ip_object rename column object_subtype2 to object_subtype;
alter table ip_objects.ip_object alter column object_subtype set not null;
alter table ip_objects.ip_single_design add column object_type varchar(50);
alter table ip_objects.ip_single_design add column object_subtype varchar(50);
update ip_objects.ip_single_design sd set object_type = (select object_type from nomenclatures.object_subtype os where os.id = sd.design_type);
update ip_objects.ip_single_design sd set object_subtype = (select object_subtype from nomenclatures.object_subtype os where os.id = sd.design_type);
alter table ip_objects.ip_single_design alter column object_type set not null;
alter table ip_objects.ip_single_design alter column object_subtype set not null;
alter table ip_objects.ip_single_design drop column design_type;

drop table nomenclatures.object_type_to_subtype;

ALTER TABLE nomenclatures.object_subtype drop column id;
ALTER TABLE nomenclatures.object_subtype add constraint object_subtype_pk PRIMARY KEY (object_type, object_subtype);
ALTER TABLE ip_objects.ip_object
    ADD CONSTRAINT object_subtype FOREIGN KEY (object_type, object_subtype) REFERENCES nomenclatures.object_subtype(object_type, object_subtype);
ALTER TABLE ip_objects.ip_single_design
    ADD CONSTRAINT single_design_type FOREIGN KEY (object_type) REFERENCES nomenclatures.object_type (backoffice_code);
ALTER TABLE ip_objects.ip_single_design
    ADD CONSTRAINT single_design_subtype FOREIGN KEY (object_type, object_subtype) REFERENCES nomenclatures.object_subtype(object_type, object_subtype);
