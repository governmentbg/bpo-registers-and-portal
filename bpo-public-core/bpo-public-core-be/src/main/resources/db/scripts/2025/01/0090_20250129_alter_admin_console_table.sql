--liquibase formatted sql

--changeset ndimov:0090 splitStatements:false
alter table bpo_registers.portal.admin_panel
    alter column title drop not null;
alter table bpo_registers.portal.admin_panel
    alter column title_en drop not null;
