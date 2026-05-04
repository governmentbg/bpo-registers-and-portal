--liquibase formatted sql

--changeset ggeorgiev:0044 splitStatements:false
drop function ip_objects.delete_common_object(v_id character varying);
create function ip_objects.delete_common_object(v_id character varying) returns integer
    language plpgsql
as
$$
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
    --delete from ip_objects.ip_object_custom_replication where object_id = v_id; --not deleting this record. It's getting deleted from the code!!!!
    delete from ip_objects.ip_object where id = v_id;
    drop table temp_object_attachments;
    return 1;
END;
$$;
