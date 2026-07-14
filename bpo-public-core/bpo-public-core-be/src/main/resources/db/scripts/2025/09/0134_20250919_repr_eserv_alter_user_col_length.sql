--liquibase formatted sql

--changeset raneva:0134 splitStatements:false

DROP VIEW IF EXISTS repr_eservices.vw_all_requests;

ALTER TABLE repr_eservices.exam ALTER COLUMN created_user_id TYPE varchar(100);
ALTER TABLE repr_eservices.course ALTER COLUMN created_user_id TYPE varchar(100);
ALTER TABLE repr_eservices.change_partnership ALTER COLUMN created_user_id TYPE varchar(100);
ALTER TABLE repr_eservices.change_physical_person ALTER COLUMN created_user_id TYPE varchar(100);
ALTER TABLE repr_eservices.register_partnership ALTER COLUMN created_user_id TYPE varchar(100);
ALTER TABLE repr_eservices.register_physical_person ALTER COLUMN created_user_id TYPE varchar(100);

