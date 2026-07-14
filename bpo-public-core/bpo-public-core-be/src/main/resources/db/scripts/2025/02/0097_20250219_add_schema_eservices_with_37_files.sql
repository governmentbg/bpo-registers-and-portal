--liquibase formatted sql

--changeset ndimov:0097 splitStatements:false

create schema eservices;

--  NOMENCLATURES_________________________________________________
-- 1
create table eservices.abdocs_codes
(
    code        varchar(30) not null
        primary key,
    abdocs_code integer     not null
);

INSERT INTO eservices.abdocs_codes (code, abdocs_code) VALUES ('PURITY_SUB_TYPE', 13);
INSERT INTO eservices.abdocs_codes (code, abdocs_code) VALUES ('NOVELTY_SUB_TYPE', 43);
INSERT INTO eservices.abdocs_codes (code, abdocs_code) VALUES ('DESIGN_STUDY_TYPE', 44);
INSERT INTO eservices.abdocs_codes (code, abdocs_code) VALUES ('INVENTIVE_STEP_SUB_TYPE', 42);
INSERT INTO eservices.abdocs_codes (code, abdocs_code) VALUES ('TECH_CONDITION_SUB_TYPE', 18);
INSERT INTO eservices.abdocs_codes (code, abdocs_code) VALUES ('UM_OBJECT_TYPE', 64);
INSERT INTO eservices.abdocs_codes (code, abdocs_code) VALUES ('MARK_STUDY_TYPE', 194);
INSERT INTO eservices.abdocs_codes (code, abdocs_code) VALUES ('PT_OBJECT_TYPE', 71);
INSERT INTO eservices.abdocs_codes (code, abdocs_code) VALUES ('SPC_OBJECT_TYPE', 59);
INSERT INTO eservices.abdocs_codes (code, abdocs_code) VALUES ('DS_OBJECT_TYPE', 61);
INSERT INTO eservices.abdocs_codes (code, abdocs_code) VALUES ('PV_OBJECT_TYPE', 65);
INSERT INTO eservices.abdocs_codes (code, abdocs_code) VALUES ('BY_THEME_SUB_TYPE', 15);
INSERT INTO eservices.abdocs_codes (code, abdocs_code) VALUES ('MARK_IDENTITY_SUB_TYPE', 62);

-- 2
create table eservices.application_submit_type
(
    code varchar(30) not null
        primary key,
    name varchar(255)
);

INSERT INTO eservices.application_submit_type (code, name) VALUES ('DRAFT', 'Чернова');
INSERT INTO eservices.application_submit_type (code, name) VALUES ('SAVED', 'Запазено');
INSERT INTO eservices.application_submit_type (code, name) VALUES ('REGISTER_APPLICATION', 'Регистрирано в деловодната ситема');
INSERT INTO eservices.application_submit_type (code, name) VALUES ('REGISTER_RECEIPT', 'Запазена разписка в деловодната ситема ');
INSERT INTO eservices.application_submit_type (code, name) VALUES ('REGISTER_ATTACHMENTS', 'Запазени приложения в деловодната система');
INSERT INTO eservices.application_submit_type (code, name) VALUES ('COMPLETED', 'Завършено');
INSERT INTO eservices.application_submit_type (code, name) VALUES ('INSERTED_FEES', 'Въведени такси');

-- 3
create table eservices.delivery_type
(
    code    varchar(15) not null
        primary key,
    name    varchar(255),
    name_en varchar(255)
);

INSERT INTO eservices.delivery_type (code, name, name_en) VALUES ('EDOC', 'на посочения адрес на е-поща', 'to the specified e-mail address');
INSERT INTO eservices.delivery_type (code, name, name_en) VALUES ('POST', 'чрез пощенски оператор', 'through a postal operator');
INSERT INTO eservices.delivery_type (code, name, name_en) VALUES ('PV', 'на ръка в ПВ', 'by hand');

-- 4
create table eservices.execution_type
(
    code    varchar(25) not null
        constraint execution_type_pk
            primary key,
    name    varchar(100),
    name_en varchar(50),
    enabled smallint default 1
);

INSERT INTO eservices.execution_type (code, name, name_en, enabled) VALUES ('NORMAL_THREE_MONTHS', 'обикновено (3 месеца)', 'normal (in 3 months)', 1);
INSERT INTO eservices.execution_type (code, name, name_en, enabled) VALUES ('NORMAL_TEN_DAYS', 'обикновено (10 раб. дни)', 'normal (in 10 working days)', 1);
INSERT INTO eservices.execution_type (code, name, name_en, enabled) VALUES ('FAST_NINE_DAYS', 'бързо (9 раб. дни)', 'fast (in 9 working days)', 1);
INSERT INTO eservices.execution_type (code, name, name_en, enabled) VALUES ('FAST_FIVE_DAYS', 'бързо (5 раб. дни)', 'fast (in 5 working days)', 1);
INSERT INTO eservices.execution_type (code, name, name_en, enabled) VALUES ('EXPRESS_FIVE_DAYS', 'експресно (5 дни)', 'express (in 5 days)', 0);
INSERT INTO eservices.execution_type (code, name, name_en, enabled) VALUES ('EXPRESS_TWO_DAYS', 'експресно (2 раб. дни)', 'express (in 2 working days)', 0);
INSERT INTO eservices.execution_type (code, name, name_en, enabled) VALUES ('SUBSCRIPTION', 'абонаментно обслужване', 'subscription service', 1);
INSERT INTO eservices.execution_type (code, name, name_en, enabled) VALUES ('STANDARD_TWENTY_DAYS', 'обикновено (20 раб. дни)', 'standard (in 20 working days)', 1);

-- 5
create table eservices.study_type
(
    code varchar(15) not null
        primary key,
    name varchar(255)
);

INSERT INTO eservices.study_type (code, name) VALUES ('DESIGN', 'Проучване на дизайн');
INSERT INTO eservices.study_type (code, name) VALUES ('PATENT', 'Проучване на изобретение');
INSERT INTO eservices.study_type (code, name) VALUES ('MARK', 'Проучване на марка/географско означение');
INSERT INTO eservices.study_type (code, name) VALUES ('OBJECTS', 'Справка за обекти на индустриална собственост');

