--liquibase formatted sql

--changeset ggeorgiev:0100 splitStatements:false
delete from nomenclatures.backoffice_dsview_status_map where backoffice_status_code not like '%-%';
delete from nomenclatures.backoffice_status_map where code not like '%-%';


INSERT INTO nomenclatures.backoffice_status_map(backoffice_status_name, for_deletion, bpo_online_status, bpo_online_status_en, show_expiration_date, code) VALUES ('ПФ Подаден потребителски документ',0,'ПФ Подаден потребителски документ','ПФ Подаден потребителски документ',0,'6-044');
INSERT INTO nomenclatures.backoffice_status_map(backoffice_status_name, for_deletion, bpo_online_status, bpo_online_status_en, show_expiration_date, code) VALUES ('ПФ В срок за отстраняване на недостатъци',0,'ПФ В срок за отстраняване на недостатъци','ПФ В срок за отстраняване на недостатъци',0,'6-045');
INSERT INTO nomenclatures.backoffice_status_map(backoffice_status_name, for_deletion, bpo_online_status, bpo_online_status_en, show_expiration_date, code) VALUES ('ПФ Отказана промяна/неспазени правни изисквания',0,'ПФ Отказана промяна/неспазени правни изисквания','ПФ Отказана промяна/неспазени правни изисквания',0,'6-046');
INSERT INTO nomenclatures.backoffice_status_map(backoffice_status_name, for_deletion, bpo_online_status, bpo_online_status_en, show_expiration_date, code) VALUES ('ПФ Правна проверка',0,'ПФ Правна проверка','ПФ Правна проверка',0,'6-047');
INSERT INTO nomenclatures.backoffice_status_map(backoffice_status_name, for_deletion, bpo_online_status, bpo_online_status_en, show_expiration_date, code) VALUES ('ПФ Одобрена промяна',0,'ПФ Одобрена промяна','ПФ Одобрена промяна',0,'6-048');
INSERT INTO nomenclatures.backoffice_status_map(backoffice_status_name, for_deletion, bpo_online_status, bpo_online_status_en, show_expiration_date, code) VALUES ('ПФ В срок за изчакване на  оригинал',0,'ПФ В срок за изчакване на  оригинал','ПФ В срок за изчакване на  оригинал',0,'6-914');
INSERT INTO nomenclatures.backoffice_status_map(backoffice_status_name, for_deletion, bpo_online_status, bpo_online_status_en, show_expiration_date, code) VALUES ('ПФ Проверка за допустимост и формална редовност',0,'ПФ Проверка за допустимост и формална редовност','ПФ Проверка за допустимост и формална редовност',0,'6-915');
INSERT INTO nomenclatures.backoffice_status_map(backoffice_status_name, for_deletion, bpo_online_status, bpo_online_status_en, show_expiration_date, code) VALUES ('ПФ Изтекъл срок за получаване на оригинал',0,'ПФ Изтекъл срок за получаване на оригинал','ПФ Изтекъл срок за получаване на оригинал',0,'6-916');
INSERT INTO nomenclatures.backoffice_status_map(backoffice_status_name, for_deletion, bpo_online_status, bpo_online_status_en, show_expiration_date, code) VALUES ('ПФ Неполучен оригинал',0,'ПФ Неполучен оригинал','ПФ Неполучен оригинал',0,'6-917');
INSERT INTO nomenclatures.backoffice_status_map(backoffice_status_name, for_deletion, bpo_online_status, bpo_online_status_en, show_expiration_date, code) VALUES ('ПФ Към произволен статус',0,'ПФ Към произволен статус','ПФ Към произволен статус',0,'6-929');
INSERT INTO nomenclatures.backoffice_status_map(backoffice_status_name, for_deletion, bpo_online_status, bpo_online_status_en, show_expiration_date, code) VALUES ('ПФ Влязло в сила решение за отказ',0,'ПФ Влязло в сила решение за отказ','ПФ Влязло в сила решение за отказ',0,'6-942');