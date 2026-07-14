--liquibase formatted sql

--changeset mnakova:0020 splitStatements:false
update nomenclatures.backoffice_status_map set bpo_online_status_en = 'EP Request in progress' where bpo_online_status = 'ЕП Искане в производство';