-- 6
create table eservices.study_sub_type
(
    code            varchar(15) not null
        primary key,
    name            varchar(255),
    name_en         varchar(255),
    study_type_code varchar(15)
        constraint study_type_fk
            references eservices.study_type
);

INSERT INTO eservices.study_sub_type (code, name, name_en, study_type_code) VALUES ('PURITY', 'Патентна чистота', 'Patent purity', 'PATENT');
INSERT INTO eservices.study_sub_type (code, name, name_en, study_type_code) VALUES ('TECH_CONDITION', 'Предшестващо състояние на техниката', 'Previous technology condition', 'PATENT');
INSERT INTO eservices.study_sub_type (code, name, name_en, study_type_code) VALUES ('NOVELTY', 'Новост', 'Novelty', 'PATENT');
INSERT INTO eservices.study_sub_type (code, name, name_en, study_type_code) VALUES ('BY_THEME', 'По тема', 'By theme', 'PATENT');
INSERT INTO eservices.study_sub_type (code, name, name_en, study_type_code) VALUES ('MARK_IDENTITY', 'Проучване на идентичност или сходство на марка или географско означение', 'Mark identity or similarity research', 'MARK');
INSERT INTO eservices.study_sub_type (code, name, name_en, study_type_code) VALUES ('SINGLE_DESIGN', 'Един дизайн', 'Single design', 'DESIGN');
INSERT INTO eservices.study_sub_type (code, name, name_en, study_type_code) VALUES ('SEVERAL_DESIGNS', 'Няколко дизайна(бр.)', 'Several designs', 'DESIGN');
INSERT INTO eservices.study_sub_type (code, name, name_en, study_type_code) VALUES ('SET', 'Комплект', 'Set', 'DESIGN');
INSERT INTO eservices.study_sub_type (code, name, name_en, study_type_code) VALUES ('COMPOSITION', 'Композиция', 'Composition', 'DESIGN');
INSERT INTO eservices.study_sub_type (code, name, name_en, study_type_code) VALUES ('OBJECTS_STUDY', 'Справка за обекти на индустриална собственост', 'Objects study for industrial property objects', 'OBJECTS');
INSERT INTO eservices.study_sub_type (code, name, name_en, study_type_code) VALUES ('INVENTIVE_STEP', 'Новост и изобретателска стъпка', 'Inventive step', 'PATENT');


-- 7
create table eservices.execution_type_to_study_sub_type
(
    code_execution_type varchar(25) not null
        constraint execution_type_to_study_sub_type_execution_type__fk
            references eservices.execution_type,
    code_study_sub_type varchar(15) not null
        constraint execution_type_to_study_sub_type_study_sub_type__fk
            references eservices.study_sub_type,
    constraint execution_type_to_study_sub_type_pk
        primary key (code_execution_type, code_study_sub_type)
);

INSERT INTO eservices.execution_type_to_study_sub_type (code_execution_type, code_study_sub_type) VALUES ('STANDARD_TWENTY_DAYS', 'PURITY');
INSERT INTO eservices.execution_type_to_study_sub_type (code_execution_type, code_study_sub_type) VALUES ('STANDARD_TWENTY_DAYS', 'TECH_CONDITION');
INSERT INTO eservices.execution_type_to_study_sub_type (code_execution_type, code_study_sub_type) VALUES ('FAST_NINE_DAYS', 'PURITY');
INSERT INTO eservices.execution_type_to_study_sub_type (code_execution_type, code_study_sub_type) VALUES ('FAST_NINE_DAYS', 'TECH_CONDITION');
INSERT INTO eservices.execution_type_to_study_sub_type (code_execution_type, code_study_sub_type) VALUES ('FAST_NINE_DAYS', 'NOVELTY');
INSERT INTO eservices.execution_type_to_study_sub_type (code_execution_type, code_study_sub_type) VALUES ('FAST_NINE_DAYS', 'INVENTIVE_STEP');
INSERT INTO eservices.execution_type_to_study_sub_type (code_execution_type, code_study_sub_type) VALUES ('NORMAL_THREE_MONTHS', 'NOVELTY');
INSERT INTO eservices.execution_type_to_study_sub_type (code_execution_type, code_study_sub_type) VALUES ('NORMAL_THREE_MONTHS', 'INVENTIVE_STEP');
INSERT INTO eservices.execution_type_to_study_sub_type (code_execution_type, code_study_sub_type) VALUES ('NORMAL_TEN_DAYS', 'MARK_IDENTITY');
INSERT INTO eservices.execution_type_to_study_sub_type (code_execution_type, code_study_sub_type) VALUES ('FAST_FIVE_DAYS', 'MARK_IDENTITY');
INSERT INTO eservices.execution_type_to_study_sub_type (code_execution_type, code_study_sub_type) VALUES ('SUBSCRIPTION', 'MARK_IDENTITY');
INSERT INTO eservices.execution_type_to_study_sub_type (code_execution_type, code_study_sub_type) VALUES ('NORMAL_TEN_DAYS', 'SINGLE_DESIGN');
INSERT INTO eservices.execution_type_to_study_sub_type (code_execution_type, code_study_sub_type) VALUES ('NORMAL_TEN_DAYS', 'SEVERAL_DESIGNS');
INSERT INTO eservices.execution_type_to_study_sub_type (code_execution_type, code_study_sub_type) VALUES ('NORMAL_TEN_DAYS', 'SET');
INSERT INTO eservices.execution_type_to_study_sub_type (code_execution_type, code_study_sub_type) VALUES ('NORMAL_TEN_DAYS', 'COMPOSITION');
INSERT INTO eservices.execution_type_to_study_sub_type (code_execution_type, code_study_sub_type) VALUES ('FAST_NINE_DAYS', 'SINGLE_DESIGN');
INSERT INTO eservices.execution_type_to_study_sub_type (code_execution_type, code_study_sub_type) VALUES ('FAST_NINE_DAYS', 'SEVERAL_DESIGNS');
INSERT INTO eservices.execution_type_to_study_sub_type (code_execution_type, code_study_sub_type) VALUES ('FAST_NINE_DAYS', 'SET');
INSERT INTO eservices.execution_type_to_study_sub_type (code_execution_type, code_study_sub_type) VALUES ('FAST_NINE_DAYS', 'COMPOSITION');

