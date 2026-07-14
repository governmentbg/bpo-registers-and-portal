--liquibase formatted sql

--changeset ndimov:0083.1 splitStatements:false
create table portal.feedback
(
    id                 serial
        primary key,
    first_name         varchar(50)  not null,
    last_name          varchar(50)  not null,
    description        text         not null,
    email              varchar(200) not null,
    object_type        varchar(20)
        constraint fk_feedback_object_type
            references nomenclatures.object_type,
    application_number varchar(20),
    message_type       varchar(20)  not null
        constraint feedback_message_type_check
            check ((message_type)::text = ANY
        ((ARRAY ['FEEDBACK'::character varying, 'IP_OBJECT_ERROR'::character varying])::text[])),
    constraint feedback_check
        check ((((message_type)::text = 'FEEDBACK'::text) AND
                ((object_type IS NULL) AND (application_number IS NULL))) OR
               (((message_type)::text = 'IP_OBJECT_ERROR'::text) AND
                ((object_type IS NOT NULL) AND (application_number IS NOT NULL))))
);

--changeset ndimov:0083.2 splitStatements:false
create table portal.feedback_attachments
(
    feedback_id   integer not null
        constraint feedback_id_fk
            references portal.feedback,
    attachment_id integer not null
        constraint attachment_id_fk
            references common.attachment,
    primary key (feedback_id, attachment_id)
);
