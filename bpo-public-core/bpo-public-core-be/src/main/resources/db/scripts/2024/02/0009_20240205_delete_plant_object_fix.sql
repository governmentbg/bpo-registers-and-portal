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
        create temp table temp_plant_attachments(attachment_id integer);
    insert into temp_plant_attachments (attachment_id) select attachment_id from ip_objects.ip_plant_attachment where plant_id = v_id;
    delete from ip_objects.ip_plant_attachment where plant_id = v_id;
    delete from ip_objects.ip_attachment where id in (select attachment_id from temp_plant_attachments);
    delete from ip_objects.ip_plant where plant_id = v_id;
    drop table temp_plant_attachments;
    perform ip_objects.delete_common_object(v_id);
    return 1;
END;
$$;
