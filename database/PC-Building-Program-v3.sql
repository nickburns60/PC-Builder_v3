drop table if exists user_pc_build, pc_case, psu, ram, motherboard, graphics_card, processor, fans, cpu_cooler, 
storage_drive, psu_wattage, form_factor, ram_type, socket, brand, users, roles;


create table brand (
	brand_id serial primary key,
	brand_name varchar(20) unique not null
);

create table socket (
	socket_id serial primary key,
	socket_type varchar(20) unique not null
);

create table ram_type (
	ram_type_id serial primary key,
	ram_type_name varchar(15) unique not null
);

create table form_factor (
	form_factor_id serial primary key,
	form_factor_name varchar(12) unique not null
);

create table psu_wattage (
	psu_wattage_id serial primary key,
	wattage int unique not null
);

create table storage_drive (
	storage_drive_id serial primary key,
	brand_id int references brand not null,
	product_name varchar(50) not null,
	model varchar(50) unique not null,
	capacity_gb int not null,
	form_factor varchar(10) not null,
	price numeric(5,2) not null
);

create table cpu_cooler (
	cpu_cooler_id serial primary key,
	brand_id int references brand not null,
	product_name varchar(50) not null,
	model varchar(50) unique not null,
	cooler_type varchar(10) not null,
	size_mm int not null,
	color varchar(20) not null,
	rgb boolean not null,
	price decimal not null
);

create table fans (
	fan_id serial primary key,
	brand_id int references brand not null,
	product_name varchar(50) not null,
	model varchar(50) unique not null,
	size_mm int not null,
	num_of_fans int not null,
	color varchar(20) not null,
	rgb boolean not null,
	price numeric(5,2)
);

create table processor (
	processor_id serial primary key,
	brand_id int references brand not null,
	socket_id int references socket,
	ram_type_id int references ram_type,
	product_name varchar(50) not null,
	model varchar(50) unique not null,
	price numeric(5,2) not null
);

create table graphics_card (
	graphics_card_id serial primary key,
	brand_id int references brand not null,
	product_name varchar(50) not null,
	model varchar(50) unique not null,
	psu_wattage_id int references psu_wattage,
	price numeric(6,2)
);

create table motherboard (
	motherboard_id serial primary key,
	socket_id int references socket,
	form_factor_id int references form_factor,
	ram_type_id int references ram_type,
	brand_id int references brand not null,
	product_name varchar(50) not null,
	model varchar(50) unique not null,
	price numeric(5,2) not null
);

create table ram (
	ram_id serial primary key,
	ram_type_id int references ram_type,
	brand_id int references brand not null,
	product_name varchar(50) not null,
	model varchar(50) unique not null,
	capacity_gb int not null,
	num_of_sticks int not null,
	rgb boolean not null,
	price numeric(5,2)
);

create table psu (
	psu_id serial primary key,
	psu_wattage_id int references psu_wattage,
	brand_id int references brand not null,
	product_name varchar(50) not null,
	model varchar(50) unique not null,
	cable_type varchar(20) not null,
	energy_efficiency varchar(20) not null,
	price numeric(5,2)
);

create table pc_case (
	case_id serial primary key,
	form_factor_id int references form_factor not null,
	brand_id int references brand not null,
	product_name varchar(50) not null,
	model varchar(50) unique not null,
	color varchar(20) not null,
	rgb boolean not null,
	length_mm int not null,
	width_mm int not null,
	num_fans_included int not null,
	price numeric(5,2) not null
);

create table user_pc_build (
	pc_id serial primary key,
	processor_id int references processor not null,
	graphics_card_id int references graphics_card not null,
	motherboard_id int references motherboard not null,
	ram_id int references ram not null,
	psu_id int references psu not null,
	storage_drive_id int references storage_drive not null,
	case_id int references pc_case not null,
	fan_id int references fans not null,
	cpu_cooler_id int references cpu_cooler not null,
	total_cost decimal not null
);

create table users (
	username varchar(256) primary key,
	password varchar(256) not null,
	email varchar(256) not null
);

create table roles (
	username varchar(256) references users,
	role varchar(256) not null,
	primary key (username, role)
);

insert into users (username, password, email) values
('admin', '$2a$10$UGO28ZuUlQSHV1EwsyBl4.ovJ7rmS996ubKxzfFvJthnr47i7y1wm', 'admin@test.com'),
('user', '$2a$10$//2DShIWcNN8nTc/WPABwensKVFJrZ6G8363aiPf7VU0Qq7hHxMfW', 'user@test.com');

