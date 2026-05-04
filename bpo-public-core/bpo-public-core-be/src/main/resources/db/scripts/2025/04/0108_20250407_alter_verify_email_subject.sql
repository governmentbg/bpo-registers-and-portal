--liquibase formatted sql

--changeset raneva:0108 splitStatements:false
UPDATE emails.email_template SET subject='Патентно ведомство: потвърждаване на профил/BPO: account verification' WHERE id='EMAIL_VERIFICATION';