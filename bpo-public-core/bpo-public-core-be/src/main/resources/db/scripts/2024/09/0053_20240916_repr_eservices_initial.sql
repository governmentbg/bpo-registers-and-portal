--liquibase formatted sql

--changeset iborisov:0053 splitStatements:false
CREATE SCHEMA repr_eservices;


CREATE TABLE repr_eservices.status (
	id INTEGER PRIMARY KEY NOT NULL,
	name VARCHAR(150) NOT NULL
);
INSERT INTO repr_eservices.status (id, name) VALUES (1, 'е-заявена');
INSERT INTO repr_eservices.status (id, name) VALUES (2, 'Подписана');
INSERT INTO repr_eservices.status (id, name) VALUES (3, 'Приета');
INSERT INTO repr_eservices.status (id, name) VALUES (4, 'Отказана');
INSERT INTO repr_eservices.status (id, name) VALUES (5, 'Грешка при регистрация');
INSERT INTO repr_eservices.status (id, name) VALUES (6, 'Чернова');


CREATE TABLE repr_eservices.type_request (
	id INTEGER PRIMARY KEY NOT NULL,
	name VARCHAR(150) NOT NULL,
	description VARCHAR(150)
);
INSERT INTO repr_eservices.type_request (id, name, description) VALUES (1, 'Register Physical Person', 'Искане за вписване на представител по индустриална собственост в регистъра');
INSERT INTO repr_eservices.type_request (id, name, description) VALUES (2, 'Change Physical Person', 'Искане за вписване на промяна в името и/или адреса на представител по индустриална собственост в регистъра');
INSERT INTO repr_eservices.type_request (id, name, description) VALUES (3, 'Register Partnership', 'Искане за вписване на съдружие/дружество на представители по индустриална собственост в регистъра');
INSERT INTO repr_eservices.type_request (id, name, description) VALUES (4, 'Change Partnership', 'Искане за вписване на промяна на име и/или адрес на съдружие/дружество на представители по индустриална собственост в регистъра');
INSERT INTO repr_eservices.type_request (id, name, description) VALUES (5, 'Course', 'Искане за включване в курс за обучение на представители по индустриална собственост');
INSERT INTO repr_eservices.type_request (id, name, description) VALUES (6, 'Exam', 'Искане за допускане до изпит за представител по индустриална собственост');


CREATE TABLE repr_eservices.work_status (
	id INTEGER PRIMARY KEY NOT NULL,
	name VARCHAR(150) NOT NULL
);
INSERT INTO repr_eservices.work_status (id, name) VALUES (1, 'Проблем при записване на файл в Acstre');
INSERT INTO repr_eservices.work_status (id, name) VALUES (2, 'Проблем при генериране на XML');
INSERT INTO repr_eservices.work_status (id, name) VALUES (3, 'Проблем при генериране на разписка');
INSERT INTO repr_eservices.work_status (id, name) VALUES (4, 'Успешно регистрирана заявка');


CREATE TABLE repr_eservices.file_type (
	id INTEGER PRIMARY KEY NOT NULL,
	name VARCHAR(255) NOT NULL,
	register BOOLEAN NULL,
	course BOOLEAN NULL,
	exam BOOLEAN NULL,
	change BOOLEAN NULL,
	partnership BOOLEAN NULL
);
INSERT INTO repr_eservices.file_type (id, name, register, course, exam, change, partnership) VALUES (1, 'Документ за успешно издържан/и изпит/и', false, false, false, false, false);
INSERT INTO repr_eservices.file_type (id, name, register, course, exam, change, partnership) VALUES (2, 'Свидетелство за съдимост', false, false, false, false, false);
INSERT INTO repr_eservices.file_type (id, name, register, course, exam, change, partnership) VALUES (3, 'Декларация за обстоятелствата по чл. 4 от  НПИС', true, false, false, false, false);
INSERT INTO repr_eservices.file_type (id, name, register, course, exam, change, partnership) VALUES (5, 'Снимка за служебна карта - 1 бр.', true, false, false, false, false);
INSERT INTO repr_eservices.file_type (id, name, register, course, exam, change, partnership) VALUES (7, 'Копие от диплома/и', false, false, false, false, false);
INSERT INTO repr_eservices.file_type (id, name, register, course, exam, change, partnership) VALUES (8, 'Удостоверение за актуално състояние /когато не е посочен ЕИК или БУЛСТАТ/', false, false, false, false, true);
INSERT INTO repr_eservices.file_type (id, name, register, course, exam, change, partnership) VALUES (9, 'Друго', true, true, true, true, true);


