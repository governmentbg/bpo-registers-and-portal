--liquibase formatted sql

--changeset ndimov:0068
DROP TABLE nomenclatures.legal_decision_object_type;
ALTER TABLE legal_decisions.document ADD COLUMN object_type varchar(50) not null;
ALTER TABLE legal_decisions.document ADD CONSTRAINT document_object_type_fk foreign key (object_type)
references nomenclatures.object_type (code);