insert into roles (username, role) values
('admin', 'ADMIN'),
('user', 'USER');

insert into brand (brand_name) values
/*1*/('Adata'), /*2*/('AMD'), /*3*/('Arctic Cooling'), /*4*/('ASRock'), /*5*/('Asus'),
/*6*/('be quiet'),
/*7*/('Cooler Master'), /*8*/('Corsair'), 
/*9*/('DeepCool'),
/*10*/('EVGA'),
/*11*/('Fractal Design'), /*12*/('Frozn'),
/*13*/('G.Skill'), /*14*/('Gigabyte'),
/*15*/('Hyte'),
/*16*/('Inland'), /*17*/('Intel'),
/*18*/('Lian Li'),
/*19*/('Montech'), /*20*/('MSI'),
/*21*/('Noctua'), /*22*/('Nvidia'), /*23*/('NZXT'),
/*24*/('Patriot'), /*25*/('PNY'), /*26*/('PowerSpec'),
/*27*/('Samsung'), /*28*/('Seagate'), /*29*/('SK Hynix'),
/*30*/('TeamGroup'), /*31*/('Thermaltake'),
/*32*/('Western Digital'),
/*33*/('Crucial'),
/*34*/('Seasonic'),
/*35*/('XFX');

insert into socket (socket_type) values
('AM4'),
('AM5'),
('LGA 1700');

insert into ram_type (ram_type_name) values
('DDR4'),
('DDR5'),
('DDR4 or DDR5');

insert into form_factor (form_factor_name) values
('Mini-ITX'),
('microATX'),
('ATX'),
('e-ATX');

insert into psu_wattage (wattage) values
(400), (500), (550), (600), (650), (700), (750),
(850), (1000), (1200), (1300), (1600), (800);

