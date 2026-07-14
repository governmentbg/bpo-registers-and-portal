--liquibase formatted sql

--changeset ndimov:0108 splitStatements:false
create table portal.portal_attachments
(
    id             varchar(255) not null
        primary key,
    label_bg       varchar(255),
    label_en       varchar(255),
    image          varchar(255),
    description_bg text,
    description_en text,
    position       integer,
    visible_flag   integer      not null,
    attachment_id  integer
        constraint portal_attachments_attachment_fk
            references common.attachment
);
