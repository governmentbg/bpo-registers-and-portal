--liquibase formatted sql

--changeset ggeorgiev:0001 splitStatements:false
--validCheckSum: 9:3d2a65aaf865d4f7b8f126d9cab45fec
--
-- TOC entry 7 (class 2615 OID 24221)
-- Name: ip_object_recordals; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA ip_object_recordals;


--
-- TOC entry 8 (class 2615 OID 24222)
-- Name: ip_objects; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA ip_objects;


--
-- TOC entry 9 (class 2615 OID 24223)
-- Name: nomenclatures; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA nomenclatures;


--
-- TOC entry 1 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

-- CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2687 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

-- COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = ip_object_recordals, pg_catalog;

--
-- TOC entry 330 (class 1255 OID 24224)
-- Name: replicate_recordals(); Type: FUNCTION; Schema: ip_object_recordals; Owner: postgres
--

CREATE FUNCTION replicate_recordals() RETURNS character varying
    LANGUAGE plpgsql
    AS $$
DECLARE
result int;

BEGIN
TRUNCATE TABLE ip_object_recordals.recordals CASCADE;
insert into ip_object_recordals.recordals (id, ip_object_id, recordal_type_id, recordal_number, recordal_date, group_type, requested_date,invalidation_date)
select id, object_id, recordal_type_id, recordal_number, recordal_date, group_type, to_date( substring ( recordal_number from length (recordal_number) - 9 ) ,'DD.MM.YYYY'),
       to_date( invalidation_date ,'YYYY-MM-DD') from ip_object_recordals.tmp_recordal_data;


--ИПРВП -----------------------------------

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'REQUEST_SENDER', applicant, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='CIRCUMS_CHANGES_REQUEST' and applicant<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'ENTRY_DATE',value,2 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='CIRCUMS_CHANGES_REQUEST' and code = 'EFFECTIVE_DATE';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'description',value,3 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='CIRCUMS_CHANGES_REQUEST' and code = 'description';

--1.2. Renewals - utility models and single designs -----------------------------------

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'STATUS', common_data2, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='EFFECTIVE_DATE_EXTENSION' and common_data2 is not null;

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'DESCRIPTION', description, 2
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='EFFECTIVE_DATE_EXTENSION' and description<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'ENTRY_DATE',value,3 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='EFFECTIVE_DATE_EXTENSION' and code = 'EFFECTIVE_DATE';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'ENTITLEMENT_DATE_NEW',value,4 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='EFFECTIVE_DATE_EXTENSION' and code = 'RENEWAL_NEW_EXPIRATION_DATE';


-- 3. renewals ---------------------------------------------------------------------------------

insert into ip_object_recordals.details (recordal_id, detail_type_id,description, order_by)
select id, 'RENEW_FOR', common_data1, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='REGISTRATION_RENEWAL' and common_data1 is not null;

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'STATUS', common_data2, 2
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='REGISTRATION_RENEWAL' and common_data2 is not null;

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'DESCRIPTION', description, 3
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='REGISTRATION_RENEWAL' and description<>'';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'ENTRY_DATE',value,4 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='REGISTRATION_RENEWAL' and code = 'EFFECTIVE_DATE';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'ENTITLEMENT_DATE_NEW',value,5 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='REGISTRATION_RENEWAL' and code = 'RENEWAL_NEW_EXPIRATION_DATE';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'APPROVED_DATA',value,6 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='REGISTRATION_RENEWAL' and code = 'APPROVED_DATA';

-- 4. partial renewals --------------------------------------------------------------------------------

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'RENEW_FOR',common_data2, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='PARTIAL_RENEWAL' and common_data2 is not null;

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'STATUS', common_data2, 2
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='PARTIAL_RENEWAL' and common_data2 is not null;

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'DESCRIPTION', description, 3
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='PARTIAL_RENEWAL' and description<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'ENTRY_DATE',value,4 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='PARTIAL_RENEWAL' and code = 'EFFECTIVE_DATE';

insert into ip_object_recordals.details (recordal_id, detail_type_id,description, order_by)
select id,'ENTITLEMENT_DATE_NEW',value,5 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='PARTIAL_RENEWAL' and code = 'RENEWAL_NEW_EXPIRATION_DATE';


insert into ip_object_recordals.details (recordal_id, detail_type_id,description, order_by)
select id,'APPROVED_DATA',value,6 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='PARTIAL_RENEWAL' and code = 'APPROVED_DATA';

-- 5. change owner -------------------------------------------------------------------------------------

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'OLD_DATA',  old_owner, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='OWNER_CHANGES' and old_owner <> '';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'CHANGED_DATA', new_owner, 2
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='OWNER_CHANGES' and new_owner <> '';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'DESCRIPTION', description, 3
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='OWNER_CHANGES' and description<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'ENTRY_DATE',value,4 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='OWNER_CHANGES' and code = 'EFFECTIVE_DATE';

-- 6.7. Transfer -------------------------------------------------------------------------------------

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'OLD_OWNER', old_owner, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='TRANSFER' and old_owner <> '';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'NEW_OWNER', new_owner, 2
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='TRANSFER' and new_owner <> '';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'DESCRIPTION', description, 3
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='TRANSFER' and description<>'';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'ENTRY_DATE',value,4 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='TRANSFER' and code = 'EFFECTIVE_DATE';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'DELETION_DATE',value,5 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='TRANSFER' and code = 'INVALIDATION_DATE';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'DELETION_GROUNDS',value,6 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='TRANSFER' and code = 'TRANSFER_INVALIDATION_REASON';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'GOODS', common_data1, 7
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='TRANSFER' and common_data1 is not null;


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'APPROVED_DATA',value,8 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='TRANSFER' and code = 'APPROVED_DATA';


-- 8. Partial Transfer -----------------------------------------------------

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'OLD_OWNER', old_owner, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='PARTIAL_TRANSFER' and old_owner <> '';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'NEW_OWNER', new_owner, 2
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='PARTIAL_TRANSFER' and new_owner <> '';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'DESCRIPTION', description, 3
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='PARTIAL_TRANSFER' and description<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'ENTRY_DATE',value,4 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='PARTIAL_TRANSFER' and code = 'EFFECTIVE_DATE';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'DELETION_DATE',value,5 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='PARTIAL_TRANSFER' and code = 'INVALIDATION_DATE';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'DELETION_GROUNDS',value,6 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='PARTIAL_TRANSFER' and code = 'TRANSFER_INVALIDATION_REASON';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'GOODS', common_data1, 7
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='PARTIAL_TRANSFER' and common_data1 is not null;


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'APPROVED_DATA',value,8 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='PARTIAL_TRANSFER' and code = 'APPROVED_DATA';

-- 9. Pledge -----------------------------------------------------

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'REQUEST_SENDER', applicant, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='SPECIAL_PLEDGE_ENTRY' and applicant<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'PLEDGED_CREDITOR', payer, 2
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='SPECIAL_PLEDGE_ENTRY' and payer<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'DEBTOR', payee, 3
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='SPECIAL_PLEDGE_ENTRY' and payee<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'ENTRY_DATE',value,4 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_ENTRY' and code = 'EFFECTIVE_DATE';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'DEADLINE_TO',value,5 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_ENTRY' and code = 'PLEDGE_EXPIRATION_DATE';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'DELETION_DATE',value,6 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_ENTRY' and code = 'INVALIDATION_DATE';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'SECURED_CLAIM',value,7 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_ENTRY' and code = 'PLEDGE_description';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'CONDITIONS',value,8 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_ENTRY' and code = 'PLEDGE_ADDITIONAL_DATA';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'PRICE',value,9 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_ENTRY' and code = 'PLEDGE_AMOUNT';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'INTEREST',value,10 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_ENTRY' and code = 'PLEDGE_INTEREST';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'PENALTIES',value,11 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_ENTRY' and code = 'PLEDGE_PENALTY';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'ORDER_NUMBER',value,12 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_ENTRY' and code = 'PLEDGE_SEQUENCE_NUMBER';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'PLEDGER', pledger, 13
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='SPECIAL_PLEDGE_ENTRY' and pledger<>'';

-- 10. Pledge termination -----------------------------------------------------

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'REQUEST_SENDER', applicant, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='SPECIAL_PLEDGE_TERMINATION' and applicant<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'PLEDGED_CREDITOR', payer, 2
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='SPECIAL_PLEDGE_TERMINATION' and payer<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'DEBTOR', payee, 3
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='SPECIAL_PLEDGE_TERMINATION' and payee<>'';



insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'DESCRIPTION', description, 3
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='SPECIAL_PLEDGE_TERMINATION' and description<>'';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'PLEDGER', pledger, 4
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='SPECIAL_PLEDGE_TERMINATION' and pledger<>'';

-- 11. Pledge renewal -----------------------------------------------------

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'REQUEST_SENDER', applicant, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='SPECIAL_PLEDGE_RENEWAL' and applicant<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'PLEDGED_CREDITOR', payer, 2
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='SPECIAL_PLEDGE_RENEWAL' and payer<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'DEBTOR', payee, 3
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='SPECIAL_PLEDGE_RENEWAL' and payee<>'';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'ENTRY_DATE',value,4 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_RENEWAL' and code = 'EFFECTIVE_DATE';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'DEADLINE_TO',value,5 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_RENEWAL' and code = 'PLEDGE_EXPIRATION_DATE';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'DELETION_DATE',value,6 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_RENEWAL' and code = 'INVALIDATION_DATE';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'SECURED_CLAIM',value,7 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_RENEWAL' and code = 'PLEDGE_description';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'CONDITIONS',value,8 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_RENEWAL' and code = 'PLEDGE_ADDITIONAL_DATA';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'PRICE',value,9 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_RENEWAL' and code = 'PLEDGE_AMOUNT';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'INTEREST',value,10 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_RENEWAL' and code = 'PLEDGE_INTEREST';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'PENALTIES',value,11 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_RENEWAL' and code = 'PLEDGE_PENALTY';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'ORDER_NUMBER',value,12 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_RENEWAL' and code = 'PLEDGE_SEQUENCE_NUMBER';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'PLEDGER', pledger, 13
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='SPECIAL_PLEDGE_RENEWAL' and pledger<>'';

-- 12. Pledge change -----------------------------------------------------

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'REQUEST_SENDER', applicant, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='SPECIAL_PLEDGE_CHANGES' and applicant<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'PLEDGED_CREDITOR', payer, 2
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='SPECIAL_PLEDGE_CHANGES' and payer<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'DEBTOR', payee, 3
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='SPECIAL_PLEDGE_CHANGES' and payee<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'ENTRY_DATE',value,4 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_CHANGES' and code = 'EFFECTIVE_DATE';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'DEADLINE_TO',value,5 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_CHANGES' and code = 'PLEDGE_EXPIRATION_DATE';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'DELETION_DATE',value,6 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_CHANGES' and code = 'INVALIDATION_DATE';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'SECURED_CLAIM',value,7 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_CHANGES' and code = 'PLEDGE_description';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'CONDITIONS',value,8 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_CHANGES' and code = 'PLEDGE_ADDITIONAL_DATA';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'PRICE',value,9 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_CHANGES' and code = 'PLEDGE_AMOUNT';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'INTEREST',value,10 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_CHANGES' and code = 'PLEDGE_INTEREST';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'PENALTIES',value,11 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_CHANGES' and code = 'PLEDGE_PENALTY';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'ORDER_NUMBER',value,12 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='SPECIAL_PLEDGE_CHANGES' and code = 'PLEDGE_SEQUENCE_NUMBER';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'PLEDGER', pledger, 13
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='SPECIAL_PLEDGE_CHANGES' and pledger<>'';