insert into storage_drive (brand_id, product_name, model, capacity_gb, form_factor, price) values
(27, '970 EVO Plus', 'MZ-V7S2T0B/AM', 2000, 'M.2', 239.99),
(27, '980 PRO 1TB', 'MZ-V8P1T0B/AM', 1000, 'M.2', 154.99),
(27, '980 PRO 2TB', 'MZ-V8P2T0B/AM', 2000, 'M.2', 219.99),
(27, '990 PRO 1TB Heatsink', 'MZ-V9P1T0CW', 1000, 'M.2', 149.99),
(27, '990 PRO 2TB', 'MZ-V9P2T0B/AM', 2000, 'M.2', 249.99),
(27, '990 EVO 1TB', 'MZ-V9E1T0B/AM', 1000, 'M.2', 149.99),
(27, '870 EVO 1TB', 'MZ-77E1T0B/AM', 1000, 'SATA', 144.99),
(27, '870 EVO 4TB', 'MZ-77E4T0B/AM', 4000, 'SATA', 489.99),
(27, '870 EVO 2TB', 'MZ-77E2T0B/AM', 2000, 'SATA', 254.99),
(27, '870 QVO 2TB', 'MZ-77Q2T0B/AM', 2000, 'SATA', 184.99),
(32, 'Black SN770 500GB', 'WDBBDL5000ANC-WRSN', 500, 'M.2', 79.99),
(32, 'Black SN770 2TB', 'WDBBDL0020BNC-WRSN', 2000, 'M.2', 239.99),
(32, 'Black SN850X 4TB', 'WDBB9G0040BNC-WRSN', 4000, 'M.2', 399.99),
(32, 'Black SN850X 1TB', 'WDBB9G0010BNC-WRSN', 1000, 'M.2', 129.99),
(32, 'Blue SA510 1TB', 'WDBB8H0010BNC-WRSN', 1000, 'SATA', 107.99),
(32, 'Blue SN580 1TB', 'WDBWMY0010BBL-WRSN', 1000, 'M.2', 93.99),
(32, 'Blue 1TB HDD', 'WD10SPZX/WDBMYH0010BNC-NRSN', 1000, 'SATA', 52.99),
(32, 'Black SN850P 4TB Heatsink', 'WDBBYV0040BNC-WRSN', 4000, 'M.2', 499.99),
(32, 'Black Gaming 4TB HDD', 'WD4005FZBX/WDBSLA0040HNC-NRSN', 4000, 'M.2', 179.99),
(33, 'MX500 2TB', 'CT2000MX500SSD1', 2000, 'SATA', 199.99),
(33, 'MX500 500GB', 'CT500MX500SSD1', 500, 'SATA', 79.99),
(33, 'P3 1TB', 'CT1000P3SSD8', 1000, 'M.2', 89.99),
(33, 'P3 4TB', 'CT4000P3SSD8', 4000, 'M.2', 344.99),
(33, 'P3 Plus 2TB', 'CT2000P3PSSD8', 2000, 'M.2', 169.99),
(33, 'T500 1TB Heatsink', 'CT1000T500SSD5', 1000, 'M.2', 141.99),
(33, 'T500 2TB Heatsink', 'CT2000T500SSD5', 2000, 'M.2', 228.99),
(33, 'BX500 1TB', 'CT1000BX500SSD1', 1000, 'SATA', 94.99),
(33, 'T700 2TB', 'CT2000T700SSD3', 2000, 'M.2', 349.99),
(33, 'T700 4TB', 'CT4000T700SSD3', 4000, 'M.2', 599.99),
(28, 'Game Drive 1TB Heatsink', 'ZP1000GP3A1011', 1000, 'M.2', 124.99),
(28, 'Game Drive 2TB Heatsink', 'ZP2000GP3A1001', 2000, 'M.2', 319.99),
(28, 'FireCuda 530 1TB Heatsink', 'ZP1000GM3A023', 1000, 'M.2', 249.99),
(28, 'FireCuda 530 4TB Heatsink', 'ZP4000GM3A023', 4000, 'M.2', 959.99),
(28, 'Lightsaber FireCuda 2TB', 'ZP2000GM3A033', 2000, 'M.2', 239.99),
(28, 'Barracuda 8TB HDD', 'ST8000DMA04', 8000, 'SATA', 149.99),
(28, 'IronWolf Pro 16TB HDD', 'ST16000NTA01', 16000, 'SATA', 329.99),
(1, 'XPG GAMMIX S70 Blade 2TB Heatsink', 'AGAMMIXS70B-2T-CS', 2000, 'M.2', 179.99),
(1, 'XPG GAMMIX S70 Blade 1TB Heatsink', 'AGAMMIXS70B-1T-CS', 1000, 'M.2', 99.99),
(1, 'XPG GAMMIX S70 Blade 8TB Heatsink', 'AGAMMIXS70B-8T-CS', 8000, 'M.2', 899.99),
(8, 'MP600 PRO LPX 2TB Heatsink', 'CSSD-F2000GBMP600PLP', 2000, 'M.2', 209.99),
(25, 'CS1030 2TB', 'M280CS1030-2TB-RB', 2000, 'M.2', 109.99),
(29, 'Platinum P41 2TB', 'HFS2T0GEJ9X', 2000, 'M.2', 179.99),
(29, 'Gold P31 1TB', 'HFS1T0GDE9X', 1000, 'M.2', 99.99),
(16, 'TN470 1TB', '1TBTN470', 1000, 'M.2', 84.99),
(16, 'TN320 256GB', '256GTN320NVME', 256, 'M.2', 29.99);

