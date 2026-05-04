--liquibase formatted sql

--changeset murlev:0110 splitStatements:false
delete from nomenclatures.country where code = 'NE'