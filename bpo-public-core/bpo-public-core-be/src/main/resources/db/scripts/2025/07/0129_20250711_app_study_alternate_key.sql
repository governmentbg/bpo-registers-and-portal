--liquibase formatted sql

--changeset mmurlev:0129 splitStatements:false
ALTER TABLE eservices.application_study
    ADD COLUMN alternate_id varchar;

UPDATE eservices.application_study
SET alternate_id = gen_random_uuid()::varchar;