insert into cpu_cooler (brand_id, product_name, model, cooler_type, size_mm, color, rgb, price) values
(9, 'AK620 ZERO DARK', 'RAK620BKNNMTG1', 'Air', 120, 'Black', false, 69.99),
(9, 'AK400 WH', 'R-AK400-WHNNMN', 'Air', 120, 'White', false, 37.99),
(21, 'NH-D15S Chromax', 'NH-D15S CH.BK', 'Air', 140, 'Black', false, 119.99),
(7, 'Hyper 212 Spectrum V3', 'RR-S4NA-17PA-R1', 'Air', 120, 'Black', true, 19.99),
(7, 'Hyper 212 Halo', 'RR-S4WW-20PA-R1', 'Air', 120, 'White', true, 34.99),
(31, 'UX100 ARGB Universal', 'CLP064AL12SWA', 'Air', 120, 'Black', true, 19.99),
(23, 'Kraken 120mm Radiator', 'RL-KR120-B1', 'Liquid', 120, 'Black', true, 89.99),
(18, 'Galahad II Trinity', 'G89.GA2T36B.00', 'Liquid', 360, 'Black', true, 149.99),
(9, 'LT720 WH Premium', 'RLT720WHAMNFG1', 'Liquid', 360, 'White', true, 139.99),
(23, 'Kraken Elite RGB', 'RL-KR36E-W1', 'Liquid', 360, 'White', true, 299.99),
(23, 'Kraken 240mm', 'RL-KN240-B1', 'Liquid', 240, 'Black', false, 139.99),
(23, 'Kraken Elite 240', 'RL-KR24E-B1', 'Liquid', 240, 'Black', true, 259.99),
(5, 'ROG STRIX LC II', 'STX LC2 360ARGB', 'Liquid', 360, 'Black', true, 149.99),
(8, 'iCue H100i ELITE CAPELLIX XT', 'CW-9060068-WW', 'Liquid', 240, 'Black', true, 179.99),
(8, 'iCue Link H100i RGB', 'CW-9061001-WW', 'Liquid', 240, 'Black', true, 199.99),
(8, 'iCue Link H150i RGB', 'CW-9061003-WW', 'Liquid', 360, 'Black', true, 239.99),
(8, 'iCue H60X RGB ELITE', 'CW-9060064-WW2', 'Liquid', 120, 'Black', true, 79.99),
(20, 'MAG CoreLiquid 240R V2', 'CORELIQUID240R2', 'Liquid', 240, 'Black', true, 109.99),
(6, 'DARK ROCK PRO 5', 'BK036', 'Air', 120, 'Black', false, 99.99),
(9, 'ASSASSIN IV', 'R-ASN4-BKNNMT-G', 'Air', 120, 'Black', false, 99.99),
(31, 'UX200 SE', 'CL-P105-AL12SW', 'Air', 120, 'Black', true, 29.99),
(12, 'A620 aRGB', 'ID-COOLING FROZ', 'Air', 120, 'Black', true, 64.99);

insert into fans (brand_id, product_name, model, size_mm, num_of_fans, color, rgb, price) values
(18, 'Uni Fan SL Infinity', 'G99.12SLIN1W.00', 120, 1, 'White', true, 32.99),
(18, 'Uni Fan Reverse SL Infinity', 'G99.12RSLIN1B.0', 120, 1, 'Black', true, 29.99),
(18, 'Uni Fan SL140 V2', 'G99.14SLV21B.00', 140, 1, 'Black', true, 29.99),
(18, 'AL120 V2', 'G99.12ALV23B.00', 120, 3, 'Black', true, 89.99),
(8, 'iCue Link QX120 RGB', 'CO-9051002-WW', 120, 3, 'Black', true, 159.99),
(8, 'AF120 RGB ELITE', 'CO-9050154-WW', 120, 3, 'Black', true, 89.99),
(8, 'AF140 RGB ELITE', 'CO-9050156-WW', 140, 2, 'Black', true, 74.99),
(8, 'QL Series RGB', 'CO-9050106-WW', 140, 2, 'White', true, 119.99),
(8, 'LL Seires RGB', 'CO-9050071-WW', 120, 1, 'Black', true, 39.99),
(8, 'iCue SP120 RGB ELITE Performance', 'CO-9050109-WW', 120, 3, 'Black', true, 79.99),
(21, 'NF-P12 Redux', 'NF-P12 REDUX-17', 120, 1, 'Gray', false, 15.99),
(21, 'NF-A12X15', 'NF-A12x15 PWM', 120, 1, 'Brown', false, 24.99),
(3, 'P12 Fluid Dynamic Bearing', 'ACFAN00135A', 120, 5, 'Black', false, 31.99),
(3, 'P12 PWM PST Fluid Dynamic Bearing', 'ACFAN00120A', 120, 1, 'Black', false, 9.99),
(9, 'FC120 RGB Hydro Bearing', 'RFC120WHAMN3G1', 120, 3, 'White', true, 49.99),
(15, 'Flow FA12 Fluid Dynamic Bearing', 'FAN-HYTE-001', 120, 3, 'Gray', false, 24.99),
(31, 'Riing 12 LED', 'CL-FO38-PL12WT-A', 120, 1, 'Black', false, 16.99),
(31, 'Riing Quad', 'CL-F088-PL12SW-B', 120, 3, 'Black', true, 119.99),
(31, 'Riing Quad', 'CL-F100-PL12SW-B', 120, 3, 'White', true, 119.99),
(31, 'Pure 20', 'CL-F0150PL20BL-A', 200, 1, 'Black', false, 13.99),
(23, 'F140 Duo RGB', 'RF-D14SF-W1', 140, 1, 'White', true, 34.99),
(23, 'F120 RGB', 'RF-R12SF-B1', 120, 1, 'Black', true, 24.99);

