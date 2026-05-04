--liquibase formatted sql

--changeset ggeorgiev:0005 splitStatements:false

alter table nomenclatures.patent_cpc_class alter column name type varchar(500);
alter table nomenclatures.patent_cpc_class alter column name_en type varchar(500);

alter table nomenclatures.patent_ipc_class alter column name  type varchar(500);
alter table nomenclatures.patent_ipc_class alter column name_en  type varchar(500);
