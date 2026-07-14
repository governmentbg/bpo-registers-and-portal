--liquibase formatted sql

--changeset murlev:0137 splitStatements:false

-- ============================================================
-- NOMENCLATURES
-- ============================================================

CREATE TABLE eservices.funding_application_type
(
    code    varchar(5)   NOT NULL CONSTRAINT funding_application_type_pk PRIMARY KEY,
    name    varchar(200) NOT NULL,
    name_en varchar(200),
    ord_num integer
);

INSERT INTO eservices.funding_application_type (code, name, name_en, ord_num) VALUES
    ('1.1', 'Приложение 1.1', 'Application 1.1', 1),
    ('1.2', 'Приложение 1.2', 'Application 1.2', 2);

CREATE TABLE eservices.funding_application_status
(
    code    varchar(20)  NOT NULL CONSTRAINT funding_application_status_pk PRIMARY KEY,
    name    varchar(200) NOT NULL,
    name_en varchar(200),
    ord_num integer
);

INSERT INTO eservices.funding_application_status (code, name, name_en, ord_num) VALUES
    ('DRAFT',     'Чернова',    'Draft',     1),
    ('SUBMITTED', 'Подадена',   'Submitted', 2);

CREATE TABLE eservices.funding_is_type
(
    code             varchar(15)  NOT NULL CONSTRAINT funding_is_type_pk PRIMARY KEY,
    name             varchar(500) NOT NULL,
    name_en          varchar(500),
    ord_num          integer,
    application_type varchar(5)
        CONSTRAINT funding_is_type_app_type_fk REFERENCES eservices.funding_application_type (code)
);

INSERT INTO eservices.funding_is_type (code, name, name_en, ord_num, application_type) VALUES
    ('IZ',     'Изобретение (ИЗ)',                           'Invention (IZ)',                           1, '1.1'),
    ('PM',     'Полезен модел (ПМ)',                         'Utility Model (PM)',                       2, '1.1'),
    ('SZ',     'Схема на интегрална схема (СЗ)',             'Layout Design of Integrated Circuit (SZ)', 3, '1.1'),
    ('TM',     'Търговска марка (ТМ)',                       'Trade Mark (TM)',                          4, '1.2'),
    ('PO',     'Промишлен образец (ПО)',                     'Industrial Design (PO)',                   5, '1.2'),
    ('TM_KOL', 'Колективна търговска марка (ТМ колект.)',    'Collective Trade Mark',                    6, '1.2'),
    ('GN',     'Географско наименование (ГН)',               'Geographical Indication (GN)',              7, '1.2');

CREATE TABLE eservices.funding_cojoint_type
(
    code    varchar(20)  NOT NULL CONSTRAINT funding_cojoint_type_pk PRIMARY KEY,
    name    varchar(500) NOT NULL,
    ord_num integer
);

INSERT INTO eservices.funding_cojoint_type (code, name, ord_num) VALUES
    ('IZ_PM',     'Заявление за ИЗ и ПМ',          1),
    ('IZ_PM_SZ',  'Заявление за ИЗ, ПМ и СЗ',      2),
    ('PM_SZ',     'Заявление за ПМ и СЗ',           3),
    ('TM_TM_KOL', 'Заявление за ТМ и ТМ колективна',4),
    ('TM_GN',     'Заявление за ТМ и ГН',           5);

CREATE TABLE eservices.funding_region
(
    code    varchar(10)  NOT NULL CONSTRAINT funding_region_pk PRIMARY KEY,
    name    varchar(200) NOT NULL,
    name_en varchar(200),
    ord_num integer
);

INSERT INTO eservices.funding_region (code, name, name_en, ord_num) VALUES
    ('YZP', 'Югозападен',        'Southwestern',  1),
    ('SZR', 'Северозападен',     'Northwestern',  2),
    ('SCR', 'Северен централен', 'North-Central', 3),
    ('SIR', 'Североизточен',     'Northeastern',  4),
    ('YIR', 'Югоизточен',        'Southeastern',  5),
    ('YCR', 'Южен централен',    'South-Central', 6);

CREATE TABLE eservices.funding_thematic_area
(
    code    varchar(20)  NOT NULL CONSTRAINT funding_thematic_area_pk PRIMARY KEY,
    name    varchar(500) NOT NULL,
    name_en varchar(500),
    ord_num integer
);

