--liquibase formatted sql

--changeset iborisov:0099 splitStatements:false

-- civil_id_type
alter table repr_eservices.civil_id_type add column name_en varchar(80);
update repr_eservices.civil_id_type set name_en='EGN [for Bulgarian citizens]' where id=1;
update repr_eservices.civil_id_type set name_en='Foreign citizen identifier' where id=2;
update repr_eservices.civil_id_type set name_en='ID/passport number' where id=3;

-- exam_citizen_type
update repr_eservices.exam_citizen_type set name_en='Bulgarian citizen' where id=1;
update repr_eservices.exam_citizen_type set name_en='Citizen of another EU Member State or an EEA country' where id=2;
update repr_eservices.exam_citizen_type set name_en='Citizen of Swiss Confederation' where id=3;

-- representing_quality
alter table repr_eservices.representing_quality add column name_en varchar(80);
update repr_eservices.representing_quality set name_en='Manager' where id=1;
update repr_eservices.representing_quality set name_en='Partner' where id=2;
update repr_eservices.representing_quality set name_en='Sole owner' where id=3;
update repr_eservices.representing_quality set name_en='Member of the Management Board' where id=4;
update repr_eservices.representing_quality set name_en='Member of the Board of Directors' where id=5;
update repr_eservices.representing_quality set name_en='Other' where id=6;

-- status
alter table repr_eservices.status add column name_en varchar(80);
update repr_eservices.status set name_en='e-filed' where id=1;
update repr_eservices.status set name_en='Signed' where id=2;
update repr_eservices.status set name_en='Accepted' where id=3;
update repr_eservices.status set name_en='Rejected' where id=4;
update repr_eservices.status set name_en='Registration error' where id=5;
update repr_eservices.status set name_en='Draft' where id=6;

-- type_request
alter table repr_eservices.type_request add column description_en varchar(150);
update repr_eservices.type_request set description_en='Request for registration of an IP representative in the register' where id=1;
update repr_eservices.type_request set description_en='Request for recordal of changes in the register about the name and/or address of an IP representative' where id=2;
update repr_eservices.type_request set description_en='Request for registration of an IP representative association in the register' where id=3;
update repr_eservices.type_request set description_en='Request for recordal of changes in the register about the name and/or address of an IP representatives association or company' where id=4;
update repr_eservices.type_request set description_en='Request for inclusion in a training course for IP representative' where id=5;
update repr_eservices.type_request set description_en='Request for admission to an examination for IP representatives' where id=6;

-- work_status
alter table repr_eservices.work_status add column name_en varchar(80);
update repr_eservices.work_status set name='Проблем при записване на файл в деловодната система', name_en='Problem saving file in the document management system' where id=1;
update repr_eservices.work_status set name_en='Problem in XML generation' where id=2;
update repr_eservices.work_status set name_en='Problem in receipt generation' where id=3;
update repr_eservices.work_status set name_en='The application successfully registered' where id=4;
