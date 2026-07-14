--liquibase formatted sql

--changeset murlev:0113 splitStatements:false
update eservices.execution_type_to_study_sub_type set code_execution_type = 'FAST_FIVE_DAYS' where code_study_sub_type = 'SINGLE_DESIGN' and code_execution_type = 'FAST_NINE_DAYS';
update eservices.execution_type_to_study_sub_type set code_execution_type = 'FAST_FIVE_DAYS' where code_study_sub_type = 'SEVERAL_DESIGNS' and code_execution_type = 'FAST_NINE_DAYS';
update eservices.execution_type_to_study_sub_type set code_execution_type = 'FAST_FIVE_DAYS' where code_study_sub_type = 'SET' and code_execution_type = 'FAST_NINE_DAYS';
update eservices.execution_type_to_study_sub_type set code_execution_type = 'FAST_FIVE_DAYS' where code_study_sub_type = 'COMPOSITION' and code_execution_type = 'FAST_NINE_DAYS';