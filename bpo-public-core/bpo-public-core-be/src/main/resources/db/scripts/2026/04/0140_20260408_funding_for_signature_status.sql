--liquibase formatted sql

--changeset murlev:1140 splitStatements:false
INSERT INTO eservices.funding_application_status (code, name, name_en, ord_num)
VALUES ('FOR_SIGNATURE', 'За подпис', 'For Signature', 3)
ON CONFLICT (code) DO NOTHING;

ALTER TABLE eservices.funding_application
    ADD COLUMN IF NOT EXISTS signed_file_uuid  VARCHAR(255),
    ADD COLUMN IF NOT EXISTS signed_file_status VARCHAR(100);
