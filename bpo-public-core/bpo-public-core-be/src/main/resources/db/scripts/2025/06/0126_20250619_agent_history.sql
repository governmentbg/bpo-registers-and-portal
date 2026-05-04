--liquibase formatted sql

--changeset ggeorgiev:0126
alter table ip_objects.ip_agent_history add column bo_history_id varchar(10);
alter table ip_objects.ip_agent_history add unique (bo_history_id);