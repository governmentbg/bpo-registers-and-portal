--liquibase formatted sql

--changeset murlev:0032
CREATE TABLE ip_object_recordals.recordal_group_type (
                                                         id character varying(50) NOT NULL,
                                                         description character varying(255),
                                                         description_en character varying(255),
                                                         primary key (id)
);

INSERT INTO ip_object_recordals.recordal_group_type (id,description,description_en)
values ('OPPOSITION','Опозиции/Възражения','Opposition/Objections');

INSERT INTO ip_object_recordals.recordal_group_type (id,description,description_en)
values ('CANCELLATION','Искане за отмяна/заличаване на регистрацията','Request for cancellation/deletion of registration');

INSERT INTO ip_object_recordals.recordal_group_type (id,description,description_en)
values ('RECORDAL','Вписвания','Recordals');