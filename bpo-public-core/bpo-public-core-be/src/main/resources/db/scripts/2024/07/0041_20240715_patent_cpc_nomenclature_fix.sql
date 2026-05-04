--liquibase formatted sql

--changeset ggeorgiev:0041 splitStatements:false
alter table nomenclatures.patent_cpc_class alter column group_code type varchar(4);