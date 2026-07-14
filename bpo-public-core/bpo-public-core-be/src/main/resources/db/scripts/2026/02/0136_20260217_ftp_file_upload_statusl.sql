--liquibase formatted sql

--changeset ggeorgiev:0136 splitStatements:false
create table ip_objects.ip_ftp_file_upload_status
(
    id              serial
        primary key,
    ffu_id          integer     not null,
    consumer        varchar(20) not null,
    status          integer     not null,
    error_count     integer     not null,
    date_sent       timestamp with time zone,
    date_last_error timestamp with time zone
);

create index ffus_status_idx on ip_objects.ip_ftp_file_upload_status (status);
create index ffus_consumer_idx on ip_objects.ip_ftp_file_upload_status (consumer);
alter table ip_objects.ip_ftp_file_upload drop column status;
alter table ip_objects.ip_ftp_file_upload drop column error_count;
alter table ip_objects.ip_ftp_file_upload drop column date_sent;
alter table ip_objects.ip_ftp_file_upload drop column date_last_error;