--liquibase formatted sql

--changeset ndimov:0099 splitStatements:false
alter table portal.admin_panel add constraint admin_panel_parent_id_check
    check (parent_id != id);

ALTER TABLE portal.panel add constraint panel_parent_id_check
    check ( parent_id != id );