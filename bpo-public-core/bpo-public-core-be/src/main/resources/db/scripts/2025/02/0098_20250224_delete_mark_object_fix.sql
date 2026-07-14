--liquibase formatted sql

--changeset ggeorgiev:0098 splitStatements:false
drop function ip_objects.delete_mark_object;
create function ip_objects.delete_mark_object(v_id character varying) returns integer
    language plpgsql
as
$$
DECLARE
    res int;
BEGIN
    select 1 into res from ip_objects.ip_object where id = v_id;
    if res is null then return 0; end if;

    create temp table temp_mark_attachments(attachment_id integer, thumbnail_id integer, tmview_picture_id integer);
    insert into temp_mark_attachments (attachment_id, thumbnail_id, tmview_picture_id) select attachment_id, thumbnail_id, tmview_picture_id from ip_objects.ip_mark_attachment where mark_id = v_id;
    delete from ip_objects.ip_mark_attachment_vienna_class where mark_id = v_id;
    delete from ip_objects.ip_mark_attachment where mark_id = v_id;
    delete from common.attachment where id in (select attachment_id from temp_mark_attachments);
    delete from common.attachment where id in (select thumbnail_id from temp_mark_attachments);
    delete from common.attachment where id in (select tmview_picture_id from temp_mark_attachments);

    delete from ip_objects.ip_mark_nice_class where mark_id = v_id;
    delete from ip_objects.ip_mark where mark_id = v_id;
    perform ip_objects.delete_common_object(v_id);
    drop table temp_mark_attachments;
    return 1;
END;
$$;
