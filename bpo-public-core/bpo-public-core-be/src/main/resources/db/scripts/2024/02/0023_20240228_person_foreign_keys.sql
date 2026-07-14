--liquibase formatted sql

--changeset ggeorgiev:0023 splitStatements:false
alter table ip_objects.ip_person_addresses
    add constraint person_address_person_fk
        foreign key (person_id) references ip_objects.ip_person;

alter table ip_objects.ip_agent
    add constraint agent_person_fk
        foreign key (person_id) references ip_objects.ip_person;