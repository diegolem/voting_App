-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-09-2018 a las 03:55:39
-- Versión del servidor: 10.1.34-MariaDB
-- Versión de PHP: 7.2.8

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `candidates`
--

CREATE TABLE `candidates` (
  `id` int(11) NOT NULL,
  `photo` varchar(45) NOT NULL,
  `politic_group_id` int(11) NOT NULL,
  `citizen_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `candidates_for_cities`
--

CREATE TABLE `candidates_for_cities` (
  `id` int(11) NOT NULL,
  `candidate_id` int(11) NOT NULL,
  `electoral_process_id` int(11) NOT NULL,
  `city_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cities`
--

CREATE TABLE `cities` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `deparment_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `citizen`
--

CREATE TABLE `citizen` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `dui` varchar(10) NOT NULL,
  `adress` varchar(100) NOT NULL,
  `birthdate` date NOT NULL,
  `citizen_type_id` varchar(3) NOT NULL,
  `headquarter_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `citizen_types`
--

CREATE TABLE `citizen_types` (
  `id` varchar(3) NOT NULL,
  `description` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `citizen_votes`
--

CREATE TABLE `citizen_votes` (
  `id` int(11) NOT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `electoral_process_id` int(11) NOT NULL,
  `citizen_id` int(11) NOT NULL,
  `jrv_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departments`
--

CREATE TABLE `departments` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `electoral_process`
--

CREATE TABLE `electoral_process` (
  `id` int(11) NOT NULL,
  `code` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `year` varchar(4) NOT NULL,
  `init_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `process_date` datetime NOT NULL,
  `electoral_process_status_id` int(11) NOT NULL,
  `electoral_process_types_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `electoral_process_status`
--

CREATE TABLE `electoral_process_status` (
  `id` int(11) NOT NULL,
  `description` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `electoral_process_types`
--

CREATE TABLE `electoral_process_types` (
  `id` int(11) NOT NULL,
  `description` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `headquarters`
--

CREATE TABLE `headquarters` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `x` varchar(45) NOT NULL,
  `y` varchar(45) NOT NULL,
  `city_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jrv`
--

CREATE TABLE `jrv` (
  `id` int(11) NOT NULL,
  `code` varchar(64) NOT NULL,
  `headquarter_id` int(11) NOT NULL,
  `electoral_process_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jrv_citizen`
--

CREATE TABLE `jrv_citizen` (
  `id` int(11) NOT NULL,
  `jrv_id` int(11) NOT NULL,
  `citizen_id` int(11) NOT NULL,
  `jrv_citizen_type_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jrv_citizen_types`
--

CREATE TABLE `jrv_citizen_types` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `politic_groups`
--

CREATE TABLE `politic_groups` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `acronym` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  `photo` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `politic_group_votes`
--

CREATE TABLE `politic_group_votes` (
  `id` int(11) NOT NULL,
  `votes` int(11) DEFAULT NULL,
  `politic_group_id` int(11) NOT NULL,
  `jrv_id` int(11) NOT NULL,
  `electoral_process_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `presidencial_candidates`
--

CREATE TABLE `presidencial_candidates` (
  `id` int(11) NOT NULL,
  `candidates_id` int(11) NOT NULL,
  `electoral_process_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `candidates`
--
ALTER TABLE `candidates`
  ADD PRIMARY KEY (`id`),
  ADD KEY `citizen_candidate_idx` (`citizen_id`),
  ADD KEY `politic_group_candidate` (`politic_group_id`);

--
-- Indices de la tabla `candidates_for_cities`
--
ALTER TABLE `candidates_for_cities`
  ADD PRIMARY KEY (`id`),
  ADD KEY `candidate_candidateforcities_idx` (`candidate_id`),
  ADD KEY `electoral_process_candidateforcities_idx` (`electoral_process_id`),
  ADD KEY `city_candidateforcities_idx` (`city_id`);

--
-- Indices de la tabla `cities`
--
ALTER TABLE `cities`
  ADD PRIMARY KEY (`id`),
  ADD KEY `department_city_idx` (`deparment_id`);

--
-- Indices de la tabla `citizen`
--
ALTER TABLE `citizen`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `dui_UNIQUE` (`dui`),
  ADD KEY `citizen_type_idx` (`citizen_type_id`),
  ADD KEY `headquarter_citizen_idx` (`headquarter_id`);

--
-- Indices de la tabla `citizen_types`
--
ALTER TABLE `citizen_types`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `citizen_votes`
--
ALTER TABLE `citizen_votes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `electoral_process_citizen_votes_idx` (`electoral_process_id`),
  ADD KEY `citizen_citizen_votes_idx` (`citizen_id`),
  ADD KEY `jrv_citizen_vote_idx` (`jrv_id`);

--
-- Indices de la tabla `departments`
--
ALTER TABLE `departments`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `electoral_process`
--
ALTER TABLE `electoral_process`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code_UNIQUE` (`code`),
  ADD KEY `electoralprocess_status_idx` (`electoral_process_status_id`),
  ADD KEY `electoralprocess_types_idx` (`electoral_process_types_id`);

--
-- Indices de la tabla `electoral_process_status`
--
ALTER TABLE `electoral_process_status`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `electoral_process_types`
--
ALTER TABLE `electoral_process_types`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `headquarters`
--
ALTER TABLE `headquarters`
  ADD PRIMARY KEY (`id`),
  ADD KEY `headquarters_city_idx` (`city_id`);

--
-- Indices de la tabla `jrv`
--
ALTER TABLE `jrv`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code_UNIQUE` (`code`),
  ADD KEY `headquarter_jrv_idx` (`headquarter_id`),
  ADD KEY `electoral_process_jrv_idx` (`electoral_process_id`);

--
-- Indices de la tabla `jrv_citizen`
--
ALTER TABLE `jrv_citizen`
  ADD PRIMARY KEY (`id`),
  ADD KEY `jrvcitizen_jrv_idx` (`jrv_id`),
  ADD KEY `jrvcitizen_citizentype_idx` (`jrv_citizen_type_id`),
  ADD KEY `citizen_jrvcitizen_idx` (`citizen_id`);

--
-- Indices de la tabla `jrv_citizen_types`
--
ALTER TABLE `jrv_citizen_types`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `politic_groups`
--
ALTER TABLE `politic_groups`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `politic_group_votes`
--
ALTER TABLE `politic_group_votes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `politic_group_politic_group_votes_idx` (`politic_group_id`),
  ADD KEY `jrv_politic_group_votes_idx` (`jrv_id`),
  ADD KEY `electoral_process_politic_group_votes_idx` (`electoral_process_id`);

--
-- Indices de la tabla `presidencial_candidates`
--
ALTER TABLE `presidencial_candidates`
  ADD PRIMARY KEY (`id`),
  ADD KEY `candidates_presidencia_candidates_idx` (`candidates_id`),
  ADD KEY `electoral_process_presidencial_candidate_idx` (`electoral_process_id`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `candidates`
--
ALTER TABLE `candidates`
  ADD CONSTRAINT `citizen_candidate` FOREIGN KEY (`citizen_id`) REFERENCES `citizen` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `politic_group_candidate` FOREIGN KEY (`politic_group_id`) REFERENCES `politic_groups` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `candidates_for_cities`
--
ALTER TABLE `candidates_for_cities`
  ADD CONSTRAINT `candidate_candidateforcities` FOREIGN KEY (`candidate_id`) REFERENCES `candidates` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `city_candidateforcities` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `electoral_process_candidateforcities` FOREIGN KEY (`electoral_process_id`) REFERENCES `electoral_process` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `cities`
--
ALTER TABLE `cities`
  ADD CONSTRAINT `department_city` FOREIGN KEY (`deparment_id`) REFERENCES `departments` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `citizen`
--
ALTER TABLE `citizen`
  ADD CONSTRAINT `citizen_type` FOREIGN KEY (`citizen_type_id`) REFERENCES `citizen_types` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `headquarter_citizen` FOREIGN KEY (`headquarter_id`) REFERENCES `headquarters` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `citizen_votes`
--
ALTER TABLE `citizen_votes`
  ADD CONSTRAINT `citizen_citizen_votes` FOREIGN KEY (`citizen_id`) REFERENCES `citizen` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `electoral_process_citizen_votes` FOREIGN KEY (`electoral_process_id`) REFERENCES `electoral_process` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `jrv_citizen_vote` FOREIGN KEY (`jrv_id`) REFERENCES `jrv` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `electoral_process`
--
ALTER TABLE `electoral_process`
  ADD CONSTRAINT `electoralprocess_status` FOREIGN KEY (`electoral_process_status_id`) REFERENCES `electoral_process_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `electoralprocess_types` FOREIGN KEY (`electoral_process_types_id`) REFERENCES `electoral_process_types` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `headquarters`
--
ALTER TABLE `headquarters`
  ADD CONSTRAINT `headquarters_city` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `jrv`
--
ALTER TABLE `jrv`
  ADD CONSTRAINT `electoral_process_jrv` FOREIGN KEY (`electoral_process_id`) REFERENCES `electoral_process` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `headquarter_jrv` FOREIGN KEY (`headquarter_id`) REFERENCES `headquarters` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `jrv_citizen`
--
ALTER TABLE `jrv_citizen`
  ADD CONSTRAINT `citizen_jrvcitizen` FOREIGN KEY (`citizen_id`) REFERENCES `citizen` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `jrvcitizen_citizentype` FOREIGN KEY (`jrv_citizen_type_id`) REFERENCES `jrv_citizen_types` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `jrvcitizen_jrv` FOREIGN KEY (`jrv_id`) REFERENCES `jrv` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `politic_group_votes`
--
ALTER TABLE `politic_group_votes`
  ADD CONSTRAINT `electoral_process_politic_group_votes` FOREIGN KEY (`electoral_process_id`) REFERENCES `electoral_process` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `jrv_politic_group_votes` FOREIGN KEY (`jrv_id`) REFERENCES `jrv` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `politic_group_politic_group_votes` FOREIGN KEY (`politic_group_id`) REFERENCES `politic_groups` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `presidencial_candidates`
--
ALTER TABLE `presidencial_candidates`
  ADD CONSTRAINT `electoral_process_presidencial_candidate` FOREIGN KEY (`electoral_process_id`) REFERENCES `electoral_process` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `presidencial_candidates_candidates` FOREIGN KEY (`candidates_id`) REFERENCES `candidates` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
