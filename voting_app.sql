-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 06-10-2018 a las 05:06:33
-- Versión del servidor: 5.7.21
-- Versión de PHP: 5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `voting_app`
--
CREATE DATABASE IF NOT EXISTS `voting_app` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `voting_app`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `candidates`
--

DROP TABLE IF EXISTS `candidates`;
CREATE TABLE IF NOT EXISTS `candidates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `photo` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `politic_group_id` int(11) NOT NULL,
  `citizen_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `citizen_candidate_idx` (`citizen_id`),
  KEY `politic_group_candidate` (`politic_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `candidates`
--

INSERT INTO `candidates` (`id`, `photo`, `politic_group_id`, `citizen_id`) VALUES
(3, 'Default-user.png', 1, 31),
(8, 'Default-user.png', 3, 41);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `candidates_for_cities`
--

DROP TABLE IF EXISTS `candidates_for_cities`;
CREATE TABLE IF NOT EXISTS `candidates_for_cities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `candidate_id` int(11) NOT NULL,
  `electoral_process_id` int(11) NOT NULL,
  `city_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `candidate_candidateforcities_idx` (`candidate_id`),
  KEY `electoral_process_candidateforcities_idx` (`electoral_process_id`),
  KEY `city_candidateforcities_idx` (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `candidates_for_cities`
--

INSERT INTO `candidates_for_cities` (`id`, `candidate_id`, `electoral_process_id`, `city_id`) VALUES
(10, 3, 4, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cities`
--

DROP TABLE IF EXISTS `cities`;
CREATE TABLE IF NOT EXISTS `cities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `deparment_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `department_city_idx` (`deparment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=270 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `cities`
--

INSERT INTO `cities` (`id`, `name`, `deparment_id`) VALUES
(1, 'Ahuachapan', 2),
(2, 'Jujutla', 1),
(3, 'Atiquizaya', 1),
(4, 'Concepci', 1),
(5, 'El Refugio', 1),
(6, 'Guaymango', 1),
(7, 'Apaneca', 1),
(8, 'San Francisco Men', 1),
(9, 'San Lorenzo', 1),
(10, 'San Pedro Puxtla', 1),
(11, 'Tacuba', 1),
(12, 'Tur', 1),
(13, 'Candelaria de la Frontera', 2),
(14, 'Chalchuapa', 2),
(15, 'Coatepeque', 2),
(16, 'El Congo', 2),
(17, 'El Porvenir', 2),
(18, 'Masahuat', 2),
(19, 'Metap', 2),
(20, 'San Antonio Pajonal', 2),
(21, 'San Sebasti', 2),
(22, 'Santa Ana', 2),
(23, 'Santa Rosa Guachipil', 2),
(24, 'Santiago de la Frontera', 2),
(25, 'Texistepeque', 2),
(26, 'Acajutla', 3),
(27, 'Armenia', 3),
(28, 'Caluco', 3),
(29, 'Cuisnahuat', 3),
(30, 'Izalco', 3),
(31, 'Juay', 3),
(32, 'Nahuizalco', 3),
(33, 'Nahulingo', 3),
(34, 'Salcoatit', 3),
(35, 'San Antonio del Monte', 3),
(36, 'San Juli', 3),
(37, 'Santa Catarina Masahuat', 3),
(38, 'Santa Isabel Ishuat', 3),
(39, 'Santo Domingo de Guzm', 3),
(40, 'Sonsonate', 3),
(41, 'Sonzacate', 3),
(42, 'Alegr', 11),
(43, 'Berl', 11),
(44, 'California', 11),
(45, 'Concepci', 11),
(46, 'El Triunfo', 11),
(47, 'Ereguayqu', 11),
(48, 'Estanzuelas', 11),
(49, 'Jiquilisco', 11),
(50, 'Jucuapa', 11),
(51, 'Jucuar', 11),
(52, 'Mercedes Uma', 11),
(53, 'Nueva Granada', 11),
(54, 'Ozatl', 11),
(55, 'Puerto El Triunfo', 11),
(56, 'San Agust', 11),
(57, 'San Buenaventura', 11),
(58, 'San Dionisio', 11),
(59, 'San Francisco Javier', 11),
(60, 'Santa Elena', 11),
(61, 'Santa Mar', 11),
(62, 'Santiago de Mar', 11),
(63, 'Tecap', 11),
(64, 'Usulut', 11),
(65, 'Carolina', 13),
(66, 'Chapeltique', 13),
(67, 'Chinameca', 13),
(68, 'Chirilagua', 13),
(69, 'Ciudad Barrios', 13),
(70, 'Comacar', 13),
(71, 'El Tr', 13),
(72, 'Lolotique', 13),
(73, 'Moncagua', 13),
(74, 'Nueva Guadalupe', 13),
(75, 'Nuevo Ed', 13),
(76, 'Quelepa', 13),
(77, 'San Antonio del Mosco', 13),
(78, 'San Gerardo', 13),
(79, 'San Jorge', 13),
(80, 'San Luis de la Reina', 13),
(81, 'San Miguel', 13),
(82, 'San Rafael Oriente', 13),
(83, 'Sesori', 13),
(84, 'Uluazapa', 13),
(85, 'Arambala', 12),
(86, 'Cacaopera', 12),
(87, 'Chilanga', 12),
(88, 'Corinto', 12),
(89, 'Delicias de Concepci', 12),
(90, 'El Divisadero', 12),
(91, 'El Rosario (Moraz', 12),
(92, 'Gualococti', 12),
(93, 'Guatajiagua', 12),
(94, 'Joateca', 12),
(95, 'Jocoaitique', 12),
(96, 'Jocoro', 12),
(97, 'Lolotiquillo', 12),
(98, 'Meanguera', 12),
(99, 'Osicala', 12),
(100, 'Perqu', 12),
(101, 'San Carlos', 12),
(102, 'San Fernando (Moraz', 12),
(103, 'San Francisco Gotera', 12),
(104, 'San Isidro (Moraz', 12),
(105, 'San Sim', 12),
(106, 'Sensembra', 12),
(107, 'Sociedad', 12),
(108, 'Torola', 12),
(109, 'Yamabal', 12),
(110, 'Yoloaiqu', 12),
(111, 'La Uni', 14),
(112, 'San Alejo', 14),
(113, 'Yucuaiqu', 14),
(114, 'Conchagua', 14),
(115, 'Intipuc', 14),
(116, 'San Jos', 14),
(117, 'El Carmen (La Uni', 14),
(118, 'Yayantique', 14),
(119, 'Bol', 14),
(120, 'Meanguera del Golfo', 14),
(121, 'Santa Rosa de Lima', 14),
(122, 'Pasaquina', 14),
(123, 'Anamoros', 14),
(124, 'Nueva Esparta', 14),
(125, 'El Sauce', 14),
(126, 'Concepci', 14),
(127, 'Polor', 14),
(128, 'Lislique', 14),
(129, 'Antiguo Cuscatl', 4),
(130, 'Chiltiup', 4),
(131, 'Ciudad Arce', 4),
(132, 'Col', 4),
(133, 'Comasagua', 4),
(134, 'Huiz', 4),
(135, 'Jayaque', 4),
(136, 'Jicalapa', 4),
(137, 'La Libertad', 4),
(138, 'Santa Tecla', 4),
(139, 'Nuevo Cuscatl', 4),
(140, 'San Juan Opico', 4),
(141, 'Quezaltepeque', 4),
(142, 'Sacacoyo', 4),
(143, 'San Jos', 4),
(144, 'San Mat', 4),
(145, 'San Pablo Tacachico', 4),
(146, 'Talnique', 4),
(147, 'Tamanique', 4),
(148, 'Teotepeque', 4),
(149, 'Tepecoyo', 4),
(150, 'Zaragoza', 4),
(151, 'Agua Caliente', 5),
(152, 'Arcatao', 5),
(153, 'Azacualpa', 5),
(154, 'Cancasque', 5),
(155, 'Chalatenango', 5),
(156, 'Cital', 5),
(157, 'Comapala', 5),
(158, 'Concepci', 5),
(159, 'Dulce Nombre de Mar', 5),
(160, 'El Carrizal', 5),
(161, 'El Para', 5),
(162, 'La Laguna', 5),
(163, 'La Palma', 5),
(164, 'La Reina', 5),
(165, 'Las Vueltas', 5),
(166, 'Nueva Concepci', 5),
(167, 'Nueva Trinidad', 5),
(168, 'Nombre de Jes', 5),
(169, 'Ojos de Agua', 5),
(170, 'Potonico', 5),
(171, 'San Antonio de la Cruz', 5),
(172, 'San Antonio Los Ranchos', 5),
(173, 'San Fernando (Chalatenango)', 5),
(174, 'San Francisco Lempa', 5),
(175, 'San Francisco Moraz', 5),
(176, 'San Ignacio', 5),
(177, 'San Isidro Labrador', 5),
(178, 'Las Flores', 5),
(179, 'San Luis del Carmen', 5),
(180, 'San Miguel de Mercedes', 5),
(181, 'San Rafael', 5),
(182, 'Santa Rita', 5),
(183, 'Tejutla', 5),
(184, 'Cojutepeque', 7),
(185, 'Candelaria', 7),
(186, 'El Carmen (Cuscatl', 7),
(187, 'El Rosario (Cuscatl', 7),
(188, 'Monte San Juan', 7),
(189, 'Oratorio de Concepci', 7),
(190, 'San Bartolom', 7),
(191, 'San Crist', 7),
(192, 'San Jos', 7),
(193, 'San Pedro Perulap', 7),
(194, 'San Rafael Cedros', 7),
(195, 'San Ram', 7),
(196, 'Santa Cruz Analquito', 7),
(197, 'Santa Cruz Michapa', 7),
(198, 'Suchitoto', 7),
(199, 'Tenancingo', 7),
(200, 'Aguilares', 6),
(201, 'Apopa', 6),
(202, 'Ayutuxtepeque', 6),
(203, 'Cuscatancingo', 6),
(204, 'Ciudad Delgado', 6),
(205, 'El Paisnal', 6),
(206, 'Guazapa', 6),
(207, 'Ilopango', 6),
(208, 'Mejicanos', 6),
(209, 'Nejapa', 6),
(210, 'Panchimalco', 6),
(211, 'Rosario de Mora', 6),
(212, 'San Marcos', 6),
(213, 'San Mart', 6),
(214, 'San Salvador', 6),
(215, 'Santiago Texacuangos', 6),
(216, 'Santo Tom', 6),
(217, 'Soyapango', 6),
(218, 'Tonacatepeque', 6),
(219, 'Zacatecoluca', 8),
(220, 'Cuyultit', 8),
(221, 'El Rosario (La Paz)', 8),
(222, 'Jerusal', 8),
(223, 'Mercedes La Ceiba', 8),
(224, 'Olocuilta', 8),
(225, 'Para', 8),
(226, 'San Antonio Masahuat', 8),
(227, 'San Emigdio', 8),
(228, 'San Francisco Chinameca', 8),
(229, 'San Pedro Masahuat', 8),
(230, 'San Juan Nonualco', 8),
(231, 'San Juan Talpa', 8),
(232, 'San Juan Tepezontes', 8),
(233, 'San Luis La Herradura', 8),
(234, 'San Luis Talpa', 8),
(235, 'San Miguel Tepezontes', 8),
(236, 'San Pedro Nonualco', 8),
(237, 'San Rafael Obrajuelo', 8),
(238, 'Santa Mar', 8),
(239, 'Santiago Nonualco', 8),
(240, 'Tapalhuaca', 8),
(241, 'Cinquera', 9),
(242, 'Dolores', 9),
(243, 'Guacotecti', 9),
(244, 'Ilobasco', 9),
(245, 'Jutiapa', 9),
(246, 'San Isidro (Caba', 9),
(247, 'Sensuntepeque', 9),
(248, 'Tejutepeque', 9),
(249, 'Victoria', 9),
(250, 'Apastepeque', 10),
(251, 'Guadalupe', 10),
(252, 'San Cayetano Istepeque', 10),
(253, 'San Esteban Catarina', 10),
(254, 'San Ildefonso', 10),
(255, 'San Lorenzo', 10),
(256, 'San Sebasti', 10),
(257, 'San Vicente', 10),
(258, 'Santa Clara', 10),
(259, 'Santo Domingo', 10),
(260, 'Tecoluca', 10),
(261, 'Tepetit', 10),
(262, 'Verapaz', 10),
(263, ' Antiguo Cuscatlán', 15),
(264, 'Antiguo Cuscatlán', 4),
(266, 'Nuevecito', 2),
(267, 'sdfsdf', 15),
(268, 'sdfsdfsdf', 16),
(269, 'dsfcxvxc', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cities_admins`
--

DROP TABLE IF EXISTS `cities_admins`;
CREATE TABLE IF NOT EXISTS `cities_admins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department_id` int(11) NOT NULL,
  `citizen_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cities_admins_departments1_idx` (`department_id`),
  KEY `fk_cities_admins_citizen1_idx` (`citizen_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `citizens`
--

DROP TABLE IF EXISTS `citizens`;
CREATE TABLE IF NOT EXISTS `citizens` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `lastname` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `password` varchar(64) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `dui` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `adress` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `birthdate` date NOT NULL,
  `citizen_type_id` varchar(6) COLLATE utf8_spanish2_ci NOT NULL,
  `headquarter_id` int(11) NOT NULL,
  `state` tinyint(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dui_UNIQUE` (`dui`),
  KEY `citizen_type_idx` (`citizen_type_id`),
  KEY `headquarter_citizen_idx` (`headquarter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `citizens`
--

INSERT INTO `citizens` (`id`, `name`, `lastname`, `password`, `dui`, `adress`, `birthdate`, `citizen_type_id`, `headquarter_id`, `state`) VALUES
(1, 'Guillermo Armando', 'Calderon Sola', '01ffe8b0e842ab31e98d82a160af7018f33240e011f9474f97bd2d19519cc373', '13742204-5', 'Escalon', '1993-11-25', 'PREJRV', 3, 1),
(2, 'Diego Alberto', 'Lemus Torres', '12346', '87654321-9', 'El Salvador, Avenida Bernal', '2018-07-04', 'EMRNPN', 1, 1),
(23, 'Pedro Parolo', 'Calderon', NULL, '13742204-2', 'Bernal', '1992-12-29', 'CITIZN', 3, 1),
(24, 'Franklin Pacacho', 'Castillo Cuadra', NULL, '12345679-1', 'Escalon', '1992-11-30', 'CITIZN', 3, 1),
(25, 'Jorge Antonio', 'Flores Ca', NULL, '12345679-4', 'Reparto las Chinamas casa #4', '1992-09-30', 'CITIZN', 1, 1),
(26, 'Luis Ernesto', 'Suarez Ramirez', '6dc830adc721b8bb9bbdf4bed0f00434519b3fa5247abc79d897462c327716cd', '01234567-2', 'Colonia Barcelona casa #1', '1994-03-30', 'PREJRV', 4, 1),
(27, 'Carlos Alexander', 'Lemus Guardado', '8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92', '12345678-9', 'Colonia Buenavista', '1998-03-31', 'EMRNPN', 1, 1),
(28, 'Mario Hugo', 'Mojica', '12345637-8', '12345637-8', 'San Salvador', '1997-07-29', 'CITIZN', 1, NULL),
(29, 'Alexander', 'Mojica', '12345667-8', '12345667-8', 'San Salvador', '1987-09-12', 'CITIZN', 1, NULL),
(30, 'Carlos Alexander', 'Lemus Guardado', '12345677-8', '12341234-8', 'Nueva Direccion', '1998-09-10', 'CITIZN', 1, NULL),
(31, 'Alexander', 'Mojica', '12345617-6', '12345617-6', 'San Salvador', '1998-08-30', 'CITIZN', 1, NULL),
(32, 'Pablo', 'Mojica', '12345267-8', '12345267-8', 'San Salvador', '1988-08-28', 'CITIZN', 4, NULL),
(33, 'Alexander', 'Mojica', '12345427-8', '12345427-8', 'San D', '1977-09-13', 'CITIZN', 1, NULL),
(34, 'Alexander Pedro', 'Mojica', '12234567-8', '12234567-8', 'San Salvador', '1978-09-04', 'CITIZN', 1, NULL),
(35, 'Gerardo Barrios', 'Mojica', '42234567-8', '42234567-8', 'San Salvador', '1998-10-01', 'CITIZN', 1, NULL),
(36, 'Alexander', 'Mojica', NULL, '12355678-9', 'San Salvador', '2018-10-01', 'CITIZN', 4, 0),
(37, 'Jorge', 'Nitales', '12345677-8', '12343677-8', '742 Avenida Siempreviva, Springfield. El nombre de la calle es Evergreen Terrace', '1998-09-29', 'CITIZN', 12, NULL),
(38, 'Alexander', 'Mojica', '7d4b8219fe08dd801b5580b075e8fa61ae9d13b4a2b006340dffdc260f3ba181', '12341234-5', 'San Salvador', '2018-10-10', 'PREJRV', 4, 1),
(39, 'Alexander', 'Mojica', '66677788-9', '66677788-9', 'San Salvador', '1998-10-01', 'CITIZN', 4, NULL),
(40, 'Alexander', 'Mojica', '12342567-8', '12342567-8', 'San Salvador', '1998-10-17', 'CITIZN', 1, NULL),
(41, 'Jotgr', 'Mojica', '23224224-2', '23224224-2', 'San Salvador', '0988-11-10', 'CITIZN', 12, NULL),
(42, 'Alexander', 'Mojica', '0a044c7ed2228b064061423a8030f084ba9ba98a1a98677bba86f4635f6357d6', '12341214-8', 'San Salvador', '1988-10-03', 'ADMGEN', 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `citizen_types`
--

DROP TABLE IF EXISTS `citizen_types`;
CREATE TABLE IF NOT EXISTS `citizen_types` (
  `id` varchar(6) COLLATE utf8_spanish2_ci NOT NULL,
  `description` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `citizen_types`
--

INSERT INTO `citizen_types` (`id`, `description`) VALUES
('ADMDEP', 'Administrador Departamental'),
('ADMGEN', 'Administrador General'),
('CITIZN', 'Ciudadano'),
('EMRNPN', 'Empleado RNPN'),
('PREJRV', 'Presidente Jrv');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `citizen_votes`
--

DROP TABLE IF EXISTS `citizen_votes`;
CREATE TABLE IF NOT EXISTS `citizen_votes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` tinyint(4) DEFAULT NULL,
  `electoral_process_id` int(11) NOT NULL,
  `citizen_id` int(11) NOT NULL,
  `jrv_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `electoral_process_citizen_votes_idx` (`electoral_process_id`),
  KEY `citizen_citizen_votes_idx` (`citizen_id`),
  KEY `jrv_citizen_vote_idx` (`jrv_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departments`
--

DROP TABLE IF EXISTS `departments`;
CREATE TABLE IF NOT EXISTS `departments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `departments`
--

INSERT INTO `departments` (`id`, `name`) VALUES
(1, 'Ahuachap'),
(2, 'Santa Ana'),
(3, 'Sonsonate'),
(4, 'La Libertad'),
(5, 'Chalatenango'),
(6, 'San Salvador'),
(7, 'Cuscatl'),
(8, 'La Paz'),
(9, 'Caba'),
(10, 'San Vicente'),
(11, 'Usulut'),
(12, 'Moraz'),
(13, 'San Miguel'),
(14, 'La Uni'),
(15, ' La Libertad'),
(16, 'Nuevo'),
(17, 'dsfsdfgdv bcv cvb n');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `electoral_process`
--

DROP TABLE IF EXISTS `electoral_process`;
CREATE TABLE IF NOT EXISTS `electoral_process` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `name` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `year` varchar(4) COLLATE utf8_spanish2_ci NOT NULL,
  `init_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `process_date` datetime NOT NULL,
  `electoral_process_status_id` int(11) NOT NULL,
  `electoral_process_types_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `electoralprocess_status_idx` (`electoral_process_status_id`),
  KEY `electoralprocess_types_idx` (`electoral_process_types_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `electoral_process`
--

INSERT INTO `electoral_process` (`id`, `code`, `name`, `year`, `init_date`, `end_date`, `process_date`, `electoral_process_status_id`, `electoral_process_types_id`) VALUES
(1, 'LG171619', 'Carlo9s', '2018', '2018-09-27 19:00:00', '2018-09-26 18:00:00', '2018-09-01 19:00:00', 2, 2),
(3, 'MG151412', 'Nuevo proceso', '2018', '2018-09-29 19:00:00', '2018-10-26 19:00:00', '2018-09-08 19:00:00', 2, 2),
(4, 'LG43619', 'Mekaku Electoral', '2018', '2018-09-28 19:00:00', '2018-10-26 18:00:00', '2018-09-19 19:00:00', 3, 1),
(6, 'LG171615', 'Proceso electoral', '2018', '2018-10-02 19:00:00', '2018-10-26 19:00:00', '2018-10-23 19:00:00', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `electoral_process_status`
--

DROP TABLE IF EXISTS `electoral_process_status`;
CREATE TABLE IF NOT EXISTS `electoral_process_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `electoral_process_status`
--

INSERT INTO `electoral_process_status` (`id`, `description`) VALUES
(1, 'Registro Candidatos y JRV'),
(2, 'Fin Registro'),
(3, 'Proceso Electoral Activo'),
(4, 'Fin Proceso Electoral');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `electoral_process_types`
--

DROP TABLE IF EXISTS `electoral_process_types`;
CREATE TABLE IF NOT EXISTS `electoral_process_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `electoral_process_types`
--

INSERT INTO `electoral_process_types` (`id`, `description`) VALUES
(1, 'Presidencial'),
(2, 'Consejos Municipales');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `headquarters`
--

DROP TABLE IF EXISTS `headquarters`;
CREATE TABLE IF NOT EXISTS `headquarters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `x` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `y` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `city_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `headquarters_city_idx` (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `headquarters`
--

INSERT INTO `headquarters` (`id`, `name`, `x`, `y`, `city_id`) VALUES
(1, 'Centro Escolar Reparto Las Guacinamas', '314321', '432251', 2),
(3, 'Centro Escolar la palma', '54357', '21317', 2),
(4, 'Colegio Costa Buena', '131463', '83953', 1),
(12, 'Universidad Don Bosco', '-89.236786', '13.674076', 264),
(13, 'Instituto Técnico Ricaldone', '-89.20518', '13.723018', 214);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jrv`
--

DROP TABLE IF EXISTS `jrv`;
CREATE TABLE IF NOT EXISTS `jrv` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(64) COLLATE utf8_spanish2_ci NOT NULL,
  `headquarter_id` int(11) NOT NULL,
  `electoral_process_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `headquarter_jrv_idx` (`headquarter_id`),
  KEY `electoral_process_jrv_idx` (`electoral_process_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `jrv`
--

INSERT INTO `jrv` (`id`, `code`, `headquarter_id`, `electoral_process_id`) VALUES
(1, 'german', 3, 1),
(7, 'JRV12346', 1, 4),
(9, 'g123212', 1, 6),
(11, 'german12', 1, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jrv_citizen`
--

DROP TABLE IF EXISTS `jrv_citizen`;
CREATE TABLE IF NOT EXISTS `jrv_citizen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jrv_id` int(11) NOT NULL,
  `citizen_id` int(11) NOT NULL,
  `jrv_citizen_type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `jrvcitizen_jrv_idx` (`jrv_id`),
  KEY `jrvcitizen_citizentype_idx` (`jrv_citizen_type_id`),
  KEY `citizen_jrvcitizen_idx` (`citizen_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `jrv_citizen`
--

INSERT INTO `jrv_citizen` (`id`, `jrv_id`, `citizen_id`, `jrv_citizen_type_id`) VALUES
(28, 7, 26, 1),
(29, 7, 23, 3),
(30, 7, 25, 2),
(31, 9, 24, 2),
(32, 11, 1, 1),
(33, 9, 38, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jrv_citizen_types`
--

DROP TABLE IF EXISTS `jrv_citizen_types`;
CREATE TABLE IF NOT EXISTS `jrv_citizen_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `description` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `jrv_citizen_types`
--

INSERT INTO `jrv_citizen_types` (`id`, `name`, `description`) VALUES
(1, 'presidente', 'Miembro principal de la junta de votos'),
(2, 'secretario ', '\r\nPersona que está empleada en una oficina '),
(3, 'vocal ', 'De la voz, expresado con la voz'),
(4, 'Participantes', 'Que participa en un suceso');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `politic_groups`
--

DROP TABLE IF EXISTS `politic_groups`;
CREATE TABLE IF NOT EXISTS `politic_groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `acronym` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `description` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `photo` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `politic_groups`
--

INSERT INTO `politic_groups` (`id`, `name`, `acronym`, `description`, `photo`) VALUES
(1, 'Arena', 'ARENA', 'Arena', 'arena.png'),
(3, 'farabundo marti liberacion nacional', 'FMLN', 'Es de color rojo', 'TcYv3gQ.jpg'),
(4, 'farabundo marti liberacion nacional2', 'FMLN2', 'Es de color rojo', 'TcYv3gQ.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `politic_group_votes`
--

DROP TABLE IF EXISTS `politic_group_votes`;
CREATE TABLE IF NOT EXISTS `politic_group_votes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `votes` int(11) DEFAULT NULL,
  `politic_group_id` int(11) NOT NULL,
  `jrv_id` int(11) NOT NULL,
  `electoral_process_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `politic_group_politic_group_votes_idx` (`politic_group_id`),
  KEY `jrv_politic_group_votes_idx` (`jrv_id`),
  KEY `electoral_process_politic_group_votes_idx` (`electoral_process_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `politic_group_votes`
--

INSERT INTO `politic_group_votes` (`id`, `votes`, `politic_group_id`, `jrv_id`, `electoral_process_id`) VALUES
(1, 12, 1, 7, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `presidencial_candidates`
--

DROP TABLE IF EXISTS `presidencial_candidates`;
CREATE TABLE IF NOT EXISTS `presidencial_candidates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `candidates_id` int(11) NOT NULL,
  `electoral_process_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `candidates_presidencia_candidates_idx` (`candidates_id`),
  KEY `electoral_process_presidencial_candidate_idx` (`electoral_process_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sequence`
--

DROP TABLE IF EXISTS `sequence`;
CREATE TABLE IF NOT EXISTS `sequence` (
  `SEQ_NAME` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', '0');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `candidates`
--
ALTER TABLE `candidates`
  ADD CONSTRAINT `citizen_candidate` FOREIGN KEY (`citizen_id`) REFERENCES `citizens` (`id`),
  ADD CONSTRAINT `politic_group_candidate` FOREIGN KEY (`politic_group_id`) REFERENCES `politic_groups` (`id`);

--
-- Filtros para la tabla `candidates_for_cities`
--
ALTER TABLE `candidates_for_cities`
  ADD CONSTRAINT `candidate_candidateforcities` FOREIGN KEY (`candidate_id`) REFERENCES `candidates` (`id`),
  ADD CONSTRAINT `city_candidateforcities` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`),
  ADD CONSTRAINT `electoral_process_candidateforcities` FOREIGN KEY (`electoral_process_id`) REFERENCES `electoral_process` (`id`);

--
-- Filtros para la tabla `cities`
--
ALTER TABLE `cities`
  ADD CONSTRAINT `department_city` FOREIGN KEY (`deparment_id`) REFERENCES `departments` (`id`);

--
-- Filtros para la tabla `cities_admins`
--
ALTER TABLE `cities_admins`
  ADD CONSTRAINT `fk_cities_admins_citizen1` FOREIGN KEY (`citizen_id`) REFERENCES `citizens` (`id`),
  ADD CONSTRAINT `fk_cities_admins_departments1` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`);

--
-- Filtros para la tabla `citizens`
--
ALTER TABLE `citizens`
  ADD CONSTRAINT `citizen_type` FOREIGN KEY (`citizen_type_id`) REFERENCES `citizen_types` (`id`),
  ADD CONSTRAINT `headquarter_citizen` FOREIGN KEY (`headquarter_id`) REFERENCES `headquarters` (`id`);

--
-- Filtros para la tabla `citizen_votes`
--
ALTER TABLE `citizen_votes`
  ADD CONSTRAINT `citizen_citizen_votes` FOREIGN KEY (`citizen_id`) REFERENCES `citizens` (`id`),
  ADD CONSTRAINT `electoral_process_citizen_votes` FOREIGN KEY (`electoral_process_id`) REFERENCES `electoral_process` (`id`),
  ADD CONSTRAINT `jrv_citizen_vote` FOREIGN KEY (`jrv_id`) REFERENCES `jrv` (`id`);

--
-- Filtros para la tabla `electoral_process`
--
ALTER TABLE `electoral_process`
  ADD CONSTRAINT `electoralprocess_status` FOREIGN KEY (`electoral_process_status_id`) REFERENCES `electoral_process_status` (`id`),
  ADD CONSTRAINT `electoralprocess_types` FOREIGN KEY (`electoral_process_types_id`) REFERENCES `electoral_process_types` (`id`);

--
-- Filtros para la tabla `headquarters`
--
ALTER TABLE `headquarters`
  ADD CONSTRAINT `headquarters_city` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`);

--
-- Filtros para la tabla `jrv`
--
ALTER TABLE `jrv`
  ADD CONSTRAINT `electoral_process_jrv` FOREIGN KEY (`electoral_process_id`) REFERENCES `electoral_process` (`id`),
  ADD CONSTRAINT `headquarter_jrv` FOREIGN KEY (`headquarter_id`) REFERENCES `headquarters` (`id`);

--
-- Filtros para la tabla `jrv_citizen`
--
ALTER TABLE `jrv_citizen`
  ADD CONSTRAINT `citizen_jrvcitizen` FOREIGN KEY (`citizen_id`) REFERENCES `citizens` (`id`),
  ADD CONSTRAINT `jrvcitizen_citizentype` FOREIGN KEY (`jrv_citizen_type_id`) REFERENCES `jrv_citizen_types` (`id`),
  ADD CONSTRAINT `jrvcitizen_jrv` FOREIGN KEY (`jrv_id`) REFERENCES `jrv` (`id`);

--
-- Filtros para la tabla `politic_group_votes`
--
ALTER TABLE `politic_group_votes`
  ADD CONSTRAINT `electoral_process_politic_group_votes` FOREIGN KEY (`electoral_process_id`) REFERENCES `electoral_process` (`id`),
  ADD CONSTRAINT `jrv_politic_group_votes` FOREIGN KEY (`jrv_id`) REFERENCES `jrv` (`id`),
  ADD CONSTRAINT `politic_group_politic_group_votes` FOREIGN KEY (`politic_group_id`) REFERENCES `politic_groups` (`id`);

--
-- Filtros para la tabla `presidencial_candidates`
--
ALTER TABLE `presidencial_candidates`
  ADD CONSTRAINT `electoral_process_presidencial_candidate` FOREIGN KEY (`electoral_process_id`) REFERENCES `electoral_process` (`id`),
  ADD CONSTRAINT `presidencial_candidates_candidates` FOREIGN KEY (`candidates_id`) REFERENCES `candidates` (`id`);

DELIMITER $$
--
-- Eventos
--
DROP EVENT `event_change_state`$$
CREATE DEFINER=`root`@`localhost` EVENT `event_change_state` ON SCHEDULE EVERY 24 HOUR STARTS '2018-10-01 10:52:26' ON COMPLETION NOT PRESERVE ENABLE DO update electoral_process e 
    set e.electoral_process_status_id = 2 
    where e.electoral_process_status_id = 1 
    and e.process_date <= now()$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
