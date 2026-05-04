--liquibase formatted sql

--changeset ndimov:0094 splitStatements:false
INSERT INTO emails.email_notification_action (code, name, name_en, notify_emails) VALUES ('FeedbackAction', 'FeedbackAction', 'FeedbackAction', null);
INSERT INTO emails.email_notification_action (code, name, name_en, notify_emails) VALUES ('ErrorMessageAction', 'ErrorMessageAction', 'ErrorMessageAction', null);
