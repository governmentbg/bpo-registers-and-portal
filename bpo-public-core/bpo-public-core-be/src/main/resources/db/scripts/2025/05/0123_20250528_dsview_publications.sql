--liquibase formatted sql

--changeset ggeorgiev:0123 splitStatements:false
insert into nomenclatures.dsview_publication (publication_section, publication_code_dsview)  values (102, 'Cancellations of Licences/Sub-Licences');
insert into nomenclatures.dsview_publication (publication_section, publication_code_dsview)  values (104, 'Invalidated security measures');
update nomenclatures.dsview_publication set publication_code_dsview = 'Security measures' where  publication_section = '103';