insert into processor (brand_id, product_name, socket_id, ram_type_id, model, price) values
(17, 'Core i5-12600K', 3, 3,'BX8071512600K', 299.99),
(17, 'Core i7-12700K', 3, 3, 'BX8071512700K', 419.99),
(17, 'Core i9-12900K', 3, 3, 'BX8071512900K', 619.99),
(17, 'Core i5-13600K', 3, 3, 'BX8071513600K', 339.99),
(17, 'Core i7-13700K', 3, 3, 'BX8071513700K', 489.99),
(17, 'Core i9-13900K', 3, 3, 'BX8071513900K', 689.99),
(17, 'Core i5-14600K', 3, 3, 'BX8071514600K', 339.99),
(17, 'Core i7-14700K', 3, 3, 'BX8071514700K', 444.99),
(17, 'Core i9-14900K', 3, 3, 'BX8071514900K', 619.99),
(2, 'Ryzen 5 5600X', 1, 1, '100-100000065BOX', 299.00),
(2, 'Ryzen 7 5800X', 1, 1, '100-100000063WOF', 449.00),
(2, 'Ryzen 9 5900X', 1, 1, '100-100000061WOF', 549.00),
(2, 'Ryzen 5 7600X', 2, 2, '100-100000593WOF', 299.99),
(2, 'Ryzen 7 7700X', 2, 2, '100-100000591WOF', 399.99),
(2, 'Ryzen 9 7900X', 2, 2, '100-100000589WOF', 549.99);

insert into graphics_card (brand_id, product_name, model, psu_wattage_id, price) values
(22, 'GeForce RTX 3060 Ti Founders Edition', '900-1G142-2520-000', 4, 349.99),
(22, 'GeForce RTX 3070 Ti Founders Edition', '900-1G143-2520-000', 7, 419.99),
(22, 'GeForce RTX 3080 Founders Edition', '900-1G133-2530-000', 7, 599.99),
(22, 'GeFroce RTX 3080 Ti Founders Edition', '900-1G133-2518-000', 7, 699.99),
(22, 'GeForce RTX 3090 Ti Founders Edition', '900-1G136-2505-000', 9, 849.99),
(22, 'GeForce RTX 4060 Ti Founders Edition', '900-1G1410-2560-000', 3, 399.00),
(22, 'GeForce RTX 4070 Founders Edition', '900-1G141-2544-000', 5, 549.99),
(22, 'GeForce RTX 4070 Super Founders Edition', '900-1G141-2534-000', 5, 599.99),
(22, 'GeForce RTX 4080 Founders Edition', '900-1G136-2560-000', 7, 949.99),
(22, 'GeForce RTX 4080 Super Founders Edition', '900-1G136-2555-000', 7, 999.99),
(22, 'GeForce RTX 4090 Founders Edition', '900-1G136-2530-000', 8, 1599.99),
(20, 'AMD Radeon RX 6500 XT Mech 2X', 'RX 6500 XT MECH 2X 4G OC', 1, 189.99),
(35, 'Speedster SWFT210 AMD Radeon RX 6600 Core', 'RX-66XL8LFDR', 5, 279.99),
(35, 'Speedster SWFT210 AMD Radeon RX 6650XT Core', 'RX-665X8DFDR', 2, 319.99),
(35, 'Speedster SWFT309 AMD Radeon RX 6700XT', 'RX-67XTYJFDR', 5, 409.99),
(35, 'Speedster SWFT319 AMD Radeon RX 6800', 'RX-68XLAQBDR', 7, 439.99),
(35, 'Speedster QICK308 AMD Radeon RX 7600', 'RX-76PQICKBY', 3, 279.99),
(14, 'Radeon RX 7600 XT Gaming OC', 'GV-R76XTGAMING OC-16GD', 4, 329.99),
(14, 'Radeon RX 7700XT Gaming OC', 'GV-R77XTGAMING OC-12GD', 6, 419.99),
(14, 'Radeon RX 7800XT Gaming OC', 'GV-R78XTGAMING OC-16GD', 6, 499.99),
(35, 'Speedster MERC310 AMD Radeon RX 7900XT', 'RX-79TMERCUR', 13, 819.99);

