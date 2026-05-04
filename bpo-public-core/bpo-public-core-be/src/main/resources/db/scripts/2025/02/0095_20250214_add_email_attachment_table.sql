--liquibase formatted sql

--changeset ndimov:0095 splitStatements:false

create table emails.email_notification_attachment
(
    email_notification_id integer not null
        constraint email_notification_id_fk
            references emails.email_notification,
    attachment_id         integer not null
        constraint attachment_id_fk
            references common.attachment,
    primary key (email_notification_id, attachment_id)
);