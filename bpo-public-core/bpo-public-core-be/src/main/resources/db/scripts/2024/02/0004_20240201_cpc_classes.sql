create table nomenclatures.patent_cpc_class
(
    edition_code  varchar(50) not null,
    section_code  varchar(1)  not null,
    class_code    varchar(2)  not null,
    subclass_code varchar(2)  not null,
    group_code    varchar(3)  not null,
    subgroup_code varchar(5)  not null,
    name          varchar(300),
    name_en       varchar(300),
    constraint patent_cpc_class_pk
        primary key (edition_code, section_code, class_code, subclass_code, group_code, subgroup_code)
);

create table ip_objects.ip_patent_cpc_classes
(
    patent_id     varchar(20) not null
        constraint cpc_class_patent_fk
            references ip_objects.ip_patent,
    edition_code  varchar(50) not null,
    section_code  varchar(1)  not null,
    class_code    varchar(2)  not null,
    subclass_code varchar(2)  not null,
    group_code    varchar(3)  not null,
    subgroup_code varchar(5)  not null,
    primary key (patent_id, edition_code, section_code, class_code, subclass_code, group_code, subgroup_code),
    constraint cpc_class_fk
        foreign key (edition_code, section_code, class_code, subclass_code, group_code,
                     subgroup_code) references nomenclatures.patent_cpc_class
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
    delete from ip_objects.ip_patent where patent_id = v_id;
    drop table temp_patent_attachments;
    perform ip_objects.delete_common_object(v_id);
    return 1;
END;
$$;

