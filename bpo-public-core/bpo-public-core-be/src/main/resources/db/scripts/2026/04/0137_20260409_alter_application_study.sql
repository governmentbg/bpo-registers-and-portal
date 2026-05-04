--liquibase formatted sql

--changeset raneva:0137 splitStatements:false

alter table eservices.application_study alter column name type varchar(500);