insert into motherboard (brand_id, product_name, model, socket_id, form_factor_id, ram_type_id, price) values
(14, 'B760I Aorus Pro DDR4', 'B760IAORUSPRODD', 3, 1, 1, 209.99),
(5, 'Z790-I ROG Strix Gaming WiFi', 'STXZ790-IGMWF', 3, 1, 2, 469.99),
(20, 'B760M-P Pro', 'PROB760MPD4MATX', 3, 2, 1, 99.99),
(5, 'Z790M-Plus Prime', 'PRIMEZ790MPLUS', 3, 2, 2, 204.99),
(5, 'Z790-E ROG Strix Gaming WiFi', 'STXZ790EGWF', 3, 3, 2, 399.99),
(14, 'Z790 Aorus Pro X', 'Z790AORUSPROX', 3, 3, 2, 389.99),
(4, 'Z790 Nova WiFi', 'MBZ790NOVAWIFI', 3, 3, 2, 329.99),
(20, 'Z790 MPG Edge Ti Max Wifi', 'MPGZ790EDGETIMA', 3, 3, 2, 329.99),
(20, 'Z790 MAG Tomahawk WiFi', 'MAGZ790TOMWFD4', 3, 3, 1, 289.99),
(20, 'Z690-A Pro WiFi', 'PRO Z690-A WIFI', 3, 3, 2, 229.99),
(4, 'Z790 PG Lightning D4', 'MBZ790PGLIGHTNI', 3, 3, 1, 199.99),
(14, 'A520I AC', 'A520IAC', 1, 1, 1, 119.99),
(4, 'B550M-ITX/ac', 'MB-B550M-ITX/AC', 1, 1, 1, 129.99),
(5, 'B650E-I ROG Strix Gaming WiFi', 'ROGSTXB650EIGM', 2, 1, 2, 329.99),
(20, 'MPG B650I EDGE WiFi', 'MPG B650I EDGE WIFI', 2, 1, 2, 299.99),
(5, 'Prime B450M-A II', 'PRIME B450M-A II', 1, 2, 1, 79.99),
(5, 'B650M-P Pro', 'PROB650MPMATX', 2, 2, 2, 119.99),
(5, 'B650M-PLUS TUF Gaming WiFi', 'TUFB650MPLUSWF', 2, 2, 2, 219.99),
(4, 'B650M PG Riptide', 'MBB650MPGRIPTID', 2, 2, 2, 169.99),
(5, 'B550-PLUS TUF Gaming WiFi II', 'TUFB550PLUSWF2', 1, 3, 1, 169.99),
(14, 'B550 Aorus ELITE AX V2', 'V2B550AORELAX', 1, 3, 1, 169.99),
(20, 'X670E MAG Tomahawk WiFi', 'MAGX670ETOMAHAW', 2, 3, 2, 279.99),
(14, 'B650 Gaming X AX V2', 'B650GAMXAXV2', 2, 3, 2, 199.99);

insert into ram (brand_id, product_name, model, ram_type_id, capacity_gb, num_of_sticks, rgb, price) values
(13, 'Ripjaws V 16GB', '3200C16D16GVKB', 1, 16, 2, false, 59.99),
(8, 'Vengeance LPX 64GB', 'CMK64GX4M2E3200', 1, 64, 2, false, 164.99),
(8, 'Vengeance RGB Pro 64GB', 'CMW64GX4M2D3600', 1, 64, 2, true, 234.99),
(30, 'T-FORCE VULCAN Z 32GB', 'TLZGD432G3200H', 1, 32, 2, false, 79.99),
(24, 'Viper Steel RGB 16GB', 'PVSR416G360C8K', 1, 16, 2, true, 59.99),
(8, 'Dominator Platinum RGB AMD Optimized 32GB', 'CMT32GX4M2E3200', 1, 32, 2, true, 111.99),
(13, 'Trident Z RGB 16GB', 'F4320016D16GTZR', 1, 16, 2, true, 79.99),
(33, 'Pro 64GB Kit', 'CP2K32G4DFRA32A', 1, 64, 2, false, 169.99),
(13, 'Flare X5 Series 64GB', '6000J302X32FX5', 2, 64, 2, false, 279.99),
(13, 'Trident Z5 Neo RGB 32GB', '6400J322X165NRW', 2, 32, 2, false, 179.99),
(30, 'T-Force Delta RGB 32GB', 'FF4D532G6000HC', 2, 32, 2, true, 169.99),
(8, 'Vengeance RGB 32GB', 'CMH32GX5M2B56WK', 2, 32, 2, true, 159.99),
(8, 'Vengeance 64GB', 'CMK64GX5M2B6000', 2, 64, 2, false, 249.99),
(8, 'Dominator Titanium RGB 96GB', '96GX5M2B6600C32', 2, 96, 2, true, 489.99),
(13, 'Ripjaws S5 Series 32GB', '6000J322X16RS5K', 2, 32, 2, false, 149.99),
(30, 'T-Force Xtreem Overclocking 48GB', 'FFXD548G8200HC3', 2, 48, 2, false, 319.99);

