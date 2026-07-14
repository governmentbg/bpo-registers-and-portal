--liquibase formatted sql

--changeset murlev:0120 splitStatements:false
update eservices.execution_type set name = 'абонаментно' where code = 'SUBSCRIPTION';