--liquibase formatted sql

--changeset akehayov:0057
alter table utility.tm_exporter_download_links
    add file_size numeric default 0;