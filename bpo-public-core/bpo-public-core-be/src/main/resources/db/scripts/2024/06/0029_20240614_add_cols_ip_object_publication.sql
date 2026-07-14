--liquibase formatted sql

--changeset akehayov:0029
alter table ip_objects.ip_object_publication
    add journal_nbr numeric(8);

alter table ip_objects.ip_object_publication
    add element_nbr numeric(8);

alter table ip_objects.ip_object_publication
    add journal_struct_node_nbr numeric(8);
