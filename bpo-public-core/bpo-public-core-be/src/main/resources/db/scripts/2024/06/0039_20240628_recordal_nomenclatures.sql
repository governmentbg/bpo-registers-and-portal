--liquibase formatted sql

--changeset ggeorgiev:0039 splitStatements:false
--validCheckSum: 9:bec47a0e684f2aaa3ce6fd64dd9e2e80
alter table nomenclatures.recordal_type add column group_type varchar(50);
alter table nomenclatures.recordal_type
    add constraint recordal_type_group_type_fk
        foreign key (group_type) references nomenclatures.recordal_group_type;
alter table nomenclatures.recordal_type alter column group_type set not null;
alter table ip_object_recordals.recordals drop column group_type;

INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('NON_EXCLUSIVE_LICENCE_REGISTRATION', 'Вписване на неизключителна лицензия', 'Non-exclusive licence registration', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('AUTH_OR_CHANGE_OF_REPRESENTATIVE', 'Упълномощаване/промяна на пълномощник', 'Authorization / change of representative', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('APPLICANT_OR_OWNER_ADDRESS_AND_NAME_CHANGE', 'Промяна на име и/или адрес на заявител/притежател', 'Applicant/Owner address or/and name change', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('APPLICANT_ERROR_CORRECTION_IN_APP_OR_REQUEST', 'Искане за поправка на допусната от заявителя техническа грешка в заявка или искане', 'Correction of an error made by the applicant in an application or a request', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('REFUSAL_OF_EXECUTION_REQUEST', 'Искане за изоставяне на изпълнение', 'Request for refusal of execution', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('BANKRUPTCY_ESTATE_DEREGISTRATION_REQUEST', 'Отписване от масата на несъстоятелността', 'Request for deregistration from the bankruptcy estate', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('SPECIAL_PLEDGE_CHANGES', 'Промени в обстоятелствата на вписан особен залог', 'Special pledge changes in circumstances', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('PARTIAL_REGISTRATION_RENEWAL', 'Частично подновяване на регистрацията', 'Registration partial renewal', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('REQUEST_FOR_ENFORCEMENT', 'Искане за вписване на принудително изпълнение', 'Request for enforcement', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('PROTECTIVE_MEASURES_REGISTRATION', 'Вписване на обезпечителни мерки', 'Protective measures registration', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('TRANSFER', 'Прехвърляне', 'Transfer', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('PROTECTIVE_MEASURES_TERMINATION', 'Прекратяване на обезпечителни мерки', 'Protective measures termination', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('ENTERED_CIRCUMS_CHANGES_REQUEST', 'Искане за вписване на други промени във вписани обстоятелства', 'Request for registration of other changes in entered circumstances', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('LICENSE_TERMINATION', 'Прекратяване на лицензия', 'License termination', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('SPECIAL_PLEDGE_REGISTRATION', 'Вписване на особен залог', 'Special pledge registration', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('DISCLAIMER', 'Отказ от право', 'Disclaimer', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('PATENT_OFFICE_ERROR_CORRECTION', 'Искане за поправка на допусната от Патентно ведомство техническа грешка', 'Correction of an error made by the patent office', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('ERROR_CORRECTION', 'Искане за поправка на техническа грешка', 'Correction of an error', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('VALIDITY_EXTENSION', 'Продължаване срока на действие', 'Extension of validity', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('OBJECTION', 'Възражение', 'Objection', 'OPPOSITION');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('INVALIDITY_REQUEST', 'Искане за обявяване на недействителност', 'Request for invalidity', 'CANCELLATION');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('PARTIAL_TRANSFER', 'Частично прехвърляне', 'Partial transfer', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('EXCLUSIVE_LICENCE_REGISTRATION', 'Вписване на изключителна лицензия', 'Exclusive licence registration', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('SPECIAL_PLEDGE_RENEWAL', 'Подновяване на особен залог', 'Special pledge renewal', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('CORRESPONDENCE_ADDRESS_CHANGE', 'Промяна на адрес за кореспонденция', 'Correspondence address change', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('BANKRUPTCY_ESTATE_ENTRY', 'Вписване в масата на несъстоятелността', 'Entry in the bankruptcy estate', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('SPECIAL_PLEDGE_TERMINATION', 'Прекратяване на особен залог', 'Special pledge termination', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('REGISTRATION_RENEWAL', 'Подновяване на регистрацията', 'Registration renewal', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('APPLICANT_ERROR_CORRECTION_IN_REGISTER_OR_CERT', 'Искане за поправка на допусната от заявителя техническа грешка в регистър или защитен документ', 'Correction of an error made by the applicant in a register or a certificate', 'RECORDAL');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('OPPOSITION', 'Опозиция', 'Opposition', 'OPPOSITION');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('REQUEST_FOR_DELETION', 'Искане за заличаване', 'Request for deletion', 'CANCELLATION');
INSERT INTO nomenclatures.recordal_type (id, description, description_en, group_type) VALUES ('ANNULMENT_REQUEST', 'Искане за отмяна', 'Request for annulment', 'CANCELLATION');


INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('LICENSE_SUBLICENSE_GRANT_RIGHT', 'Право на предоставяне на сублицензия', 'Sublicense rights');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('DEADLINE', 'Срок до', 'Deadline');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('DESCRIPTION', 'Описание', 'Description');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('RENEWAL_FOR', 'Подновяване за', 'Renewal for');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('NEW_GOODS_AND_SERVICES', 'Нови стоки и услуги', 'New goods and services');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('OLD_GOODS_AND_SERVICES', 'Стари стоки и услуги', 'Old goods and services');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('BANKRUPTCY_CASE_NUMBER', 'Номер на делото', 'Case number');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('INVALIDATION_DATE', 'Дата на заличаване', 'Invalidation date');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('DECISION', 'Решение ', 'Decision');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('EFFECTIVE_DATE', 'Дата на вписване', 'Recordal date');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('PLEDGE_PENALTY', 'Неустойки', 'Forfeit');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('BANKRUPTCY_COURT_NAME', 'Съд, в който е образувано делото', 'Court');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('SECURITY_MEASURE_PROHIBITION_RIGHTS_MANAGE', 'Забрана за разпореждане с правата върху марка от притежателя или лицензополучателя', 'Owner and grantee - prohibition of disposition ');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('LICENSE_SUBLICENSE_IDENTIFIER', 'Идентификатор на лицензия', 'License id');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('TRANSFER_INVALIDATION_REASON', 'Основание за заличаване', 'Invalidity grounds');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('PLEDGE_INTEREST', 'Лихви', 'Dividend');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('LICENSE_TERRITORIAL_RESTRICTION', 'Териториално ограничение', 'Territory restriction');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('LICENSE_TYPE', 'Вид на лицензията', 'License type');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('GOODS_AND_SERVICES', 'Стоки и услуги', 'Goods and services');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('PLEDGE_AMOUNT', 'Сума', 'Amount');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('LICENSE_EXPIRATION_DATE_TYPE', 'Срок', 'Term');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('SECURITY_MEASURE_PROHIBITION_RIGHTS_USE', 'Забрана за използване на правата върху марка от притежателя или лицензополучателя', 'Owner and grantee - prohibition of use ');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('PLEDGE_ADDITIONAL_DATA', 'Условия', 'Terms');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('LICENSE_SUBLICENSE', 'Сублицензия', 'Sublicense');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('PLEDGE_SEQUENCE_NUMBER', 'Пореден номер', 'Order number');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('LICENSE_TERRITORIAL_SCOPE', 'Териториален обхват', 'Territorial scope');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('LICENSE_IS_COMPULSORY', 'Принудителна', 'Compulsory');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('LICENSE_CONTRACT_NUMBER_AND_DATE', 'Номер и дата на лицензионен договор', 'License contract number and date');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('PLEDGE_DESCRIPTION', 'Описание на обезпеченото вземане', 'Secured receivable description');
INSERT INTO nomenclatures.recordal_detail_type (id, description, description_en) VALUES ('RENEWAL_NEW_EXPIRATION_DATE', 'Нов срок на закрила', 'New expiration date');