CREATE TABLE repr_eservices.civil_id_type (
	id INTEGER PRIMARY KEY NOT NULL,
	name VARCHAR(80) NOT NULL
);
INSERT INTO repr_eservices.civil_id_type (id, name) VALUES (1, 'ЕГН');
INSERT INTO repr_eservices.civil_id_type (id, name) VALUES (2, 'ЛНЧ');
INSERT INTO repr_eservices.civil_id_type (id, name) VALUES (3, 'личен документ');


CREATE TABLE repr_eservices.representing_quality (
	id INTEGER PRIMARY KEY NOT NULL,
	name VARCHAR(150) NOT NULL
);
INSERT INTO repr_eservices.representing_quality (id, name) VALUES (1, 'Управител');
INSERT INTO repr_eservices.representing_quality (id, name) VALUES (2, 'Съдружник');
INSERT INTO repr_eservices.representing_quality (id, name) VALUES (3, 'Едноличен собственик');
INSERT INTO repr_eservices.representing_quality (id, name) VALUES (4, 'Член на управителния съвет');
INSERT INTO repr_eservices.representing_quality (id, name) VALUES (5, 'Член на съвета на директорите');
INSERT INTO repr_eservices.representing_quality (id, name) VALUES (6, 'Друго');


CREATE TABLE repr_eservices.exam_citizen_type (
	id INTEGER PRIMARY KEY NOT NULL,
	name VARCHAR(250) NOT NULL,
	name_en VARCHAR(250)
);
INSERT INTO repr_eservices.exam_citizen_type (id, name) VALUES (1, 'български гражданин');
INSERT INTO repr_eservices.exam_citizen_type (id, name) VALUES (2, 'гражданин на друга държава членка на Европейския съюз, на държава - страна по Споразумението за Европейското икономическо пространство');
INSERT INTO repr_eservices.exam_citizen_type (id, name) VALUES (3, 'гражданин на Конфедерация Швейцария');


CREATE TABLE repr_eservices.partnership_type (
	id VARCHAR(50) PRIMARY KEY NOT NULL,
	name VARCHAR(150) NOT NULL,
	name_en VARCHAR(150) NOT NULL
);
INSERT INTO repr_eservices.partnership_type (id, name, name_en) VALUES ('PARTNERSHIP', 'Съдружие', 'Partnership');
INSERT INTO repr_eservices.partnership_type (id, name, name_en) VALUES ('COMPANY', 'Дружество', 'Company');


CREATE TABLE repr_eservices.xml_signed (
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	signed_xml_content TEXT NOT NULL,
	issuer VARCHAR(255) NOT NULL,
	name VARCHAR(255),
	email VARCHAR(255),
	civil_id VARCHAR(255),
	unified_id_code VARCHAR(255),
	validity_from TIMESTAMPTZ,
	validity_to TIMESTAMPTZ
);


CREATE TABLE repr_eservices.xml (
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	signed BOOLEAN DEFAULT false NOT NULL,
	signed_xml INTEGER,
	portal_user BIGINT,
	date_signed TIMESTAMPTZ NOT NULL,
	xml TEXT NOT NULL,
	FOREIGN KEY (signed_xml) REFERENCES repr_eservices.xml_signed(id)
);


CREATE TABLE repr_eservices.address (
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	country VARCHAR(2),
	city VARCHAR(150) NOT NULL,
	city_en VARCHAR(150) NOT NULL,
	postal_code VARCHAR(25) NOT NULL,
	street VARCHAR(300) NOT NULL,
	street_en VARCHAR(300) NOT NULL,
	fax VARCHAR(25),
	phone VARCHAR(150),
	email VARCHAR(120),
	website VARCHAR(300),
	FOREIGN KEY (country) REFERENCES nomenclatures.country(code)
);


