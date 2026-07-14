--liquibase formatted sql

--changeset ggeorgiev:0128 splitStatements:false
create table ip_objects.userarea_minor_replications
(
    id serial not null
        constraint uamr_pk
            primary key,
    date_start      timestamp not null,
    modified_rows   integer not null
);

insert into ip_objects.userarea_minor_replications (date_start, modified_rows)
select max(date_start), 0 from ip_objects.userarea_replications;