--liquibase formatted sql

--changeset ndimov:0082 splitStatements:false
alter table common.panel drop column panel_type;
create schema portal;
alter table common.panel set schema portal;
alter table common.service_definition set schema portal;

CREATE TABLE portal.portal_text (
                                               id VARCHAR(255) NOT NULL PRIMARY KEY,
                                               description_bg TEXT,
                                               description_en TEXT,
                                               dynamic_content int,
                                               active_from TIMESTAMP,
                                               active_to TIMESTAMP
);