CREATE TABLE repr_eservices.change_partnership (
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	repr_number VARCHAR(50) NOT NULL,
	name VARCHAR(240) NOT NULL,
	name_en VARCHAR(240) NOT NULL,
	address INTEGER NOT NULL,
	cor_address INTEGER,
	date_generate TIMESTAMPTZ,
	date_update TIMESTAMPTZ,
	created_user_id BIGINT,
	work_status INTEGER,
	status INTEGER,
	type INTEGER,
	xml INTEGER,
	add_in_register BOOLEAN DEFAULT false NOT NULL,
	registration_number VARCHAR(150),
	registration_date TIMESTAMPTZ,
	url VARCHAR(250),
	guid VARCHAR(100),
	receipt VARCHAR(255),
	note TEXT,
	FOREIGN KEY (address) REFERENCES repr_eservices.address(id),
	FOREIGN KEY (cor_address) REFERENCES repr_eservices.address(id),
	FOREIGN KEY (status) REFERENCES repr_eservices.status(id),
	FOREIGN KEY (type) REFERENCES repr_eservices.type_request(id),
	FOREIGN KEY (work_status) REFERENCES repr_eservices.work_status(id),
	FOREIGN KEY (xml) REFERENCES repr_eservices.xml(id)
);


CREATE TABLE repr_eservices.change_partnership_files (
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	change_partnership_id INTEGER NOT NULL,
	file_id VARCHAR(255) NOT NULL,
	file_name VARCHAR(255) NOT NULL,
	full_path VARCHAR(255) NOT NULL,
	file_type_id INTEGER NOT NULL,
	FOREIGN KEY (change_partnership_id) REFERENCES repr_eservices.change_partnership(id) ON DELETE CASCADE,
	FOREIGN KEY (file_type_id) REFERENCES repr_eservices.file_type(id)
);


CREATE TABLE repr_eservices.person_name (
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	fname VARCHAR(50) NOT NULL,
	sname VARCHAR(50) NOT NULL,
	lname VARCHAR(50) NOT NULL,
	fname_en VARCHAR(50) NOT NULL,
	sname_en VARCHAR(50) NOT NULL,
	lname_en VARCHAR(50) NOT NULL
);


CREATE TABLE repr_eservices.person (
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	person_name INTEGER NOT NULL,
	nationality VARCHAR(2) NOT NULL,
	specialty VARCHAR(255) NOT NULL,
	specialty_en VARCHAR(255) NOT NULL,
	repr_number VARCHAR(50),
	civil_id_type INTEGER,
	civil_id VARCHAR(25),
	id_card_number VARCHAR(50),
	id_card_reg_date DATE,
	address_id INTEGER NOT NULL,
	residence_address VARCHAR(300),
	cor_address INTEGER,
	FOREIGN KEY (civil_id_type) REFERENCES repr_eservices.civil_id_type(id),
	FOREIGN KEY (nationality) REFERENCES nomenclatures.country(code),
	FOREIGN KEY (address_id) REFERENCES repr_eservices.address(id),
	FOREIGN KEY (cor_address) REFERENCES repr_eservices.address(id),
	FOREIGN KEY (person_name) REFERENCES repr_eservices.person_name(id)
);


CREATE TABLE repr_eservices.change_physical_person (
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	repr_number VARCHAR(50) NOT NULL,
	person_name INTEGER NOT NULL,
	address INTEGER NOT NULL,
	cor_address INTEGER,
	date_generate TIMESTAMPTZ,
	date_update TIMESTAMPTZ,
	created_user_id BIGINT,
	work_status INTEGER,
	status INTEGER,
	type INTEGER,
	xml INTEGER,
	add_in_register BOOLEAN DEFAULT false NOT NULL,
	registration_number VARCHAR(150),
	registration_date TIMESTAMPTZ,
	url VARCHAR(250),
	guid VARCHAR(100),
	receipt VARCHAR(255),
	note TEXT,
	FOREIGN KEY (address) REFERENCES repr_eservices.address(id),
	FOREIGN KEY (cor_address) REFERENCES repr_eservices.address(id),
	FOREIGN KEY (person_name) REFERENCES repr_eservices.person_name(id),
	FOREIGN KEY (status) REFERENCES repr_eservices.status(id),
	FOREIGN KEY (type) REFERENCES repr_eservices.type_request(id),
	FOREIGN KEY (work_status) REFERENCES repr_eservices.work_status(id),
	FOREIGN KEY (xml) REFERENCES repr_eservices.xml(id)
);


CREATE TABLE repr_eservices.change_physical_person_files (
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	change_physical_person_id INTEGER NOT NULL,
	file_id VARCHAR(255) NOT NULL,
	file_name VARCHAR(255) NOT NULL,
	full_path VARCHAR(255) NOT NULL,
	file_type_id INTEGER NOT NULL,
	FOREIGN KEY (change_physical_person_id) REFERENCES repr_eservices.change_physical_person(id) ON DELETE CASCADE,
	FOREIGN KEY (file_type_id) REFERENCES repr_eservices.file_type(id)
);