INSERT INTO eservices.funding_thematic_area (code, name, name_en, ord_num) VALUES
    ('ICT',      'Информатика и ИКТ',                                                    'Informatics and ICT',                                   1),
    ('MECH',     'Мехатроника и чисти технологии',                                       'Mechatronics and Clean Technologies',                   2),
    ('HEALTH',   'Индустрия за здравословен живот, биоикономика и биотехнологии',         'Industry for Healthy Life, Bio-economy and Biotechnologies', 3),
    ('CREATIVE', 'Нови технологии в креативните и рекреативните индустрии',               'New Technologies in the Creative and Recreational Industries', 4),
    ('TOURISM',  'Туризъм',                                                               'Tourism',                                               5);

CREATE TABLE eservices.funding_thematic_subarea
(
    code      varchar(30)   NOT NULL CONSTRAINT funding_thematic_subarea_pk PRIMARY KEY,
    area_code varchar(20)   NOT NULL CONSTRAINT funding_thematic_subarea_area_fk REFERENCES eservices.funding_thematic_area (code),
    name      varchar(1000) NOT NULL,
    ord_num   integer
);

INSERT INTO eservices.funding_thematic_subarea (code, area_code, name, ord_num) VALUES
    ('ICT_1',  'ICT', 'Индустриален и производствен софтуер',        1),
    ('ICT_2',  'ICT', 'ИКТ в образованието и науката',              2),
    ('ICT_3',  'ICT', 'Дигитални културни артефакти',               3),
    ('ICT_4',  'ICT', 'Информационна и киберсигурност',             4),
    ('ICT_5',  'ICT', 'Изкуствен интелект и машинно обучение',      5),
    ('ICT_6',  'ICT', 'Big Data и аналитика на данни',              6),
    ('ICT_7',  'ICT', 'Интернет на нещата (IoT)',                   7),
    ('ICT_8',  'ICT', 'Облачни технологии и услуги',                8),
    ('ICT_9',  'ICT', 'Разширена и виртуална реалност (AR/VR)',      9),
    ('ICT_10', 'ICT', 'Блокчейн и разпределени регистри',           10);

INSERT INTO eservices.funding_thematic_subarea (code, area_code, name, ord_num) VALUES
    ('MECH_1', 'MECH', 'Машиностроене и металообработване',                     1),
    ('MECH_2', 'MECH', 'Роботика и автоматизация',                              2),
    ('MECH_3', 'MECH', 'Електрическа мобилност и алтернативни горива',          3),
    ('MECH_4', 'MECH', 'Възобновяеми енергийни източници (ВЕИ)',                4),
    ('MECH_5', 'MECH', 'Енергийна ефективност в индустрията и строителството',  5),
    ('MECH_6', 'MECH', 'Нови материали и нанотехнологии',                       6),
    ('MECH_7', 'MECH', 'Фотоника и сензорни технологии',                        7),
    ('MECH_8', 'MECH', 'Интелигентни транспортни системи',                      8);

INSERT INTO eservices.funding_thematic_subarea (code, area_code, name, ord_num) VALUES
    ('HEALTH_1', 'HEALTH', 'Фармацевтика, биомедицина и медицински изделия',             1),
    ('HEALTH_2', 'HEALTH', 'Медицинска апаратура и диагностика',                        2),
    ('HEALTH_3', 'HEALTH', 'Биотехнологии в земеделието и растениевъдството',           3),
    ('HEALTH_4', 'HEALTH', 'Органично земеделие и биологични продукти',                 4),
    ('HEALTH_5', 'HEALTH', 'Хранително-вкусова промишленост и безопасност на храните',  5),
    ('HEALTH_6', 'HEALTH', 'Горско стопанство, дървообработване и биомаса',             6),
    ('HEALTH_7', 'HEALTH', 'Рибарство, аквакултури и преработка на риба',               7),
    ('HEALTH_8', 'HEALTH', 'Екологични технологии и управление на отпадъците',          8);

INSERT INTO eservices.funding_thematic_subarea (code, area_code, name, ord_num) VALUES
    ('CREATIVE_1', 'CREATIVE', 'Дигитален дизайн, анимация и визуални ефекти', 1),
    ('CREATIVE_2', 'CREATIVE', 'Архитектура и интериорен дизайн',              2),
    ('CREATIVE_3', 'CREATIVE', 'Мода, текстил и кожена промишленост',          3),
    ('CREATIVE_4', 'CREATIVE', 'Художествени занаяти и традиционни изкуства',  4),
    ('CREATIVE_5', 'CREATIVE', 'Игрова индустрия и интерактивни медии',        5),
    ('CREATIVE_6', 'CREATIVE', 'Музикална, филмова и аудио-визуална индустрия',6),
    ('CREATIVE_7', 'CREATIVE', 'Рекламна и комуникационна индустрия',          7);

