--liquibase formatted sql

--changeset mnakova:0125.1
INSERT INTO nomenclatures.relationship_type (relationship_typ, application_typ, relationship_name, relationship_name_en, direct_relationship_name, direct_relationship_name_en, inverse_relationship_name, inverse_relationship_name_en)
VALUES ('СТР', 'NONE', 'Старшинство', 'Seniority', 'Старшинство', 'Seniority', 'Старшинство', 'Seniority');


--changeset mnakova:0125.2
create or replace view ip_objects.vw_object_relationships
            (object_id, relationship_typ, application_typ, relationship_name, relationship_name_en, description,
             description_en, registration_number, registration_date, registration_country, cancellation_date,
             priority_date, serve_message_date, object_reference, related_object_id)
as
SELECT orl.object_id2  AS object_id,
       rt.relationship_typ,
       rt.application_typ,
       rt.relationship_name,
       rt.relationship_name_en,
       concat(rt.inverse_relationship_name, ' ', obj.id,
              CASE
                  WHEN obj.filing_date IS NOT NULL THEN concat(' - ', to_char(obj.filing_date::timestamp with time zone,
                                                                              'DD.MM.YYYY'::text))
                  ELSE ''::text
                  END) AS description,
       concat(rt.inverse_relationship_name_en, ' ', obj.id,
              CASE
                  WHEN obj.filing_date IS NOT NULL THEN concat(' - ', to_char(obj.filing_date::timestamp with time zone,
                                                                              'DD.MM.YYYY'::text))
                  ELSE ''::text
                  END) AS description_en,
       orl.registration_number,
       orl.registration_date,
       orl.registration_country,
       orl.cancellation_date,
       orl.priority_date,
       orl.serve_message_date,
       CASE
           WHEN obj.id IS NOT NULL THEN obj.id
           ELSE 'NONE'::character varying
END         AS object_reference,
       obj.id as related_object_id
FROM ip_objects.ip_object_relationship orl
         JOIN nomenclatures.relationship_type rt ON rt.relationship_typ::text = orl.relationship_typ::text AND
                                                    rt.application_typ::text = orl.application_typ::text
         LEFT JOIN ip_objects.ip_object obj ON obj.id::text = orl.object_id1::text
WHERE orl.object_id2::text <> 'NONE'::text
UNION ALL
SELECT orl.object_id1  AS object_id,
       rt.relationship_typ,
       rt.application_typ,
       rt.relationship_name,
       rt.relationship_name_en,
       concat(rt.direct_relationship_name, ' ', obj.id,
              CASE
                  WHEN obj.filing_date IS NOT NULL THEN concat(' - ', to_char(obj.filing_date::timestamp with time zone,
                                                                              'DD.MM.YYYY'::text))
                  ELSE ''::text
                  END) AS description,
       concat(rt.direct_relationship_name_en, ' ', obj.id,
              CASE
                  WHEN obj.filing_date IS NOT NULL THEN concat(' - ', to_char(obj.filing_date::timestamp with time zone,
                                                                              'DD.MM.YYYY'::text))
                  ELSE ''::text
                  END) AS description_en,
       orl.registration_number,
       orl.registration_date,
       orl.registration_country,
       orl.cancellation_date,
       orl.priority_date,
       orl.serve_message_date,
       CASE
           WHEN obj.id IS NOT NULL THEN obj.id
           ELSE 'NONE'::character varying
END         AS object_reference,
       obj.id as related_object_id
FROM ip_objects.ip_object_relationship orl
         JOIN nomenclatures.relationship_type rt ON rt.relationship_typ::text = orl.relationship_typ::text AND
                                                    rt.application_typ::text = orl.application_typ::text
         LEFT JOIN ip_objects.ip_object obj ON obj.id::text = orl.object_id2::text
WHERE orl.object_id2::text <> 'NONE'::text
UNION ALL
SELECT orl.object_id1            AS object_id,
       rt.relationship_typ,
       rt.application_typ,
       rt.relationship_name,
       rt.relationship_name_en,
       concat(rt.direct_relationship_name, ' ', orl.filing_number,
              CASE
                  WHEN orl.filing_date IS NOT NULL THEN concat(' - ', to_char(orl.filing_date::timestamp with time zone,
                                                                              'DD.MM.YYYY'::text))
                  ELSE ''::text
                  END)           AS description,
       concat(rt.direct_relationship_name_en, ' ', orl.filing_number,
              CASE
                  WHEN orl.filing_date IS NOT NULL THEN concat(' - ', to_char(orl.filing_date::timestamp with time zone,
                                                                              'DD.MM.YYYY'::text))
                  ELSE ''::text
                  END)           AS description_en,
       orl.registration_number,
       orl.registration_date,
       orl.registration_country,
       orl.cancellation_date,
       orl.priority_date,
       orl.serve_message_date,
       'NONE'::character varying AS object_reference,
       orl.filing_number as related_object_id
FROM ip_objects.ip_object_relationship orl
    JOIN nomenclatures.relationship_type rt ON rt.relationship_typ::text = orl.relationship_typ::text AND
    rt.application_typ::text = orl.application_typ::text
WHERE orl.object_id2::text = 'NONE'::text
UNION ALL
SELECT s.object_id,
       rt.relationship_typ,
       rt.application_typ,
       rt.relationship_name,
       rt.relationship_name_en,
       concat('EUTM: ', ' [[', REGEXP_REPLACE(s.eutm_idappli, 'EM$', ''), ']] ', s.eutm_deno) AS description,
       concat('EUTM: ', ' [[', REGEXP_REPLACE(s.eutm_idappli, 'EM$', ''), ']] ', s.eutm_deno) AS description_en,
       null::varchar(50)            AS registration_number,
        null::date                   AS registration_date,
        null::varchar(30)            AS registration_country,
        null::date                   AS cancellation_date,
        null::date                   AS priority_date,
        null::date                   AS serve_message_date,
        'NONE'                       AS object_reference,
       REGEXP_REPLACE(s.eutm_idappli, 'EM$', '') as related_object_id
FROM seniority.seniority_bg s
         JOIN nomenclatures.relationship_type rt on rt.relationship_typ='СТР'
WHERE s.object_id IS NOT NULL;

