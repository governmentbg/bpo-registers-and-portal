--liquibase formatted sql

--changeset akehayov:0056
create schema utility;

create table utility.tm_exporter_download_links
(
    file_name varchar primary key,
    full_path varchar unique,
    date_created timestamp default now(),
    full_export integer default 0 not null
);