INSERT INTO eservices.funding_thematic_subarea (code, area_code, name, ord_num) VALUES
    ('TOURISM_1', 'TOURISM', 'Балнеология, СПА и уелнес туризъм',         1),
    ('TOURISM_2', 'TOURISM', 'Конгресен и бизнес туризъм',                2),
    ('TOURISM_3', 'TOURISM', 'Еко туризъм и селски туризъм',              3),
    ('TOURISM_4', 'TOURISM', 'Алтернативен и приключенски туризъм',       4),
    ('TOURISM_5', 'TOURISM', 'Историко-познавателен и културен туризъм',  5),
    ('TOURISM_6', 'TOURISM', 'Ски и зимен туризъм',                       6);

CREATE TABLE eservices.funding_representation_method
(
    code    varchar(20)  NOT NULL CONSTRAINT funding_representation_method_pk PRIMARY KEY,
    name    varchar(200) NOT NULL,
    ord_num integer
);

INSERT INTO eservices.funding_representation_method (code, name, ord_num) VALUES
    ('TOGETHER', 'Заедно',             1),
    ('SEPARATE', 'Поотделно',          2),
    ('BOTH',     'Заедно и поотделно', 3),
    ('OTHER',    'Друго',              4);

CREATE TABLE eservices.funding_company_category
(
    code    varchar(20)  NOT NULL CONSTRAINT funding_company_category_pk PRIMARY KEY,
    name    varchar(200) NOT NULL,
    ord_num integer
);

INSERT INTO eservices.funding_company_category (code, name, ord_num) VALUES
    ('MICRO',  'Микро предприятие',  1),
    ('SMALL',  'Малко предприятие',  2),
    ('MEDIUM', 'Средно предприятие', 3);

CREATE TABLE eservices.funding_correspondence_type
(
    code    varchar(20)  NOT NULL CONSTRAINT funding_correspondence_type_pk PRIMARY KEY,
    name    varchar(200) NOT NULL,
    ord_num integer
);

INSERT INTO eservices.funding_correspondence_type (code, name, ord_num) VALUES
    ('ENTERPRISE',     'На кандидатстващото предприятие', 1),
    ('REPRESENTATIVE', 'На пълномощника',                 2),
    ('OTHER',          'Друг адрес',                      3);

-- ============================================================
-- MAIN APPLICATION TABLE (metadata only)
-- ============================================================

CREATE TABLE eservices.funding_application
(
    id                   bigserial   NOT NULL CONSTRAINT funding_application_pk PRIMARY KEY,
    application_type_code varchar(5) NOT NULL
        CONSTRAINT funding_application_type_fk REFERENCES eservices.funding_application_type (code),
    status_code          varchar(20) NOT NULL DEFAULT 'DRAFT'
        CONSTRAINT funding_application_status_fk REFERENCES eservices.funding_application_status (code),
    created_date         timestamp with time zone DEFAULT now(),
    modified_date        timestamp with time zone,
    submitted_date       timestamp with time zone,
    user_name            varchar(100),
    org_id               varchar(100)
);

-- ============================================================
-- SECTION 2 - Company data
-- ============================================================

CREATE TABLE eservices.funding_application_company
(
    id                          bigserial   NOT NULL CONSTRAINT funding_application_company_pk PRIMARY KEY,
    application_id              bigint      NOT NULL UNIQUE
        CONSTRAINT funding_application_company_app_fk REFERENCES eservices.funding_application (id),
    company_name                varchar(500),
    eik                         varchar(20),
    legal_form                  varchar(200),
    company_reps                text,
    iban                        varchar(50),
    vat_number                  boolean     DEFAULT false,
    company_category_code       varchar(20)
        CONSTRAINT funding_app_company_cat_fk REFERENCES eservices.funding_company_category (code),
    representation_method_code  varchar(20)
        CONSTRAINT funding_app_repr_method_fk REFERENCES eservices.funding_representation_method (code),
    representation_method_other varchar(500),
    street                      varchar(500),
    city                        varchar(200),
    postal_code                 varchar(20),
    country                     varchar(10)
        CONSTRAINT funding_app_company_country_fk REFERENCES nomenclatures.country (code),
    email                       varchar(200),
    phone                       varchar(100),
    fax                         varchar(100)
);

