--liquibase formatted sql

--changeset ggeorgiev:0038 splitStatements:false
alter table ip_object_recordals.recordal_types rename to recordal_type;
alter table ip_object_recordals.recordal_type set schema nomenclatures;
alter table ip_object_recordals.detail_types rename to recordal_detail_type;
alter table ip_object_recordals.recordal_detail_type set schema nomenclatures;
alter table ip_object_recordals.recordal_group_type set schema nomenclatures;