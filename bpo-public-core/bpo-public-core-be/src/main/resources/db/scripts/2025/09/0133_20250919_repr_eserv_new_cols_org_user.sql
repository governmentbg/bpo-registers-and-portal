--liquibase formatted sql

--changeset raneva:0133 splitStatements:false

ALTER TABLE repr_eservices.exam ADD COLUMN created_org_user_id varchar(100);
ALTER TABLE repr_eservices.course ADD COLUMN created_org_user_id varchar(100);
ALTER TABLE repr_eservices.change_partnership ADD COLUMN created_org_user_id varchar(100);
ALTER TABLE repr_eservices.change_physical_person ADD COLUMN created_org_user_id varchar(100);
ALTER TABLE repr_eservices.register_partnership ADD COLUMN created_org_user_id varchar(100);
ALTER TABLE repr_eservices.register_physical_person ADD COLUMN created_org_user_id varchar(100);

UPDATE repr_eservices.exam SET created_org_user_id = created_user_id WHERE created_org_user_id IS NULL AND created_user_id IS NOT NULL;
UPDATE repr_eservices.course SET created_org_user_id = created_user_id WHERE created_org_user_id IS NULL AND created_user_id IS NOT NULL;
UPDATE repr_eservices.change_partnership SET created_org_user_id = created_user_id WHERE created_org_user_id IS NULL AND created_user_id IS NOT NULL;
UPDATE repr_eservices.change_physical_person SET created_org_user_id = created_user_id WHERE created_org_user_id IS NULL AND created_user_id IS NOT NULL;
UPDATE repr_eservices.register_partnership SET created_org_user_id = created_user_id WHERE created_org_user_id IS NULL AND created_user_id IS NOT NULL;
UPDATE repr_eservices.register_physical_person SET created_org_user_id = created_user_id WHERE created_org_user_id IS NULL AND created_user_id IS NOT NULL;
