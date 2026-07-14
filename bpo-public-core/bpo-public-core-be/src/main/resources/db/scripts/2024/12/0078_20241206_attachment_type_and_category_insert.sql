--liquibase formatted sql

--changeset murlev:0078 splitStatements:false
INSERT INTO nomenclatures.attachment_category (code, name, name_en) VALUES ('PS', 'Проучване на изобретение', 'Patent study');
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code, category_code) VALUES ('DESC', 'Описание', 'Description', null, 'PS');
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code, category_code) VALUES ('DRAW', 'Чертежи', 'Drawings', null, 'PS');
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code, category_code) VALUES ('CLAIM', 'Претенции', 'Claims', null, 'PS');
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code, category_code) VALUES ('OTHER', 'Други', 'Others', null, 'PS');


