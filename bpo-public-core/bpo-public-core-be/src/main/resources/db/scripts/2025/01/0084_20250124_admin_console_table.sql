--liquibase formatted sql

--changeset ndimov:0084 splitStatements:false
create table portal.admin_panel
(
    id           serial primary key,
    title        varchar(255) not null,
    title_en     varchar(255) not null,
    access_roles varchar(255),
    position     integer,
    index_id     integer,
    url          varchar(255),
    parent_id    integer
        constraint fk_admin_panel_parent
            references portal.admin_panel
);
