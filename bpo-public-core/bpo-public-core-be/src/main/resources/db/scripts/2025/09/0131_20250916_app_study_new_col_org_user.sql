--liquibase formatted sql

--changeset raneva:0131 splitStatements:false
ALTER TABLE eservices.application_study
    ADD COLUMN created_org_user varchar(100);

UPDATE eservices.application_study SET created_org_user = created_user WHERE created_org_user IS NULL AND created_user IS NOT NULL;

