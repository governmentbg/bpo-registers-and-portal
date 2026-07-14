--liquibase formatted sql

--changeset ggeorgiev:0026 splitStatements:false
create table nomenclatures.attachment_category
(
    code            varchar(5) not null primary key,
    name            varchar(100),
    name_en         varchar(100)
);
alter table nomenclatures.attachment_type add column category_code varchar(5);
alter table nomenclatures.attachment_type
    add constraint att_acc_fk
        foreign key (category_code) references nomenclatures.attachment_category;

insert into nomenclatures.attachment_category(code, name, name_en) VALUES ('DC', 'Описания и претенции', 'Descriptions and claims');
insert into nomenclatures.attachment_category(code, name, name_en) VALUES ('DRAW', 'Изображения', 'Drawings');
insert into nomenclatures.attachment_category(code, name, name_en) VALUES ('THUMB', 'Thumbnails', 'Thumbnails');
insert into nomenclatures.attachment_category(code, name, name_en) VALUES ('TMVP', 'TmView Picture', 'TmView Picture');



update nomenclatures.attachment_type set category_code = 'THUMB' where code = 'THUMB';
update nomenclatures.attachment_type set category_code = 'TMVP' where code = 'TMVP';
update nomenclatures.attachment_type set category_code = 'DC' where code in ('A1','B1','B2','U1','U2','T1','T3','T4','T5','T6','B8','U8','T3C','T4C','T6C','UR1','UR2');
update nomenclatures.attachment_type set category_code = 'DRAW' where code in ('VIDEO','AUDIO','IMAGE');
alter table nomenclatures.attachment_type alter column category_code set not null;
