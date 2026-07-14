--liquibase formatted sql

--changeset ggeorgiev:0086 splitStatements:false
alter table ip_objects.ip_patent_cpc_classes add column class_order int not null default 0;
alter table ip_objects.ip_patent_cpc_classes alter column class_order drop default;

alter table ip_objects.ip_patent_ipc_classes add column class_order int not null default 0;
alter table ip_objects.ip_patent_ipc_classes alter column class_order drop default;