insert into psu (brand_id, product_name, model, psu_wattage_id, cable_type, energy_efficiency, price) values
(8, 'RM750e', 'CP-9020262-NA', 7, 'Fully Modular', '80 Plus Gold', 99.99),
(23, 'C850', 'PA-8G1BB-US', 8, 'Fully Modular', '80 Plus Gold', 139.99),
(31, 'Toughpower GX2', 'PSTPD0600NNFAGU', 4, 'Non-Modular', '80 Plus Gold', 69.99),
(31, 'Smart Series', 'PS-SPD-0700NPCW', 6, 'Non-Modular', '80 Plus', 59.99),
(10, '850W Modular BQ Power Supply', '110-BQ-0850-V1', 8, 'Semi-Modular', '80 Plus Bronze', 119.99),
(20, 'MPG A1000G PCIE5', 'MPGA1000GPCIE5', 9, 'Fully Modular', '80 Plus Gold', 199.99),
(10, 'SuperNOVA 1000 GT', '220-GT-1000-X1', 9, 'Fully Modular', '80 Plus Gold', 189.99),
(8, 'RMx Series RM1000x', 'CP-9020201-NA', 9, 'Fully Modular', '80 Plus Gold', 189.99),
(8, 'CX-M Series CX750M', 'CP-9020222-NA', 7, 'Semi-Modular', '80 Plus Bronze', 89.99),
(8, 'HXi Series', 'CP-9020259-NA', 9, 'Fully-Modular', '80 Plus Platinum', 259.99),
(10, '650 BP', '100-BP-0650-K1', 5, 'Non-Modular', '80 Plus Bronze', 74.99),
(20, 'MAG A750GL PCIE5', 'MAG A750GL PCIE', 7, 'Fully Modular', '80 Plus Gold', 99.99),
(26, 'Power Supply Fixed Cable', 'PS500WF', 2, 'Non-Modular', '80 Plus', 49.99),
(5, 'TUF Gaming 750 Watt 80 Plus Gold', 'TUF-GAMING-750G', 7, 'Fully Modular', '80 Plus Gold', 134.99),
(34, 'USA Focus V3 GX-750', 'SSR-750FX3', 7, 'Fully Modular', '80 Plus Gold', 129.99),
(34, 'USA Focus V3 GX-850', 'SSR-850FX3', 8, 'Fully Modular', '80 Plus Gold', 159.99),
(18, 'SP850', 'G89.SP850W.01US', 8, 'Fully Modular', '80 Plus Gold', 149.99),
(13, '750 Watt 80 Plus Gold ATX', 'GPX750S', 7, 'Fully Modular', '80 Plus Gold', 139.99);

insert into pc_case (brand_id, product_name, model, form_factor_id, color, rgb, length_mm, width_mm, num_fans_included, price) values
(8, 'iCue 5000D RGB AirFlow Tempered Glass', 'CC-9011242-WW', 3, 'Black', true, 520, 245, 3, 219.99),
(23, 'H6 FlowRGB Tempered Glass', 'CC-H61FW-R1', 3, 'White', true, 414, 284, 3, 134.99),
(7, 'MasterBox Q300L', 'MCB-Q300L-KANN', 2, 'Black', false, 386, 230, 1, 39.99),
(5, 'ROG Hyperion GR701 Tempered Glass', 'GR701/BK/PWMFAN', 4, 'Black', false, 659, 268, 4, 499.99),
(18, 'O11 Dynamic EVO XL RGB Tempered Glass', 'G99.O11DEXL-X.0', 4, 'Black', true, 522, 304, 0, 234.99);

