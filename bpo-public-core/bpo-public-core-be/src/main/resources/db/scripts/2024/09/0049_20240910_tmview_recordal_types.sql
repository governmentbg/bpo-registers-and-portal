--liquibase formatted sql

--changeset ggeorgiev:0049
alter table nomenclatures.recordal_type add column tmview_name varchar(255);

UPDATE nomenclatures.recordal_type set tmview_name = 'Non-exclusive licence registration' where id = 'NON_EXCLUSIVE_LICENCE_REGISTRATION';
UPDATE nomenclatures.recordal_type set tmview_name = 'Authorization / change of representative' where id = 'AUTH_OR_CHANGE_OF_REPRESENTATIVE';
UPDATE nomenclatures.recordal_type set tmview_name = 'Applicant/Owner address or/and name change' where id = 'APPLICANT_OR_OWNER_ADDRESS_AND_NAME_CHANGE';
UPDATE nomenclatures.recordal_type set tmview_name = 'Correction of an error made by the applicant in an application or a request' where id = 'APPLICANT_ERROR_CORRECTION_IN_APP_OR_REQUEST';
UPDATE nomenclatures.recordal_type set tmview_name = 'Request for annulment' where id = 'REFUSAL_OF_EXECUTION_REQUEST';
UPDATE nomenclatures.recordal_type set tmview_name = 'Request for deregistration from the bankruptcy estate' where id = 'BANKRUPTCY_ESTATE_DEREGISTRATION_REQUEST';
UPDATE nomenclatures.recordal_type set tmview_name = 'Special pledge changes in circumstances' where id = 'SPECIAL_PLEDGE_CHANGES';
UPDATE nomenclatures.recordal_type set tmview_name = 'Registration partial renewal' where id = 'PARTIAL_REGISTRATION_RENEWAL';
UPDATE nomenclatures.recordal_type set tmview_name = 'Request for enforcement' where id = 'REQUEST_FOR_ENFORCEMENT';
UPDATE nomenclatures.recordal_type set tmview_name = 'Protective measures registration' where id = 'PROTECTIVE_MEASURES_REGISTRATION';
UPDATE nomenclatures.recordal_type set tmview_name = 'Transfer' where id = 'TRANSFER';
UPDATE nomenclatures.recordal_type set tmview_name = 'Protective measures termination' where id = 'PROTECTIVE_MEASURES_TERMINATION';
UPDATE nomenclatures.recordal_type set tmview_name = 'Request for registration of other changes in entered circumstances' where id = 'ENTERED_CIRCUMS_CHANGES_REQUEST';
UPDATE nomenclatures.recordal_type set tmview_name = 'License termination' where id = 'LICENSE_TERMINATION';
UPDATE nomenclatures.recordal_type set tmview_name = 'Special pledge registration' where id = 'SPECIAL_PLEDGE_REGISTRATION';
UPDATE nomenclatures.recordal_type set tmview_name = 'Disclaimer' where id = 'DISCLAIMER';
UPDATE nomenclatures.recordal_type set tmview_name = 'Correction of an error made by the patent office' where id = 'PATENT_OFFICE_ERROR_CORRECTION';
UPDATE nomenclatures.recordal_type set tmview_name = 'Correction of an error' where id = 'ERROR_CORRECTION';
UPDATE nomenclatures.recordal_type set tmview_name = 'Extension of validity' where id = 'VALIDITY_EXTENSION';
UPDATE nomenclatures.recordal_type set tmview_name = 'Objection' where id = 'OBJECTION';
UPDATE nomenclatures.recordal_type set tmview_name = 'Request for invalidity' where id = 'INVALIDITY_REQUEST';
UPDATE nomenclatures.recordal_type set tmview_name = 'Partial transfer' where id = 'PARTIAL_TRANSFER';
UPDATE nomenclatures.recordal_type set tmview_name = 'Exclusive licence registration' where id = 'EXCLUSIVE_LICENCE_REGISTRATION';
UPDATE nomenclatures.recordal_type set tmview_name = 'Special pledge renewal' where id = 'SPECIAL_PLEDGE_RENEWAL';
UPDATE nomenclatures.recordal_type set tmview_name = 'Correspondence address change' where id = 'CORRESPONDENCE_ADDRESS_CHANGE';
UPDATE nomenclatures.recordal_type set tmview_name = 'Entry in the bankruptcy estate' where id = 'BANKRUPTCY_ESTATE_ENTRY';
UPDATE nomenclatures.recordal_type set tmview_name = 'Special pledge termination' where id = 'SPECIAL_PLEDGE_TERMINATION';
UPDATE nomenclatures.recordal_type set tmview_name = 'Registration renewal' where id = 'REGISTRATION_RENEWAL';
UPDATE nomenclatures.recordal_type set tmview_name = 'Correction of an error made by the applicant in a register or a certificate' where id = 'APPLICANT_ERROR_CORRECTION_IN_REGISTER_OR_CERT';
UPDATE nomenclatures.recordal_type set tmview_name = 'Request for deletion' where id = 'REQUEST_FOR_DELETION';
UPDATE nomenclatures.recordal_type set tmview_name = 'Request for annulment' where id = 'ANNULMENT_REQUEST';