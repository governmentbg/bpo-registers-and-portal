--liquibase formatted sql

--changeset ggeorgiev:0064
CREATE TABLE nomenclatures.locarno_class
(
    id varchar(2) NOT NULL,
    description text,
    CONSTRAINT locarno_class_pkey PRIMARY KEY (id)
);
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('01','Хранителни продукти');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('02','Облекло, обувки и галантерия');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('03','Принадлежности за пътуване, куфари, чанти, чадъри и лични принадлежности, невключени в други класове');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('04','Четки');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('05','Тъкани, прежди, листове от изкуствени и естествени материали');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('06','Мебели, възглавници, пердета, щори, килими, постелъчно бельо, покривки, завивки');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('07','Домакински съдове и уреди, невключени в други класове');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('08','Сечива и железарски инструменти, крепежни елементи, дребна железария');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('09','Опаковки и контейнери за транспортиране и съхранение на стоки и обработка на товари');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('10','Часовници и измервателни, контролни и сигнализиращи уреди');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('11','Изделия за украса');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('12','Транспортни средства и подемни устройства');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('13','Оборудване за производство, разпределение и преобразуване на електрическа енергия');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('14','Устройства за записване, предаване и обработка на информация');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('15','Машини, невключени в други класове');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('16','Фотографски, кинематографски и оптически апарати');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('17','Музикални инструменти');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('18','Печатарски и канцеларски машини');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('19','Хартиени изделия и канцеларски материали, учебни и нагледни пособия и принадлежности за художници');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('20','Търговско и рекламно оборудване, указателни знаци');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('21','Игри, играчки, палатки и спортни принадлежности');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('22','Оръжия, пиротехнически изделия, принадлежности за лов, риболов и унищожаване на вредители');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('23','Устройства за разпределяне на флуиди, санитарно, отоплително, вентилационно и кондициониращо оборудване, твърди горива');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('24','Медицинско и лабораторно оборудване');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('25','Строителни материали, елементи и сгради /конструкции/');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('26','Осветителни тела');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('27','Тютюни /тютюневи изделия/ и принадлежности за пушачи');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('28','Фармацевтични и козметични продукти, тоалетни принадлежности и уреди');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('29','Съоръжения и оборудване срещу пожари, за предпазване от бедствия и нещастни случаи и за спасяване');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('30','Средства за отглеждане и грижа за животни');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('31','Машини и апарати за приготвяне на храна и напитки, невключени в други класове');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('32','Графични символи и лога, мотиви за декориране на повърхности, украса');
INSERT INTO nomenclatures.locarno_class(id, description) VALUES ('99','Разни');

CREATE TABLE nomenclatures.locarno_subclass
(
    id varchar(5) NOT NULL,
    class_id varchar(2) NOT NULL,
    subclass_id varchar(2) not null,
    description text,
    CONSTRAINT locarno_subclass_pkey PRIMARY KEY (id),
    constraint locarno_class_fk foreign key (class_id)
        references nomenclatures.locarno_class

);