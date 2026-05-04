--liquibase formatted sql

--changeset mmurlev:0130 splitStatements:false
ALTER TABLE eservices.address ALTER COLUMN phone TYPE varchar(50);