--liquibase formatted sql

--changeset ndimov:0098 splitStatements:false

ALTER TABLE portal.admin_panel DROP COLUMN IF EXISTS index_id;