--liquibase formatted sql

--changeset ggeorgiev:0055 splitStatements:false
update nomenclatures.recordal_person_role set tmview_name = 'applicant' where code = 'CLAIMANT';