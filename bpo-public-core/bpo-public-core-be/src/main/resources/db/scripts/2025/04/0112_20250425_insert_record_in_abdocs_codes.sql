--liquibase formatted sql

--changeset murlev:0112 splitStatements:false
insert into eservices.abdocs_codes (code,abdocs_code) values ('TM_OBJECT_TYPE',62)