--liquibase formatted sql

--changeset akehayov:0070
drop view ip_objects.vw_person_object_relationships;

create view ip_objects.vw_person_object_relationships
            (object_id, object_title, object_title_en, role_code, role_description, role_description_en, person_name, person_id)
as
SELECT DISTINCT a.id             AS object_id,
                a.title          AS object_title,
                a.title_en       AS object_title_en,
                d.code           AS role_code,
                d.description    AS role_description,
                d.description_en AS role_description_en,
                c.name           AS person_name,
                b.person_id
FROM ip_objects.ip_object a
         JOIN ip_objects.ip_person_to_ip_object b ON a.id::text = b.object_id::text
         JOIN ip_objects.ip_person c ON c.person_id = b.person_id
         JOIN nomenclatures.person_role d ON d.code::text = b.person_role::text
where d.code <> 'CORRESPONDENCE_ADDRESS' and a.published = 1;