-- 8
create table eservices.mark_classes
(
    id                integer not null
        primary key,
    name              text,
    name_en           text,
    short_description varchar(80)
);

INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (14, 'Благородни метали и техните сплави, и изделия от благородни метали или с такова покритие, които не са включени в други класове; бижутерия, скъпоценнчни и технологически услуги и свързани с тях изследователски и проектантски услуги; промишлени анализи и проучвания; проектиране и разработване на компютърен софтуер и хардуер; юридически услуги.', 'Precious metals and their alloys and goods in precious metals or coated therewith (except cutlery, forks and spoons);  jewellery, precious stones, horological and other chronometric instruments.', 'PRECIOUS METALS AND THEIR ALLOYS AND GOODS');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (15, 'Музикални инструменти.', 'Musical instruments (other than talking machines and wireless apparatus).', 'MUSICAL INSTRUMENTS.');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (16, 'Хартия, картон и стоки от тези материали, които не са включени в други класове; печатни произведения; материали за подвързване на книги; фотографии; канцеларски принадлежности; канцеларски лепила и лепила за домакински цели; материали за художници; четки за рисуване; пишещи машини и канцеларско оборудване (с изключение на мебели); учебни материали (с изключение на апарати); пластмасови материали за опаковки (които не са включени в други класове);  печатарски букви; клишета.', 'Paper and paper articles, cardboard and cardboard articles;  printed matter, newspapers and periodicals, books;  book-binding material;  photographs;  stationery, adhesive materials (stationery);  artists'' materials;  paint brushes;  typewriters and office requisites (other than furniture);  instructional and teaching material (other than apparatus); printers'' type and cliches (stereotype).', 'PAPER, CARDBOARD AND GOODS');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (17, 'Каучук, гутаперча, гума, азбест, слюда и стоки от тези материали, които не са включени в други класове; формувани пластмаси за производството; материали за запушване, уплътняване и изолиране; неметални гъвкави тръби.', 'Gutta percha, indiarubber, balata and substutes, articles made from these substances and not included in other classes;  plastics in the form of sheets, blocks and rods, being for use in manufacture;  materials for packing, stopping or insulating;  asbestos, mica and their products;  hose pipes (non-metallic).', 'RUBBER, GUTTA-PERCHA, GUM, ASBESTOS, MICA AND GOOD');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (1, 'Химически продукти, предназначени за промишлеността, науката, фотографията, селското стопанство, градинарството и горското стопанство; изкуствени смоли в необработено състояние, пластмаси в необработено състояние; торове; пожарогасителни състави; препарати за закаляване и заваряване на метали; химически вещества за консервиране на храни; дъбилни вещества; адхезиви (лепливи вещества), предназначени за промишлеността.', 'Chemicals products used in industry, science photography, agriculture, horticulture, forestry;  artificial and synthetic resins; plastics in the form of powders, liquids or pastes, for industrial use;  manures (natural and artificial); fire extinguishing compositions; tempering substances and chemical preparations for soldering and chemical substances for preserving foodstuffs; tanning substances; adhesives used in industry.', 'CHEMICALS USED IN INDUSTRY, SCIENCE AND PHOTOGRAPH');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (2, 'Бои, лакове, политури; антикорозионни вещества и вещества, предпазващи дървения материал от повреждане; оцветители; байцове; необработени естествени смоли; метали във вид на фолио и прах за бояджии, декоратори, печатари и художници.', 'Paints, varnishes, lacquers; preservatives against rust and against deterioration of wood; colouring matters; dyestuffs;  mordants; natural resins; metals in foil and powder form for painters and decorators.', 'PAINTS, VARNISHES, LACQUERS; PRESERVATIVES');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (3, 'Препарати за избелване и пране; препарати за почистване, полиране, обезмасляване и шлифоване; сапуни; парфюмерия, етерични масла, козметика, лосиони за коса; средства за почистване на зъби.', e'Bleaching preparations and other substances for laundry use;

cleaning, polishing, scouring and abrasive preparations; soaps; perfumery, essential oils, cosmetics, hair lotions; dentifrices.', 'BLEACHING PREPARATIONS AND OTHER SUBSTANCES');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (4, 'Промишлени масла и греси; смазки; препарати за абсорбиране на прах, омокрящи и свързващи средства; горива (включително моторно гориво) и осветителни вещества; свещи, фитили.', 'Industrial oils and greases (other than edible oils and fats and essential oils);  lubricants; dust laying and absorbing compositions; fuels (including motor spirit) and illuminants; candles, tapers, rightlights and wicks.', 'INDUSTRIAL OILS AND GREASES; LUBRICANTS; DUST');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (5, 'Фармацевтични, ветеринарни и хигиенни препарати; диетични вещества за медицински цели, бебешки храни; пластири, превързочни материали; материали за пломбиране на зъби и за зъбни отливки; дезинфектанти; препарати за унищожаване на вредни животни; фунгициди, хербициди.', 'Pharmaceutical, veterinary and sanitary substances;  infants'' and invalids'' foods;  plasters, material for bandaging; material for stopping teeth, dental wax; disinfectants; preparations for destroying vermin.', 'PHARMACEUTICAL, VETERINARY AND SANITARY');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (37, 'Строителство; ремонт; монтажни услуги.', 'Construction and repair.', 'BUILDING CONSTRUCTION; REPAIR; INSTALLATION');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (38, 'Телекомуникации.', 'Communication.', 'TELECOMMUNICATIONS.');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (39, 'Транспорт; опаковане и съхраняване на стоки; организиране на пътувания.', 'Transportation and storage.', 'TRANSPORT; PACKAGING AND STORAGE OF GOODS; TRAVEL');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (40, 'Обработване на материали.', 'Material treatment.', 'TREATMENT OF MATERIALS.');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (43, 'Ресторантьорство; временно настаняване.', null, 'SERVICES FOR PROVIDING FOOD AND DRINK; TEMPORARY ACCOMMODATION');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (6, 'Неблагородни метали и техните сплави; метални строителни материали; преносими метални конструкции; метални материали за железопътни линии; неелектрически метални кабели и жици; железария, метална кинкалерия; метални тръби; метални каси; метални продукти, които не са включени в други класове; руди.', 'Unwrought and partly wrought common metals and their alloys;  anchors, anvils, bells, rolled and cast building materials;  rails and other metallic materials for railway tracks;  chains (except driving chains for vehicles);  cables and wires (non-electric);  locksmiths'' work:  metallic pipes and tubes;  safes and cash boxes;  steel balls;  horseshoes;  nails and screws;  other goods in non-precious metal not included in other classes;  ores.', 'COMMON METALS AND THEIR ALLOYS; METAL BUILDING');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (7, 'Машини и машинни инструменти; електродвигатели и двигатели с вътрешно горене (с изключение на такива за сухопътни превозни средства); съединителни елементи и трансмисионни предавки (с изключение на такива за сухопътни превозни средства); селскостопански инструменти, с изключение на ръчно задвижвани; инкубатори за яйца.', 'Machines and machine tools; motors (except for land vehicles); machine couplings and  belting (except for land vehicles); large size agricultural implements; incubators.', 'MACHINES AND MACHINE TOOLS; MOTORS AND ENGINES');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (8, 'Ръчни, ръчно задвижвани инструменти и сечива; ножарски изделия и прибори за хранене; хладни оръжия; бръсначи.', 'Hand tools and instruments;  cutlery, forks and spoons;  side arms.', 'HAND TOOLS AND IMPLEMENTS (HAND OPERATED); CUTLERY');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (9, 'Научни, навигационни, геодезически, фотографски, кинематографични, оптични, теглилни, измервателни, сигнални, контролни, надзорни, животоспасяващи и учебни апарати и уреди; апарати и уреди за провеждане, комутиране, транспортиране, акумулиране, регулиране и управление на електричество; апарати за записване, предаване или възпроизвеждане на звук или образ; магнитни носители на данни, записващи дискове; автомати за продажба и механизми за монетни апарати; касови апарати, сметачни машини, оборудване за обработка на информация и компютри; пожарогасители.', 'Scientific, nautical, surveying and electrical apparatus and instruments (including wireless), photographic, cinematographic, optical, weighing, measuring, signalling, checking (supervision), life-saving and teaching apparatus and instruments;  coin or counter-freed apparatus, talking machines;  cash registers;  calculating machines;  fire-extinguishing apparatus.', 'SCIENTIFIC, NAUTICAL, SURVEYING, ELECTRIC,');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (10, 'Хирургически, медицински, стоматологични и ветеринарни апарати и инструменти, изкуствени крайници, очи и зъби; ортопедични изделия; хирургични материали.', 'Surgical, medical, dental and veterinary instruments and apparatus (including artificial limbs, eyes and teeth).', 'SURGICAL, MEDICAL, DENTAL AND VETERINARY APPARATUS');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (11, 'Уреди за осветление, отопление, парогенериране, готвене, охлаждане, сушене, вентилация, водоснабдяване и за санитарни цели.', 'Installations for lighting, heating, steam generating, cooking, refrigerating, drying, ventilating, water supply and sanitary pruposes.', 'APPARATUS FOR LIGHTING, HEATING, STEAM GENERATING');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (12, 'Превозни средства; апарати за придвижване по земя, вода и въздух.', 'Vehicles;  apparatus for locomotion by land, air or water.', 'VEHICLES; APPARATUS FOR LOCOMOTION BY LAND, AIR');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (13, 'Огнестрелни оръжия; амуниции и снаряди; експлозиви; пиротехнически средства.', 'Firearms;  ammunition and projectiles;  explosive substances;  fireworks.', 'FIREARMS; AMMUNITION AND PROJECTILES; EXPLOSIVES;');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (18, 'Кожа и имитации на кожа и стоки от тези материали, които не са включени в други класове; животински кожи; куфари и пътни чанти; чадъри, слънчобрани и бастуни; камшици, хамути и сарашки стоки.', 'Leather and imitation of leather, and articles made from these materials and not included in other classes;  skins, hides;  trunks and travelling bas;  umbrellas, parasols and walking sticks;  whips, harness and saddlery.', 'LEATHER AND IMITATIONS OF LEATHER, AND GOODS');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (19, 'Неметални строителни материали; неметални твърди тръби за строителството; асфалт, катран и битум; неметални преносими конструкции; неметални паметници.', 'Building materials, natural and artificial stone, cement, lime, mortar, plaster and gravel;  pipes of carthenware or cement;  road-making materials;  asphalt, pitch and bitumen;  portable buildings;  stone monuments;  chimney pots.', 'BUILDING MATERIALS (NON-METALLIC); NON-METALLIC');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (20, 'Мебели, огледала, рамки за картини; стоки (които не са включени в други класове) от дърво, корк, камъш, тръстика, ракита, рог, кост, слонова кост, бален, раковина, кехлибар, седеф, морска пяна и от заместители на тези материали или от пластмаса.', 'Furniture, mirrors, picture frames;  articles (not included in other classes) of wood, cork, reeds, cane, wicker, horn, bone, ivory, whalebone, shell, amber, mother-of-pearl, meerschaum, celluloid, substitutes for all these materials, or of plastics.', 'FURNITURE, MIRRORS, PICTURE FRAMES; GOODS');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (44, 'Медицинско обслужване; ветеринарни услуги; грижи за хигиената и красотата на хора и животни; услуги в областта на селското стопанство, градинарството и лесовъдството.', null, 'MEDICAL SERVICES; VETERINARY SERVICES');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (45, 'Персонални и социални услуги, извършвани от трети лица с цел удовлетворяване на нуждите на индивидите; охранителни услуги с цел закрила на собственост и хора.', null, 'PERSONAL AND SOCIAL SERVICES RENDERED BY OTHERS TO MEET THE NEEDS OF INDIVIDUALS');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (21, 'Домакински или кухненски прибори и съдове (които не са от благородни метали или с такова покритие); гребени и гъби; четки (с изключение на четки за рисуване); материали за изработване на четки; средства за почистване; стоманена тел за домакински цели; необработено или полуобработено стъкло (с изключение на стъкло, използвано в строителството); стъклария, порцеланови и керамични изделия, които не са включени в други класове.', 'Small domestic utensils and containers (not of precious metal or coated therewith);  combs and sponges;  brushes (other than paint brushes);  brush-making materials;  instruments and material for cleaning purposes;  steelwool;  glassware, porcelain and carthenware, not included in other classes.', 'HOUSEHOLD OR KITCHEN UTENSILS AND CONTAINERS');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (22, 'Въжета, канап, мрежи, навеси, чергила, брезент, корабни платна, торби и чанти (които не са включени в други класове); материали за подложки и пълнеж (с изключение на каучукови или пластмасови); текстилни материали от необработени влакна.', 'Ropes, string, nets, tents, awnings, tarpaulins, sails, sacks;  padding and stuffing materials (hair, caapoc, feathers, seaweed, etc.);  raw fibrous textile materials.', 'ROPES, STRING, NETS, TENTS, AWNINGS, TARPAULINS');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (25, 'Облекло, обувки, шапки и други принадлежности за глава.', 'Clothing, including boots, shoes and slippers.', 'CLOTHING, FOOTWEAR, HEADGEAR.');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (26, 'Дантели и бродерии, панделки и ширити; копчета, телени копчета, карфици и игли; изкуствени цветя.', 'Lace and embroidery, ribands and braid;  buttons, press buttons, hooks and eyes, pins and needles;  artificial flowers.', 'LACE AND EMBROIDERY, RIBBONS AND BRAID; BUTTONS');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (27, 'Килими, черги, изтривалки, рогозки, линолеум и други материали за подови покрития; нетекстилни драперии за стена.', 'Carpets, rugs, mats and matting;  linoleums and other materials for covering floors;  wall hangings (non-textile).', 'CARPETS, RUGS, MATS AND MATTING, LINOLEUM');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (28, 'Игри и играчки; гимнастически и спортни артикули, които не са включени в други класове; украшения за коледни елхи.', 'Games and playthings;  gymnastic and sporting articles (except clothing);  ornaments and decorations for Christmas trees.', 'GAMES AND PLAYTHINGS; GYMNASTIC');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (29, 'Месо, риба, птици и дивеч; месни екстракти; консервирани, сушени, варени и печени плодове и зеленчуци; желета, конфитюри и компоти; яйца, мляко и млечни произведения; хранителни масла и мазнини.', 'Meat, fish, poultry and game;  meat extracts;  preserved, dried and cooked fruits and vegetables; jellies, james; eggs, milk and other dairy products;  edible oils and fats;  preserves, pickles. ', 'MEAT, FISH, POULTRY AND GAME; MEAT EXTRACTS;');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (30, 'Кафе, чай, какао, захар, ориз, тапиока, саго, заместители на кафе; брашно и произведения от зърнени храни, хляб, сладкиши и захарни изделия, сладоледи; мед, меласа; мая, бакпулвер; сол, горчица; оцет, сосове (подправки); подправки; лед.', 'Coffee, tea, cocoa, sugar, rice, tapioca, sago, coffee substitutes; flour and preparations made from cereals;  bread, biscuits, cakes, pastry and confectionery, ices;  honey, treacle;  yeast, baking-powder;  salt, mustard;  pepper, vinegar, sauces, spices;  ice.', 'COFFEE, TEA, COCOA, SUGAR, RICE, TAPIOCA, SAGO, AR');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (31, 'Селскостопански, градинарски и горски продукти и семена, които не са включени в други класове; живи животни; пресни плодове и зеленчуци; семена за посев, живи растения и цветя; храни за животни; малц.', 'Agricultural, horticultural and forestry products and grains not included in other classes;  living animals;  fresh fruits and vegetables;  seeds;  live plants and flowers;  foodstuffs for animals, malt.', 'AGRICULTURAL, HORTICULTURAL AND FORESTRY PRODUCTS');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (32, 'Бира; безалкохолни напитки, минерални и газирани води; плодови напитки и сокове; сиропи и други препарати за приготвяне на напитки.', 'Beer, ale and porter;  mineral and aerated waters and other non-alcoholic drinks;  syrups and other preparations for making beverages.', 'BEERS; MINERAL AND AERATED WATERS');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (33, 'Алкохолни напитки (с изключение на бира).', 'Wines, spirits and liqueurs.', 'ALCOHOLIC BEVERAGES (EXCEPT BEERS).');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (23, 'Прежди и конци за текстилна употреба.', 'Yarns, threads.', 'YARNS AND THREADS, FOR TEXTILE USE.');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (24, 'Текстил и текстилни изделия, които не са включени в други класове; покривки за легла и маси.', 'Tissues (piece goods);  bed and table covers;  textile articles not included in other classes.', 'TEXTILES AND TEXTILE GOODS');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (41, 'Образование; обучение; развлечение, спортна и културна дейност.', 'Education and entertainment.', 'EDUCATION; PROVIDING OF TRAINING; ENTERTAINMENT;');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (42, 'Научни и технологически услуги и свързани с тях изследователски и проектантски услуги; промишлени анализи и проучвания; проектиране и разработване на компютърен софтуер и хардуер; юридически услуги.', null, 'SCIENTIFIC AND TEHNOLOGICAL SERVICES AND RESEARCH AND DESIGN RELATING THERETO');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (34, 'Тютюн; принадлежности за пушачи; кибрит.', 'Tobacco, raw or manufactured;  smokers'' articles;  matches.', 'TOBACCO; SMOKERS'' ARTICLES; MATCHES.');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (35, 'Реклама; управление на търговски сделки; търговска администрация; административна дейност.', 'Advertising and business.', 'ADVERTISING; BUSINESS MANAGEMENT;');
INSERT INTO eservices.mark_classes (id, name, name_en, short_description) VALUES (36, 'Застрахователна дейност; финансови сделки; парични сделки; сделки с недвижимо имущество.', 'Insurance and financial.', 'INSURANCE; FINANCIAL AFFAIRS; MONETARY AFFAIRS;');

-- 9
create table eservices.mark_type
(
    code    varchar(15) not null
        primary key,
    name    varchar(255),
    name_en varchar(255)
);

INSERT INTO eservices.mark_type (code, name, name_en) VALUES ('WORD', 'Словна', 'Word');
INSERT INTO eservices.mark_type (code, name, name_en) VALUES ('FIGURATIVE', 'Фигуративна', 'Figurative');
INSERT INTO eservices.mark_type (code, name, name_en) VALUES ('COMBINED', 'Комбинирана', 'Combined');
INSERT INTO eservices.mark_type (code, name, name_en) VALUES ('3D', 'Триизмерна', 'Three dimensional');
INSERT INTO eservices.mark_type (code, name, name_en) VALUES ('SOUND', 'Звукова', 'Sound');


-- 10
create table eservices.study_object_type
(
    code    varchar(7) not null
        primary key,
    name    varchar(255),
    name_en varchar(255)
);

INSERT INTO eservices.study_object_type (code, name, name_en) VALUES ('PT', 'Изобретение', 'Patent');
INSERT INTO eservices.study_object_type (code, name, name_en) VALUES ('UM', 'Полезен модел', 'Utility model');
INSERT INTO eservices.study_object_type (code, name, name_en) VALUES ('SPC', 'SPC', 'SPC');
INSERT INTO eservices.study_object_type (code, name, name_en) VALUES ('TM', 'Марка/Географско означение', 'Mark/Geographical indication');
INSERT INTO eservices.study_object_type (code, name, name_en) VALUES ('DS', 'Дизайн', 'Design');
INSERT INTO eservices.study_object_type (code, name, name_en) VALUES ('PV', 'Сорт растения/Порода животни', 'Sort and breeds');

-- 11
create table eservices.objects_study_number_type
(
    code varchar(15) not null
        primary key,
    name varchar(50)
);

INSERT INTO eservices.objects_study_number_type (code, name) VALUES ('PROTECTED_DOC', 'Защитен документ');
INSERT INTO eservices.objects_study_number_type (code, name) VALUES ('REGISTRATION', 'Регистрация');
INSERT INTO eservices.objects_study_number_type (code, name) VALUES ('CERTIFICATE', 'Сертификат');
INSERT INTO eservices.objects_study_number_type (code, name) VALUES ('REQUEST', 'Номер на заявка');

-- 12
create table eservices.object_type_to_number_type
(
    object_type_code varchar(7)  not null
        constraint object_type_code_fk
            references eservices.study_object_type,
    number_type_code varchar(15) not null
        constraint number_type_code_fk
            references eservices.objects_study_number_type,
    constraint objects_study_object_to_number_type_pk
        primary key (object_type_code, number_type_code)
);

INSERT INTO eservices.object_type_to_number_type (object_type_code, number_type_code) VALUES ('PT', 'REQUEST');
INSERT INTO eservices.object_type_to_number_type (object_type_code, number_type_code) VALUES ('PT', 'PROTECTED_DOC');
INSERT INTO eservices.object_type_to_number_type (object_type_code, number_type_code) VALUES ('UM', 'REQUEST');
INSERT INTO eservices.object_type_to_number_type (object_type_code, number_type_code) VALUES ('UM', 'PROTECTED_DOC');
INSERT INTO eservices.object_type_to_number_type (object_type_code, number_type_code) VALUES ('SPC', 'REQUEST');
INSERT INTO eservices.object_type_to_number_type (object_type_code, number_type_code) VALUES ('SPC', 'PROTECTED_DOC');
INSERT INTO eservices.object_type_to_number_type (object_type_code, number_type_code) VALUES ('TM', 'REQUEST');
INSERT INTO eservices.object_type_to_number_type (object_type_code, number_type_code) VALUES ('TM', 'REGISTRATION');
INSERT INTO eservices.object_type_to_number_type (object_type_code, number_type_code) VALUES ('DS', 'REQUEST');
INSERT INTO eservices.object_type_to_number_type (object_type_code, number_type_code) VALUES ('DS', 'PROTECTED_DOC');
INSERT INTO eservices.object_type_to_number_type (object_type_code, number_type_code) VALUES ('PV', 'REQUEST');
INSERT INTO eservices.object_type_to_number_type (object_type_code, number_type_code) VALUES ('PV', 'CERTIFICATE');

-- 13
create table eservices.objects_study_research_type
(
    code varchar(15) not null
        primary key,
    name varchar(50)
);

INSERT INTO eservices.objects_study_research_type (code, name) VALUES ('BY_NAME', 'По име на');
INSERT INTO eservices.objects_study_research_type (code, name) VALUES ('BY_NUMBER', 'По номер на');
INSERT INTO eservices.objects_study_research_type (code, name) VALUES ('PLANT_NAME', 'По наименование на сорт/порода');
INSERT INTO eservices.objects_study_research_type (code, name) VALUES ('LEGAL_STATUS', 'За правен статус на');
INSERT INTO eservices.objects_study_research_type (code, name) VALUES ('PT_ANALOG', 'За патентен аналог');

-- 14
create table eservices.patent_declaration_type
(
    code    varchar(15) not null
        primary key,
    name    varchar(255),
    name_en varchar(255)
);

INSERT INTO eservices.patent_declaration_type (code, name, name_en) VALUES ('SMALL_COPY', 'микро- или малко предприятие по ЗМСП', null);
INSERT INTO eservices.patent_declaration_type (code, name, name_en) VALUES ('SCHOOL', 'държавно / общинско училище', null);
INSERT INTO eservices.patent_declaration_type (code, name, name_en) VALUES ('UNIVERSITY', 'държавно висше училище / академична научноизследователска организация на бюджетна издръжка', null);
INSERT INTO eservices.patent_declaration_type (code, name, name_en) VALUES ('INVENTOR', 'изобретател, вписан в Държавния регистър на Патентното ведомство', null);
INSERT INTO eservices.patent_declaration_type (code, name, name_en) VALUES ('NO_DECLARATION', e'
без декларация', null);

-- 15
create table eservices.patent_novelty_result
(
    code    varchar(15) not null
        primary key,
    name    varchar(255),
    name_en varchar(255)
);

INSERT INTO eservices.patent_novelty_result (code, name, name_en) VALUES ('REPORT', 'с доклад', 'With report');
INSERT INTO eservices.patent_novelty_result (code, name, name_en) VALUES ('REPORT_OPTION', 'с доклад и становище', 'With report and opinion');

-- 16
create table eservices.payment_status
(
    name        varchar(50) not null
        primary key,
    description varchar(300)
);

INSERT INTO eservices.payment_status (name, description) VALUES ('GENERATED', 'Заявката за плащане е регистрирана в системата');
INSERT INTO eservices.payment_status (name, description) VALUES ('PAID', 'Плащането е получено по сметката на доставчика');
INSERT INTO eservices.payment_status (name, description) VALUES ('EXPIRED', 'Заявката за плащане е изтекла');
INSERT INTO eservices.payment_status (name, description) VALUES ('SUSPENDED', 'Заявката за плащане е отказана от АИС');
INSERT INTO eservices.payment_status (name, description) VALUES ('PENDING', 'Очаква плащане');
INSERT INTO eservices.payment_status (name, description) VALUES ('AUTHORIZED', 'Получена е авторизация от виртуалния ПОС терминал');
INSERT INTO eservices.payment_status (name, description) VALUES ('ORDERED', 'Плащането е наредено');
INSERT INTO eservices.payment_status (name, description) VALUES ('ERROR', 'Грешка при плащане');
INSERT INTO eservices.payment_status (name, description) VALUES ('CANCELLED', 'Заявката за плащане е отказана от потребителя');

-- 17
create table eservices.person_quality
(
    code    varchar(15) not null
        primary key,
    name    varchar(255),
    name_en varchar(255)
);

INSERT INTO eservices.person_quality (code, name, name_en) VALUES ('APPLICANT', 'Заявител/Притежател', 'Applicant');
INSERT INTO eservices.person_quality (code, name, name_en) VALUES ('AUTHOR', 'Автор', 'Author');
INSERT INTO eservices.person_quality (code, name, name_en) VALUES ('AGENT', 'Представител по ИС', 'Agent');
INSERT INTO eservices.person_quality (code, name, name_en) VALUES ('INVENTOR', 'Изобретател', 'Inventor');
INSERT INTO eservices.person_quality (code, name, name_en) VALUES ('SELECTIONER', 'Селекционер', 'Selectioner');

-- 18
create table eservices.study_attachment_type
(
    code    varchar(20) not null
        primary key,
    name    varchar(255),
    name_en varchar(255)
);

INSERT INTO eservices.study_attachment_type (code, name, name_en) VALUES ('COMMON_ATTACHMENT', 'Общи приложения', null);
INSERT INTO eservices.study_attachment_type (code, name, name_en) VALUES ('DESIGN_IMAGE', 'Изображения за дизайни', null);
INSERT INTO eservices.study_attachment_type (code, name, name_en) VALUES ('MARK_IMAGE', 'Изображения за марки', null);

-- WORK TABLES_____________________________________________________________________________
-- 1
create table eservices.address
(
    id           serial
        constraint eserv_address_pk
            primary key,
    email        varchar(80),
    address      text,
    postalcode   varchar(12),
    phone        varchar(25),
    fax          varchar(25),
    country_code varchar(2)
        constraint "address_country_FK"
            references nomenclatures.country,
    city         varchar(100),
    postalbox    varchar(100)
);

-- 2
create table eservices.public_person
(
    id           serial
        primary key,
    first_name   varchar(50),
    second_name  varchar(50),
    last_name    varchar(50),
    company_name varchar(255)
);

--3
create table eservices.study_payment
(
    id               serial
        constraint study_payment_pk
            primary key,
    payment_amount   varchar(10),
    content_id       varchar(30),
    obligated_person varchar(30),
    description      text,
    created_date     timestamp with time zone,
    status           varchar(50)
        constraint status_fk
            references eservices.payment_status
);

-- 4
create table eservices.application_study
(
    id                     serial
        constraint application_study_pk
            primary key,
    public_person_id       integer
        constraint application_study_public_person_id_fk
            references eservices.public_person,
    address_id             integer
        constraint application_study_address_id_fk
            references eservices.address,
    correspondence_by_mail smallint,
    delivery_type_code     varchar(15)
        constraint application_study_delivery_type_code_fk
            references eservices.delivery_type,
    study_sub_type_code    varchar(15)
        constraint application_study_sub_type_code_fk
            references eservices.study_sub_type,
    name                   varchar(200),
    description            text,
    execution_type_code    varchar(25)
        constraint application_study_execution_type_code_fk
            references eservices.execution_type,
    total_fees             numeric(8, 2),
    created_date           timestamp with time zone,
    created_user           varchar(100),
    updated_date           timestamp with time zone,
    updated_user           varchar(100),
    registration_number    varchar(150),
    registration_date      timestamp with time zone,
    url                    varchar,
    guid                   varchar,
    submit_type_code       varchar(30)
        constraint submit_type_code_fk
            references eservices.application_submit_type
);

-- 5
create table eservices.application_design_study
(
    id               integer not null
        primary key
        constraint study_id_fk
            references eservices.application_study,
    design_count     integer,
    locarno_class_id varchar(5)
        constraint locarno_class_id_fk
            references nomenclatures.locarno_class
);

-- 6
create table eservices.application_design_study_details
(
    study_id            integer not null
        constraint study_id_fk
            references eservices.application_study,
    id                  serial
        primary key,
    bg_study            smallint,
    depth               integer,
    execution_type_code varchar(15)
        constraint execution_type_code_fk
            references eservices.execution_type
);

-- 7
create table eservices.application_mark_study
(
    id             integer     not null
        primary key
        constraint study_id_fk
            references eservices.application_study,
    mark_type_code varchar(15) not null
        constraint mark_type_code_fk
            references eservices.mark_type
);

-- 8
create table eservices.application_mark_study_detail
(
    id            integer not null
        primary key
        constraint study_id_fk
            references eservices.application_study,
    bg_study      smallint,
    foreign_study smallint
);

-- 9
create table eservices.application_objects_study
(
    id                     integer    not null
        primary key
        constraint study_id_fk
            references eservices.application_study,
    study_object_type_code varchar(5) not null
        constraint study_object_type_code_fk
            references eservices.study_object_type
);

-- 10
create table eservices.application_objects_study_details
(
    study_id                        integer     not null
        constraint study_id_fk
            references eservices.application_study,
    id                              serial
        primary key,
    study_param_name                varchar(200),
    study_param_app_number          varchar(50),
    study_other_details             text,
    country_code                    varchar(2)
        constraint country_code_fk
            references nomenclatures.country,
    foreign_country_code            varchar(2)
        constraint study_country_code_fk
            references nomenclatures.country,
    bg_study_registration           smallint,
    person_quality_code             varchar(15)
        constraint person_quality_code_fk
            references eservices.person_quality,
    object_study_research_type_code varchar(15) not null
        constraint object_study_research_type_code_fk
            references eservices.objects_study_research_type,
    object_study_number_type_code   varchar(15)
        constraint object_study_number_type_code_fk
            references eservices.objects_study_number_type,
    study_param_obj_name            varchar(200)
);

-- 11
create table eservices.application_patent_study
(
    id                           integer not null
        primary key
        constraint study_id_fk
            references eservices.application_study,
    patent_novelty_result_code   varchar(15)
        constraint patent_novelty_result_code_fk
            references eservices.patent_novelty_result,
    keywords                     text,
    depth                        integer,
    other                        text,
    patent_declaration_type_code varchar(15)
        constraint patent_declaration_type_code_fk
            references eservices.patent_declaration_type
);

-- 12
create table eservices.design_study_detail_countries
(
    id           integer    not null
        constraint detail_id_fk
            references eservices.application_design_study_details,
    country_code varchar(2) not null
        constraint country_code_fk
            references nomenclatures.country,
    primary key (id, country_code)
);

-- 13
create table eservices.mark_study_classes
(
    study_id   integer    not null
        constraint study_id_fk
            references eservices.application_study,
    stdclassid varchar(2) not null,
    primary key (study_id, stdclassid)
);

-- 14
create table eservices.mark_study_detail_countries
(
    id           integer    not null
        constraint detail_id_fk
            references eservices.application_mark_study_detail,
    country_code varchar(2) not null
        constraint country_code_fk
            references nomenclatures.country,
    primary key (id, country_code)
);

-- 15
create table eservices.patent_study_classes
(
    study_id  integer      not null
        constraint study_id_fk
            references eservices.application_study,
    ipc_class varchar(100) not null,
    primary key (study_id, ipc_class)
);

-- 16
create table eservices.study_attachments
(
    study_id             integer not null
        constraint study_id_fk
            references eservices.application_study,
    attachment_id        integer not null
        constraint attachment_id_fk
            references common.attachment,
    attachment_type_code varchar(20)
        constraint attachment_type_code_fk
            references eservices.study_attachment_type,
    primary key (study_id, attachment_id)
);

-- 17
create table eservices.study_countries
(
    study_id     integer    not null
        constraint study_id_fk
            references eservices.application_study,
    country_code varchar(2) not null
        constraint country_code_fk
            references nomenclatures.country,
    primary key (study_id, country_code)
);

-- VIEWS _________________________________________________________________________________________
-- 1
create view eservices.application_summary_object_type_view(code, name) as
SELECT sot.code,
       sot.name
FROM eservices.study_object_type sot
UNION
SELECT sst.code,
       sst.name
FROM eservices.study_sub_type sst;

-- 2
create view eservices.application_summary_view
            (id, registration_number, object_type_name, object_type_code, applicant, created_date, submit_type_name,
             submit_type_code, study_type, created_user, updated_user)
as
SELECT asy.id,
       asy.registration_number,
       CASE
           WHEN sot.name IS NOT NULL THEN sot.name
           WHEN sst.name IS NOT NULL THEN sst.name
           ELSE 'Unknown'::character varying
END  AS object_type_name,
       CASE
           WHEN sot.code IS NOT NULL THEN sot.code
           WHEN sst.code IS NOT NULL THEN sst.code
           ELSE 'Unknown'::character varying
END  AS object_type_code,
       CASE
           WHEN pp.first_name IS NOT NULL OR pp.second_name IS NOT NULL OR pp.last_name IS NOT NULL THEN (
               (((COALESCE(pp.first_name, ''::character varying)::text || ' '::text) ||
                 COALESCE(pp.second_name, ''::character varying)::text) || ' '::text) ||
               COALESCE(pp.last_name, ''::character varying)::text)::character varying
           ELSE COALESCE(pp.company_name, 'Unknown'::character varying)
END  AS applicant,
       asy.created_date,
       ast.name AS submit_type_name,
       ast.code AS submit_type_code,
       st.code  AS study_type,
       asy.created_user,
       asy.updated_user
FROM eservices.application_study asy
         LEFT JOIN eservices.application_objects_study aos ON asy.id = aos.id
         LEFT JOIN eservices.study_object_type sot ON aos.study_object_type_code::text = sot.code::text
         LEFT JOIN eservices.study_sub_type sst ON asy.study_sub_type_code::text = sst.code::text
         LEFT JOIN eservices.study_type st ON st.code::text = sst.study_type_code::text
         LEFT JOIN eservices.public_person pp ON asy.public_person_id = pp.id
         LEFT JOIN eservices.application_submit_type ast ON asy.submit_type_code::text = ast.code::text;




