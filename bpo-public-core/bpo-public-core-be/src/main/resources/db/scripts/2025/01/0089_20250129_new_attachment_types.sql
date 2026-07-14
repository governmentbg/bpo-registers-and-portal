--liquibase formatted sql

--changeset ggeorgiev:0089 splitStatements:false
--validCheckSum: 9:fd4ee16816dad04125bac6689853fdbb
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('A1E', 'Пълнотекстово описание A1', 'Пълнотекстово описание A1', '23');
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('B1E', 'Пълнотекстово описание B1', 'Пълнотекстово описание B1', '24');
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('U1E', 'Пълнотекстово описание U1', 'Пълнотекстово описание U1', '25');
