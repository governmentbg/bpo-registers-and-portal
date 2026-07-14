--liquibase formatted sql

--changeset mnakova:0085.1
CREATE SCHEMA emails;

--changeset mnakova:0085.2
create table emails.email_notification
(
    id           serial
        constraint email_notification_pk
            primary key,
    subject      text                                   not null,
    text         text                                   not null,
    recipients   varchar(255)                           not null,
    reply_to     varchar(255)             default NULL::character varying,
    cc           varchar(255)             default NULL::character varying,
    bcc          varchar(255)             default NULL::character varying,
    created_date timestamp with time zone default now() not null,
    sent_date    timestamp with time zone,
    comment      varchar(2000)            default NULL::character varying,
    skip_sending boolean                  default false not null,
    is_html      boolean                  default false not null
);

create table emails.email_notification_action
(
    code          varchar(255) not null
        primary key,
    name          varchar(255) not null,
    name_en       varchar(255),
    notify_emails text
);

create table emails.email_template
(
    id               varchar(255)                           not null
        constraint email_template_pk
            primary key,
    name             varchar(255)                           not null,
    name_en          varchar(255),
    subject          text                                   not null,
    text             text                                   not null,
    created_date     timestamp with time zone default now() not null,
    last_update_date timestamp with time zone default now() not null,
    is_html          boolean                  default false not null,
    user_create      varchar(255),
    user_last_update varchar(255),
    params           text
);