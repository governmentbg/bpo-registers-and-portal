--liquibase formatted sql

--changeset murlev:0138 splitStatements:false

-- Categories for funding application sections 8, 9, 10
INSERT INTO nomenclatures.attachment_category (code, name, name_en) VALUES ('FD_8', 'Служебни документи', 'Official documents');
INSERT INTO nomenclatures.attachment_category (code, name, name_en) VALUES ('FD_9', 'Декларации и справки', 'Declarations');
INSERT INTO nomenclatures.attachment_category (code, name, name_en) VALUES ('FD10', 'Други документи', 'Other documents');

-- Section 8 – Служебни документи
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('F8_1', 'Справка за актуално състояние на МСП', 'Current status report of SME', null);
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('F8_2', 'Справка за наличие/липса на задължения (НАП)', 'Tax liabilities report (NRA)', null);
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('F8_3', 'Справка по ЕИК за наказателни постановления', 'Report on penal decrees by UIC', null);
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('F8_4', 'Свидетелство за съдимост', 'Certificate of criminal record', null);

-- Section 9 – Приложени декларации и справки
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('F9_1', 'Декларация по чл. 3 и чл. 4 от ЗМСП', 'Declaration under Art. 3 and 4 of SMA', null);
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('F9_2', 'Декларация за минимални помощи', 'Declaration for de minimis aid', null);
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('F9_3', 'Справка за обобщените параметри на предприятието', 'Summary parameters of the enterprise', null);
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('F9_4', 'Декларация за съгласие с условията', 'Declaration of consent with the conditions', null);

-- Section 10 – Други документи
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('FA_1', 'Удостоверение за банкова сметка на МСП', 'Bank account certificate of SME', null);
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('FA_2', 'Изрично пълномощно', 'Explicit power of attorney', null);
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('FA_3', 'Удостоверение от НСИ за код на дейността', 'NSI certificate for activity code', null);
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('FA_4', 'Книга за акционерите', 'Shareholders register', null);
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('FA_5', 'Дружествен договор', 'Articles of association', null);
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('FA_6', 'Книга за акционерите и устав', 'Shareholders register and articles', null);
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('FA_7', 'Справка за разпределение на капитала', 'Capital distribution report', null);
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('FA_8', 'Отчет за приходите, разходите и Баланс', 'Income statement and balance sheet', null);
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('FA_9', 'Отчет за заетите лица и разходи за труд', 'Report on employees and labour costs', null);

-- Link types to categories
INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('F8_1', 'FD_8');
INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('F8_2', 'FD_8');
INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('F8_3', 'FD_8');
INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('F8_4', 'FD_8');
INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('F9_1', 'FD_9');
INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('F9_2', 'FD_9');
INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('F9_3', 'FD_9');
INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('F9_4', 'FD_9');
INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('FA_1', 'FD10');
INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('FA_2', 'FD10');
INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('FA_3', 'FD10');
INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('FA_4', 'FD10');
INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('FA_5', 'FD10');
INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('FA_6', 'FD10');
INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('FA_7', 'FD10');
INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('FA_8', 'FD10');
INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('FA_9', 'FD10');
