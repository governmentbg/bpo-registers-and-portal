--liquibase formatted sql

--changeset murlev:0004 splitStatements:false

alter table ip_objects.ip_agent drop column inactive;

ALTER TABLE ip_objects.ip_agent RENAME COLUMN ext_status TO agent_status;

ALTER TABLE ip_objects.ip_agent ALTER COLUMN agent_status type integer;

ALTER TABLE ONLY ip_objects.ip_agent
    ADD CONSTRAINT agent_status_fk FOREIGN KEY (agent_status) REFERENCES nomenclatures.agent_status(id);
