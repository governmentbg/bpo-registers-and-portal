--liquibase formatted sql

--changeset ggeorgiev:0050
--validCheckSum: 9:cd81b04d76019e038a3ec967f3b139a9
alter table nomenclatures.recordal_person_role add column tmview_name varchar(255);

update nomenclatures.recordal_person_role set tmview_name = 'opponent' where code = 'OPPONENT';
update nomenclatures.recordal_person_role set tmview_name = 'applicant' where code = 'APPLICANT';
update nomenclatures.recordal_person_role set tmview_name = 'representative' where code = 'REPRESENTATIVE';
update nomenclatures.recordal_person_role set tmview_name = 'other' where tmview_name is null; --TODO OTHER Persons