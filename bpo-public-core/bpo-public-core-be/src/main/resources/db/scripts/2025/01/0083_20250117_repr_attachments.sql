--liquibase formatted sql

--changeset iborisov:0083 splitStatements:false

INSERT INTO nomenclatures.attachment_category (code, name, name_en) VALUES ('REPPE', 'Вписване на ПИС', 'Registration of an IP representative');
INSERT INTO nomenclatures.attachment_category (code, name, name_en) VALUES ('REPPA', 'Вписване на съдружие', 'Registration of an IPR association');
INSERT INTO nomenclatures.attachment_category (code, name, name_en) VALUES ('REPEX', 'Допускане до изпит', 'Admission to an examination');
INSERT INTO nomenclatures.attachment_category (code, name, name_en) VALUES ('REPCO', 'Включване в курс на обучение', 'Subscription for a training course');
INSERT INTO nomenclatures.attachment_category (code, name, name_en) VALUES ('REPCH', 'Промяна на обстоятелства', 'IPR change');

INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('STMCI', 'Декларация за обстоятелствата по чл. 4 от НПИС', 'Декларация за обстоятелствата по чл. 4 от НПИС', null);
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('BCAPH', 'Снимка за служебна карта - 1 бр.', 'Снимка за служебна карта - 1 бр.', null);
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('CECUS', 'Удостоверение за актуално състояние /когато не е посочен ЕИК или БУЛСТАТ/', 'Удостоверение за актуално състояние /когато не е посочен ЕИК или БУЛСТАТ/', null);

INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('STMCI', 'REPPE');
INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('BCAPH', 'REPPE');
INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('OTHER', 'REPPE');

INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('CECUS', 'REPPA');
INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('OTHER', 'REPPA');

INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('OTHER', 'REPEX');

INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('OTHER', 'REPCO');

INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) VALUES ('OTHER', 'REPCH');

ALTER TABLE repr_eservices.address ALTER COLUMN postal_code DROP NOT NULL;
ALTER TABLE repr_eservices.exam ADD COLUMN accept_citizen_declaration BOOLEAN DEFAULT false NOT NULL;

DROP TABLE repr_eservices.register_physical_person_files;
CREATE TABLE repr_eservices.register_physical_person_attachments (
	register_physical_person_id INTEGER NOT NULL,
	attachment_id INTEGER NOT NULL,
	FOREIGN KEY (register_physical_person_id) REFERENCES repr_eservices.register_physical_person(id) ON DELETE CASCADE,
	FOREIGN KEY (attachment_id) REFERENCES common.attachment(id) ON DELETE CASCADE,
	CONSTRAINT register_physical_person_attachments_pk PRIMARY KEY (register_physical_person_id, attachment_id)
);

DROP TABLE repr_eservices.register_partnership_files;
CREATE TABLE repr_eservices.register_partnership_attachments (
	register_partnership_id INTEGER NOT NULL,
	attachment_id INTEGER NOT NULL,
	FOREIGN KEY (register_partnership_id) REFERENCES repr_eservices.register_partnership(id) ON DELETE CASCADE,
	FOREIGN KEY (attachment_id) REFERENCES common.attachment(id) ON DELETE CASCADE,
	CONSTRAINT register_partnership_attachments_pk PRIMARY KEY (register_partnership_id, attachment_id)
);

DROP TABLE repr_eservices.course_files;
CREATE TABLE repr_eservices.course_attachments (
	course_id INTEGER NOT NULL,
	attachment_id INTEGER NOT NULL,
	FOREIGN KEY (course_id) REFERENCES repr_eservices.course(id) ON DELETE CASCADE,
	FOREIGN KEY (attachment_id) REFERENCES common.attachment(id) ON DELETE CASCADE,
	CONSTRAINT course_attachments_pk PRIMARY KEY (course_id, attachment_id)
);

DROP TABLE repr_eservices.exam_files;
CREATE TABLE repr_eservices.exam_attachments (
	exam_id INTEGER NOT NULL,
	attachment_id INTEGER NOT NULL,
	FOREIGN KEY (exam_id) REFERENCES repr_eservices.exam(id) ON DELETE CASCADE,
	FOREIGN KEY (attachment_id) REFERENCES common.attachment(id) ON DELETE CASCADE,
	CONSTRAINT exam_attachments_pk PRIMARY KEY (exam_id, attachment_id)
);

DROP TABLE repr_eservices.change_physical_person_files;
CREATE TABLE repr_eservices.change_physical_person_attachments (
	change_physical_person_id INTEGER NOT NULL,
	attachment_id INTEGER NOT NULL,
	FOREIGN KEY (change_physical_person_id) REFERENCES repr_eservices.change_physical_person(id) ON DELETE CASCADE,
	FOREIGN KEY (attachment_id) REFERENCES common.attachment(id) ON DELETE CASCADE,
	CONSTRAINT change_physical_person_attachments_pk PRIMARY KEY (change_physical_person_id, attachment_id)
);

DROP TABLE repr_eservices.change_partnership_files;
CREATE TABLE repr_eservices.change_partnership_attachments (
	change_partnership_id INTEGER NOT NULL,
	attachment_id INTEGER NOT NULL,
	FOREIGN KEY (change_partnership_id) REFERENCES repr_eservices.change_partnership(id) ON DELETE CASCADE,
	FOREIGN KEY (attachment_id) REFERENCES common.attachment(id) ON DELETE CASCADE,
	CONSTRAINT change_partnership_attachments_pk PRIMARY KEY (change_partnership_id, attachment_id)
);
