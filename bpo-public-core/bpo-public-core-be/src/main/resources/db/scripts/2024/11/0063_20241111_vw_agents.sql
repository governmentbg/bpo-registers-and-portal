--liquibase formatted sql

--changeset ggeorgiev:0063
create or replace view ip_objects.vw_agent as
select person_id, agent_code, representative_type, agent_status  from ip_objects.ip_agent
union
select distinct person_id, person_id::varchar, representative_type, 1 from ip_objects.ip_person_to_ip_object where person_role = 'REPRESENTATIVE' and representative_type not in ('AG', 'IP', 'PC', 'PP');