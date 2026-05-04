--liquibase formatted sql

--changeset ggeorgiev:0071
alter table legal_decisions.document alter column id type varchar(255);
alter table legal_decisions.document alter column id drop default ;
drop sequence if exists legal_decisions.document_id_seq;
alter table legal_decisions.document alter column object_id type varchar(20);
alter table legal_decisions.document alter column object_id set not null;

create sequence if not exists nomenclatures.legal_decision_type_id_seq;
alter table nomenclatures.legal_decision_type alter column id set default nextval('nomenclatures.legal_decision_type_id_seq');
SELECT setval('nomenclatures.legal_decision_type_id_seq', (select max(id) from nomenclatures.legal_decision_type), true);

create sequence if not exists nomenclatures.legal_decision_ground_type_id_seq;
alter table nomenclatures.legal_decision_ground_type alter column id set default nextval('nomenclatures.legal_decision_ground_type_id_seq');
SELECT setval('nomenclatures.legal_decision_ground_type_id_seq', (select max(id) from nomenclatures.legal_decision_ground_type), true);

alter table legal_decisions.document add column attachment_id int;
alter table legal_decisions.document
    add constraint ldd_attachment_fk
        foreign key (attachment_id) references ip_objects.ip_attachment;
INSERT INTO nomenclatures.attachment_category (code, name, name_en) VALUES ('LD', 'Правни основания', 'Legal Decisions');
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code, category_code) VALUES ('LD', 'Правно основание', 'Legal Decision', null, 'LD');


