--liquibase formatted sql

--changeset akehayov:0034
create view ip_objects.vw_person_object_relationships
            (object_id, object_title, object_title_en, role_description, role_description_en, person_name, person_id)
as
select distinct a.id as object_id,
                a.title as object_title,
                a.title_en as object_title_en,
                d.description as role_description,
                d.description_en as role_description_en,
                c.name as person_name,
                b.person_id as person_id
from ip_objects.ip_object a
         join ip_objects.ip_person_to_ip_object b on a.id = b.object_id
         join ip_objects.ip_person c on c.person_id = b.person_id
         join nomenclatures.person_role d on d.code = b.person_role;