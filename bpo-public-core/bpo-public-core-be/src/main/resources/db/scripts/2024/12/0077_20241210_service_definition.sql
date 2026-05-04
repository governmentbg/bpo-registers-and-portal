--liquibase formatted sql

--changeset ggeorgiev:0077 splitStatements:false
create table common.panel
(
    id              varchar(255) not null
        primary key,
    label_bg        varchar(255),
    label_en        varchar(255),
    image           varchar(255),
    description_bg  text,
    description_en  text,
    panel_order     integer      not null,
    parent_id       varchar(255)
        constraint panel_parent_fk
            references common.panel,
    url             varchar(255),
    panel_type      varchar(10),
    open_in_new_tab integer,
    constraint panel_type_check
        check ((parent_id is null and panel_type is null) or (parent_id is not null and panel_type in ('BIG', 'SMALL'))),
    constraint open_in_new_tab_check
        check (((NULLIF((url), '') IS NULL) AND (open_in_new_tab IS NULL)) OR
               ((NULLIF((url), '') IS NOT NULL) AND (open_in_new_tab IS NOT NULL)))
);

create table common.service_definition
(
    id             varchar(255) not null
        primary key,
    label_bg       varchar(255),
    label_en       varchar(255),
    description_bg text,
    description_en text,
    new_url        varchar(255),
    view_url       varchar(255),
    panel_id       varchar(255)
        constraint sdn_panel_fk
            references common.panel,
    service_order  integer      not null
);