-- ============================================================
-- SECTION 3 - Representative
-- ============================================================

CREATE TABLE eservices.funding_application_representative
(
    id                 bigserial   NOT NULL CONSTRAINT funding_application_representative_pk PRIMARY KEY,
    application_id     bigint      NOT NULL UNIQUE
        CONSTRAINT funding_application_rep_app_fk REFERENCES eservices.funding_application (id),
    has_representative boolean     DEFAULT false,
    name               varchar(500),
    eik                varchar(20),
    legal_form         varchar(200),
    street             varchar(500),
    city               varchar(200),
    postal_code        varchar(20),
    country            varchar(10)
        CONSTRAINT funding_app_rep_country_fk REFERENCES nomenclatures.country (code),
    email              varchar(200),
    phone              varchar(100)
);

-- ============================================================
-- SECTION 4 - Correspondence address
-- ============================================================

CREATE TABLE eservices.funding_application_correspondence
(
    id                        bigserial   NOT NULL CONSTRAINT funding_application_correspondence_pk PRIMARY KEY,
    application_id            bigint      NOT NULL UNIQUE
        CONSTRAINT funding_application_corr_app_fk REFERENCES eservices.funding_application (id),
    correspondence_type_code  varchar(20)
        CONSTRAINT funding_app_corr_type_fk REFERENCES eservices.funding_correspondence_type (code),
    street                    varchar(500),
    city                      varchar(200),
    postal_code               varchar(20),
    country                   varchar(10)
        CONSTRAINT funding_app_corr_country_fk REFERENCES nomenclatures.country (code)
);

-- ============================================================
-- SECTIONS 1, 5, 6, 7 - IS object, Region, KID, ИСИС
-- ============================================================

CREATE TABLE eservices.funding_application_is_object
(
    id                          bigserial   NOT NULL CONSTRAINT funding_application_is_object_pk PRIMARY KEY,
    application_id              bigint      NOT NULL UNIQUE
        CONSTRAINT funding_application_is_obj_app_fk REFERENCES eservices.funding_application (id),
    is_type_code                varchar(15)
        CONSTRAINT funding_app_is_type_fk REFERENCES eservices.funding_is_type (code),
    region_code                 varchar(10)
        CONSTRAINT funding_app_region_fk REFERENCES eservices.funding_region (code),
    kid_main_activity_code      varchar(10),
    kid_additional_activity_code varchar(10),
    kid_new_activity_code       varchar(10),
    kid_description             text,
    isis_additional_info        text
);

-- ============================================================
-- SECTION 1 - Co-joint filing (Съзаявяване)
-- ============================================================

CREATE TABLE eservices.funding_application_cojoint
(
    id                bigserial   NOT NULL CONSTRAINT funding_application_cojoint_pk PRIMARY KEY,
    application_id    bigint      NOT NULL
        CONSTRAINT funding_application_cojoint_app_fk REFERENCES eservices.funding_application (id),
    cojoint_type_code varchar(20) NOT NULL
        CONSTRAINT funding_application_cojoint_type_fk REFERENCES eservices.funding_cojoint_type (code)
);

-- ============================================================
-- SECTION 7 - Thematic subareas
-- ============================================================

CREATE TABLE eservices.funding_application_thematic_subarea
(
    id             bigserial   NOT NULL CONSTRAINT funding_app_thematic_sub_pk PRIMARY KEY,
    application_id bigint      NOT NULL
        CONSTRAINT funding_app_thematic_sub_app_fk REFERENCES eservices.funding_application (id),
    subarea_code   varchar(30) NOT NULL
        CONSTRAINT funding_app_thematic_sub_code_fk REFERENCES eservices.funding_thematic_subarea (code)
);

-- ============================================================
-- SECTIONS 8-10 - File uploads
-- ============================================================

CREATE TABLE eservices.funding_application_file
(
    id             bigserial    NOT NULL CONSTRAINT funding_application_file_pk PRIMARY KEY,
    application_id bigint       NOT NULL
        CONSTRAINT funding_application_file_app_fk REFERENCES eservices.funding_application (id),
    section_number integer      NOT NULL,
    file_position  integer      NOT NULL,
    file_name      varchar(500),
    file_uuid      varchar(100),
    uploaded_date  timestamp with time zone DEFAULT now()
);
