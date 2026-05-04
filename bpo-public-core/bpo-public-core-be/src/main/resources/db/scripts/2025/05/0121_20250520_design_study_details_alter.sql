--liquibase formatted sql

--changeset murlev:0121 splitStatements:false
ALTER TABLE eservices.application_design_study_details ALTER COLUMN execution_type_code TYPE varchar(25);