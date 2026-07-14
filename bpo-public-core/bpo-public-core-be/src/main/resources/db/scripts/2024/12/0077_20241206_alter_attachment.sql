--liquibase formatted sql

--changeset murlev:0077 splitStatements:false
alter table common.attachment add column description text;
