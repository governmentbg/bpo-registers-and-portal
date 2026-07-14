--liquibase formatted sql

--changeset ggeorgiev:0011 splitStatements:false

UPDATE nomenclatures.country set code = '1' where code = '1 ';