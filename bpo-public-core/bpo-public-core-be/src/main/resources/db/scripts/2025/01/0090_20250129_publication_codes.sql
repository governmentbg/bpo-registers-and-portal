--liquibase formatted sql

--changeset ggeorgiev:0090 splitStatements:false
UPDATE nomenclatures.publication_section set publ_code = 'A' where idsection = 3;
UPDATE nomenclatures.publication_section set publ_code = 'B1' where idsection = 4;
UPDATE nomenclatures.publication_section set publ_code = 'U1' where idsection = 14;