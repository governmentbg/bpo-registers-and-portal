--liquibase formatted sql

--changeset ggeorgiev:0007 splitStatements:false
create table ip_objects.ip_patent_citation (
                                   patent_id character varying(16) NOT NULL,
                                   ref_nbr int not null,
                                   ref_description varchar(255),
                                   ref_claims varchar(30),
                                   CONSTRAINT patent_citation_pk PRIMARY KEY (patent_id, ref_nbr),
                                   constraint patent_citation_patent_fk
                                       foreign key (patent_id) references ip_objects.ip_patent

);

drop function if exists ip_objects.delete_patent_object;
create function ip_objects.delete_patent_object(v_id character varying) returns integer
    language plpgsql
as
$$
DECLARE
res int;
BEGIN
    select 1 into res from ip_objects.ip_object where id = v_id;
    if res is null then return 0; end if;


    create temp table temp_patent_attachments(attachment_id integer);
    insert into temp_patent_attachments (attachment_id) select attachment_id from ip_objects.ip_patent_attachment where patent_id = v_id;
    delete from ip_objects.ip_patent_attachment where patent_id = v_id;
    delete from ip_objects.ip_attachment where id in (select attachment_id from temp_patent_attachments);
    delete from ip_objects.ip_patent_summary where patent_id = v_id;
    delete from ip_objects.ip_patent_pct where patent_id = v_id;
    delete from ip_objects.ip_patent_ipc_classes where patent_id = v_id;
    delete from ip_objects.ip_patent_cpc_classes where patent_id = v_id;
    delete from ip_objects.ip_patent_citation where patent_id = v_id;
    delete from ip_objects.ip_patent where patent_id = v_id;
    drop table temp_patent_attachments;
    perform ip_objects.delete_common_object(v_id);
    return 1;
END;
$$;

