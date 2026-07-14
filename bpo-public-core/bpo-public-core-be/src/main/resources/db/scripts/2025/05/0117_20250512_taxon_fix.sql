--liquibase formatted sql

--changeset ggeorgiev:0117 splitStatements:false
--validCheckSum: 9:092067237515d578d90231806c4d1252
update nomenclatures.plant_taxon set taxon_code = taxon_code ||'.'||id where taxon_code not like '%.%';