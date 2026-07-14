--liquibase formatted sql

--changeset murlev:0066 splitStatements:false
INSERT INTO nomenclatures.legal_decision_object_type (code, description) VALUES ('DN', 'Национални марки');
INSERT INTO nomenclatures.legal_decision_object_type (code, description) VALUES ('Г', 'Географски означения');
INSERT INTO nomenclatures.legal_decision_object_type (code, description) VALUES ('M', 'Международни марки');
INSERT INTO nomenclatures.legal_decision_object_type (code, description) VALUES ('Д', 'Промишлени дизайни');
INSERT INTO nomenclatures.legal_decision_object_type (code, description) VALUES ('P', 'Национални патенти');
INSERT INTO nomenclatures.legal_decision_object_type (code, description) VALUES ('U', 'Полезни модели');
INSERT INTO nomenclatures.legal_decision_object_type (code, description) VALUES ('S', 'SPC');
INSERT INTO nomenclatures.legal_decision_object_type (code, description) VALUES ('EP', 'Европейски патенти');
INSERT INTO nomenclatures.legal_decision_object_type (code, description) VALUES ('С', 'Сортове и породи');