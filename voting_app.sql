-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: voting_app
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.34-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `candidates`
--
drop database if exists voting_app;
create DATABASE voting_app;

use voting_app;

DROP TABLE IF EXISTS `candidates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `candidates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `photo` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `politic_group_id` int(11) NOT NULL,
  `citizen_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `citizen_candidate_idx` (`citizen_id`),
  KEY `politic_group_candidate` (`politic_group_id`),
  CONSTRAINT `citizen_candidate` FOREIGN KEY (`citizen_id`) REFERENCES `citizens` (`id`),
  CONSTRAINT `politic_group_candidate` FOREIGN KEY (`politic_group_id`) REFERENCES `politic_groups` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidates`
--

LOCK TABLES `candidates` WRITE;
/*!40000 ALTER TABLE `candidates` DISABLE KEYS */;
/*!40000 ALTER TABLE `candidates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `candidates_for_cities`
--

DROP TABLE IF EXISTS `candidates_for_cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `candidates_for_cities` (
  `id` int(11) NOT NULL,
  `candidate_id` int(11) NOT NULL,
  `electoral_process_id` int(11) NOT NULL,
  `city_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `candidate_candidateforcities_idx` (`candidate_id`),
  KEY `electoral_process_candidateforcities_idx` (`electoral_process_id`),
  KEY `city_candidateforcities_idx` (`city_id`),
  CONSTRAINT `candidate_candidateforcities` FOREIGN KEY (`candidate_id`) REFERENCES `candidates` (`id`),
  CONSTRAINT `city_candidateforcities` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`),
  CONSTRAINT `electoral_process_candidateforcities` FOREIGN KEY (`electoral_process_id`) REFERENCES `electoral_process` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidates_for_cities`
--

LOCK TABLES `candidates_for_cities` WRITE;
/*!40000 ALTER TABLE `candidates_for_cities` DISABLE KEYS */;
/*!40000 ALTER TABLE `candidates_for_cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cities` (
  `id` int(11) NOT NULL,
  `name` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `deparment_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `department_city_idx` (`deparment_id`),
  CONSTRAINT `department_city` FOREIGN KEY (`deparment_id`) REFERENCES `departments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES (1,'Ahuachapán',1),(2,'Jujutla',1),(3,'Atiquizaya',1),(4,'Concepción de Ataco',1),(5,'El Refugio',1),(6,'Guaymango',1),(7,'Apaneca',1),(8,'San Francisco Menéndez',1),(9,'San Lorenzo',1),(10,'San Pedro Puxtla',1),(11,'Tacuba',1),(12,'Turín',1),(13,'Candelaria de la Frontera',2),(14,'Chalchuapa',2),(15,'Coatepeque',2),(16,'El Congo',2),(17,'El Porvenir',2),(18,'Masahuat',2),(19,'Metapán',2),(20,'San Antonio Pajonal',2),(21,'San Sebastián Salitrillo',2),(22,'Santa Ana',2),(23,'Santa Rosa Guachipilín',2),(24,'Santiago de la Frontera',2),(25,'Texistepeque',2),(26,'Acajutla',3),(27,'Armenia',3),(28,'Caluco',3),(29,'Cuisnahuat',3),(30,'Izalco',3),(31,'Juayúa',3),(32,'Nahuizalco',3),(33,'Nahulingo',3),(34,'Salcoatitán',3),(35,'San Antonio del Monte',3),(36,'San Julián',3),(37,'Santa Catarina Masahuat',3),(38,'Santa Isabel Ishuatán',3),(39,'Santo Domingo de Guzmán',3),(40,'Sonsonate',3),(41,'Sonzacate',3),(42,'Alegría',11),(43,'Berlín',11),(44,'California',11),(45,'Concepción Batres',11),(46,'El Triunfo',11),(47,'Ereguayquín',11),(48,'Estanzuelas',11),(49,'Jiquilisco',11),(50,'Jucuapa',11),(51,'Jucuarán',11),(52,'Mercedes Umaña',11),(53,'Nueva Granada',11),(54,'Ozatlán',11),(55,'Puerto El Triunfo',11),(56,'San Agustín',11),(57,'San Buenaventura',11),(58,'San Dionisio',11),(59,'San Francisco Javier',11),(60,'Santa Elena',11),(61,'Santa María',11),(62,'Santiago de María',11),(63,'Tecapán',11),(64,'Usulután',11),(65,'Carolina',13),(66,'Chapeltique',13),(67,'Chinameca',13),(68,'Chirilagua',13),(69,'Ciudad Barrios',13),(70,'Comacarán',13),(71,'El Tránsito',13),(72,'Lolotique',13),(73,'Moncagua',13),(74,'Nueva Guadalupe',13),(75,'Nuevo Edén de San Juan',13),(76,'Quelepa',13),(77,'San Antonio del Mosco',13),(78,'San Gerardo',13),(79,'San Jorge',13),(80,'San Luis de la Reina',13),(81,'San Miguel',13),(82,'San Rafael Oriente',13),(83,'Sesori',13),(84,'Uluazapa',13),(85,'Arambala',12),(86,'Cacaopera',12),(87,'Chilanga',12),(88,'Corinto',12),(89,'Delicias de Concepción',12),(90,'El Divisadero',12),(91,'El Rosario (Morazán)',12),(92,'Gualococti',12),(93,'Guatajiagua',12),(94,'Joateca',12),(95,'Jocoaitique',12),(96,'Jocoro',12),(97,'Lolotiquillo',12),(98,'Meanguera',12),(99,'Osicala',12),(100,'Perquín',12),(101,'San Carlos',12),(102,'San Fernando (Morazán)',12),(103,'San Francisco Gotera',12),(104,'San Isidro (Morazán)',12),(105,'San Simón',12),(106,'Sensembra',12),(107,'Sociedad',12),(108,'Torola',12),(109,'Yamabal',12),(110,'Yoloaiquín',12),(111,'La Unión',14),(112,'San Alejo',14),(113,'Yucuaiquín',14),(114,'Conchagua',14),(115,'Intipucá',14),(116,'San José',14),(117,'El Carmen (La Unión)',14),(118,'Yayantique',14),(119,'Bolívar',14),(120,'Meanguera del Golfo',14),(121,'Santa Rosa de Lima',14),(122,'Pasaquina',14),(123,'Anamoros',14),(124,'Nueva Esparta',14),(125,'El Sauce',14),(126,'Concepción de Oriente',14),(127,'Polorós',14),(128,'Lislique',14),(129,'Antiguo Cuscatlán',4),(130,'Chiltiupán',4),(131,'Ciudad Arce',4),(132,'Colón',4),(133,'Comasagua',4),(134,'Huizúcar',4),(135,'Jayaque',4),(136,'Jicalapa',4),(137,'La Libertad',4),(138,'Santa Tecla',4),(139,'Nuevo Cuscatlán',4),(140,'San Juan Opico',4),(141,'Quezaltepeque',4),(142,'Sacacoyo',4),(143,'San José Villanueva',4),(144,'San Matías',4),(145,'San Pablo Tacachico',4),(146,'Talnique',4),(147,'Tamanique',4),(148,'Teotepeque',4),(149,'Tepecoyo',4),(150,'Zaragoza',4),(151,'Agua Caliente',5),(152,'Arcatao',5),(153,'Azacualpa',5),(154,'Cancasque',5),(155,'Chalatenango',5),(156,'Citalá',5),(157,'Comapala',5),(158,'Concepción Quezaltepeque',5),(159,'Dulce Nombre de María',5),(160,'El Carrizal',5),(161,'El Paraíso',5),(162,'La Laguna',5),(163,'La Palma',5),(164,'La Reina',5),(165,'Las Vueltas',5),(166,'Nueva Concepción',5),(167,'Nueva Trinidad',5),(168,'Nombre de Jesús',5),(169,'Ojos de Agua',5),(170,'Potonico',5),(171,'San Antonio de la Cruz',5),(172,'San Antonio Los Ranchos',5),(173,'San Fernando (Chalatenango)',5),(174,'San Francisco Lempa',5),(175,'San Francisco Morazán',5),(176,'San Ignacio',5),(177,'San Isidro Labrador',5),(178,'Las Flores',5),(179,'San Luis del Carmen',5),(180,'San Miguel de Mercedes',5),(181,'San Rafael',5),(182,'Santa Rita',5),(183,'Tejutla',5),(184,'Cojutepeque',7),(185,'Candelaria',7),(186,'El Carmen (Cuscatlán)',7),(187,'El Rosario (Cuscatlán)',7),(188,'Monte San Juan',7),(189,'Oratorio de Concepción',7),(190,'San Bartolomé Perulapía',7),(191,'San Cristóbal',7),(192,'San José Guayabal',7),(193,'San Pedro Perulapán',7),(194,'San Rafael Cedros',7),(195,'San Ramón',7),(196,'Santa Cruz Analquito',7),(197,'Santa Cruz Michapa',7),(198,'Suchitoto',7),(199,'Tenancingo',7),(200,'Aguilares',6),(201,'Apopa',6),(202,'Ayutuxtepeque',6),(203,'Cuscatancingo',6),(204,'Ciudad Delgado',6),(205,'El Paisnal',6),(206,'Guazapa',6),(207,'Ilopango',6),(208,'Mejicanos',6),(209,'Nejapa',6),(210,'Panchimalco',6),(211,'Rosario de Mora',6),(212,'San Marcos',6),(213,'San Martín',6),(214,'San Salvador',6),(215,'Santiago Texacuangos',6),(216,'Santo Tomás',6),(217,'Soyapango',6),(218,'Tonacatepeque',6),(219,'Zacatecoluca',8),(220,'Cuyultitán',8),(221,'El Rosario (La Paz)',8),(222,'Jerusalén',8),(223,'Mercedes La Ceiba',8),(224,'Olocuilta',8),(225,'Paraíso de Osorio',8),(226,'San Antonio Masahuat',8),(227,'San Emigdio',8),(228,'San Francisco Chinameca',8),(229,'San Pedro Masahuat',8),(230,'San Juan Nonualco',8),(231,'San Juan Talpa',8),(232,'San Juan Tepezontes',8),(233,'San Luis La Herradura',8),(234,'San Luis Talpa',8),(235,'San Miguel Tepezontes',8),(236,'San Pedro Nonualco',8),(237,'San Rafael Obrajuelo',8),(238,'Santa María Ostuma',8),(239,'Santiago Nonualco',8),(240,'Tapalhuaca',8),(241,'Cinquera',9),(242,'Dolores',9),(243,'Guacotecti',9),(244,'Ilobasco',9),(245,'Jutiapa',9),(246,'San Isidro (Cabañas)',9),(247,'Sensuntepeque',9),(248,'Tejutepeque',9),(249,'Victoria',9),(250,'Apastepeque',10),(251,'Guadalupe',10),(252,'San Cayetano Istepeque',10),(253,'San Esteban Catarina',10),(254,'San Ildefonso',10),(255,'San Lorenzo',10),(256,'San Sebastián',10),(257,'San Vicente',10),(258,'Santa Clara',10),(259,'Santo Domingo',10),(260,'Tecoluca',10),(261,'Tepetitán',10),(262,'Verapaz',10);
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cities_admins`
--

DROP TABLE IF EXISTS `cities_admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cities_admins` (
  `id` int(11) NOT NULL,
  `department_id` int(11) NOT NULL,
  `citizen_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cities_admins_departments1_idx` (`department_id`),
  KEY `fk_cities_admins_citizen1_idx` (`citizen_id`),
  CONSTRAINT `fk_cities_admins_citizen1` FOREIGN KEY (`citizen_id`) REFERENCES `citizens` (`id`),
  CONSTRAINT `fk_cities_admins_departments1` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities_admins`
--

LOCK TABLES `cities_admins` WRITE;
/*!40000 ALTER TABLE `cities_admins` DISABLE KEYS */;
/*!40000 ALTER TABLE `cities_admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `citizen_types`
--

DROP TABLE IF EXISTS `citizen_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `citizen_types` (
  `id` varchar(6) COLLATE utf8_spanish2_ci NOT NULL,
  `description` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `citizen_types`
--

LOCK TABLES `citizen_types` WRITE;
/*!40000 ALTER TABLE `citizen_types` DISABLE KEYS */;
INSERT INTO `citizen_types` VALUES ('ADMDEP','Administrador Departamental'),('ADMGEN','Administrador General'),('CITIZN','Ciudadano'),('EMRNPN','Empleado RNPN'),('PREJRV','Presidente Jrv');
/*!40000 ALTER TABLE `citizen_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `citizen_votes`
--

DROP TABLE IF EXISTS `citizen_votes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `citizen_votes` (
  `id` int(11) NOT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `electoral_process_id` int(11) NOT NULL,
  `citizen_id` int(11) NOT NULL,
  `jrv_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `electoral_process_citizen_votes_idx` (`electoral_process_id`),
  KEY `citizen_citizen_votes_idx` (`citizen_id`),
  KEY `jrv_citizen_vote_idx` (`jrv_id`),
  CONSTRAINT `citizen_citizen_votes` FOREIGN KEY (`citizen_id`) REFERENCES `citizens` (`id`),
  CONSTRAINT `electoral_process_citizen_votes` FOREIGN KEY (`electoral_process_id`) REFERENCES `electoral_process` (`id`),
  CONSTRAINT `jrv_citizen_vote` FOREIGN KEY (`jrv_id`) REFERENCES `jrv` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `citizen_votes`
--

LOCK TABLES `citizen_votes` WRITE;
/*!40000 ALTER TABLE `citizen_votes` DISABLE KEYS */;
/*!40000 ALTER TABLE `citizen_votes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `citizens`
--

DROP TABLE IF EXISTS `citizens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `citizens` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `lastname` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `password` varchar(64) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `dui` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `adress` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `birthdate` date NOT NULL,
  `citizen_type_id` varchar(6) COLLATE utf8_spanish2_ci NOT NULL,
  `headquarter_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dui_UNIQUE` (`dui`),
  KEY `citizen_type_idx` (`citizen_type_id`),
  KEY `headquarter_citizen_idx` (`headquarter_id`),
  CONSTRAINT `citizen_type` FOREIGN KEY (`citizen_type_id`) REFERENCES `citizen_types` (`id`),
  CONSTRAINT `headquarter_citizen` FOREIGN KEY (`headquarter_id`) REFERENCES `headquarters` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `citizens`
--

LOCK TABLES `citizens` WRITE;
/*!40000 ALTER TABLE `citizens` DISABLE KEYS */;
INSERT INTO `citizens` VALUES (1,'Carlos','Mojica','12345','12345678-9','El Salvador','2018-09-08','ADMGEN',1),(2,'Diego Alberto','Lemus Torres','1234','87654321-9','El Salvador, Avenida Bernal','2018-07-04','EMRNPN',1);
/*!40000 ALTER TABLE `citizens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departments`
--

DROP TABLE IF EXISTS `departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departments` (
  `id` int(11) NOT NULL,
  `name` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departments`
--

LOCK TABLES `departments` WRITE;
/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` VALUES (1,'Ahuachapán'),(2,'Santa Ana'),(3,'Sonsonate'),(4,'La Libertad'),(5,'Chalatenango'),(6,'San Salvador'),(7,'Cuscatlán'),(8,'La Paz'),(9,'Cabañas'),(10,'San Vicente'),(11,'Usulután'),(12,'Morazán'),(13,'San Miguel'),(14,'La Unión');
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `electoral_process`
--

DROP TABLE IF EXISTS `electoral_process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `electoral_process` (
  `id` int(11) NOT NULL,
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
  KEY `electoralprocess_types_idx` (`electoral_process_types_id`),
  CONSTRAINT `electoralprocess_status` FOREIGN KEY (`electoral_process_status_id`) REFERENCES `electoral_process_status` (`id`),
  CONSTRAINT `electoralprocess_types` FOREIGN KEY (`electoral_process_types_id`) REFERENCES `electoral_process_types` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `electoral_process`
--

LOCK TABLES `electoral_process` WRITE;
/*!40000 ALTER TABLE `electoral_process` DISABLE KEYS */;
/*!40000 ALTER TABLE `electoral_process` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `electoral_process_status`
--

DROP TABLE IF EXISTS `electoral_process_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `electoral_process_status` (
  `id` int(11) NOT NULL,
  `description` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `electoral_process_status`
--

LOCK TABLES `electoral_process_status` WRITE;
/*!40000 ALTER TABLE `electoral_process_status` DISABLE KEYS */;
INSERT INTO `electoral_process_status` VALUES (1,'Registro Candidatos y JRV'),(2,'Fin Registro'),(3,'Proceso Electoral en Proceso'),(4,'Fin Proceso Electoral');
/*!40000 ALTER TABLE `electoral_process_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `electoral_process_types`
--

DROP TABLE IF EXISTS `electoral_process_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `electoral_process_types` (
  `id` int(11) NOT NULL,
  `description` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `electoral_process_types`
--

LOCK TABLES `electoral_process_types` WRITE;
/*!40000 ALTER TABLE `electoral_process_types` DISABLE KEYS */;
INSERT INTO `electoral_process_types` VALUES (1,'Presidencial'),(2,'Consejos Municipales');
/*!40000 ALTER TABLE `electoral_process_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `headquarters`
--

DROP TABLE IF EXISTS `headquarters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `headquarters` (
  `id` int(11) NOT NULL,
  `name` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `x` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `y` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `city_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `headquarters_city_idx` (`city_id`),
  CONSTRAINT `headquarters_city` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `headquarters`
--

LOCK TABLES `headquarters` WRITE;
/*!40000 ALTER TABLE `headquarters` DISABLE KEYS */;
INSERT INTO `headquarters` VALUES (1,'Centro Escolar Reparto Las Guacinamas','314321','432251',1);
/*!40000 ALTER TABLE `headquarters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jrv`
--

DROP TABLE IF EXISTS `jrv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jrv` (
  `id` int(11) NOT NULL,
  `code` varchar(64) COLLATE utf8_spanish2_ci NOT NULL,
  `headquarter_id` int(11) NOT NULL,
  `electoral_process_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `headquarter_jrv_idx` (`headquarter_id`),
  KEY `electoral_process_jrv_idx` (`electoral_process_id`),
  CONSTRAINT `electoral_process_jrv` FOREIGN KEY (`electoral_process_id`) REFERENCES `electoral_process` (`id`),
  CONSTRAINT `headquarter_jrv` FOREIGN KEY (`headquarter_id`) REFERENCES `headquarters` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jrv`
--

LOCK TABLES `jrv` WRITE;
/*!40000 ALTER TABLE `jrv` DISABLE KEYS */;
/*!40000 ALTER TABLE `jrv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jrv_citizen`
--

DROP TABLE IF EXISTS `jrv_citizen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jrv_citizen` (
  `id` int(11) NOT NULL,
  `jrv_id` int(11) NOT NULL,
  `citizen_id` int(11) NOT NULL,
  `jrv_citizen_type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `jrvcitizen_jrv_idx` (`jrv_id`),
  KEY `jrvcitizen_citizentype_idx` (`jrv_citizen_type_id`),
  KEY `citizen_jrvcitizen_idx` (`citizen_id`),
  CONSTRAINT `citizen_jrvcitizen` FOREIGN KEY (`citizen_id`) REFERENCES `citizens` (`id`),
  CONSTRAINT `jrvcitizen_citizentype` FOREIGN KEY (`jrv_citizen_type_id`) REFERENCES `jrv_citizen_types` (`id`),
  CONSTRAINT `jrvcitizen_jrv` FOREIGN KEY (`jrv_id`) REFERENCES `jrv` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jrv_citizen`
--

LOCK TABLES `jrv_citizen` WRITE;
/*!40000 ALTER TABLE `jrv_citizen` DISABLE KEYS */;
/*!40000 ALTER TABLE `jrv_citizen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jrv_citizen_types`
--

DROP TABLE IF EXISTS `jrv_citizen_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jrv_citizen_types` (
  `id` int(11) NOT NULL,
  `name` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `description` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jrv_citizen_types`
--

LOCK TABLES `jrv_citizen_types` WRITE;
/*!40000 ALTER TABLE `jrv_citizen_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `jrv_citizen_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `politic_group_votes`
--

DROP TABLE IF EXISTS `politic_group_votes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `politic_group_votes` (
  `id` int(11) NOT NULL,
  `votes` int(11) DEFAULT NULL,
  `politic_group_id` int(11) NOT NULL,
  `jrv_id` int(11) NOT NULL,
  `electoral_process_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `politic_group_politic_group_votes_idx` (`politic_group_id`),
  KEY `jrv_politic_group_votes_idx` (`jrv_id`),
  KEY `electoral_process_politic_group_votes_idx` (`electoral_process_id`),
  CONSTRAINT `electoral_process_politic_group_votes` FOREIGN KEY (`electoral_process_id`) REFERENCES `electoral_process` (`id`),
  CONSTRAINT `jrv_politic_group_votes` FOREIGN KEY (`jrv_id`) REFERENCES `jrv` (`id`),
  CONSTRAINT `politic_group_politic_group_votes` FOREIGN KEY (`politic_group_id`) REFERENCES `politic_groups` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `politic_group_votes`
--

LOCK TABLES `politic_group_votes` WRITE;
/*!40000 ALTER TABLE `politic_group_votes` DISABLE KEYS */;
/*!40000 ALTER TABLE `politic_group_votes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `politic_groups`
--

DROP TABLE IF EXISTS `politic_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `politic_groups` (
  `id` int(11) NOT NULL,
  `name` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `acronym` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `description` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `photo` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `politic_groups`
--

LOCK TABLES `politic_groups` WRITE;
/*!40000 ALTER TABLE `politic_groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `politic_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presidencial_candidates`
--

DROP TABLE IF EXISTS `presidencial_candidates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `presidencial_candidates` (
  `id` int(11) NOT NULL,
  `candidates_id` int(11) NOT NULL,
  `electoral_process_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `candidates_presidencia_candidates_idx` (`candidates_id`),
  KEY `electoral_process_presidencial_candidate_idx` (`electoral_process_id`),
  CONSTRAINT `electoral_process_presidencial_candidate` FOREIGN KEY (`electoral_process_id`) REFERENCES `electoral_process` (`id`),
  CONSTRAINT `presidencial_candidates_candidates` FOREIGN KEY (`candidates_id`) REFERENCES `candidates` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presidencial_candidates`
--

LOCK TABLES `presidencial_candidates` WRITE;
/*!40000 ALTER TABLE `presidencial_candidates` DISABLE KEYS */;
/*!40000 ALTER TABLE `presidencial_candidates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence`
--

LOCK TABLES `sequence` WRITE;
/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` VALUES ('SEQ_GEN',0);
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-22 23:06:43
