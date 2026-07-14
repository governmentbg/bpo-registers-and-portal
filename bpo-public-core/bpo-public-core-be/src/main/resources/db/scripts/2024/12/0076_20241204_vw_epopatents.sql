--liquibase formatted sql

--changeset ggeorgiev:0076 splitStatements:false
create or replace view ip_objects.vw_epopatents
as
select alternate_key as application_number,
       'EP'||registration_number as publication_number,
       array_to_string(ARRAY( SELECT pen.name
                              FROM ip_objects.ip_person pen
                                       JOIN ip_objects.ip_person_to_ip_object pto on pto.person_id = pen.person_id and person_role = 'OWNER'
                              WHERE pto.object_id = pat.patent_id
                              ORDER BY person_order), ';') AS owner,
       sm.ep_status_code as status,
       CASE sm.fep_inval_date_tag
           WHEN 'withdrawn_date' THEN pat.not_in_force_date
           ELSE NULL
           END AS withdrawn_date,
       CASE sm.fep_inval_date_tag
           WHEN 'revoked_date' THEN pat.not_in_force_date
           ELSE NULL
           END AS revoked_date,
       CASE sm.fep_inval_date_tag
           WHEN 'lapsed_date' THEN pat.not_in_force_date
           WHEN 'lapsed_date_notpaid' THEN
               CASE
                   WHEN pat.renewal_fees_paid_to IS NOT NULL THEN pat.renewal_fees_paid_to
                   ELSE pat.not_in_force_date
                   END
           ELSE NULL
           END AS lapsed_date,
       CASE sm.fep_inval_date_tag
           WHEN 'expired_date' THEN obt.expiration_date
           ELSE NULL
           END AS expired_date,
       CASE sm.fep_inval_date_tag
           WHEN 'not_in_force_date' THEN pat.not_in_force_date
           ELSE NULL
           END AS not_in_force_date,
       CASE sm.ep_status_code
           WHEN 'NO_STATUS_5' THEN NULL::date
           WHEN 'NO_STATUS_15' THEN NULL::date
           WHEN 'NO_STATUS_26' THEN NULL::date
           ELSE pat.renewal_fees_last_paid
           END AS renewal_fees_last_paid,
       CASE sm.ep_status_code
           WHEN 'NO_STATUS_5' THEN NULL
           WHEN 'NO_STATUS_15' THEN NULL
           WHEN 'NO_STATUS_26' THEN NULL
           ELSE 'Latest annual fee paid: year ' || pat.last_paid_year
           END AS last_renewal_fee_descr,
       GREATEST(obt.date_updated, pat.renewal_fees_last_paid) AS last_update_of_national_register
from ip_objects.ip_patent pat
         join ip_objects.ip_object obt on obt.id = pat.patent_id
         join nomenclatures.backoffice_epopatent_status_map sm on sm.backoffice_status_code = obt.status
where object_type = 'T';