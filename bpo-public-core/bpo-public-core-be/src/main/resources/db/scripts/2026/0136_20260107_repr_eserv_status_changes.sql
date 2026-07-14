--liquibase formatted sql

--changeset murlev:0136 splitStatements:false
UPDATE repr_eservices.status SET name = 'Подадено без подпис' WHERE id = 1;
INSERT INTO repr_eservices.status (id, name, name_en) VALUES (7, 'За подпис', 'For signature') ON CONFLICT (id) DO NOTHING;
ALTER TABLE repr_eservices.exam ADD COLUMN IF NOT EXISTS civil_id varchar(25);
