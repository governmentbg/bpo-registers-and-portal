--liquibase formatted sql

--changeset murlev:0065 splitStatements:false
create table nomenclatures.legal_decision_object_type
(
    code  varchar(5)  primary key,
    description text
);

create table legal_decisions.document
(
    id                serial
        primary key,
    doc_type          integer
        references nomenclatures.legal_decision_type,
    doc_date          date,
    object_id           varchar(12),
    doc_num           integer,
    legal_ground_type integer
        constraint legal_ground_type_fk
            references nomenclatures.legal_decision_ground_type,
    title             text
);