--liquibase formatted sql

--changeset ggeorgiev:0037 splitStatements:false
--validCheckSum: 9:022cc816239602ef2af23def00d4dbec
create table nomenclatures.recordal_person_role
(
    code           varchar(50) not null
        primary key,
    description    text,
    description_en text
);
INSERT INTO nomenclatures.recordal_person_role (code, description, description_en) VALUES ('APPLICANT', 'Подател на искането', 'Applicant');
INSERT INTO nomenclatures.recordal_person_role (code, description, description_en) VALUES ('OLD_DATA', 'Стари данни', 'Old Data');
INSERT INTO nomenclatures.recordal_person_role (code, description, description_en) VALUES ('CHANGED_DATA', 'Променени данни', 'Changed data');
INSERT INTO nomenclatures.recordal_person_role (code, description, description_en) VALUES ('CLAIMANT', 'Искател', 'Claimant');
INSERT INTO nomenclatures.recordal_person_role (code, description, description_en) VALUES ('GRANTEE', 'Лицензополучател', 'Grantee');
INSERT INTO nomenclatures.recordal_person_role (code, description, description_en) VALUES ('GRANTOR', 'Лицензодател', 'Grantor');
INSERT INTO nomenclatures.recordal_person_role (code, description, description_en) VALUES ('NEW_OWNER', 'Нови притежатели', 'New owners');
INSERT INTO nomenclatures.recordal_person_role (code, description, description_en) VALUES ('OLD_OWNER', 'Стари притежатели', 'Old owners');
INSERT INTO nomenclatures.recordal_person_role (code, description, description_en) VALUES ('OPPONENT', 'Опонент', 'Opponent');
INSERT INTO nomenclatures.recordal_person_role (code, description, description_en) VALUES ('PAYEE', 'Длъжник', 'Payee');
INSERT INTO nomenclatures.recordal_person_role (code, description, description_en) VALUES ('PAYER', 'Заложен кредитор', 'Payer');
INSERT INTO nomenclatures.recordal_person_role (code, description, description_en) VALUES ('PLEDGER', 'Залогодател', 'Pledger');
INSERT INTO nomenclatures.recordal_person_role (code, description, description_en) VALUES ('AUTHORISED', 'Упълномощен', 'Authorised');
INSERT INTO nomenclatures.recordal_person_role (code, description, description_en) VALUES ('OLD_REPRESENTATIVE', 'Стари пълномощници', 'Old representatives');
INSERT INTO nomenclatures.recordal_person_role (code, description, description_en) VALUES ('NEW_CORRESPONDENCE_ADDRESS', 'Нов адрес за кореспонденция', 'New correspondence address');

create table ip_object_recordals.recordal_persons
(
    recordal_id           varchar(20) not null
        constraint recordal_person_recordal_fk
            references ip_object_recordals.recordals,
    person_id           integer     not null
        constraint recordal_person_person_fk
            references ip_objects.ip_person,
    person_role         varchar     not null
        constraint recordal_person_role_fk
            references nomenclatures.recordal_person_role,
    person_order        integer,
    primary key (recordal_id, person_id, person_role)
);