CREATE TABLE repr_eservices.course (
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	person INTEGER NOT NULL,
	ipobjects VARCHAR(2) NOT NULL,
	date_generate TIMESTAMPTZ,
	date_update TIMESTAMPTZ,
	created_user_id BIGINT,
	work_status INTEGER,
	status INTEGER,
	type INTEGER,
	xml INTEGER,
	registration_number VARCHAR(150),
	registration_date TIMESTAMPTZ,
	url VARCHAR(250),
	guid VARCHAR(100),
	receipt VARCHAR(255),
	note TEXT,
	FOREIGN KEY (ipobjects) REFERENCES nomenclatures.agent_speciality(code),
	FOREIGN KEY (person) REFERENCES repr_eservices.person(id),
	FOREIGN KEY (status) REFERENCES repr_eservices.status(id),
	FOREIGN KEY (type) REFERENCES repr_eservices.type_request(id),
	FOREIGN KEY (work_status) REFERENCES repr_eservices.work_status(id)
);


CREATE TABLE repr_eservices.course_files (
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	course_id INTEGER NOT NULL,
	file_id VARCHAR(255) NOT NULL,
	file_name VARCHAR(255) NOT NULL,
	full_path VARCHAR(255) NOT NULL,
	file_type_id INTEGER NOT NULL,
	FOREIGN KEY (course_id) REFERENCES repr_eservices.course(id),
	FOREIGN KEY (file_type_id) REFERENCES repr_eservices.file_type(id)
);


CREATE TABLE repr_eservices.exam (
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	person_name INTEGER NOT NULL,
	address_id INTEGER NOT NULL,
	cor_address INTEGER,
	nationality VARCHAR(2) NOT NULL,
	university VARCHAR(255) NOT NULL,
	diploma VARCHAR(150) NOT NULL,
	ipobjects VARCHAR(2) NOT NULL,
	date_generate TIMESTAMPTZ,
	date_update TIMESTAMPTZ,
	created_user_id BIGINT,
	work_status INTEGER,
	status INTEGER,
	type INTEGER,
	xml INTEGER,
	registration_number VARCHAR(150),
	registration_date TIMESTAMPTZ,
	url VARCHAR(250),
	guid VARCHAR(100),
	receipt VARCHAR(255),
	note TEXT,
	citizen_type INTEGER NOT NULL,
	FOREIGN KEY (address_id) REFERENCES repr_eservices.address(id),
	FOREIGN KEY (cor_address) REFERENCES repr_eservices.address(id),
	FOREIGN KEY (ipobjects) REFERENCES nomenclatures.agent_speciality(code),
	FOREIGN KEY (person_name) REFERENCES repr_eservices.person_name(id),
	FOREIGN KEY (status) REFERENCES repr_eservices.status(id),
	FOREIGN KEY (type) REFERENCES repr_eservices.type_request(id),
	FOREIGN KEY (work_status) REFERENCES repr_eservices.work_status(id),
	FOREIGN KEY (citizen_type) REFERENCES repr_eservices.exam_citizen_type(id)
);


CREATE TABLE repr_eservices.exam_files (
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	exam_id INTEGER NOT NULL,
	file_id VARCHAR(255) NOT NULL,
	file_name VARCHAR(255) NOT NULL,
	full_path VARCHAR(255) NOT NULL,
	file_type_id INTEGER NOT NULL,
	FOREIGN KEY (exam_id) REFERENCES repr_eservices.exam(id),
	FOREIGN KEY (file_type_id) REFERENCES repr_eservices.file_type(id)
);


CREATE TABLE repr_eservices.partnership (
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	name VARCHAR(150) NOT NULL,
	name_en VARCHAR(150) NOT NULL,
	representing_quality INTEGER NOT NULL,
	representing VARCHAR(150) NOT NULL,
	representing_in_country_of_origin VARCHAR(500),
	repr_number VARCHAR(50),
	country_of_origin VARCHAR(2) NOT NULL,
	eik VARCHAR(50),
	address_id INTEGER NOT NULL,
	cor_address INTEGER,
	partnership_type VARCHAR(50),
	FOREIGN KEY (country_of_origin) REFERENCES nomenclatures.country(code),
	FOREIGN KEY (address_id) REFERENCES repr_eservices.address(id),
	FOREIGN KEY (cor_address) REFERENCES repr_eservices.address(id),
	FOREIGN KEY (partnership_type) REFERENCES repr_eservices.partnership_type(id),
	FOREIGN KEY (representing_quality) REFERENCES repr_eservices.representing_quality(id)
);


