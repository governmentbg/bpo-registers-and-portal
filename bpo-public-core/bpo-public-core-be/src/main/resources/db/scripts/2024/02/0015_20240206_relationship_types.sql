--liquibase formatted sql

--changeset ggeorgiev:0015 splitStatements:false
INSERT INTO nomenclatures.relationship_type (relationship_typ, application_typ, relationship_name, relationship_name_en, direct_relationship_name, direct_relationship_name_en, inverse_relationship_name, inverse_relationship_name_en)
VALUES ('РСП', 'NONE', 'Разделен сорт/порода', 'Divided variety/breed', 'Разделен сорт/порода на', 'Divided into', 'Разделен сорт/порода от', 'Divided by');

INSERT INTO nomenclatures.relationship_type (relationship_typ, application_typ, relationship_name, relationship_name_en, direct_relationship_name, direct_relationship_name_en, inverse_relationship_name, inverse_relationship_name_en)
VALUES ('ЗАМ', 'WO', 'Заместена с МР', 'Replaced by IR', 'Международна регистрация', 'International registration', 'Национална марка', 'National mark');

INSERT INTO nomenclatures.relationship_type (relationship_typ, application_typ, relationship_name, relationship_name_en, direct_relationship_name, direct_relationship_name_en, inverse_relationship_name, inverse_relationship_name_en)
VALUES ('МР', 'WO', 'МР с базова регистрация БГ марка', 'IR based on BG national mark', 'Международна регистрация', 'International registration', 'Базова регистрация', 'Base registration');
