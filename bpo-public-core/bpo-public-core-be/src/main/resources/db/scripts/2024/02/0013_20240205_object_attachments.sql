--liquibase formatted sql

--changeset ggeorgiev:0013 splitStatements:false
--validCheckSum: 9:866f998fb96f700b7cbde7e3745bd836
create table ip_objects.ip_object_attachment
(
    object_id     varchar(20) not null
        constraint ip_object_fk
            references ip_objects.ip_object,
    attachment_id integer     not null
        constraint ip_patent_attachment_fk
            references ip_objects.ip_attachment,
    seq_nbr       integer     not null,
    primary key (object_id, seq_nbr)
);

INSERT INTO ip_objects.ip_object_attachment (object_id, attachment_id, seq_nbr)
select patent_id, attachment_id, seq_nbr from ip_objects.ip_patent_attachment;

INSERT into ip_objects.ip_object_attachment (object_id, attachment_id, seq_nbr)
select plant_id, attachment_id, att_seq_nbr from ip_objects.ip_plant_attachment;

INSERT into ip_objects.ip_object_attachment (object_id, attachment_id, seq_nbr)
select plant_id, thumbnail_id, att_seq_nbr * 100 from ip_objects.ip_plant_attachment;

drop function if exists ip_objects.delete_plant_object(v_id character varying) ;
create function ip_objects.delete_plant_object(v_id character varying) returns integer
    language plpgsql
as
$$
DECLARE
res int;
BEGIN
    select 1 into res from ip_objects.ip_object where id = v_id;
    if res is null then return 0; end if;
    delete from ip_objects.ip_plant where plant_id = v_id;
    perform ip_objects.delete_common_object(v_id);
return 1;
END;
$$;

drop function if exists ip_objects.delete_patent_object;
create function ip_objects.delete_patent_object(v_id character varying) returns integer
    language plpgsql
as
$$
DECLARE
res int;
BEGIN
select 1 into res from ip_objects.ip_object where id = v_id;
if res is null then return 0; end if;


    delete from ip_objects.ip_patent_summary where patent_id = v_id;
    delete from ip_objects.ip_patent_pct where patent_id = v_id;
    delete from ip_objects.ip_patent_ipc_classes where patent_id = v_id;
    delete from ip_objects.ip_patent_cpc_classes where patent_id = v_id;
    delete from ip_objects.ip_patent_citation where patent_id = v_id;
    delete from ip_objects.ip_patent where patent_id = v_id;
    perform ip_objects.delete_common_object(v_id);
return 1;
END;
$$;


drop function if exists ip_objects.delete_common_object;
CREATE FUNCTION ip_objects.delete_common_object(v_id character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
res int;
begin
    create temp table temp_object_attachments(attachment_id integer);
    insert into temp_object_attachments (attachment_id) select attachment_id from ip_objects.ip_object_attachment where object_id = v_id;
    delete from ip_objects.ip_object_attachment where object_id = v_id;
    delete from ip_objects.ip_attachment where id in (select attachment_id from temp_object_attachments);
    delete from ip_objects.ip_object_relationship where object_id1 = v_id or object_id2 = v_id;
    delete from ip_objects.ip_object_priority where object_id = v_id;
    delete from ip_objects.ip_object_publication where object_id = v_id;
    delete from ip_objects.ip_person_to_ip_object where object_id = v_id;
    delete from ip_objects.ip_object_custom_replication where object_id = v_id;
    delete from ip_objects.ip_object where id = v_id;
    drop table temp_object_attachments;
return 1;
END;
$$;
drop table ip_objects.ip_patent_attachment;
drop table ip_objects.ip_plant_attachment;
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('THUMB', 'Thumbnail', 'Thumbnail', null);
INSERT INTO nomenclatures.attachment_type (code, name, name_en, backoffice_code) VALUES ('TMVP', 'Tmview Picture', 'Tmview Picture', null);
