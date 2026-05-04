--liquibase formatted sql

--changeset ggeorgiev:0099 splitStatements:false
alter table emails.email_template add column notification_action varchar(255);
UPDATE emails.email_template set notification_action = 'BaseAction' where id = 'BASE';
UPDATE emails.email_template set notification_action = 'FeedbackAction' where id = 'FEEDBACK';
UPDATE emails.email_template set notification_action = 'ErrorMessageAction' where id = 'ERROR_MESSAGE';
alter table emails.email_template
    add constraint email_template_notification_action_fk
    foreign key (notification_action) references emails.email_notification_action;