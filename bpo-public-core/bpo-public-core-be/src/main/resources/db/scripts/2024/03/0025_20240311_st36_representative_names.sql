--liquibase formatted sql

--changeset ggeorgiev:0025 splitStatements:false
alter table nomenclatures.representative_type add column st36_name varchar(255);
alter table nomenclatures.representative_type add column st36_name_en varchar(255);
update nomenclatures.representative_type set st36_name_en = euipo_representative_kind_code;
update nomenclatures.representative_type set st36_name = description;