--liquibase formatted sql

--changeset ggeorgiev:0091 splitStatements:false
drop function ip_objects.delete_design_object;
create function ip_objects.delete_design_object(v_id character varying) returns integer
    language plpgsql
as
$$
DECLARE
    res int;
    r integer;
BEGIN
    select 1 into res from ip_objects.ip_object where id = v_id;
    if res is null then return 0; end if;


    create temp table temp_design_attachments(attachment_id integer, thumbnail_id integer, dsview_picture_id integer);
    insert into temp_design_attachments (attachment_id, thumbnail_id, dsview_picture_id) select attachment_id, thumbnail_id, dsview_picture_id from ip_objects.ip_single_design_drawing where single_design_id in (select single_design_id from ip_objects.ip_single_design where main_design_id = v_id);
    delete from ip_objects.ip_single_design_drawing where single_design_id in (select single_design_id from ip_objects.ip_single_design where main_design_id = v_id);
    delete from common.attachment where id in (select attachment_id from temp_design_attachments);
    delete from common.attachment where id in (select thumbnail_id from temp_design_attachments);
    delete from common.attachment where id in (select dsview_picture_id from temp_design_attachments);
    delete from ip_objects.ip_single_design_locarno where single_design_id in (select single_design_id from ip_objects.ip_single_design where main_design_id = v_id);
    delete from ip_objects.ip_single_design where main_design_id = v_id;
    delete from ip_objects.ip_design where design_id = v_id;
    drop table temp_design_attachments;
    perform ip_objects.delete_common_object(v_id);
    return 1;
END;
$$;