-- 'LICENSEE','RENEW_FOR','DESCRIPTION','ENTRY_ORDER',17. Licence -----------------------------------------------------

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'REQUEST_SENDER', applicant, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id in ('EXCLUSIVE_LICENSE_ENTRY','LICENSE_TERMINATION','EXCLUSIVE_LICENSE_ENTRY') and applicant<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'LICENSOR', grantor, 2
from ip_object_recordals.tmp_recordal_data
where  recordal_type_id in ('EXCLUSIVE_LICENSE_ENTRY','LICENSE_TERMINATION','EXCLUSIVE_LICENSE_ENTRY') and grantor<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'LICENSEE', grantee, 3
from ip_object_recordals.tmp_recordal_data
where  recordal_type_id in ('EXCLUSIVE_LICENSE_ENTRY','LICENSE_TERMINATION','EXCLUSIVE_LICENSE_ENTRY') and grantee<>'';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'DESCRIPTION', description, 3
from ip_object_recordals.tmp_recordal_data
where recordal_type_id in ('EXCLUSIVE_LICENSE_ENTRY','LICENSE_TERMINATION','EXCLUSIVE_LICENSE_ENTRY') and description<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'EFFECTIVE_DATE',value,4 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id in ('EXCLUSIVE_LICENSE_ENTRY','EXCLUSIVE_LICENSE_ENTRY') and code = 'EFFECTIVE_DATE';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'TERM',value,5 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id in ('EXCLUSIVE_LICENSE_ENTRY','EXCLUSIVE_LICENSE_ENTRY') and code = 'LICENSE_EXPIRATION_DATE_TYPE';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'DEADLINE_TO',value,6 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id in ('EXCLUSIVE_LICENSE_ENTRY','EXCLUSIVE_LICENSE_ENTRY') and code = 'LICENSE_EXPIRATION_DATE';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'DELETION_DATE',value,7 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id in ('EXCLUSIVE_LICENSE_ENTRY','EXCLUSIVE_LICENSE_ENTRY') and code = 'INVALIDATION_DATE';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'LICENSE_AGREEMENT_DATA',value,8 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id in ('EXCLUSIVE_LICENSE_ENTRY','EXCLUSIVE_LICENSE_ENTRY') and code = 'LICENSE_CONTRACT_NUMBER_AND_DATE';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'TERRITORIAL_SCOPE',value,9 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id in ('PLEDGED_CREDITOR','EXCLUSIVE_LICENSE_ENTRY') and code = 'LICENSE_TERRITORIAL_SCOPE';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'TERRITORIAL_RESTRICTION',value,10 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id in ('EXCLUSIVE_LICENSE_ENTRY','EXCLUSIVE_LICENSE_ENTRY') and code = 'LICENSE_TERRITORIAL_RESTRICTION';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'SUBLICENSE',value,11 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id in ('EXCLUSIVE_LICENSE_ENTRY','EXCLUSIVE_LICENSE_ENTRY') and code = 'LICENSE_SUBLICENSE';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'LICENSE_ID',value,12 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id in ('EXCLUSIVE_LICENSE_ENTRY','EXCLUSIVE_LICENSE_ENTRY') and code = 'LICENSE_SUBLICENSE_IDENTIFIER';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'DESCRIPTION',value,13 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id in ('EXCLUSIVE_LICENSE_ENTRY','EXCLUSIVE_LICENSE_ENTRY') and code = 'description';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'LICENSE_TYPE',value,14 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id in ('EXCLUSIVE_LICENSE_ENTRY','EXCLUSIVE_LICENSE_ENTRY') and code = 'LICENSE_TYPE';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'FORCED',value,15 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id in ('EXCLUSIVE_LICENSE_ENTRY','EXCLUSIVE_LICENSE_ENTRY') and code = 'LICENSE_IS_COMPULSORY';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'SUBLICENSE_RIGHT',value,16 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id in ('EXCLUSIVE_LICENSE_ENTRY','EXCLUSIVE_LICENSE_ENTRY') and code = 'LICENSE_SUBLICENSE_GRANT_RIGHT';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'GOODS', common_data1, 17
from ip_object_recordals.tmp_recordal_data
where recordal_type_id in ('EXCLUSIVE_LICENSE_ENTRY','EXCLUSIVE_LICENSE_ENTRY') and common_data1 is not null;

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'APPROVED_DATA',value,18 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id in ('EXCLUSIVE_LICENSE_ENTRY','LICENSE_TERMINATION','EXCLUSIVE_LICENSE_ENTRY') and code = 'APPROVED_DATA';

-- 18. Opposition -----------------------------------------------------

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'OPPONENT', applicant, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='OPPOSITION' and applicant<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'STATUS', common_data2, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='OPPOSITION' and common_data2<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'DESCRIPTION', description, 3
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='OPPOSITION' and description<>'';

-- 19. Objection -----------------------------------------------------

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'OPPONENT', applicant, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='OBJECTION' and applicant<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'STATUS', common_data2, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='OBJECTION' and common_data2<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'DESCRIPTION', description, 3
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='OBJECTION' and description<>'';

-- 20. Revocation -----------------------------------------------------

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'CLAIMANT', applicant, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='DELETION' and applicant<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'STATUS', common_data2, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='DELETION' and common_data2<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'DESCRIPTION', description, 3
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='DELETION' and description<>'';

-- 21. Cancellation -----------------------------------------------------

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'CLAIMANT', applicant, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='REVOCATION' and applicant<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'STATUS', common_data2, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='REVOCATION' and common_data2<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'DESCRIPTION', description, 3
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='REVOCATION' and description<>'';


-- 22. Patent invalidity -----------------------------------------------------

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'CLAIMANT', applicant, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='PATENT_INVALIDITY_REQUEST' and applicant<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'STATUS', common_data2, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='PATENT_INVALIDITY_REQUEST' and common_data2<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'DESCRIPTION', description, 3
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='PATENT_INVALIDITY_REQUEST' and description<>'';


-- 23. PoA representative change -----------------------------------------------------

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'AUTHORISED', description, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='ATTORNEY_CHANGES' and description<>'';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'ENTRY_DATE',value,2 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='ATTORNEY_CHANGES' and code = 'EFFECTIVE_DATE';

-- 24. CorrecpAddrChange -----------------------------------------------------

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'NEW_CORR_ADDRESS', description, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='CORR_ADDRESS_CHANGES' and description <> '';

-- 25. Securities -----------------------------------------------------

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'DESCRIPTION', description, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='PROTECTIVE_MEASURES_ENTRY' and description <> '';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'REQUEST_SENDER', applicant, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='PROTECTIVE_MEASURES_ENTRY' and applicant <> '';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'ENTRY_DATE',value,2 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='PROTECTIVE_MEASURES_ENTRY' and code = 'EFFECTIVE_DATE';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'DELETION_DATE',value,3 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='PROTECTIVE_MEASURES_ENTRY' and code = 'INVALIDATION_DATE';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'MARK_USE_PROHIBITION',value,4 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='PROTECTIVE_MEASURES_ENTRY' and code = 'SECURITY_MEASURE_PROHIBITION_RIGHTS_USE';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'MARK_DISPOSITION_PROHIBITION',value,5 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='PROTECTIVE_MEASURES_ENTRY' and code = 'SECURITY_MEASURE_PROHIBITION_RIGHTS_MANAGE';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'DESCRIPTION',value,6 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='PROTECTIVE_MEASURES_ENTRY' and code = 'description';


-- 26. Securities termination -----------------------------------------------------

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'DESCRIPTION', description, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='PROTECTIVE_MEASURES_TERMINATION' and description <> '';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'REQUEST_SENDER', applicant, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='PROTECTIVE_MEASURES_TERMINATION' and applicant <> '';

-- 27. Bankruptcy -----------------------------------------------------

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'REQUEST_SENDER', applicant, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='BANKRUPTCY_ENTRY' and applicant <> '';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'ENTRY_DATE',value,2 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='BANKRUPTCY_ENTRY' and code = 'EFFECTIVE_DATE';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'DELETION_DATE',value,3 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='BANKRUPTCY_ENTRY' and code = 'INVALIDATION_DATE';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'CASE_NUMBER',value,4 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='BANKRUPTCY_ENTRY' and code = 'BANKRUPTCY_CASE_NUMBER';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'COURT',value,5 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='BANKRUPTCY_ENTRY' and code = 'BANKRUPTCY_COURT_NAME';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'DESCRIPTION',value,6 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='BANKRUPTCY_ENTRY' and code = 'description';

-- 28. Bankruptcy termination -----------------------------------------------------
insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'DESCRIPTION', description, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='BANKRUPTCY_DELETION' and description <> '';

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'REQUEST_SENDER', applicant, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='BANKRUPTCY_DELETION' and applicant <> '';

--'COURT',30 Surrender of rights  -----------------------------------

insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'REQUEST_SENDER', applicant, 1
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='DISCLAIMER' and applicant <> '';



insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'ENTRY_DATE',value,2 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='DISCLAIMER' and code = 'EFFECTIVE_DATE';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id, 'GOODS', common_data1, 3
from ip_object_recordals.tmp_recordal_data
where recordal_type_id='DISCLAIMER' and common_data1 is not null;


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'DESCRIPTION',value,4 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='DISCLAIMER' and code = 'description';


insert into ip_object_recordals.details (recordal_id, detail_type_id, description, order_by)
select id,'APPROVED_DATA',value,5 from ip_object_recordals.tmp_recordal_extra_data
where recordal_type_id='DISCLAIMER' and code = 'APPROVED_DATA';


RETURN 1;
END;
$$;


SET search_path = ip_objects, pg_catalog;

--
-- TOC entry 343 (class 1255 OID 24226)
-- Name: delete_common_object(character varying); Type: FUNCTION; Schema: ip_objects; Owner: postgres
--

