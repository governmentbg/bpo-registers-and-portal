--liquibase formatted sql

--changeset ggeorgiev:0062
CREATE TABLE ip_objects.ip_object_custom_indexation (
                                              object_id character varying(20) NOT NULL,
                                              replicator_type character varying,
                                              reason text,
                                              date timestamp without time zone
);
alter table ip_objects.ip_object_custom_indexation
    add constraint oci_rte_fk
        foreign key (replicator_type) references nomenclatures.replicator_type;
