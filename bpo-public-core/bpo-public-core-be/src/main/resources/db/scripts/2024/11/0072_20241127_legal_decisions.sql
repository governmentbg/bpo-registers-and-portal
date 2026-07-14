--liquibase formatted sql

--changeset ggeorgiev:0072
create table legal_decisions.replication_times
(
    id              serial not null
        constraint rte_pk
            primary key,
    date_start      timestamp not null,
    modified_rows   integer,
    date_end        timestamp
);

create table legal_decisions.replication_details
(
    id  serial not null
        constraint rdl_pk
            primary key,
    document_id varchar(255) not null,
    rte_id integer not null
        constraint replication_details_replication_time_id_fkey
            references legal_decisions.replication_times,
    operation_code varchar(10) not null
);

create index rdl_rte_idx
    on legal_decisions.replication_details (rte_id);

