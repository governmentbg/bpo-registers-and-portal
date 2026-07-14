--liquibase formatted sql

--changeset ggeorgiev:0018 splitStatements:false
update nomenclatures.backoffice_status_map set bpo_online_status = 'ТМ Подадена заявка' where bpo_online_status = 'ТМ Подадена Заявка';
update nomenclatures.backoffice_status_map set bpo_online_status = 'ПТ За експертно заключение' where bpo_online_status = 'ПТ За Експертно заключение';
update nomenclatures.backoffice_status_map set bpo_online_status = 'ПМ За експертно заключение' where bpo_online_status = 'ПМ За Експертно заключение';

update nomenclatures.backoffice_status_map set bpo_online_status_en = 'ТМ Подадена заявка' where bpo_online_status_en = 'ТМ Подадена Заявка';