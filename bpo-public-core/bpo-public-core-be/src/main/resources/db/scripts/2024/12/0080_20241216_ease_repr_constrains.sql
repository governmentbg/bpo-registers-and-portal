--liquibase formatted sql

--changeset iborisov:0080 splitStatements:false
alter table repr_eservices.person_name alter column fname_en drop NOT NULL;
alter table repr_eservices.person_name alter column sname_en drop NOT NULL;
alter table repr_eservices.person_name alter column lname_en drop NOT NULL;

alter table repr_eservices.address alter column city_en drop NOT NULL;
alter table repr_eservices.address alter column street_en drop NOT NULL;

alter table repr_eservices.person alter column specialty_en drop NOT NULL;
