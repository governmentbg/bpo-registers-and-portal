--liquibase formatted sql

--changeset murlev:0111 splitStatements:false
create or replace view eservices.application_summary_object_type_view(code, name) as
SELECT sot.code,
       concat('Справка за обекти на ИС - ',sot.name)::varchar(255) as name
FROM eservices.study_object_type sot
UNION
SELECT sst.code,
       concat(st.name,' - ',sst.name)::varchar(255) as name
FROM eservices.study_sub_type sst
         inner join eservices.study_type st on st.code = sst.study_type_code
where st.code !='OBJECTS';

update eservices.study_type set name = 'Справка за обекти на ИС' where code = 'OBJECTS';
update eservices.study_type set name = 'Проучване на марка/ГО' where code = 'MARK';

create or replace view eservices.application_summary_view
            (id, registration_number, object_type_name, object_type_code, applicant, created_date, submit_type_name,
             submit_type_code, study_type, created_user, updated_user, filing_date)
as
SELECT asy.id,
       asy.registration_number,
       CASE
           WHEN sot.name IS NOT NULL THEN concat('Справка за обекти на ИС - ',sot.name)
           WHEN sst.name IS NOT NULL THEN concat(st.name,' - ',sst.name)
           ELSE 'Unknown'::character varying
END  AS object_type_name,
       CASE
           WHEN sot.code IS NOT NULL THEN sot.code
           WHEN sst.code IS NOT NULL THEN sst.code
           ELSE 'Unknown'::character varying
END  AS object_type_code,
       CASE
           WHEN pp.first_name IS NOT NULL OR pp.second_name IS NOT NULL OR pp.last_name IS NOT NULL THEN (
               (((COALESCE(pp.first_name, ''::character varying)::text || ' '::text) ||
                 COALESCE(pp.second_name, ''::character varying)::text) || ' '::text) ||
               COALESCE(pp.last_name, ''::character varying)::text)::character varying
           ELSE COALESCE(pp.company_name, 'Unknown'::character varying)
END  AS applicant,
       asy.created_date,
       ast.name AS submit_type_name,
       ast.code AS submit_type_code,
       st.code  AS study_type,
       asy.created_user,
       asy.updated_user,
       asy.filing_date
FROM eservices.application_study asy
         LEFT JOIN eservices.application_objects_study aos ON asy.id = aos.id
         LEFT JOIN eservices.study_object_type sot ON aos.study_object_type_code::text = sot.code::text
         LEFT JOIN eservices.study_sub_type sst ON asy.study_sub_type_code::text = sst.code::text
         LEFT JOIN eservices.study_type st ON st.code::text = sst.study_type_code::text
         LEFT JOIN eservices.public_person pp ON asy.public_person_id = pp.id
         LEFT JOIN eservices.application_submit_type ast ON asy.submit_type_code::text = ast.code::text;
