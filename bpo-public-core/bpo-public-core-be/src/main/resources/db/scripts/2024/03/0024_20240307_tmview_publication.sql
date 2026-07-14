--liquibase formatted sql

--changeset ggeorgiev:0024 splitStatements:false
delete from nomenclatures.tmview_publication;


/**
  select distinct section, 'insert into nomenclatures.tmview_publication (publication_section, publication_code_tmview)  values (' + cast (section as varchar(max)) + ', ''TODO-' + name_en + ''');', nd.NAME, name_en from (
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
			  AND (p.FILE_TYP in ('N', 'D') or p.USERDOC_FILE_TYP in ('N','D'))


) as a
join EXT_JOURNAL.NODE_DEFINITION  nd on nd.NODE_DEF_NBR = section
order by section
 */

insert into nomenclatures.tmview_publication (publication_section, publication_code_tmview)  values (19, 'Application');
insert into nomenclatures.tmview_publication (publication_section, publication_code_tmview)  values (20, 'Registration');
insert into nomenclatures.tmview_publication (publication_section, publication_code_tmview)  values (68, 'Enforcement');
insert into nomenclatures.tmview_publication (publication_section, publication_code_tmview)  values (78, 'Transfer of rights');
insert into nomenclatures.tmview_publication (publication_section, publication_code_tmview)  values (79, 'Renewal');
insert into nomenclatures.tmview_publication (publication_section, publication_code_tmview)  values (80, 'Change of owner name or address');
insert into nomenclatures.tmview_publication (publication_section, publication_code_tmview)  values (81, 'Invalidation');
insert into nomenclatures.tmview_publication (publication_section, publication_code_tmview)  values (82, 'Revocation');
insert into nomenclatures.tmview_publication (publication_section, publication_code_tmview)  values (83, 'Rights in REM');
insert into nomenclatures.tmview_publication (publication_section, publication_code_tmview)  values (84, 'Terminated rights in REM');
insert into nomenclatures.tmview_publication (publication_section, publication_code_tmview)  values (85, 'Security measures');
insert into nomenclatures.tmview_publication (publication_section, publication_code_tmview)  values (86, 'Terminated security measures');
insert into nomenclatures.tmview_publication (publication_section, publication_code_tmview)  values (87, 'License');
insert into nomenclatures.tmview_publication (publication_section, publication_code_tmview)  values (88, 'Terminated licenses');
insert into nomenclatures.tmview_publication (publication_section, publication_code_tmview)  values (89, 'Surrender of rights');
insert into nomenclatures.tmview_publication (publication_section, publication_code_tmview)  values (90, 'Limitations of goods and services');
insert into nomenclatures.tmview_publication (publication_section, publication_code_tmview)  values (91, 'Bankruptcy');
insert into nomenclatures.tmview_publication (publication_section, publication_code_tmview)  values (92, 'Expiration');
insert into nomenclatures.tmview_publication (publication_section, publication_code_tmview)  values (137, 'Renewal of rights in REM');
insert into nomenclatures.tmview_publication (publication_section, publication_code_tmview)  values (151, 'Other amendments');
insert into nomenclatures.tmview_publication (publication_section, publication_code_tmview)  values (163, 'Other amendments');