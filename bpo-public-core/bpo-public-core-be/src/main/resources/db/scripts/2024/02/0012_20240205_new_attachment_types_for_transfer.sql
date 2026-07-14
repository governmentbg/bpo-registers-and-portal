--liquibase formatted sql

--changeset ggeorgiev:0012 splitStatements:false
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('T3C', 'Описание за публикация Т3 (коригиран)', 'Описание за публикация Т3 (коригиран)', '16');
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('T4C', 'Описание за публикация Т4 (коригиран)', 'Описание за публикация Т4 (коригиран)', '17');
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('T6C', 'Описание за публикация Т6 (коригиран)', 'Описание за публикация Т6 (коригиран)', '18');
