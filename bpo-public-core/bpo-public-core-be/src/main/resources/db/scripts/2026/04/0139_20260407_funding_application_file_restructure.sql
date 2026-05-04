--liquibase formatted sql

--changeset murlev:0139 splitStatements:false

-- ============================================================
-- New nomenclature: funding_attachment_type
-- ============================================================

CREATE TABLE eservices.funding_attachment_type
(
    code    varchar(20)  NOT NULL CONSTRAINT funding_attachment_type_pk PRIMARY KEY,
    name    varchar(200) NOT NULL,
    name_en varchar(200),
    ord_num integer
);

INSERT INTO eservices.funding_attachment_type (code, name, name_en, ord_num) VALUES
    ('OFFICIAL',     'Служебни документи',             'Official Documents',          1),
    ('DECLARATIONS', 'Приложени декларации и справки',  'Declarations and References', 2),
    ('OTHER',        'Други документи',                 'Other Documents',             3);

-- ============================================================
-- Restructure funding_application_file to use common.attachment
-- Replaces custom file columns with reference to EAttachment
-- Old rows are incompatible with the new structure, so the
-- table is truncated before the restructure.
-- ============================================================

ALTER TABLE eservices.funding_application_file DROP COLUMN IF EXISTS section_number;
ALTER TABLE eservices.funding_application_file DROP COLUMN IF EXISTS file_position;
ALTER TABLE eservices.funding_application_file DROP COLUMN IF EXISTS file_name;
ALTER TABLE eservices.funding_application_file DROP COLUMN IF EXISTS file_uuid;
ALTER TABLE eservices.funding_application_file DROP COLUMN IF EXISTS uploaded_date;

ALTER TABLE eservices.funding_application_file
    ADD COLUMN attachment_id integer NOT NULL
        CONSTRAINT funding_application_file_att_fk REFERENCES common.attachment (id);

ALTER TABLE eservices.funding_application_file
    ADD COLUMN funding_attachment_type_code varchar(20) NOT NULL
        CONSTRAINT funding_application_file_fat_fk REFERENCES eservices.funding_attachment_type (code);
