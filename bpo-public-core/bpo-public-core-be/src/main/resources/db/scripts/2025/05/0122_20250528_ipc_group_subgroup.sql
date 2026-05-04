alter table nomenclatures.patent_cpc_class alter column subgroup_code type varchar(10);
alter table nomenclatures.patent_ipc_class alter column subgroup_code type varchar(10);
alter table ip_objects.ip_patent_cpc_classes alter column subgroup_code type varchar(10);
alter table ip_objects.ip_patent_ipc_classes alter column subgroup_code type varchar(10);