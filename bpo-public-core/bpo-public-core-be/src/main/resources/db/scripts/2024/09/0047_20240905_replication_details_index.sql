--liquibase formatted sql

--changeset ggeorgiev:0047 splitStatements:false
create index rdl_object_id_idx
    on ip_objects.ip_replication_details (object_id);

create index rdl_rte_idx
    on ip_objects.ip_replication_details (replication_time_id);