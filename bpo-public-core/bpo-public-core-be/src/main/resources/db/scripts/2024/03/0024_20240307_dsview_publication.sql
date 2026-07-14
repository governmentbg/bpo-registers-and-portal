--liquibase formatted sql

--changeset ggeorgiev:0024 splitStatements:false
delete from nomenclatures.dsview_publication;


/**
  select distinct ACTION_TYP, section, 'insert into nomenclatures.dsview_publication (publication_section, publication_code_dsview)  values (' + cast (section as varchar(max)) + ', ''TODO-' + name_en + ''');', nd.NAME, name_en from (
SELECT
STUFF(j.JOURNAL_CODE, CHARINDEX(cast (year(PUBLICATION_DATE) as varchar(4)), j.JOURNAL_CODE), 4, '')	AS GAZETTE_NO,
year(j.PUBLICATION_DATE)																				AS GAZETTE_YEAR,
nd.NODE_DEF_NBR															as SECTION,
j.PUBLICATION_DATE,
a.ACTION_TYP,

case when p.USERDOC_FILE_SEQ is not null then 1 else 0 end as IS_USERDOC
              FROM ipasprod.[IP_ACTION] as a
              JOIN ipasprod.[IP_PROC] as p on (p.PROC_NBR = a.PROC_NBR and a.PROC_TYP = p.PROC_TYP)
              left JOIN ipasprod.[IP_JOURNAL] as j on (j.JOURNAL_CODE = a.JOURNAL_CODE)
			  left join EXT_JOURNAL.JOURNAL_ELEMENT e on e.PROC_TYP = a.PROC_TYP and e.PROC_NBR = a.PROC_NBR and e.ACTION_NBR = a.ACTION_NBR
			  left join EXT_JOURNAL.JOURNAL_STRUCT_NODE n on e.JOURNAL_STRUCT_NODE_NBR = n.NODE_NBR
			  left join EXT_JOURNAL.NODE_DEFINITION nd on n.NODE_DEF_NBR = nd.NODE_DEF_NBR
              WHERE (a.JOURNAL_CODE is not null AND j.PUBLICATION_DATE is not null)
			  AND (p.FILE_TYP in ('Д', 'Р', 'Е') or p.USERDOC_FILE_TYP in ('Д', 'Р', 'Е'))


) as a
join EXT_JOURNAL.NODE_DEFINITION  nd on nd.NODE_DEF_NBR = section
order by section
 */

insert into nomenclatures.dsview_publication (publication_section, publication_code_dsview)  values (22, 'Registrations Fully Published');
insert into nomenclatures.dsview_publication (publication_section, publication_code_dsview)  values (28, 'Change Names Addresses of Owners');
insert into nomenclatures.dsview_publication (publication_section, publication_code_dsview)  values (95, 'Renewals');
insert into nomenclatures.dsview_publication (publication_section, publication_code_dsview)  values (96, 'Change Names Addresses of Owners');
insert into nomenclatures.dsview_publication (publication_section, publication_code_dsview)  values (97, 'Other Changes');
insert into nomenclatures.dsview_publication (publication_section, publication_code_dsview)  values (98, 'Other Changes');
insert into nomenclatures.dsview_publication (publication_section, publication_code_dsview)  values (99, 'Cancellations of Right in Rem');
insert into nomenclatures.dsview_publication (publication_section, publication_code_dsview)  values (100, 'Transfers of Ownership');
insert into nomenclatures.dsview_publication (publication_section, publication_code_dsview)  values (101, 'Licences/Sub-Licences');
insert into nomenclatures.dsview_publication (publication_section, publication_code_dsview)  values (103, 'Entry of the Levy');
insert into nomenclatures.dsview_publication (publication_section, publication_code_dsview)  values (108, 'Total Surrenders');
insert into nomenclatures.dsview_publication (publication_section, publication_code_dsview)  values (135, 'Registrations Subject to Deferment');
insert into nomenclatures.dsview_publication (publication_section, publication_code_dsview)  values (139, 'Right in Rem');