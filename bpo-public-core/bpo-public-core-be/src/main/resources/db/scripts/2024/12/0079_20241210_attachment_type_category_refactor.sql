--liquibase formatted sql

--changeset iborisov:0079 splitStatements:false

CREATE TABLE nomenclatures.attachment_type_attachment_category (
	attachment_type varchar(5) NOT NULL,
	attachment_category varchar(5) NOT NULL,
	FOREIGN KEY (attachment_type) REFERENCES nomenclatures.attachment_type(code),
	FOREIGN KEY (attachment_category) REFERENCES nomenclatures.attachment_category(code)
);

INSERT INTO nomenclatures.attachment_type_attachment_category (attachment_type, attachment_category) SELECT code,
category_code FROM nomenclatures.attachment_type;

ALTER TABLE nomenclatures.attachment_type DROP COLUMN category_code;
