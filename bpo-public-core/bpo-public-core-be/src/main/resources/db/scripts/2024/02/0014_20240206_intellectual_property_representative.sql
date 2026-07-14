--liquibase formatted sql

--changeset ggeorgiev:0014 splitStatements:false
alter table nomenclatures.representative_type add column agent_code_from int;
alter table nomenclatures.representative_type add column agent_code_to int;
UPDATE nomenclatures.representative_type set agent_code_to = 9999 where representative_type = 'AG';

INSERT INTO nomenclatures.representative_type (representative_type, description, ipas_partnership_type, has_agent_code, euipo_representative_kind_code, ipas_representative_type, description_en, agent_code_from)
VALUES ('IP', 'Представител по интелектуална собственост', null, 1, null, 'AG', null, 10000);
