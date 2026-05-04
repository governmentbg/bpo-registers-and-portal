--liquibase formatted sql

--changeset ggeorgiev:0042 splitStatements:false
INSERT INTO nomenclatures.backoffice_status_map (backoffice_status_name, for_deletion, bpo_online_status, bpo_online_status_en, show_expiration_date, code)
VALUES ('ЕП с единно действие', 1, 'ЕП с единно действие', 'Unitary patent', 1, '14-1575');
INSERT INTO nomenclatures.backoffice_status_map (backoffice_status_name, for_deletion, bpo_online_status, bpo_online_status_en, show_expiration_date, code)
VALUES ('ЕП Спряно производство', 0, 'ЕП Спряно производство', 'EP Stopped proceeding', 1, '14-1576');
INSERT INTO nomenclatures.backoffice_status_map (backoffice_status_name, for_deletion, bpo_online_status, bpo_online_status_en, show_expiration_date, code)
VALUES ('ЕП Прекратено производство', 0, 'ЕП Прекратено производство', 'EP Terminated proceeding', 1, '14-1577');
INSERT INTO nomenclatures.backoffice_epopatent_status_map (backoffice_status_code, ep_status_code, fep_inval_date_tag) VALUES ('14-1576', 'NO_STATUS_5', null);
INSERT INTO nomenclatures.backoffice_epopatent_status_map (backoffice_status_code, ep_status_code, fep_inval_date_tag) VALUES ('14-1577', 'NO_STATUS_26', null);
