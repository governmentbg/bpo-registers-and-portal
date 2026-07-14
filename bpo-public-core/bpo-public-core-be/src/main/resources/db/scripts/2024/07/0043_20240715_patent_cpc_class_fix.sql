--liquibase formatted sql

--changeset ggeorgiev:0043 splitStatements:false
alter table ip_objects.ip_patent_cpc_classes alter column group_code type varchar(4);