CREATE FUNCTION delete_common_object(v_id character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
res int;
begin
delete from ip_objects.ip_object_relationship where object_id1 = v_id or object_id2 = v_id;
delete from ip_objects.ip_object_priority where object_id = v_id;
delete from ip_objects.ip_object_publication where object_id = v_id;
delete from ip_objects.ip_person_to_ip_object where object_id = v_id;
delete from ip_objects.ip_object_custom_replication where object_id = v_id;
delete from ip_objects.ip_object where id = v_id;
return 1;
END;
$$;


--
-- TOC entry 348 (class 1255 OID 24227)
-- Name: delete_design_object(character varying); Type: FUNCTION; Schema: ip_objects; Owner: postgres
--

CREATE FUNCTION delete_design_object(v_id character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
res int;
    r integer;
BEGIN
select 1 into res from ip_objects.ip_object where id = v_id;
if res is null then return 0; end if;


    create temp table temp_design_attachments(attachment_id integer, thumbnail_id integer, dsview_picture_id integer);
insert into temp_design_attachments (attachment_id, thumbnail_id, dsview_picture_id) select attachment_id, thumbnail_id, dsview_picture_id from ip_objects.ip_single_design_drawing where single_design_id in (select single_design_id from ip_objects.ip_single_design where main_design_id = v_id);
delete from ip_objects.ip_single_design_drawing where single_design_id in (select single_design_id from ip_objects.ip_single_design where main_design_id = v_id);
delete from ip_objects.ip_attachment where id in (select attachment_id from temp_design_attachments);
delete from ip_objects.ip_attachment where id in (select thumbnail_id from temp_design_attachments);
delete from ip_objects.ip_attachment where id in (select dsview_picture_id from temp_design_attachments);
delete from ip_objects.ip_single_design_locarno where single_design_id in (select single_design_id from ip_objects.ip_single_design where main_design_id = v_id);
delete from ip_objects.ip_single_design where main_design_id = v_id;
delete from ip_objects.ip_design where design_id = v_id;
drop table temp_design_attachments;
perform ip_objects.delete_common_object(v_id);
return 1;
END;
$$;


--
-- TOC entry 346 (class 1255 OID 29132)
-- Name: delete_ip_attachment(); Type: FUNCTION; Schema: ip_objects; Owner: postgres
--

CREATE FUNCTION delete_ip_attachment() RETURNS trigger
    LANGUAGE plpgsql
    AS $$

BEGIN
INSERT INTO ip_objects.ip_attachment_deletes (attachment_id, bucket_name, file_location, status) VALUES (old.id, old.bucket_name, old.file_location, 0);
return new;
END;

$$;

--
-- TOC entry 347 (class 1255 OID 24228)
-- Name: delete_mark_object(character varying); Type: FUNCTION; Schema: ip_objects; Owner: postgres
--

CREATE FUNCTION delete_mark_object(v_id character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
res int;
BEGIN
select 1 into res from ip_objects.ip_object where id = v_id;
if res is null then return 0; end if;

    create temp table temp_mark_attachments(attachment_id integer, thumbnail_id integer, tmview_picture_id integer);
insert into temp_mark_attachments (attachment_id, thumbnail_id, tmview_picture_id) select attachment_id, thumbnail_id, tmview_picture_id from ip_objects.ip_mark_attachment where mark_id = v_id;
delete from ip_objects.ip_mark_attachment_vienna_class where mark_id = v_id;
delete from ip_objects.ip_mark_attachment where mark_id = v_id;
delete from ip_objects.ip_attachment where id in (select attachment_id from temp_mark_attachments);
delete from ip_objects.ip_attachment where id in (select thumbnail_id from temp_mark_attachments);
delete from ip_objects.ip_attachment where id in (select tmview_picture_id from temp_mark_attachments);

delete from ip_objects.ip_mark_nice_class where mark_id = v_id;
delete from ip_objects.ip_mark where mark_id = v_id;
perform ip_objects.delete_common_object(v_id);
drop table temp_mark_attachments;
return 1;
END;
$$;

--
-- TOC entry 345 (class 1255 OID 24229)
-- Name: delete_patent_object(character varying); Type: FUNCTION; Schema: ip_objects; Owner: postgres
--

CREATE FUNCTION delete_patent_object(v_id character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
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
delete from ip_objects.ip_patent where patent_id = v_id;
drop table temp_patent_attachments;
perform ip_objects.delete_common_object(v_id);
return 1;
END;
$$;

--
-- TOC entry 344 (class 1255 OID 24230)
-- Name: delete_plant_object(character varying); Type: FUNCTION; Schema: ip_objects; Owner: postgres
--

CREATE FUNCTION delete_plant_object(v_id character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
res int;
BEGIN
select 1 into res from ip_objects.ip_object where id = v_id;
if res is null then return 0; end if;

delete from ip_objects.ip_plant where plant_id = v_id;
perform ip_objects.delete_common_object(v_id);
return 1;
END;
$$;

SET search_path = ip_object_recordals, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 241 (class 1259 OID 24231)
-- Name: detail_types; Type: TABLE; Schema: ip_object_recordals; Owner: postgres
--

CREATE TABLE detail_types (
                              id character varying(50) NOT NULL,
                              description character varying(255),
                              description_en character varying(255)
);

--
-- TOC entry 242 (class 1259 OID 24237)
-- Name: details; Type: TABLE; Schema: ip_object_recordals; Owner: postgres
--

CREATE TABLE details (
                         id integer NOT NULL,
                         recordal_id integer NOT NULL,
                         detail_type_id character varying(50) NOT NULL,
                         description text NOT NULL,
                         order_by integer
);


--
-- TOC entry 243 (class 1259 OID 24243)
-- Name: details_id_seq; Type: SEQUENCE; Schema: ip_object_recordals; Owner: postgres
--

CREATE SEQUENCE details_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 2688 (class 0 OID 0)
-- Dependencies: 243
-- Name: details_id_seq; Type: SEQUENCE OWNED BY; Schema: ip_object_recordals; Owner: postgres
--

ALTER SEQUENCE details_id_seq OWNED BY details.id;


--
-- TOC entry 244 (class 1259 OID 24245)
-- Name: recordal_types; Type: TABLE; Schema: ip_object_recordals; Owner: postgres
--

CREATE TABLE recordal_types (
                                id character varying(50) NOT NULL,
                                description character varying(255),
                                description_en character varying(255)
);




--
-- TOC entry 245 (class 1259 OID 24251)
-- Name: recordals; Type: TABLE; Schema: ip_object_recordals; Owner: postgres
--

CREATE TABLE recordals (
                           id integer NOT NULL,
                           ip_object_id character varying(20) NOT NULL,
                           recordal_type_id character varying(50) NOT NULL,
                           recordal_number character varying(50) NOT NULL,
                           recordal_id integer,
                           requested_date date NOT NULL,
                           invalidation_date date,
                           recordal_date character varying(100),
                           group_type integer
);




--
-- TOC entry 246 (class 1259 OID 24254)
-- Name: recordals_id_seq; Type: SEQUENCE; Schema: ip_object_recordals; Owner: postgres
--

CREATE SEQUENCE recordals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 2689 (class 0 OID 0)
-- Dependencies: 246
-- Name: recordals_id_seq; Type: SEQUENCE OWNED BY; Schema: ip_object_recordals; Owner: postgres
--

ALTER SEQUENCE recordals_id_seq OWNED BY recordals.id;


--
-- TOC entry 247 (class 1259 OID 24256)
-- Name: tmp_recordal_data; Type: TABLE; Schema: ip_object_recordals; Owner: postgres
--

CREATE TABLE tmp_recordal_data (
                                   id integer NOT NULL,
                                   object_id character varying(20) NOT NULL,
                                   recordal_type_id character varying(50),
                                   recordal_number character varying(50) NOT NULL,
                                   recordal_date character varying(20),
                                   recordal_id integer,
                                   group_type integer NOT NULL,
                                   old_owner text,
                                   new_owner text,
                                   description text,
                                   contract_or_seq_nbr integer,
                                   contract_date character varying(20),
                                   common_data1 text,
                                   common_data2 text,
                                   applicant text,
                                   payer text,
                                   payee text,
                                   grantor text,
                                   grantee text,
                                   pledger text,
                                   invalidation_date text
);




--
-- TOC entry 248 (class 1259 OID 24262)
-- Name: tmp_recordal_extra_data; Type: TABLE; Schema: ip_object_recordals; Owner: postgres
--

CREATE TABLE tmp_recordal_extra_data (
                                         id integer NOT NULL,
                                         recordal_type_id character varying(50),
                                         code character varying(255) NOT NULL,
                                         value text,
                                         order_by integer NOT NULL
);




SET search_path = ip_objects, pg_catalog;

--
-- TOC entry 249 (class 1259 OID 24268)
-- Name: ip_agent; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_agent (
                          person_id integer NOT NULL,
                          agent_code character varying(5),
                          representative_type character varying(2) NOT NULL,
                          agent_speciality character varying(2) NOT NULL,
                          name_en character varying(255),
                          first_name_en character varying(255),
                          second_name_en character varying(255),
                          last_name_en character varying(255),
                          qualif_country_code character varying(2),
                          speciality character varying(255),
                          speciality_en character varying(255),
                          inactive character varying(2),
                          ext_status numeric(2,0),
                          represent_persons character varying(2000)
);




--
-- TOC entry 313 (class 1259 OID 31017)
-- Name: ip_agent_addresses; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_agent_addresses (
                                    person_id integer NOT NULL,
                                    city_name_en character varying(254),
                                    address_street_en character varying(2000),
                                    website character varying(100),
                                    address_street_ca character varying(2000),
                                    address_street_ca_en character varying(2000),
                                    zip_code_ca character varying(15),
                                    phone_ca character varying(100),
                                    email_ca character varying(100),
                                    fax_ca character varying(100),
                                    city_name_ca character varying(254),
                                    city_name_ca_en character varying(254)
);




--
-- TOC entry 328 (class 1259 OID 141870)
-- Name: ip_agent_history; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_agent_history (
                                  id integer NOT NULL,
                                  person_id integer,
                                  history_type character varying(55),
                                  history_timestamp timestamp with time zone,
                                  history_xml_data text,
                                  initiating_doc character varying(50)
);




--
-- TOC entry 327 (class 1259 OID 141868)
-- Name: ip_agent_history_id_seq; Type: SEQUENCE; Schema: ip_objects; Owner: postgres
--

CREATE SEQUENCE ip_agent_history_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 2690 (class 0 OID 0)
-- Dependencies: 327
-- Name: ip_agent_history_id_seq; Type: SEQUENCE OWNED BY; Schema: ip_objects; Owner: postgres
--

ALTER SEQUENCE ip_agent_history_id_seq OWNED BY ip_agent_history.id;


--
-- TOC entry 325 (class 1259 OID 141845)
-- Name: ip_agent_partnership; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_agent_partnership (
                                      idpartnership integer NOT NULL,
                                      idagent integer NOT NULL
);




--
-- TOC entry 250 (class 1259 OID 24271)
-- Name: ip_attachment; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_attachment (
                               id integer NOT NULL,
                               file_name character varying(255),
                               content_type character varying(50),
                               file_size integer,
                               file_location character varying(100),
                               attachment_type_code character varying(5) NOT NULL,
                               bucket_name character varying(50) NOT NULL,
                               content bytea
);




--
-- TOC entry 311 (class 1259 OID 29125)
-- Name: ip_attachment_deletes; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_attachment_deletes (
                                       id integer NOT NULL,
                                       bucket_name character varying(50) NOT NULL,
                                       file_location character varying(255) NOT NULL,
                                       status integer NOT NULL,
                                       attachment_id integer NOT NULL
);




--
-- TOC entry 310 (class 1259 OID 29123)
-- Name: ip_attachment_deletes_id_seq; Type: SEQUENCE; Schema: ip_objects; Owner: postgres
--

CREATE SEQUENCE ip_attachment_deletes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 2691 (class 0 OID 0)
-- Dependencies: 310
-- Name: ip_attachment_deletes_id_seq; Type: SEQUENCE OWNED BY; Schema: ip_objects; Owner: postgres
--

ALTER SEQUENCE ip_attachment_deletes_id_seq OWNED BY ip_attachment_deletes.id;


--
-- TOC entry 251 (class 1259 OID 24274)
-- Name: ip_design; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_design (
                           design_id character varying(20) NOT NULL,
                           application_comment text,
                           publication_date date,
                           effective_date date,
                           deferment_expiration_date date
);




--
-- TOC entry 252 (class 1259 OID 24286)
-- Name: ip_ftp_file_upload; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_ftp_file_upload (
                                    id integer NOT NULL,
                                    replicator_type character varying(20) NOT NULL,
                                    file_path text NOT NULL,
                                    file_name text NOT NULL,
                                    status integer NOT NULL,
                                    error_count integer NOT NULL,
                                    date_sent timestamp with time zone,
                                    date_last_error timestamp with time zone,
                                    date_created timestamp with time zone DEFAULT now() NOT NULL
);




--
-- TOC entry 253 (class 1259 OID 24293)
-- Name: ip_ftp_file_upload_id_seq; Type: SEQUENCE; Schema: ip_objects; Owner: postgres
--

CREATE SEQUENCE ip_ftp_file_upload_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 2693 (class 0 OID 0)
-- Dependencies: 253
-- Name: ip_ftp_file_upload_id_seq; Type: SEQUENCE OWNED BY; Schema: ip_objects; Owner: postgres
--

ALTER SEQUENCE ip_ftp_file_upload_id_seq OWNED BY ip_ftp_file_upload.id;


--
-- TOC entry 254 (class 1259 OID 24295)
-- Name: ip_ftp_file_upload_to_replication_time; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_ftp_file_upload_to_replication_time (
                                                        id integer NOT NULL,
                                                        ffu_id integer NOT NULL,
                                                        rte_id integer NOT NULL
);




--
-- TOC entry 255 (class 1259 OID 24298)
-- Name: ip_ftp_file_upload_to_replication_time_id_seq; Type: SEQUENCE; Schema: ip_objects; Owner: postgres
--

CREATE SEQUENCE ip_ftp_file_upload_to_replication_time_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 2694 (class 0 OID 0)
-- Dependencies: 255
-- Name: ip_ftp_file_upload_to_replication_time_id_seq; Type: SEQUENCE OWNED BY; Schema: ip_objects; Owner: postgres
--

ALTER SEQUENCE ip_ftp_file_upload_to_replication_time_id_seq OWNED BY ip_ftp_file_upload_to_replication_time.id;


--
-- TOC entry 256 (class 1259 OID 24300)
-- Name: ip_mark; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_mark (
                         mark_id character varying(20) NOT NULL,
                         mark_kind character varying NOT NULL,
                         mark_description character varying,
                         mark_transliteration character varying,
                         mark_translation character varying,
                         disclaimer character varying(2000),
                         status_changed_date date,
                         opposition_date date,
                         opposition_end_date date
);




--
-- TOC entry 305 (class 1259 OID 28968)
-- Name: ip_mark_attachment; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_mark_attachment (
                                    mark_id character varying(20) NOT NULL,
                                    att_seq_nbr integer NOT NULL,
                                    color_description text,
                                    attachment_id integer NOT NULL,
                                    thumbnail_id integer,
                                    tmview_picture_id integer
);




--
-- TOC entry 306 (class 1259 OID 28981)
-- Name: ip_mark_attachment_vienna_class; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_mark_attachment_vienna_class (
                                                 mark_id character varying(20) NOT NULL,
                                                 att_seq_nbr integer NOT NULL,
                                                 category_id character varying(8) NOT NULL
);




--
-- TOC entry 257 (class 1259 OID 24306)
-- Name: ip_mark_nice_class; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_mark_nice_class (
                                    mark_id character varying(20) NOT NULL,
                                    class_id integer NOT NULL,
                                    specification text
);




--
-- TOC entry 258 (class 1259 OID 24315)
-- Name: ip_object; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_object (
                           id character varying(20) NOT NULL,
                           alternate_key character varying(20),
                           object_type character varying NOT NULL,
                           title text,
                           title_en text,
                           notes text,
                           filing_date date,
                           entitlement_date date,
                           expiration_date date,
                           registration_date date,
                           registration_number integer,
                           status integer,
                           status_date date,
                           registration_dup character varying(2),
                           object_subtype integer,
                           published integer NOT NULL
);




--
-- TOC entry 259 (class 1259 OID 24321)
-- Name: ip_object_blob_id_seq; Type: SEQUENCE; Schema: ip_objects; Owner: postgres
--

CREATE SEQUENCE ip_object_blob_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 2695 (class 0 OID 0)
-- Dependencies: 259
-- Name: ip_object_blob_id_seq; Type: SEQUENCE OWNED BY; Schema: ip_objects; Owner: postgres
--

ALTER SEQUENCE ip_object_blob_id_seq OWNED BY ip_attachment.id;


--
-- TOC entry 260 (class 1259 OID 24323)
-- Name: ip_object_custom_replication; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_object_custom_replication (
                                              object_id character varying(20) NOT NULL,
                                              replicator_type character varying,
                                              reason text,
                                              date timestamp without time zone
);




--
-- TOC entry 261 (class 1259 OID 24329)
-- Name: ip_object_priority; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_object_priority (
                                    id integer NOT NULL,
                                    object_id character varying(20) NOT NULL,
                                    country character varying(2),
                                    priority_number character varying(20),
                                    priority_date date NOT NULL,
                                    remarks text,
                                    status integer NOT NULL,
                                    priority_type character varying(1) NOT NULL
);




--
-- TOC entry 262 (class 1259 OID 24335)
-- Name: ip_object_priority_id_seq; Type: SEQUENCE; Schema: ip_objects; Owner: postgres
--

CREATE SEQUENCE ip_object_priority_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 2696 (class 0 OID 0)
-- Dependencies: 262
-- Name: ip_object_priority_id_seq; Type: SEQUENCE OWNED BY; Schema: ip_objects; Owner: postgres
--

ALTER SEQUENCE ip_object_priority_id_seq OWNED BY ip_object_priority.id;


--
-- TOC entry 263 (class 1259 OID 24337)
-- Name: ip_object_publication; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_object_publication (
                                       id integer NOT NULL,
                                       object_id character varying(20) NOT NULL,
                                       publication_number character varying(20) NOT NULL,
                                       publication_year smallint NOT NULL,
                                       publication_date date NOT NULL,
                                       publication_section integer NOT NULL,
                                       seq_nbr integer
);




--
-- TOC entry 264 (class 1259 OID 24343)
-- Name: ip_object_publication_id_seq; Type: SEQUENCE; Schema: ip_objects; Owner: postgres
--

CREATE SEQUENCE ip_object_publication_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 2697 (class 0 OID 0)
-- Dependencies: 264
-- Name: ip_object_publication_id_seq; Type: SEQUENCE OWNED BY; Schema: ip_objects; Owner: postgres
--

ALTER SEQUENCE ip_object_publication_id_seq OWNED BY ip_object_publication.id;


--
-- TOC entry 265 (class 1259 OID 24345)
-- Name: ip_object_relationship; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_object_relationship (
                                        object_id1 character varying(20) NOT NULL,
                                        object_id2 character varying(20) NOT NULL,
                                        relationship_typ character varying(20) NOT NULL,
                                        application_typ character varying(20) NOT NULL,
                                        filing_number character varying(50),
                                        filing_date date,
                                        registration_number character varying(50),
                                        registration_date date,
                                        registration_country character varying(30),
                                        cancellation_date date,
                                        priority_date date,
                                        serve_message_date date
);




--
-- TOC entry 266 (class 1259 OID 24348)
-- Name: ip_patent; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_patent (
                           patent_id character varying(20) NOT NULL,
                           drawings_count integer,
                           claims_count integer,
                           description_pages_count integer,
                           drawing_publication integer,
                           inventions_group_count integer,
                           renewal_fees_last_paid date,
                           renewal_fees_paid_to date,
                           last_paid_year integer,
                           not_in_force_date date,
                           effective_date date,
                           main_abstract text,
                           en_abstract text
);




--
-- TOC entry 315 (class 1259 OID 32790)
-- Name: ip_patent_attachment; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_patent_attachment (
                                      patent_id character varying(20) NOT NULL,
                                      attachment_id integer NOT NULL,
                                      date_created timestamp with time zone,
                                      seq_nbr integer NOT NULL
);




--
-- TOC entry 267 (class 1259 OID 24360)
-- Name: ip_patent_attachment_attachment_id_seq; Type: SEQUENCE; Schema: ip_objects; Owner: postgres
--

CREATE SEQUENCE ip_patent_attachment_attachment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 268 (class 1259 OID 24362)
-- Name: ip_patent_ipc_classes; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_patent_ipc_classes (
                                       patent_id character varying(20) NOT NULL,
                                       edition_code character varying(50) NOT NULL,
                                       section_code character varying(1) NOT NULL,
                                       class_code character varying(2) NOT NULL,
                                       subclass_code character varying(2) NOT NULL,
                                       group_code character varying(3) NOT NULL,
                                       subgroup_code character varying(5) NOT NULL
);




--
-- TOC entry 269 (class 1259 OID 24365)
-- Name: ip_patent_pct; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_patent_pct (
                               patent_id character varying(20) NOT NULL,
                               pct_application_id character varying(20),
                               pct_application_date date,
                               pct_publication_country_code character varying(2),
                               pct_publication_id character varying(25),
                               pct_publication_date date,
                               pct_phase integer
);




--
-- TOC entry 270 (class 1259 OID 24368)
-- Name: ip_patent_summary; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_patent_summary (
                                   patent_id character varying(20) NOT NULL,
                                   language_code character varying(20) NOT NULL,
                                   description text
);




--
-- TOC entry 271 (class 1259 OID 24374)
-- Name: ip_payment_id_seq; Type: SEQUENCE; Schema: ip_objects; Owner: postgres
--

CREATE SEQUENCE ip_payment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 272 (class 1259 OID 24376)
-- Name: ip_person; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_person (
                           person_id integer NOT NULL,
                           name character varying(700),
                           nationality_country_code character varying(2),
                           telephone character varying(200),
                           email character varying(200),
                           portal_user_number numeric(8,0),
                           legal_type integer NOT NULL,
                           first_name character varying(255),
                           second_name character varying(255),
                           last_name character varying(255)
);




--
-- TOC entry 273 (class 1259 OID 24382)
-- Name: ip_person_address_id_seq; Type: SEQUENCE; Schema: ip_objects; Owner: postgres
--

CREATE SEQUENCE ip_person_address_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 274 (class 1259 OID 24384)
-- Name: ip_person_addresses; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_person_addresses (
                                     person_id integer NOT NULL,
                                     address_street character varying(2000),
                                     city_name character varying(254),
                                     residence_country_code character varying(2),
                                     state_name character varying(254),
                                     zip_code character varying(16)
);




--
-- TOC entry 275 (class 1259 OID 24390)
-- Name: ip_person_id_seq; Type: SEQUENCE; Schema: ip_objects; Owner: postgres
--

CREATE SEQUENCE ip_person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 276 (class 1259 OID 24392)
-- Name: ip_person_to_ip_object; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_person_to_ip_object (
                                        object_id character varying(20) NOT NULL,
                                        person_id integer NOT NULL,
                                        person_role character varying NOT NULL,
                                        person_order integer,
                                        representative_type character varying(2)
);




--
-- TOC entry 277 (class 1259 OID 24398)
-- Name: ip_plant; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_plant (
                          plant_id character varying(20) NOT NULL,
                          drawings_count integer,
                          effective_date date,
                          main_abstract text,
                          en_abstract text,
                          features text,
                          stability text,
                          testing text,
                          approved_title text,
                          approved_engtitle text,
                          taxon_id integer
);




--
-- TOC entry 278 (class 1259 OID 24404)
-- Name: ip_replication_details; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_replication_details (
                                        id integer NOT NULL,
                                        object_id character varying(20) NOT NULL,
                                        replication_time_id integer NOT NULL,
                                        operation_code character varying(10) NOT NULL
);




--
-- TOC entry 279 (class 1259 OID 24407)
-- Name: ip_replication_details_id_seq; Type: SEQUENCE; Schema: ip_objects; Owner: postgres
--

CREATE SEQUENCE ip_replication_details_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 2698 (class 0 OID 0)
-- Dependencies: 279
-- Name: ip_replication_details_id_seq; Type: SEQUENCE OWNED BY; Schema: ip_objects; Owner: postgres
--

ALTER SEQUENCE ip_replication_details_id_seq OWNED BY ip_replication_details.id;


--
-- TOC entry 280 (class 1259 OID 24409)
-- Name: ip_replication_times; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_replication_times (
                                      id integer NOT NULL,
                                      exec_time timestamp without time zone NOT NULL,
                                      replicator_type character varying,
                                      modified_rows integer,
                                      xml_generated integer DEFAULT 0 NOT NULL,
                                      indexed integer NOT NULL
);




--
-- TOC entry 281 (class 1259 OID 24416)
-- Name: ip_replication_times_id_seq; Type: SEQUENCE; Schema: ip_objects; Owner: postgres
--

CREATE SEQUENCE ip_replication_times_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 2699 (class 0 OID 0)
-- Dependencies: 281
-- Name: ip_replication_times_id_seq; Type: SEQUENCE OWNED BY; Schema: ip_objects; Owner: postgres
--

ALTER SEQUENCE ip_replication_times_id_seq OWNED BY ip_replication_times.id;


--
-- TOC entry 282 (class 1259 OID 24418)
-- Name: ip_single_design; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_single_design (
                                  single_design_id character varying(20) NOT NULL,
                                  main_design_id character varying(20) NOT NULL,
                                  name text,
                                  name_en text,
                                  status integer,
                                  design_type integer,
                                  published integer NOT NULL
);




--
-- TOC entry 283 (class 1259 OID 24424)
-- Name: ip_single_design_drawing; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_single_design_drawing (
                                          single_design_id character varying(20) NOT NULL,
                                          drawing_nbr integer NOT NULL,
                                          view_type_id integer,
                                          attachment_id integer,
                                          thumbnail_id integer,
                                          dsview_picture_id integer
);




--
-- TOC entry 284 (class 1259 OID 24427)
-- Name: ip_single_design_locarno; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_single_design_locarno (
                                          single_design_id character varying(20) NOT NULL,
                                          locarno_class_code character varying(5) NOT NULL
);




--
-- TOC entry 285 (class 1259 OID 24430)
-- Name: ip_single_design_replication_details; Type: TABLE; Schema: ip_objects; Owner: postgres
--

CREATE TABLE ip_single_design_replication_details (
                                                      id integer NOT NULL,
                                                      single_design_id character varying(20) NOT NULL,
                                                      main_design_replication_detail_id integer NOT NULL,
                                                      operation_code character varying(10) NOT NULL,
                                                      registration_number integer
);




--
-- TOC entry 286 (class 1259 OID 24433)
-- Name: ip_single_design_replication_details_id_seq; Type: SEQUENCE; Schema: ip_objects; Owner: postgres
--

CREATE SEQUENCE ip_single_design_replication_details_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 2700 (class 0 OID 0)
-- Dependencies: 286
-- Name: ip_single_design_replication_details_id_seq; Type: SEQUENCE OWNED BY; Schema: ip_objects; Owner: postgres
--

ALTER SEQUENCE ip_single_design_replication_details_id_seq OWNED BY ip_single_design_replication_details.id;




SET search_path = nomenclatures, pg_catalog;

--
-- TOC entry 326 (class 1259 OID 141860)
-- Name: agent_history_type; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE agent_history_type (
                                    code character varying(55) NOT NULL,
                                    name character varying(255),
                                    name_en character varying(255)
);




--
-- TOC entry 312 (class 1259 OID 30986)
-- Name: agent_speciality; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE agent_speciality (
                                  code character varying(2) NOT NULL,
                                  name character varying(255),
                                  short_name character varying(50),
                                  name_en character varying(255),
                                  short_name_en character varying(50),
                                  ipas_code integer NOT NULL
);




--
-- TOC entry 320 (class 1259 OID 41957)
-- Name: agent_status; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE agent_status (
                              id integer NOT NULL,
                              name character varying NOT NULL,
                              name_en character varying NOT NULL
);




--
-- TOC entry 287 (class 1259 OID 24435)
-- Name: attachment_type; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE attachment_type (
                                 code character varying(5) NOT NULL,
                                 name character varying(100),
                                 name_en character varying(100),
                                 backoffice_code character varying(5)
);




--
-- TOC entry 288 (class 1259 OID 24438)
-- Name: backoffice_dsview_status_map; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE backoffice_dsview_status_map (
                                              backoffice_status_id integer NOT NULL,
                                              dsview_list_code character varying(100),
                                              dsview_detailed_code character varying(100),
                                              dsview_detailed_code_bg character varying(100),
                                              deferred_publication integer
);




--
-- TOC entry 289 (class 1259 OID 24441)
-- Name: backoffice_epopatent_status_map; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE backoffice_epopatent_status_map (
                                                 backoffice_status_id integer NOT NULL,
                                                 ep_status_code character varying(20),
                                                 fep_inval_date_tag character varying(30)
);




--
-- TOC entry 290 (class 1259 OID 24444)
-- Name: backoffice_status_map; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE backoffice_status_map (
                                       backoffice_status_id integer NOT NULL,
                                       backoffice_status_name character varying(100),
                                       for_deletion integer DEFAULT 0 NOT NULL,
                                       bpo_online_status character varying(100),
                                       bpo_online_status_en character varying(100),
                                       show_expiration_date integer DEFAULT 1 NOT NULL
);




--
-- TOC entry 291 (class 1259 OID 24449)
-- Name: backoffice_tmview_status_map; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE backoffice_tmview_status_map (
                                              backoffice_status_id integer NOT NULL,
                                              tmview_list_code character varying(15),
                                              tmview_detailed_code character varying(30),
                                              tmview_detailed_code_bg character varying(30),
                                              show_person_missing_publication integer DEFAULT 0 NOT NULL
);




--
-- TOC entry 292 (class 1259 OID 24453)
-- Name: country; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE country (
                         code varchar(2) NOT NULL,
                         name character varying(80) NOT NULL,
                         name_en character varying(80)
);




--
-- TOC entry 293 (class 1259 OID 24456)
-- Name: drawing_view_type; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE drawing_view_type (
                                   id integer NOT NULL,
                                   view_type_name text
);




--
-- TOC entry 307 (class 1259 OID 29037)
-- Name: dsview_publication; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE dsview_publication (
                                    publication_section integer NOT NULL,
                                    publication_code_dsview character varying(255) NOT NULL
);




--
-- TOC entry 322 (class 1259 OID 41982)
-- Name: legal_decision_ground_type; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE legal_decision_ground_type (
                                            id integer NOT NULL,
                                            name character varying NOT NULL,
                                            name_en character varying
);




--
-- TOC entry 321 (class 1259 OID 41973)
-- Name: legal_decision_type; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE legal_decision_type (
                                     id integer NOT NULL,
                                     name character varying NOT NULL,
                                     name_en character varying
);




--
-- TOC entry 294 (class 1259 OID 24462)
-- Name: mark_kind; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE mark_kind (
                           backoffice_code character varying(50) NOT NULL,
                           description character varying NOT NULL,
                           description_en character varying NOT NULL,
                           tmview_mark_feature character varying(50)
);




--
-- TOC entry 295 (class 1259 OID 24468)
-- Name: object_subtype; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE object_subtype (
                                backoffice_application_type character varying(50) NOT NULL,
                                backoffice_application_subtype character varying(50) NOT NULL,
                                description character varying NOT NULL,
                                description_en character varying NOT NULL,
                                id integer NOT NULL
);




--
-- TOC entry 314 (class 1259 OID 32545)
-- Name: object_subtype_id_seq; Type: SEQUENCE; Schema: nomenclatures; Owner: postgres
--

CREATE SEQUENCE object_subtype_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 2701 (class 0 OID 0)
-- Dependencies: 314
-- Name: object_subtype_id_seq; Type: SEQUENCE OWNED BY; Schema: nomenclatures; Owner: postgres
--

ALTER SEQUENCE object_subtype_id_seq OWNED BY object_subtype.id;


--
-- TOC entry 296 (class 1259 OID 24480)
-- Name: object_type; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE object_type (
                             backoffice_code character varying(50) NOT NULL,
                             description text NOT NULL,
                             description_en text
);




--
-- TOC entry 297 (class 1259 OID 24486)
-- Name: object_type_person_role; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE object_type_person_role (
                                         object_type character varying NOT NULL,
                                         person_role character varying NOT NULL,
                                         use_in_legacy_requests boolean DEFAULT false NOT NULL
);




--
-- TOC entry 319 (class 1259 OID 41949)
-- Name: object_type_to_status; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE object_type_to_status (
                                       object_type character varying NOT NULL,
                                       status_id integer NOT NULL
);




--
-- TOC entry 323 (class 1259 OID 42080)
-- Name: object_type_to_subtype; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE object_type_to_subtype (
                                        object_type character varying NOT NULL,
                                        object_subtype integer NOT NULL
);




--
-- TOC entry 298 (class 1259 OID 24493)
-- Name: patent_ipc_class; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE patent_ipc_class (
                                  edition_code character varying(50) NOT NULL,
                                  section_code character varying(1) NOT NULL,
                                  class_code character varying(2) NOT NULL,
                                  subclass_code character varying(2) NOT NULL,
                                  group_code character varying(3) NOT NULL,
                                  subgroup_code character varying(5) NOT NULL,
                                  name character varying(300),
                                  name_en character varying(300)
);




--
-- TOC entry 299 (class 1259 OID 24499)
-- Name: person_role; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE person_role (
                             code character varying DEFAULT ''::character varying NOT NULL,
                             description text,
                             description_en text
);




--
-- TOC entry 324 (class 1259 OID 141773)
-- Name: plant_taxon; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE plant_taxon (
                             id integer NOT NULL,
                             taxon_code character varying(30) NOT NULL,
                             common_classify_bg character varying(255) NOT NULL,
                             common_classify_en character varying(255) NOT NULL,
                             latin_classify character varying(255) NOT NULL
);




--
-- TOC entry 300 (class 1259 OID 24506)
-- Name: priority_status; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE priority_status (
                                 backoffice_code character varying NOT NULL,
                                 description character varying NOT NULL,
                                 description_en character varying,
                                 object_type character varying NOT NULL
);




--
-- TOC entry 301 (class 1259 OID 24512)
-- Name: priority_type; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE priority_type (
                               backoffice_code character varying(1) NOT NULL,
                               description character varying(255) NOT NULL,
                               description_en character varying(255) NOT NULL
);




--
-- TOC entry 302 (class 1259 OID 24518)
-- Name: publication_section; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE publication_section (
                                     idsection integer NOT NULL,
                                     nmsection varchar(255) NOT NULL,
                                     is_searchable smallint,
                                     publ_code character varying(3),
                                     nmsection_en varchar(255) NOT NULL,
                                     notes text
);




--
-- TOC entry 303 (class 1259 OID 24530)
-- Name: relationship_type; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE relationship_type (
                                   relationship_typ character varying(20) NOT NULL,
                                   application_typ character varying(20) NOT NULL,
                                   relationship_name character varying(100) NOT NULL,
                                   relationship_name_en character varying(100) NOT NULL,
                                   direct_relationship_name character varying(100) NOT NULL,
                                   direct_relationship_name_en character varying(100) NOT NULL,
                                   inverse_relationship_name character varying(100) NOT NULL,
                                   inverse_relationship_name_en character varying(100) NOT NULL
);




--
-- TOC entry 309 (class 1259 OID 29094)
-- Name: replicator_type; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE replicator_type (
                                 replicator_type character varying(50) NOT NULL,
                                 description text NOT NULL
);




--
-- TOC entry 304 (class 1259 OID 24536)
-- Name: representative_type; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE representative_type (
                                     representative_type character varying(2) NOT NULL,
                                     description character varying(100),
                                     ipas_partnership_type character varying(50),
                                     has_agent_code integer,
                                     euipo_representative_kind_code character varying(50),
                                     ipas_representative_type character varying(2),
                                     description_en character varying(100)
);




--
-- TOC entry 308 (class 1259 OID 29042)
-- Name: tmview_publication; Type: TABLE; Schema: nomenclatures; Owner: postgres
--

CREATE TABLE tmview_publication (
                                    publication_section integer NOT NULL,
                                    publication_code_tmview character varying(255) NOT NULL
);




SET search_path = ip_object_recordals, pg_catalog;

--
-- TOC entry 2320 (class 2604 OID 24539)
-- Name: id; Type: DEFAULT; Schema: ip_object_recordals; Owner: postgres
--

ALTER TABLE ONLY details ALTER COLUMN id SET DEFAULT nextval('details_id_seq'::regclass);


--
-- TOC entry 2321 (class 2604 OID 24540)
-- Name: id; Type: DEFAULT; Schema: ip_object_recordals; Owner: postgres
--

ALTER TABLE ONLY recordals ALTER COLUMN id SET DEFAULT nextval('recordals_id_seq'::regclass);


SET search_path = ip_objects, pg_catalog;

--
-- TOC entry 2339 (class 2604 OID 141873)
-- Name: id; Type: DEFAULT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_agent_history ALTER COLUMN id SET DEFAULT nextval('ip_agent_history_id_seq'::regclass);


--
-- TOC entry 2322 (class 2604 OID 24541)
-- Name: id; Type: DEFAULT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_attachment ALTER COLUMN id SET DEFAULT nextval('ip_object_blob_id_seq'::regclass);


--
-- TOC entry 2338 (class 2604 OID 29128)
-- Name: id; Type: DEFAULT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_attachment_deletes ALTER COLUMN id SET DEFAULT nextval('ip_attachment_deletes_id_seq'::regclass);


--
-- TOC entry 2324 (class 2604 OID 24542)
-- Name: id; Type: DEFAULT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_ftp_file_upload ALTER COLUMN id SET DEFAULT nextval('ip_ftp_file_upload_id_seq'::regclass);


--
-- TOC entry 2325 (class 2604 OID 24543)
-- Name: id; Type: DEFAULT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_ftp_file_upload_to_replication_time ALTER COLUMN id SET DEFAULT nextval('ip_ftp_file_upload_to_replication_time_id_seq'::regclass);


--
-- TOC entry 2326 (class 2604 OID 24544)
-- Name: id; Type: DEFAULT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_object_priority ALTER COLUMN id SET DEFAULT nextval('ip_object_priority_id_seq'::regclass);


--
-- TOC entry 2327 (class 2604 OID 24545)
-- Name: id; Type: DEFAULT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_object_publication ALTER COLUMN id SET DEFAULT nextval('ip_object_publication_id_seq'::regclass);


--
-- TOC entry 2328 (class 2604 OID 24546)
-- Name: id; Type: DEFAULT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_replication_details ALTER COLUMN id SET DEFAULT nextval('ip_replication_details_id_seq'::regclass);


--
-- TOC entry 2330 (class 2604 OID 24547)
-- Name: id; Type: DEFAULT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_replication_times ALTER COLUMN id SET DEFAULT nextval('ip_replication_times_id_seq'::regclass);


--
-- TOC entry 2331 (class 2604 OID 24548)
-- Name: id; Type: DEFAULT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_single_design_replication_details ALTER COLUMN id SET DEFAULT nextval('ip_single_design_replication_details_id_seq'::regclass);


SET search_path = nomenclatures, pg_catalog;

--
-- TOC entry 2335 (class 2604 OID 32547)
-- Name: id; Type: DEFAULT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY object_subtype ALTER COLUMN id SET DEFAULT nextval('object_subtype_id_seq'::regclass);


SET search_path = ip_object_recordals, pg_catalog;

--
-- TOC entry 2341 (class 2606 OID 24551)
-- Name: detail_types_pkey; Type: CONSTRAINT; Schema: ip_object_recordals; Owner: postgres
--

ALTER TABLE ONLY detail_types
    ADD CONSTRAINT detail_types_pkey PRIMARY KEY (id);


--
-- TOC entry 2343 (class 2606 OID 24553)
-- Name: details_pkey; Type: CONSTRAINT; Schema: ip_object_recordals; Owner: postgres
--

ALTER TABLE ONLY details
    ADD CONSTRAINT details_pkey PRIMARY KEY (id);


--
-- TOC entry 2345 (class 2606 OID 24555)
-- Name: recordal_types_pkey; Type: CONSTRAINT; Schema: ip_object_recordals; Owner: postgres
--

ALTER TABLE ONLY recordal_types
    ADD CONSTRAINT recordal_types_pkey PRIMARY KEY (id);


--
-- TOC entry 2347 (class 2606 OID 24557)
-- Name: recordals_pkey; Type: CONSTRAINT; Schema: ip_object_recordals; Owner: postgres
--

ALTER TABLE ONLY recordals
    ADD CONSTRAINT recordals_pkey PRIMARY KEY (id);


--
-- TOC entry 2349 (class 2606 OID 24559)
-- Name: tmp_recordals_data_pkey; Type: CONSTRAINT; Schema: ip_object_recordals; Owner: postgres
--

ALTER TABLE ONLY tmp_recordal_data
    ADD CONSTRAINT tmp_recordals_data_pkey PRIMARY KEY (id);


--
-- TOC entry 2353 (class 2606 OID 24561)
-- Name: tmp_register_extra_data_pk; Type: CONSTRAINT; Schema: ip_object_recordals; Owner: postgres
--

ALTER TABLE ONLY tmp_recordal_extra_data
    ADD CONSTRAINT tmp_register_extra_data_pk PRIMARY KEY (id, code);


SET search_path = ip_objects, pg_catalog;

--
-- TOC entry 2357 (class 2606 OID 24563)
-- Name: blob_type_pk; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_attachment
    ADD CONSTRAINT blob_type_pk PRIMARY KEY (id);


--
-- TOC entry 2473 (class 2606 OID 31024)
-- Name: ip_agent_addresses_pkey; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_agent_addresses
    ADD CONSTRAINT ip_agent_addresses_pkey PRIMARY KEY (person_id);


--
-- TOC entry 2495 (class 2606 OID 141878)
-- Name: ip_agent_history_pkey; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_agent_history
    ADD CONSTRAINT ip_agent_history_pkey PRIMARY KEY (id);


--
-- TOC entry 2491 (class 2606 OID 141849)
-- Name: ip_agent_partnership_pk; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_agent_partnership
    ADD CONSTRAINT ip_agent_partnership_pk PRIMARY KEY (idpartnership, idagent);


--
-- TOC entry 2355 (class 2606 OID 24565)
-- Name: ip_agent_pkey; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_agent
    ADD CONSTRAINT ip_agent_pkey PRIMARY KEY (person_id);


--
-- TOC entry 2469 (class 2606 OID 29130)
-- Name: ip_attachment_deletes_pkey; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_attachment_deletes
    ADD CONSTRAINT ip_attachment_deletes_pkey PRIMARY KEY (id);


--
-- TOC entry 2360 (class 2606 OID 24567)
-- Name: ip_design_pkey; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_design
    ADD CONSTRAINT ip_design_pkey PRIMARY KEY (design_id);


--
-- TOC entry 2362 (class 2606 OID 24571)
-- Name: ip_ftp_file_upload_pkey; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_ftp_file_upload
    ADD CONSTRAINT ip_ftp_file_upload_pkey PRIMARY KEY (id);


--
-- TOC entry 2368 (class 2606 OID 24573)
-- Name: ip_ftp_file_upload_to_replication_time_pk; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_ftp_file_upload_to_replication_time
    ADD CONSTRAINT ip_ftp_file_upload_to_replication_time_pk PRIMARY KEY (id);


--
-- TOC entry 2459 (class 2606 OID 28975)
-- Name: ip_mark_attachment_pkey; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_mark_attachment
    ADD CONSTRAINT ip_mark_attachment_pkey PRIMARY KEY (mark_id, att_seq_nbr);


--
-- TOC entry 2461 (class 2606 OID 28985)
-- Name: ip_mark_category_pkey; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_mark_attachment_vienna_class
    ADD CONSTRAINT ip_mark_category_pkey PRIMARY KEY (mark_id, att_seq_nbr, category_id);


--
-- TOC entry 2372 (class 2606 OID 24577)
-- Name: ip_mark_classes_pkey; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_mark_nice_class
    ADD CONSTRAINT ip_mark_classes_pkey PRIMARY KEY (mark_id, class_id);


--
-- TOC entry 2370 (class 2606 OID 24579)
-- Name: ip_mark_pkey; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_mark
    ADD CONSTRAINT ip_mark_pkey PRIMARY KEY (mark_id);


--
-- TOC entry 2377 (class 2606 OID 24581)
-- Name: ip_object_custom_replication_pk; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_object_custom_replication
    ADD CONSTRAINT ip_object_custom_replication_pk PRIMARY KEY (object_id);


--
-- TOC entry 2375 (class 2606 OID 24583)
-- Name: ip_object_pkey; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_object
    ADD CONSTRAINT ip_object_pkey PRIMARY KEY (id);


--
-- TOC entry 2380 (class 2606 OID 24585)
-- Name: ip_object_priority_pk; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_object_priority
    ADD CONSTRAINT ip_object_priority_pk PRIMARY KEY (id);


--
-- TOC entry 2382 (class 2606 OID 29029)
-- Name: ip_object_publication_object_id_seq_nbr_key; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_object_publication
    ADD CONSTRAINT ip_object_publication_object_id_seq_nbr_key UNIQUE (object_id, seq_nbr);


--
-- TOC entry 2475 (class 2606 OID 33175)
-- Name: ip_patent_attachment_pkey; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_patent_attachment
    ADD CONSTRAINT ip_patent_attachment_pkey PRIMARY KEY (patent_id, seq_nbr);


--
-- TOC entry 2390 (class 2606 OID 24587)
-- Name: ip_patent_ipc_classes_pkey; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_patent_ipc_classes
    ADD CONSTRAINT ip_patent_ipc_classes_pkey PRIMARY KEY (patent_id, edition_code, section_code, class_code, subclass_code, group_code, subgroup_code);


--
-- TOC entry 2388 (class 2606 OID 24589)
-- Name: ip_patent_pkey; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_patent
    ADD CONSTRAINT ip_patent_pkey PRIMARY KEY (patent_id);


--
-- TOC entry 2394 (class 2606 OID 24591)
-- Name: ip_patent_summary_pkey; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_patent_summary
    ADD CONSTRAINT ip_patent_summary_pkey PRIMARY KEY (patent_id, language_code);


--
-- TOC entry 2398 (class 2606 OID 24593)
-- Name: ip_person_addresses_pkey; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_person_addresses
    ADD CONSTRAINT ip_person_addresses_pkey PRIMARY KEY (person_id);


--
-- TOC entry 2396 (class 2606 OID 24595)
-- Name: ip_person_pkey; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_person
    ADD CONSTRAINT ip_person_pkey PRIMARY KEY (person_id);


--
-- TOC entry 2401 (class 2606 OID 24597)
-- Name: ip_person_to_ip_object_pkey; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_person_to_ip_object
    ADD CONSTRAINT ip_person_to_ip_object_pkey PRIMARY KEY (object_id, person_id, person_role);


--
-- TOC entry 2414 (class 2606 OID 24599)
-- Name: ip_single_design_drawing_pk; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_single_design_drawing
    ADD CONSTRAINT ip_single_design_drawing_pk PRIMARY KEY (single_design_id, drawing_nbr);


--
-- TOC entry 2416 (class 2606 OID 24601)
-- Name: ip_single_design_locarno_pk; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_single_design_locarno
    ADD CONSTRAINT ip_single_design_locarno_pk PRIMARY KEY (single_design_id, locarno_class_code);


--
-- TOC entry 2418 (class 2606 OID 24603)
-- Name: ip_single_design_replication_details_pk; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_single_design_replication_details
    ADD CONSTRAINT ip_single_design_replication_details_pk PRIMARY KEY (id);


--
-- TOC entry 2386 (class 2606 OID 24605)
-- Name: object_relationship_pkey; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_object_relationship
    ADD CONSTRAINT object_relationship_pkey PRIMARY KEY (object_id1, object_id2, relationship_typ, application_typ);


--
-- TOC entry 2412 (class 2606 OID 24607)
-- Name: object_right_type_pkey; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_single_design
    ADD CONSTRAINT object_right_type_pkey PRIMARY KEY (single_design_id);


--
-- TOC entry 2392 (class 2606 OID 24611)
-- Name: pct_pkey; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_patent_pct
    ADD CONSTRAINT pct_pkey PRIMARY KEY (patent_id);


--
-- TOC entry 2404 (class 2606 OID 24613)
-- Name: plant_id_pk; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_plant
    ADD CONSTRAINT plant_id_pk PRIMARY KEY (plant_id);


--
-- TOC entry 2384 (class 2606 OID 24615)
-- Name: publication_pkey; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_object_publication
    ADD CONSTRAINT publication_pkey PRIMARY KEY (id);


--
-- TOC entry 2406 (class 2606 OID 24617)
-- Name: rdl_pk; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_replication_details
    ADD CONSTRAINT rdl_pk PRIMARY KEY (id);


--
-- TOC entry 2409 (class 2606 OID 24619)
-- Name: rte_pk; Type: CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_replication_times
    ADD CONSTRAINT rte_pk PRIMARY KEY (id);


SET search_path = nomenclatures, pg_catalog;

--
-- TOC entry 2493 (class 2606 OID 141867)
-- Name: agent_history_type_pkey; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY agent_history_type
    ADD CONSTRAINT agent_history_type_pkey PRIMARY KEY (code);


--
-- TOC entry 2471 (class 2606 OID 30993)
-- Name: agent_speciality_pkey; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY agent_speciality
    ADD CONSTRAINT agent_speciality_pkey PRIMARY KEY (code);


--
-- TOC entry 2481 (class 2606 OID 41964)
-- Name: agent_status_id; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY agent_status
    ADD CONSTRAINT agent_status_id PRIMARY KEY (id);


--
-- TOC entry 2420 (class 2606 OID 28825)
-- Name: attachment_type_pkey; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY attachment_type
    ADD CONSTRAINT attachment_type_pkey PRIMARY KEY (code);


--
-- TOC entry 2422 (class 2606 OID 24623)
-- Name: backoffice_dsview_status_map_pk; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY backoffice_dsview_status_map
    ADD CONSTRAINT backoffice_dsview_status_map_pk PRIMARY KEY (backoffice_status_id);


--
-- TOC entry 2424 (class 2606 OID 24625)
-- Name: backoffice_epopatent_status_map_pk; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY backoffice_epopatent_status_map
    ADD CONSTRAINT backoffice_epopatent_status_map_pk PRIMARY KEY (backoffice_status_id);


--
-- TOC entry 2426 (class 2606 OID 24627)
-- Name: backoffice_status_map_pkey; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY backoffice_status_map
    ADD CONSTRAINT backoffice_status_map_pkey PRIMARY KEY (backoffice_status_id);


--
-- TOC entry 2428 (class 2606 OID 24629)
-- Name: backoffice_tmview_status_map_pk; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY backoffice_tmview_status_map
    ADD CONSTRAINT backoffice_tmview_status_map_pk PRIMARY KEY (backoffice_status_id);


--
-- TOC entry 2444 (class 2606 OID 24631)
-- Name: cip_pk; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY patent_ipc_class
    ADD CONSTRAINT cip_pk PRIMARY KEY (edition_code, section_code, class_code, subclass_code, group_code, subgroup_code);


--
-- TOC entry 2430 (class 2606 OID 24633)
-- Name: country_pkey; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY country
    ADD CONSTRAINT country_pkey PRIMARY KEY (code);


--
-- TOC entry 2432 (class 2606 OID 24635)
-- Name: drawing_view_type_pk; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY drawing_view_type
    ADD CONSTRAINT drawing_view_type_pk PRIMARY KEY (id);


--
-- TOC entry 2463 (class 2606 OID 29041)
-- Name: dsview_publication_pkey; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY dsview_publication
    ADD CONSTRAINT dsview_publication_pkey PRIMARY KEY (publication_section, publication_code_dsview);


--
-- TOC entry 2485 (class 2606 OID 41989)
-- Name: legal_decision_ground_type_pkey; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY legal_decision_ground_type
    ADD CONSTRAINT legal_decision_ground_type_pkey PRIMARY KEY (id);


--
-- TOC entry 2483 (class 2606 OID 41980)
-- Name: legal_decision_type_pkey; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY legal_decision_type
    ADD CONSTRAINT legal_decision_type_pkey PRIMARY KEY (id);


--
-- TOC entry 2434 (class 2606 OID 24637)
-- Name: mark_kind_pk; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY mark_kind
    ADD CONSTRAINT mark_kind_pk PRIMARY KEY (backoffice_code);


--
-- TOC entry 2436 (class 2606 OID 32570)
-- Name: object_subtype_backoffice_application_type_backoffice_appli_key; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY object_subtype
    ADD CONSTRAINT object_subtype_backoffice_application_type_backoffice_appli_key UNIQUE (backoffice_application_type, backoffice_application_subtype);


--
-- TOC entry 2438 (class 2606 OID 32556)
-- Name: object_subtype_pkey; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY object_subtype
    ADD CONSTRAINT object_subtype_pkey PRIMARY KEY (id);


--
-- TOC entry 2442 (class 2606 OID 24643)
-- Name: object_type_person_role_pk; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY object_type_person_role
    ADD CONSTRAINT object_type_person_role_pk PRIMARY KEY (object_type, person_role);


--
-- TOC entry 2440 (class 2606 OID 24645)
-- Name: object_type_pkey; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY object_type
    ADD CONSTRAINT object_type_pkey PRIMARY KEY (backoffice_code);


--
-- TOC entry 2479 (class 2606 OID 41956)
-- Name: object_type_to_status_pk; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY object_type_to_status
    ADD CONSTRAINT object_type_to_status_pk PRIMARY KEY (object_type, status_id);


--
-- TOC entry 2487 (class 2606 OID 42087)
-- Name: object_type_to_subtype_pk; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY object_type_to_subtype
    ADD CONSTRAINT object_type_to_subtype_pk PRIMARY KEY (object_type, object_subtype);


--
-- TOC entry 2446 (class 2606 OID 24647)
-- Name: person_role_pkey; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY person_role
    ADD CONSTRAINT person_role_pkey PRIMARY KEY (code);


--
-- TOC entry 2489 (class 2606 OID 141780)
-- Name: plant_taxon_nomenclature_pkey; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY plant_taxon
    ADD CONSTRAINT plant_taxon_nomenclature_pkey PRIMARY KEY (id);


--
-- TOC entry 2448 (class 2606 OID 24649)
-- Name: priority_status_pk; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY priority_status
    ADD CONSTRAINT priority_status_pk PRIMARY KEY (backoffice_code);


--
-- TOC entry 2450 (class 2606 OID 32591)
-- Name: priority_type_pkey; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY priority_type
    ADD CONSTRAINT priority_type_pkey PRIMARY KEY (backoffice_code);


--
-- TOC entry 2452 (class 2606 OID 24657)
-- Name: publisect_pkey; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY publication_section
    ADD CONSTRAINT publisect_pkey PRIMARY KEY (idsection);


--
-- TOC entry 2454 (class 2606 OID 24659)
-- Name: relationship_type_pkey; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY relationship_type
    ADD CONSTRAINT relationship_type_pkey PRIMARY KEY (relationship_typ, application_typ);


--
-- TOC entry 2467 (class 2606 OID 29101)
-- Name: replicator_type_pkey; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY replicator_type
    ADD CONSTRAINT replicator_type_pkey PRIMARY KEY (replicator_type);


--
-- TOC entry 2456 (class 2606 OID 24661)
-- Name: representative_type_pk; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY representative_type
    ADD CONSTRAINT representative_type_pk PRIMARY KEY (representative_type);


--
-- TOC entry 2465 (class 2606 OID 29046)
-- Name: tmview_publication_pkey; Type: CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY tmview_publication
    ADD CONSTRAINT tmview_publication_pkey PRIMARY KEY (publication_section, publication_code_tmview);


SET search_path = ip_object_recordals, pg_catalog;

--
-- TOC entry 2351 (class 1259 OID 24662)
-- Name: tmp_recordal_extra_data_recordal_type_id_ind; Type: INDEX; Schema: ip_object_recordals; Owner: postgres
--

CREATE INDEX tmp_recordal_extra_data_recordal_type_id_ind ON tmp_recordal_extra_data USING btree (recordal_type_id);


--
-- TOC entry 2350 (class 1259 OID 24663)
-- Name: tmp_recordals_data_recordal_type_id_ind; Type: INDEX; Schema: ip_object_recordals; Owner: postgres
--

CREATE INDEX tmp_recordals_data_recordal_type_id_ind ON tmp_recordal_data USING btree (recordal_type_id);


SET search_path = ip_objects, pg_catalog;

--
-- TOC entry 2358 (class 1259 OID 28841)
-- Name: fki_att_atc_fk; Type: INDEX; Schema: ip_objects; Owner: postgres
--

CREATE INDEX fki_att_atc_fk ON ip_attachment USING btree (attachment_type_code);


--
-- TOC entry 2410 (class 1259 OID 32562)
-- Name: fki_dte_fk; Type: INDEX; Schema: ip_objects; Owner: postgres
--

CREATE INDEX fki_dte_fk ON ip_single_design USING btree (design_type);


--
-- TOC entry 2365 (class 1259 OID 24664)
-- Name: fki_ffu_rte_ffu_idx; Type: INDEX; Schema: ip_objects; Owner: postgres
--

CREATE INDEX fki_ffu_rte_ffu_idx ON ip_ftp_file_upload_to_replication_time USING btree (ffu_id);


--
-- TOC entry 2366 (class 1259 OID 24665)
-- Name: fki_ffu_rte_rte_idx; Type: INDEX; Schema: ip_objects; Owner: postgres
--

CREATE INDEX fki_ffu_rte_rte_idx ON ip_ftp_file_upload_to_replication_time USING btree (rte_id);


--
-- TOC entry 2457 (class 1259 OID 266728)
-- Name: fki_mat_att_fk; Type: INDEX; Schema: ip_objects; Owner: postgres
--

CREATE INDEX fki_mat_att_fk ON ip_mark_attachment USING btree (attachment_id);


--
-- TOC entry 2373 (class 1259 OID 32568)
-- Name: fki_obt_ste_fk; Type: INDEX; Schema: ip_objects; Owner: postgres
--

CREATE INDEX fki_obt_ste_fk ON ip_object USING btree (object_subtype);


--
-- TOC entry 2402 (class 1259 OID 141786)
-- Name: fki_plt_ptn_fk; Type: INDEX; Schema: ip_objects; Owner: postgres
--

CREATE INDEX fki_plt_ptn_fk ON ip_plant USING btree (taxon_id);


--
-- TOC entry 2399 (class 1259 OID 29493)
-- Name: fki_pto_rte_fk; Type: INDEX; Schema: ip_objects; Owner: postgres
--

CREATE INDEX fki_pto_rte_fk ON ip_person_to_ip_object USING btree (representative_type);


--
-- TOC entry 2407 (class 1259 OID 29107)
-- Name: fki_rte_rte_fk; Type: INDEX; Schema: ip_objects; Owner: postgres
--

CREATE INDEX fki_rte_rte_fk ON ip_replication_times USING btree (replicator_type);


--
-- TOC entry 2363 (class 1259 OID 24666)
-- Name: ip_ftp_file_upload_status_idx; Type: INDEX; Schema: ip_objects; Owner: postgres
--

CREATE INDEX ip_ftp_file_upload_status_idx ON ip_ftp_file_upload USING btree (status);


--
-- TOC entry 2364 (class 1259 OID 24667)
-- Name: ip_ftp_file_upload_type_idx; Type: INDEX; Schema: ip_objects; Owner: postgres
--

CREATE INDEX ip_ftp_file_upload_type_idx ON ip_ftp_file_upload USING btree (replicator_type);


--
-- TOC entry 2378 (class 1259 OID 41404)
-- Name: ip_object_custom_replication_replicator_type_idx; Type: INDEX; Schema: ip_objects; Owner: postgres
--

CREATE INDEX ip_object_custom_replication_replicator_type_idx ON ip_object_custom_replication USING btree (replicator_type);


--
-- TOC entry 2570 (class 2620 OID 29284)
-- Name: delete_ip_attachment_trigger; Type: TRIGGER; Schema: ip_objects; Owner: postgres
--

CREATE TRIGGER delete_ip_attachment_trigger AFTER DELETE OR UPDATE ON ip_attachment FOR EACH ROW EXECUTE PROCEDURE delete_ip_attachment();


SET search_path = ip_object_recordals, pg_catalog;

--
-- TOC entry 2496 (class 2606 OID 24668)
-- Name: detail_type_fk; Type: FK CONSTRAINT; Schema: ip_object_recordals; Owner: postgres
--

ALTER TABLE ONLY details
    ADD CONSTRAINT detail_type_fk FOREIGN KEY (detail_type_id) REFERENCES detail_types(id);


--
-- TOC entry 2498 (class 2606 OID 24673)
-- Name: recordal_id_fk; Type: FK CONSTRAINT; Schema: ip_object_recordals; Owner: postgres
--

ALTER TABLE ONLY recordals
    ADD CONSTRAINT recordal_id_fk FOREIGN KEY (recordal_id) REFERENCES recordals(id);


--
-- TOC entry 2497 (class 2606 OID 24678)
-- Name: recordal_id_fk; Type: FK CONSTRAINT; Schema: ip_object_recordals; Owner: postgres
--

ALTER TABLE ONLY details
    ADD CONSTRAINT recordal_id_fk FOREIGN KEY (recordal_id) REFERENCES recordals(id);


--
-- TOC entry 2499 (class 2606 OID 24683)
-- Name: recordal_type_fk; Type: FK CONSTRAINT; Schema: ip_object_recordals; Owner: postgres
--

ALTER TABLE ONLY recordals
    ADD CONSTRAINT recordal_type_fk FOREIGN KEY (recordal_type_id) REFERENCES recordal_types(id);


SET search_path = ip_objects, pg_catalog;

--
-- TOC entry 2501 (class 2606 OID 30994)
-- Name: agent_speciality_fkey; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_agent
    ADD CONSTRAINT agent_speciality_fkey FOREIGN KEY (agent_speciality) REFERENCES nomenclatures.agent_speciality(code);


--
-- TOC entry 2503 (class 2606 OID 28836)
-- Name: att_atc_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_attachment
    ADD CONSTRAINT att_atc_fk FOREIGN KEY (attachment_type_code) REFERENCES nomenclatures.attachment_type(code);


--
-- TOC entry 2528 (class 2606 OID 24698)
-- Name: country_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_person
    ADD CONSTRAINT country_fk FOREIGN KEY (nationality_country_code) REFERENCES nomenclatures.country(code);


--
-- TOC entry 2529 (class 2606 OID 24703)
-- Name: country_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_person_addresses
    ADD CONSTRAINT country_fk FOREIGN KEY (residence_country_code) REFERENCES nomenclatures.country(code);


--
-- TOC entry 2525 (class 2606 OID 24708)
-- Name: country_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_patent_pct
    ADD CONSTRAINT country_fk FOREIGN KEY (pct_publication_country_code) REFERENCES nomenclatures.country(code);


--
-- TOC entry 2540 (class 2606 OID 32557)
-- Name: dte_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_single_design
    ADD CONSTRAINT dte_fk FOREIGN KEY (design_type) REFERENCES nomenclatures.object_subtype(id);


--
-- TOC entry 2506 (class 2606 OID 24713)
-- Name: ffu_rte_ffu_idx; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_ftp_file_upload_to_replication_time
    ADD CONSTRAINT ffu_rte_ffu_idx FOREIGN KEY (ffu_id) REFERENCES ip_ftp_file_upload(id);


--
-- TOC entry 2505 (class 2606 OID 29113)
-- Name: ffu_rte_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_ftp_file_upload
    ADD CONSTRAINT ffu_rte_fk FOREIGN KEY (replicator_type) REFERENCES nomenclatures.replicator_type(replicator_type);


--
-- TOC entry 2507 (class 2606 OID 24718)
-- Name: ffu_rte_rte_idx; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_ftp_file_upload_to_replication_time
    ADD CONSTRAINT ffu_rte_rte_idx FOREIGN KEY (rte_id) REFERENCES ip_replication_times(id);


--
-- TOC entry 2559 (class 2606 OID 31025)
-- Name: ip_agent_addresses_person_id_fkey; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_agent_addresses
    ADD CONSTRAINT ip_agent_addresses_person_id_fkey FOREIGN KEY (person_id) REFERENCES ip_agent(person_id);


--
-- TOC entry 2569 (class 2606 OID 141884)
-- Name: ip_agent_history_history_type_fkey; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_agent_history
    ADD CONSTRAINT ip_agent_history_history_type_fkey FOREIGN KEY (history_type) REFERENCES nomenclatures.agent_history_type(code);


--
-- TOC entry 2568 (class 2606 OID 141879)
-- Name: ip_agent_history_person_id_fkey; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_agent_history
    ADD CONSTRAINT ip_agent_history_person_id_fkey FOREIGN KEY (person_id) REFERENCES ip_agent(person_id);


--
-- TOC entry 2567 (class 2606 OID 141855)
-- Name: ip_agent_partnership_idagent_fkey; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_agent_partnership
    ADD CONSTRAINT ip_agent_partnership_idagent_fkey FOREIGN KEY (idagent) REFERENCES ip_agent(person_id);


--
-- TOC entry 2566 (class 2606 OID 141850)
-- Name: ip_agent_partnership_idpartnership_fkey; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_agent_partnership
    ADD CONSTRAINT ip_agent_partnership_idpartnership_fkey FOREIGN KEY (idpartnership) REFERENCES ip_agent(person_id);


--
-- TOC entry 2500 (class 2606 OID 29483)
-- Name: ip_agent_representative_type_fkey; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_agent
    ADD CONSTRAINT ip_agent_representative_type_fkey FOREIGN KEY (representative_type) REFERENCES nomenclatures.representative_type(representative_type);


--
-- TOC entry 2554 (class 2606 OID 28976)
-- Name: ip_mark_attachment_mark_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_mark_attachment
    ADD CONSTRAINT ip_mark_attachment_mark_fk FOREIGN KEY (mark_id) REFERENCES ip_mark(mark_id);


--
-- TOC entry 2508 (class 2606 OID 24723)
-- Name: ip_mark_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_mark
    ADD CONSTRAINT ip_mark_fk FOREIGN KEY (mark_kind) REFERENCES nomenclatures.mark_kind(backoffice_code);


--
-- TOC entry 2510 (class 2606 OID 24733)
-- Name: ip_mark_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_mark_nice_class
    ADD CONSTRAINT ip_mark_fk FOREIGN KEY (mark_id) REFERENCES ip_mark(mark_id);


--
-- TOC entry 2522 (class 2606 OID 24738)
-- Name: ip_object_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_patent
    ADD CONSTRAINT ip_object_fk FOREIGN KEY (patent_id) REFERENCES ip_object(id);


--
-- TOC entry 2530 (class 2606 OID 24743)
-- Name: ip_object_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_person_to_ip_object
    ADD CONSTRAINT ip_object_fk FOREIGN KEY (object_id) REFERENCES ip_object(id);


--
-- TOC entry 2518 (class 2606 OID 24748)
-- Name: ip_object_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_object_publication
    ADD CONSTRAINT ip_object_fk FOREIGN KEY (object_id) REFERENCES ip_object(id);


--
-- TOC entry 2534 (class 2606 OID 24753)
-- Name: ip_object_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_plant
    ADD CONSTRAINT ip_object_fk FOREIGN KEY (plant_id) REFERENCES ip_object(id);


--
-- TOC entry 2504 (class 2606 OID 24758)
-- Name: ip_object_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_design
    ADD CONSTRAINT ip_object_fk FOREIGN KEY (design_id) REFERENCES ip_object(id);


--
-- TOC entry 2515 (class 2606 OID 24763)
-- Name: ip_object_priority_country_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_object_priority
    ADD CONSTRAINT ip_object_priority_country_fk FOREIGN KEY (country) REFERENCES nomenclatures.country(code);


--
-- TOC entry 2516 (class 2606 OID 24768)
-- Name: ip_object_priority_ip_object_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_object_priority
    ADD CONSTRAINT ip_object_priority_ip_object_fk FOREIGN KEY (object_id) REFERENCES ip_object(id);


--
-- TOC entry 2517 (class 2606 OID 32599)
-- Name: ip_object_priority_priority_type_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_object_priority
    ADD CONSTRAINT ip_object_priority_priority_type_fk FOREIGN KEY (priority_type) REFERENCES nomenclatures.priority_type(backoffice_code);


--
-- TOC entry 2560 (class 2606 OID 32803)
-- Name: ip_patent_attachment_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_patent_attachment
    ADD CONSTRAINT ip_patent_attachment_fk FOREIGN KEY (attachment_id) REFERENCES ip_attachment(id);


--
-- TOC entry 2523 (class 2606 OID 24778)
-- Name: ip_patent_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_patent_ipc_classes
    ADD CONSTRAINT ip_patent_fk FOREIGN KEY (patent_id) REFERENCES ip_patent(patent_id);


--
-- TOC entry 2526 (class 2606 OID 24788)
-- Name: ip_patent_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_patent_pct
    ADD CONSTRAINT ip_patent_fk FOREIGN KEY (patent_id) REFERENCES ip_patent(patent_id);


--
-- TOC entry 2527 (class 2606 OID 24793)
-- Name: ip_patent_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_patent_summary
    ADD CONSTRAINT ip_patent_fk FOREIGN KEY (patent_id) REFERENCES ip_patent(patent_id);


--
-- TOC entry 2561 (class 2606 OID 33176)
-- Name: ip_patent_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_patent_attachment
    ADD CONSTRAINT ip_patent_fk FOREIGN KEY (patent_id) REFERENCES ip_patent(patent_id);


--
-- TOC entry 2544 (class 2606 OID 28991)
-- Name: ip_single_design_drawing_ip_attachment_id_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_single_design_drawing
    ADD CONSTRAINT ip_single_design_drawing_ip_attachment_id_fk FOREIGN KEY (thumbnail_id) REFERENCES ip_attachment(id);


--
-- TOC entry 2545 (class 2606 OID 28996)
-- Name: ip_single_design_drawing_ip_attachment_id_fk_2; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_single_design_drawing
    ADD CONSTRAINT ip_single_design_drawing_ip_attachment_id_fk_2 FOREIGN KEY (dsview_picture_id) REFERENCES ip_attachment(id);


--
-- TOC entry 2524 (class 2606 OID 24808)
-- Name: ipc_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_patent_ipc_classes
    ADD CONSTRAINT ipc_fk FOREIGN KEY (edition_code, section_code, class_code, subclass_code, group_code, subgroup_code) REFERENCES nomenclatures.patent_ipc_class(edition_code, section_code, class_code, subclass_code, group_code, subgroup_code);


--
-- TOC entry 2538 (class 2606 OID 24813)
-- Name: main_design_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_single_design
    ADD CONSTRAINT main_design_fk FOREIGN KEY (main_design_id) REFERENCES ip_design(design_id);


--
-- TOC entry 2509 (class 2606 OID 24818)
-- Name: mark_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_mark
    ADD CONSTRAINT mark_fk FOREIGN KEY (mark_id) REFERENCES ip_object(id);


--
-- TOC entry 2555 (class 2606 OID 266723)
-- Name: mat_att_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_mark_attachment
    ADD CONSTRAINT mat_att_fk FOREIGN KEY (attachment_id) REFERENCES ip_attachment(id);


--
-- TOC entry 2556 (class 2606 OID 266729)
-- Name: mat_thumbnail_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_mark_attachment
    ADD CONSTRAINT mat_thumbnail_fk FOREIGN KEY (thumbnail_id) REFERENCES ip_attachment(id);


--
-- TOC entry 2557 (class 2606 OID 266734)
-- Name: mat_tmview_picture_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_mark_attachment
    ADD CONSTRAINT mat_tmview_picture_fk FOREIGN KEY (tmview_picture_id) REFERENCES ip_attachment(id);


--
-- TOC entry 2558 (class 2606 OID 28986)
-- Name: mavc_mat_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_mark_attachment_vienna_class
    ADD CONSTRAINT mavc_mat_fk FOREIGN KEY (mark_id, att_seq_nbr) REFERENCES ip_mark_attachment(mark_id, att_seq_nbr);


--
-- TOC entry 2520 (class 2606 OID 24823)
-- Name: object_id1_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_object_relationship
    ADD CONSTRAINT object_id1_fk FOREIGN KEY (object_id1) REFERENCES ip_object(id);


--
-- TOC entry 2511 (class 2606 OID 24833)
-- Name: object_type_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_object
    ADD CONSTRAINT object_type_fk FOREIGN KEY (object_type) REFERENCES nomenclatures.object_type(backoffice_code);


--
-- TOC entry 2513 (class 2606 OID 32563)
-- Name: obt_ste_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_object
    ADD CONSTRAINT obt_ste_fk FOREIGN KEY (object_subtype) REFERENCES nomenclatures.object_subtype(id);


--
-- TOC entry 2514 (class 2606 OID 29108)
-- Name: ocr_rte_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_object_custom_replication
    ADD CONSTRAINT ocr_rte_fk FOREIGN KEY (replicator_type) REFERENCES nomenclatures.replicator_type(replicator_type);


--
-- TOC entry 2519 (class 2606 OID 41917)
-- Name: opn_pbn_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_object_publication
    ADD CONSTRAINT opn_pbn_fk FOREIGN KEY (publication_section) REFERENCES nomenclatures.publication_section(idsection);


--
-- TOC entry 2531 (class 2606 OID 24838)
-- Name: person_address_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_person_to_ip_object
    ADD CONSTRAINT person_address_fk FOREIGN KEY (person_id) REFERENCES ip_person(person_id);


--
-- TOC entry 2532 (class 2606 OID 24843)
-- Name: person_role_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_person_to_ip_object
    ADD CONSTRAINT person_role_fk FOREIGN KEY (person_role) REFERENCES nomenclatures.person_role(code);


--
-- TOC entry 2535 (class 2606 OID 141781)
-- Name: plt_ptn_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_plant
    ADD CONSTRAINT plt_ptn_fk FOREIGN KEY (taxon_id) REFERENCES nomenclatures.plant_taxon(id);


--
-- TOC entry 2533 (class 2606 OID 29488)
-- Name: pto_rte_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_person_to_ip_object
    ADD CONSTRAINT pto_rte_fk FOREIGN KEY (representative_type) REFERENCES nomenclatures.representative_type(representative_type);


--
-- TOC entry 2502 (class 2606 OID 31002)
-- Name: qualif_country_code_fkey; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_agent
    ADD CONSTRAINT qualif_country_code_fkey FOREIGN KEY (qualif_country_code) REFERENCES nomenclatures.country(code);


--
-- TOC entry 2521 (class 2606 OID 24853)
-- Name: relationship_type_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_object_relationship
    ADD CONSTRAINT relationship_type_fk FOREIGN KEY (relationship_typ, application_typ) REFERENCES nomenclatures.relationship_type(relationship_typ, application_typ);


--
-- TOC entry 2547 (class 2606 OID 24858)
-- Name: replication_details_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_single_design_replication_details
    ADD CONSTRAINT replication_details_fk FOREIGN KEY (main_design_replication_detail_id) REFERENCES ip_replication_details(id);


--
-- TOC entry 2536 (class 2606 OID 24863)
-- Name: replication_details_replication_time_id_fkey; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_replication_details
    ADD CONSTRAINT replication_details_replication_time_id_fkey FOREIGN KEY (replication_time_id) REFERENCES ip_replication_times(id);


--
-- TOC entry 2537 (class 2606 OID 29102)
-- Name: rte_rte_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_replication_times
    ADD CONSTRAINT rte_rte_fk FOREIGN KEY (replicator_type) REFERENCES nomenclatures.replicator_type(replicator_type);


--
-- TOC entry 2541 (class 2606 OID 24878)
-- Name: single_design_drawing_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_single_design_drawing
    ADD CONSTRAINT single_design_drawing_fk FOREIGN KEY (attachment_id) REFERENCES ip_attachment(id);


--
-- TOC entry 2542 (class 2606 OID 24883)
-- Name: single_design_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_single_design_drawing
    ADD CONSTRAINT single_design_fk FOREIGN KEY (single_design_id) REFERENCES ip_single_design(single_design_id) ON DELETE CASCADE;


--
-- TOC entry 2546 (class 2606 OID 24888)
-- Name: single_design_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_single_design_locarno
    ADD CONSTRAINT single_design_fk FOREIGN KEY (single_design_id) REFERENCES ip_single_design(single_design_id) ON DELETE CASCADE;


--
-- TOC entry 2539 (class 2606 OID 24893)
-- Name: single_design_status_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_single_design
    ADD CONSTRAINT single_design_status_fk FOREIGN KEY (status) REFERENCES nomenclatures.backoffice_status_map(backoffice_status_id);


--
-- TOC entry 2512 (class 2606 OID 24898)
-- Name: status_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_object
    ADD CONSTRAINT status_fk FOREIGN KEY (status) REFERENCES nomenclatures.backoffice_status_map(backoffice_status_id);


--
-- TOC entry 2543 (class 2606 OID 24903)
-- Name: view_type_fk; Type: FK CONSTRAINT; Schema: ip_objects; Owner: postgres
--

ALTER TABLE ONLY ip_single_design_drawing
    ADD CONSTRAINT view_type_fk FOREIGN KEY (view_type_id) REFERENCES nomenclatures.drawing_view_type(id);


SET search_path = nomenclatures, pg_catalog;

--
-- TOC entry 2549 (class 2606 OID 24908)
-- Name: backoffice_epopatent_status_map_fk; Type: FK CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY backoffice_epopatent_status_map
    ADD CONSTRAINT backoffice_epopatent_status_map_fk FOREIGN KEY (backoffice_status_id) REFERENCES backoffice_status_map(backoffice_status_id);


--
-- TOC entry 2550 (class 2606 OID 24913)
-- Name: backoffice_status_id_fk; Type: FK CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY backoffice_tmview_status_map
    ADD CONSTRAINT backoffice_status_id_fk FOREIGN KEY (backoffice_status_id) REFERENCES backoffice_status_map(backoffice_status_id);


--
-- TOC entry 2548 (class 2606 OID 24918)
-- Name: backoffice_status_id_fk; Type: FK CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY backoffice_dsview_status_map
    ADD CONSTRAINT backoffice_status_id_fk FOREIGN KEY (backoffice_status_id) REFERENCES backoffice_status_map(backoffice_status_id);


--
-- TOC entry 2565 (class 2606 OID 42093)
-- Name: object_subtype_fk; Type: FK CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY object_type_to_subtype
    ADD CONSTRAINT object_subtype_fk FOREIGN KEY (object_subtype) REFERENCES object_subtype(id);


--
-- TOC entry 2563 (class 2606 OID 42075)
-- Name: object_type_fk; Type: FK CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY object_type_to_status
    ADD CONSTRAINT object_type_fk FOREIGN KEY (object_type) REFERENCES object_type(backoffice_code);


--
-- TOC entry 2564 (class 2606 OID 42088)
-- Name: object_type_fk; Type: FK CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY object_type_to_subtype
    ADD CONSTRAINT object_type_fk FOREIGN KEY (object_type) REFERENCES object_type(backoffice_code);


--
-- TOC entry 2551 (class 2606 OID 24928)
-- Name: object_type_person_role_object_type_id_fk; Type: FK CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY object_type_person_role
    ADD CONSTRAINT object_type_person_role_object_type_id_fk FOREIGN KEY (object_type) REFERENCES object_type(backoffice_code);


--
-- TOC entry 2552 (class 2606 OID 24933)
-- Name: object_type_person_role_person_role_id_fk; Type: FK CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY object_type_person_role
    ADD CONSTRAINT object_type_person_role_person_role_id_fk FOREIGN KEY (person_role) REFERENCES person_role(code);


--
-- TOC entry 2553 (class 2606 OID 24938)
-- Name: priority_status_fk; Type: FK CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY priority_status
    ADD CONSTRAINT priority_status_fk FOREIGN KEY (object_type) REFERENCES object_type(backoffice_code);


--
-- TOC entry 2562 (class 2606 OID 42070)
-- Name: status_id_fk; Type: FK CONSTRAINT; Schema: nomenclatures; Owner: postgres
--

ALTER TABLE ONLY object_type_to_status
    ADD CONSTRAINT status_id_fk FOREIGN KEY (status_id) REFERENCES backoffice_status_map(backoffice_status_id);