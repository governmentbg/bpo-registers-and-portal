--liquibase formatted sql

--changeset murlev:0107 splitStatements:false
update eservices.person_quality set name = 'Пълномощник' where code = 'AGENT';

create table eservices.object_type_to_person_quality
(
    object_type_code varchar(7)  not null
        constraint object_type_code_fk
            references eservices.study_object_type,
    person_quality_code varchar(15) not null
        constraint person_quality_code_fk
            references eservices.person_quality,
    constraint objects_study_object_to_person_quality_pk
        primary key (object_type_code, person_quality_code)
);

INSERT INTO eservices.object_type_to_person_quality (object_type_code, person_quality_code) VALUES ('PT', 'APPLICANT');
INSERT INTO eservices.object_type_to_person_quality (object_type_code, person_quality_code) VALUES ('UM', 'APPLICANT');
INSERT INTO eservices.object_type_to_person_quality (object_type_code, person_quality_code) VALUES ('SPC', 'APPLICANT');
INSERT INTO eservices.object_type_to_person_quality (object_type_code, person_quality_code) VALUES ('TM', 'APPLICANT');
INSERT INTO eservices.object_type_to_person_quality (object_type_code, person_quality_code) VALUES ('DS', 'APPLICANT');
INSERT INTO eservices.object_type_to_person_quality (object_type_code, person_quality_code) VALUES ('PV', 'APPLICANT');
INSERT INTO eservices.object_type_to_person_quality (object_type_code, person_quality_code) VALUES ('PT', 'AGENT');
INSERT INTO eservices.object_type_to_person_quality (object_type_code, person_quality_code) VALUES ('UM', 'AGENT');
INSERT INTO eservices.object_type_to_person_quality (object_type_code, person_quality_code) VALUES ('SPC', 'AGENT');
INSERT INTO eservices.object_type_to_person_quality (object_type_code, person_quality_code) VALUES ('TM', 'AGENT');
INSERT INTO eservices.object_type_to_person_quality (object_type_code, person_quality_code) VALUES ('DS', 'AGENT');
INSERT INTO eservices.object_type_to_person_quality (object_type_code, person_quality_code) VALUES ('PV', 'AGENT');
INSERT INTO eservices.object_type_to_person_quality (object_type_code, person_quality_code) VALUES ('DS', 'AUTHOR');
INSERT INTO eservices.object_type_to_person_quality (object_type_code, person_quality_code) VALUES ('PT', 'INVENTOR');
INSERT INTO eservices.object_type_to_person_quality (object_type_code, person_quality_code) VALUES ('UM', 'INVENTOR');
INSERT INTO eservices.object_type_to_person_quality (object_type_code, person_quality_code) VALUES ('SPC', 'INVENTOR');
INSERT INTO eservices.object_type_to_person_quality (object_type_code, person_quality_code) VALUES ('PV', 'SELECTIONER');
