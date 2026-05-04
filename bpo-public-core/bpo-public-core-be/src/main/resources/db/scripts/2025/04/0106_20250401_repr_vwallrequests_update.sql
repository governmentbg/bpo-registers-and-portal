--liquibase formatted sql

--changeset iborisov:0106 splitStatements:false

CREATE OR REPLACE VIEW repr_eservices.vw_all_requests
AS SELECT r.id,
    NULL::text AS partnership_name,
    prname.fname,
    prname.sname,
    prname.lname,
    CONCAT(prname.fname, ' ', prname.sname, ' ', prname.lname) as name,
    r.created_user_id,
    r.date_generate,
    r.date_update,
    r.work_status AS work_status_id,
    r.status AS status_id,
    r.type AS type_request_id,
    r.registration_number,
    r.registration_date,
    r.url
   FROM repr_eservices.register_physical_person r
     JOIN repr_eservices.person pr ON r.person = pr.id
     JOIN repr_eservices.person_name prname ON pr.person_name = prname.id
UNION
 SELECT chr.id,
    NULL::text AS partnership_name,
    chp.fname,
    chp.sname,
    chp.lname,
    CONCAT(chp.fname, ' ', chp.sname, ' ', chp.lname) as name,
    chr.created_user_id,
    chr.date_generate,
    chr.date_update,
    chr.work_status AS work_status_id,
    chr.status AS status_id,
    chr.type AS type_request_id,
    chr.registration_number,
    chr.registration_date,
    chr.url
   FROM repr_eservices.change_physical_person chr
     JOIN repr_eservices.person_name chp ON chr.person_name = chp.id
UNION
 SELECT rp.id,
    p.name AS partnership_name,
    NULL::character varying AS fname,
    NULL::character varying AS sname,
    NULL::character varying AS lname,
    p.name AS name,
    rp.created_user_id,
    rp.date_generate,
    rp.date_update,
    rp.work_status AS work_status_id,
    rp.status AS status_id,
    rp.type AS type_request_id,
    rp.registration_number,
    rp.registration_date,
    rp.url
   FROM repr_eservices.register_partnership rp
     JOIN repr_eservices.partnership p ON rp.partnership = p.id
UNION
 SELECT chpart.id,
    chpart.name AS partnership_name,
    NULL::character varying AS fname,
    NULL::character varying AS sname,
    NULL::character varying AS lname,
    chpart.name AS name,
    chpart.created_user_id,
    chpart.date_generate,
    chpart.date_update,
    chpart.work_status AS work_status_id,
    chpart.status AS status_id,
    chpart.type AS type_request_id,
    chpart.registration_number,
    chpart.registration_date,
    chpart.url
   FROM repr_eservices.change_partnership chpart
UNION
 SELECT c.id,
    NULL::text AS partnership_name,
    cprname.fname,
    cprname.sname,
    cprname.lname,
    CONCAT(cprname.fname, ' ', cprname.sname, ' ', cprname.lname) as name,
    c.created_user_id,
    c.date_generate,
    c.date_update,
    c.work_status AS work_status_id,
    c.status AS status_id,
    c.type AS type_request_id,
    c.registration_number,
    c.registration_date,
    c.url
   FROM repr_eservices.course c
     JOIN repr_eservices.person cpr ON c.person = cpr.id
     JOIN repr_eservices.person_name cprname ON cpr.person_name = cprname.id
UNION
 SELECT e.id,
    NULL::text AS partnership_name,
    eprname.fname,
    eprname.sname,
    eprname.lname,
    CONCAT(eprname.fname, ' ', eprname.sname, ' ', eprname.lname) as name,
    e.created_user_id,
    e.date_generate,
    e.date_update,
    e.work_status AS work_status_id,
    e.status AS status_id,
    e.type AS type_request_id,
    e.registration_number,
    e.registration_date,
    e.url
   FROM repr_eservices.exam e
     JOIN repr_eservices.person_name eprname ON e.person_name = eprname.id;
