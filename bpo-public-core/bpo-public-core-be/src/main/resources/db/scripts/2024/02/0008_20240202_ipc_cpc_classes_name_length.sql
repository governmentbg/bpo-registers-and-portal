--liquibase formatted sql

--changeset ggeorgiev:0008 splitStatements:false
alter table nomenclatures.patent_ipc_class alter column name type varchar(4000);
alter table nomenclatures.patent_ipc_class alter column name_en type varchar(4000);

alter table nomenclatures.patent_cpc_class alter column name type varchar(4000);
alter table nomenclatures.patent_cpc_class alter column name_en type varchar(4000);