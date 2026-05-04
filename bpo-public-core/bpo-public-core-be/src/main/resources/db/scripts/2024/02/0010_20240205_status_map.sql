--liquibase formatted sql

--changeset ggeorgiev:0010 splitStatements:false

INSERT INTO nomenclatures.backoffice_status_map (backoffice_status_id, backoffice_status_name, for_deletion, bpo_online_status, bpo_online_status_en, show_expiration_date)
VALUES (1561, 'SPC Обявен за недействителен (Чл.15, т.1а - прекратен основен патент)', 0, 'SPC Обявен за недействителен (Чл.15, т.1а - прекратен основен патент)', 'SPC Обявен за недействителен (Чл.15, т.1а - прекратен основен патент)', 1);

INSERT INTO nomenclatures.backoffice_status_map (backoffice_status_id, backoffice_status_name, for_deletion, bpo_online_status, bpo_online_status_en, show_expiration_date)
VALUES (1558, 'SPC Прекратен поради отказ от право', 0, 'SPC Прекратен поради отказ от право', 'SPC Прекратен поради отказ от право', 1);