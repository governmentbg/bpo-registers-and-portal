--liquibase formatted sql

--changeset raneva:114 splitStatements:false
UPDATE nomenclatures.representative_type
    SET euipo_representative_kind_code='Intellectual Property Representative'
    WHERE representative_type='IP';