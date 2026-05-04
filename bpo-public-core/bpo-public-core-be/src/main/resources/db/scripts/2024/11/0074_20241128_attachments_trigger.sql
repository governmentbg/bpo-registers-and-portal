--liquibase formatted sql

--changeset ggeorgiev:0074 splitStatements:false
drop trigger delete_ip_attachment_trigger on common.attachment;
drop function  ip_objects.delete_ip_attachment();

create function common.delete_attachment() returns trigger
    language plpgsql
as
$$

BEGIN
    INSERT INTO common.attachment_deletes (attachment_id, bucket_name, file_location, status) VALUES (old.id, old.bucket_name, old.file_location, 0);
    return new;
END;

$$;

create trigger delete_attachment_trigger
    after update or delete
    on common.attachment
    for each row
execute procedure common.delete_attachment();