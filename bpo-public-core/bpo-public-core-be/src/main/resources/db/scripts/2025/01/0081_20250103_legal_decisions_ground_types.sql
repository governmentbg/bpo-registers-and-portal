--liquibase formatted sql

--changeset ggeorgiev:0081 splitStatements:false
create table legal_decisions.document_ground_types (
    document_id varchar(255) not null,
    legal_ground_type int not null,
    primary key (document_id, legal_ground_type),
    constraint dgt_document_fk foreign key (document_id)
       references legal_decisions.document,
    constraint dgt_legal_ground_type_fk foreign key (legal_ground_type)
       references nomenclatures.legal_decision_ground_type
);
create index dgt_document_idx
    on legal_decisions.document_ground_types (document_id);
create index dgt_legal_ground_type_idx
    on legal_decisions.document_ground_types (legal_ground_type);

create index document_object_type_idx
    on legal_decisions.document (object_type);

insert into legal_decisions.document_ground_types (document_id, legal_ground_type)
select id, legal_ground_type from legal_decisions.document where legal_ground_type is not null;
alter table legal_decisions.document drop column legal_ground_type;