CREATE TABLE repr_eservices.partnership_agent (
	id_agent INTEGER NOT NULL,
	id_partnership INTEGER NOT NULL
);


CREATE TABLE repr_eservices.register_partnership (
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	partnership INTEGER NOT NULL,
	ipobjects VARCHAR(2),
	date_generate TIMESTAMPTZ,
	date_update TIMESTAMPTZ,
	created_user_id BIGINT,
	work_status INTEGER,
	status INTEGER,
	type INTEGER,
	xml INTEGER,
	add_in_register BOOLEAN DEFAULT false NOT NULL,
	registration_number VARCHAR(150),
	registration_date TIMESTAMPTZ,
	url VARCHAR(250),
	guid VARCHAR(100),
	receipt VARCHAR(255),
	note TEXT,
	FOREIGN KEY (partnership) REFERENCES repr_eservices.partnership(id),
	FOREIGN KEY (ipobjects) REFERENCES nomenclatures.agent_speciality(code),
	FOREIGN KEY (status) REFERENCES repr_eservices.status(id),
	FOREIGN KEY (type) REFERENCES repr_eservices.type_request(id),
	FOREIGN KEY (work_status) REFERENCES repr_eservices.work_status(id),
	FOREIGN KEY (xml) REFERENCES repr_eservices.xml(id)
);


CREATE TABLE repr_eservices.register_partnership_files (
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	register_partnership_id INTEGER NOT NULL,
	file_id VARCHAR(255) NOT NULL,
	file_name VARCHAR(255) NOT NULL,
	full_path VARCHAR(255) NOT NULL,
	file_type_id INTEGER NOT NULL,
	FOREIGN KEY (file_type_id) REFERENCES repr_eservices.file_type(id),
	FOREIGN KEY (register_partnership_id) REFERENCES repr_eservices.register_partnership(id) ON DELETE CASCADE
);


CREATE TABLE repr_eservices.register_physical_person (
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	person INTEGER NOT NULL,
	ipobjects VARCHAR(2) NOT NULL,
	country_of_acquirement VARCHAR(2) NOT NULL,
	date_generate TIMESTAMPTZ,
	date_update TIMESTAMPTZ,
	created_user_id BIGINT,
	work_status INTEGER,
	status INTEGER,
	type INTEGER,
	xml INTEGER,
	add_in_register BOOLEAN DEFAULT false NOT NULL,
	registration_number VARCHAR(150),
	registration_date TIMESTAMPTZ,
	url VARCHAR(250),
	guid VARCHAR(100),
	receipt VARCHAR(255),
	note TEXT,
	accept_oath BOOLEAN DEFAULT false NOT NULL,
	accept_declaration BOOLEAN DEFAULT false NOT NULL,
	accept_agreement_declaration BOOLEAN DEFAULT false NOT NULL,
	FOREIGN KEY (country_of_acquirement) REFERENCES nomenclatures.country(code),
	FOREIGN KEY (ipobjects) REFERENCES nomenclatures.agent_speciality(code),
	FOREIGN KEY (person) REFERENCES repr_eservices.person(id),
	FOREIGN KEY (status) REFERENCES repr_eservices.status(id),
	FOREIGN KEY (type) REFERENCES repr_eservices.type_request(id),
	FOREIGN KEY (work_status) REFERENCES repr_eservices.work_status(id),
	FOREIGN KEY (xml) REFERENCES repr_eservices.xml(id)
);


CREATE TABLE repr_eservices.register_physical_person_files (
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	register_physical_person_id INTEGER NOT NULL,
	file_id VARCHAR(255) NOT NULL,
	file_name VARCHAR(255) NOT NULL,
	full_path VARCHAR(255) NOT NULL,
	file_type_id INTEGER NOT NULL,
	FOREIGN KEY (file_type_id) REFERENCES repr_eservices.file_type(id),
	FOREIGN KEY (register_physical_person_id) REFERENCES repr_eservices.register_physical_person(id) ON DELETE CASCADE
);
