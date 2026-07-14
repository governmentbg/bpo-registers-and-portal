--liquibase formatted sql

--changeset ggeorgiev:0116 splitStatements:false
create table nomenclatures.backoffice_userdoc_processes (
    process_type varchar(4) not null primary key
);
INSERT INTO nomenclatures.backoffice_userdoc_processes (process_type) VALUES ('17');
INSERT INTO nomenclatures.backoffice_userdoc_processes (process_type) VALUES ('18');
INSERT INTO nomenclatures.backoffice_userdoc_processes (process_type) VALUES ('19');
INSERT INTO nomenclatures.backoffice_userdoc_processes (process_type) VALUES ('20');
INSERT INTO nomenclatures.backoffice_userdoc_processes (process_type) VALUES ('21');
INSERT INTO nomenclatures.backoffice_userdoc_processes (process_type) VALUES ('23');
INSERT INTO nomenclatures.backoffice_userdoc_processes (process_type) VALUES ('25');
INSERT INTO nomenclatures.backoffice_userdoc_processes (process_type) VALUES ('26');
INSERT INTO nomenclatures.backoffice_userdoc_processes (process_type) VALUES ('32');
INSERT INTO nomenclatures.backoffice_userdoc_processes (process_type) VALUES ('34');
INSERT INTO nomenclatures.backoffice_userdoc_processes (process_type) VALUES ('35');
INSERT INTO nomenclatures.backoffice_userdoc_processes (process_type) VALUES ('36');
INSERT INTO nomenclatures.backoffice_userdoc_processes (process_type) VALUES ('37');
INSERT INTO nomenclatures.backoffice_userdoc_processes (process_type) VALUES ('4');
INSERT INTO nomenclatures.backoffice_userdoc_processes (process_type) VALUES ('5');
INSERT INTO nomenclatures.backoffice_userdoc_processes (process_type) VALUES ('6');
INSERT INTO nomenclatures.backoffice_userdoc_processes (process_type) VALUES ('7');