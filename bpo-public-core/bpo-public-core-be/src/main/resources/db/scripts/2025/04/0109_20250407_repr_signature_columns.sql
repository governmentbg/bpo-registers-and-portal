--liquibase formatted sql

--changeset iborisov:0109 splitStatements:false
alter table repr_eservices.register_physical_person add column signed_file_uuid varchar(255), add column signed_file_status varchar(15);
alter table repr_eservices.register_partnership add column signed_file_uuid varchar(255), add column signed_file_status varchar(15);
alter table repr_eservices.exam add column signed_file_uuid varchar(255), add column signed_file_status varchar(15);
alter table repr_eservices.course add column signed_file_uuid varchar(255), add column signed_file_status varchar(15);
alter table repr_eservices.change_physical_person add column signed_file_uuid varchar(255), add column signed_file_status varchar(15);
alter table repr_eservices.change_partnership add column signed_file_uuid varchar(255), add column signed_file_status varchar(15);
