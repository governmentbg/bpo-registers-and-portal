--liquibase formatted sql

--changeset ndimov:0109 splitStatements:false
INSERT INTO nomenclatures.dsview_publication (publication_section, publication_code_dsview) VALUES (176, 'Design application published (before 2011)');
INSERT INTO nomenclatures.dsview_publication (publication_section, publication_code_dsview) VALUES (177, 'Changes (legacy)');