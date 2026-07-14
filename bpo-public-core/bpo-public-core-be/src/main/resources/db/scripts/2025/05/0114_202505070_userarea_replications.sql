--liquibase formatted sql

--changeset ggeorgiev:0114 splitStatements:false
create table ip_objects.userarea_replications
(
    id serial not null
        constraint uar_pk
            primary key,
    date_start      timestamp not null,
    modified_rows   integer not null
);