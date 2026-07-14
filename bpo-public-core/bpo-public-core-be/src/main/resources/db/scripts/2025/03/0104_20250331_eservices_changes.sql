--liquibase formatted sql

--changeset murlev:0104 splitStatements:false
UPDATE eservices.application_submit_type SET name = 'Подадена' WHERE code = 'SAVED';
UPDATE eservices.application_submit_type SET name = 'Приета' WHERE code = 'COMPLETED';
ALTER TABLE eservices.application_study ADD COLUMN filing_date timestamp with time zone;
UPDATE eservices.application_study SET filing_date = created_date WHERE filing_date is null;