--liquibase formatted sql

--changeset mnakova:0030
UPDATE nomenclatures.object_subtype set description = 'Търговска', description_en = 'Търговска' where code = 'ИМ';
UPDATE nomenclatures.object_subtype set description = 'Колективна', description_en = 'Колективна' where code = 'КМ';
UPDATE nomenclatures.object_subtype set description = 'Сертификатна', description_en = 'Сертификатна' where code = 'СМ';
