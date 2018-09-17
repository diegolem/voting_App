-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema voting_app
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `voting_app` ;

-- -----------------------------------------------------
-- Schema voting_app
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `voting_app` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci ;
USE `voting_app` ;

-- -----------------------------------------------------
-- Table `voting_app`.`citizen_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voting_app`.`citizen_types` ;

CREATE TABLE IF NOT EXISTS `voting_app`.`citizen_types` (
  `id` VARCHAR(6) NOT NULL,
  `description` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `voting_app`.`departments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voting_app`.`departments` ;

CREATE TABLE IF NOT EXISTS `voting_app`.`departments` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `voting_app`.`cities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voting_app`.`cities` ;

CREATE TABLE IF NOT EXISTS `voting_app`.`cities` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `deparment_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `department_city_idx` (`deparment_id` ASC),
  CONSTRAINT `department_city`
    FOREIGN KEY (`deparment_id`)
    REFERENCES `voting_app`.`departments` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `voting_app`.`headquarters`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voting_app`.`headquarters` ;

CREATE TABLE IF NOT EXISTS `voting_app`.`headquarters` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `x` VARCHAR(45) NOT NULL,
  `y` VARCHAR(45) NOT NULL,
  `city_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `headquarters_city_idx` (`city_id` ASC),
  CONSTRAINT `headquarters_city`
    FOREIGN KEY (`city_id`)
    REFERENCES `voting_app`.`cities` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `voting_app`.`citizens`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voting_app`.`citizens` ;

CREATE TABLE IF NOT EXISTS `voting_app`.`citizens` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `password` VARCHAR(64) NULL DEFAULT NULL,
  `dui` VARCHAR(10) NOT NULL,
  `adress` VARCHAR(100) NOT NULL,
  `birthdate` DATE NOT NULL,
  `citizen_type_id` VARCHAR(6) NOT NULL,
  `headquarter_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `dui_UNIQUE` (`dui` ASC),
  INDEX `citizen_type_idx` (`citizen_type_id` ASC),
  INDEX `headquarter_citizen_idx` (`headquarter_id` ASC),
  CONSTRAINT `citizen_type`
    FOREIGN KEY (`citizen_type_id`)
    REFERENCES `voting_app`.`citizen_types` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `headquarter_citizen`
    FOREIGN KEY (`headquarter_id`)
    REFERENCES `voting_app`.`headquarters` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `voting_app`.`politic_groups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voting_app`.`politic_groups` ;

CREATE TABLE IF NOT EXISTS `voting_app`.`politic_groups` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `acronym` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `photo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `voting_app`.`candidates`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voting_app`.`candidates` ;

CREATE TABLE IF NOT EXISTS `voting_app`.`candidates` (
  `id` INT(11) NOT NULL,
  `photo` VARCHAR(45) NOT NULL,
  `politic_group_id` INT(11) NOT NULL,
  `citizen_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `citizen_candidate_idx` (`citizen_id` ASC),
  INDEX `politic_group_candidate` (`politic_group_id` ASC),
  CONSTRAINT `citizen_candidate`
    FOREIGN KEY (`citizen_id`)
    REFERENCES `voting_app`.`citizens` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `politic_group_candidate`
    FOREIGN KEY (`politic_group_id`)
    REFERENCES `voting_app`.`politic_groups` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `voting_app`.`electoral_process_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voting_app`.`electoral_process_status` ;

CREATE TABLE IF NOT EXISTS `voting_app`.`electoral_process_status` (
  `id` INT(11) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `voting_app`.`electoral_process_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voting_app`.`electoral_process_types` ;

CREATE TABLE IF NOT EXISTS `voting_app`.`electoral_process_types` (
  `id` INT(11) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `voting_app`.`electoral_process`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voting_app`.`electoral_process` ;

CREATE TABLE IF NOT EXISTS `voting_app`.`electoral_process` (
  `id` INT(11) NOT NULL,
  `code` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `year` VARCHAR(4) NOT NULL,
  `init_date` DATETIME NOT NULL,
  `end_date` DATETIME NOT NULL,
  `process_date` DATETIME NOT NULL,
  `electoral_process_status_id` INT(11) NOT NULL,
  `electoral_process_types_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC),
  INDEX `electoralprocess_status_idx` (`electoral_process_status_id` ASC),
  INDEX `electoralprocess_types_idx` (`electoral_process_types_id` ASC),
  CONSTRAINT `electoralprocess_status`
    FOREIGN KEY (`electoral_process_status_id`)
    REFERENCES `voting_app`.`electoral_process_status` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `electoralprocess_types`
    FOREIGN KEY (`electoral_process_types_id`)
    REFERENCES `voting_app`.`electoral_process_types` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `voting_app`.`candidates_for_cities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voting_app`.`candidates_for_cities` ;

CREATE TABLE IF NOT EXISTS `voting_app`.`candidates_for_cities` (
  `id` INT(11) NOT NULL,
  `candidate_id` INT(11) NOT NULL,
  `electoral_process_id` INT(11) NOT NULL,
  `city_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `candidate_candidateforcities_idx` (`candidate_id` ASC),
  INDEX `electoral_process_candidateforcities_idx` (`electoral_process_id` ASC),
  INDEX `city_candidateforcities_idx` (`city_id` ASC),
  CONSTRAINT `candidate_candidateforcities`
    FOREIGN KEY (`candidate_id`)
    REFERENCES `voting_app`.`candidates` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `city_candidateforcities`
    FOREIGN KEY (`city_id`)
    REFERENCES `voting_app`.`cities` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `electoral_process_candidateforcities`
    FOREIGN KEY (`electoral_process_id`)
    REFERENCES `voting_app`.`electoral_process` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `voting_app`.`jrv`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voting_app`.`jrv` ;

CREATE TABLE IF NOT EXISTS `voting_app`.`jrv` (
  `id` INT(11) NOT NULL,
  `code` VARCHAR(64) NOT NULL,
  `headquarter_id` INT(11) NOT NULL,
  `electoral_process_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC),
  INDEX `headquarter_jrv_idx` (`headquarter_id` ASC),
  INDEX `electoral_process_jrv_idx` (`electoral_process_id` ASC),
  CONSTRAINT `electoral_process_jrv`
    FOREIGN KEY (`electoral_process_id`)
    REFERENCES `voting_app`.`electoral_process` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `headquarter_jrv`
    FOREIGN KEY (`headquarter_id`)
    REFERENCES `voting_app`.`headquarters` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `voting_app`.`citizen_votes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voting_app`.`citizen_votes` ;

CREATE TABLE IF NOT EXISTS `voting_app`.`citizen_votes` (
  `id` INT(11) NOT NULL,
  `status` TINYINT(4) NULL DEFAULT NULL,
  `electoral_process_id` INT(11) NOT NULL,
  `citizen_id` INT(11) NOT NULL,
  `jrv_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `electoral_process_citizen_votes_idx` (`electoral_process_id` ASC),
  INDEX `citizen_citizen_votes_idx` (`citizen_id` ASC),
  INDEX `jrv_citizen_vote_idx` (`jrv_id` ASC),
  CONSTRAINT `citizen_citizen_votes`
    FOREIGN KEY (`citizen_id`)
    REFERENCES `voting_app`.`citizens` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `electoral_process_citizen_votes`
    FOREIGN KEY (`electoral_process_id`)
    REFERENCES `voting_app`.`electoral_process` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `jrv_citizen_vote`
    FOREIGN KEY (`jrv_id`)
    REFERENCES `voting_app`.`jrv` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `voting_app`.`jrv_citizen_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voting_app`.`jrv_citizen_types` ;

CREATE TABLE IF NOT EXISTS `voting_app`.`jrv_citizen_types` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `voting_app`.`jrv_citizen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voting_app`.`jrv_citizen` ;

CREATE TABLE IF NOT EXISTS `voting_app`.`jrv_citizen` (
  `id` INT(11) NOT NULL,
  `jrv_id` INT(11) NOT NULL,
  `citizen_id` INT(11) NOT NULL,
  `jrv_citizen_type_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `jrvcitizen_jrv_idx` (`jrv_id` ASC),
  INDEX `jrvcitizen_citizentype_idx` (`jrv_citizen_type_id` ASC),
  INDEX `citizen_jrvcitizen_idx` (`citizen_id` ASC),
  CONSTRAINT `citizen_jrvcitizen`
    FOREIGN KEY (`citizen_id`)
    REFERENCES `voting_app`.`citizens` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `jrvcitizen_citizentype`
    FOREIGN KEY (`jrv_citizen_type_id`)
    REFERENCES `voting_app`.`jrv_citizen_types` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `jrvcitizen_jrv`
    FOREIGN KEY (`jrv_id`)
    REFERENCES `voting_app`.`jrv` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `voting_app`.`politic_group_votes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voting_app`.`politic_group_votes` ;

CREATE TABLE IF NOT EXISTS `voting_app`.`politic_group_votes` (
  `id` INT(11) NOT NULL,
  `votes` INT(11) NULL DEFAULT NULL,
  `politic_group_id` INT(11) NOT NULL,
  `jrv_id` INT(11) NOT NULL,
  `electoral_process_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `politic_group_politic_group_votes_idx` (`politic_group_id` ASC),
  INDEX `jrv_politic_group_votes_idx` (`jrv_id` ASC),
  INDEX `electoral_process_politic_group_votes_idx` (`electoral_process_id` ASC),
  CONSTRAINT `electoral_process_politic_group_votes`
    FOREIGN KEY (`electoral_process_id`)
    REFERENCES `voting_app`.`electoral_process` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `jrv_politic_group_votes`
    FOREIGN KEY (`jrv_id`)
    REFERENCES `voting_app`.`jrv` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `politic_group_politic_group_votes`
    FOREIGN KEY (`politic_group_id`)
    REFERENCES `voting_app`.`politic_groups` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `voting_app`.`presidencial_candidates`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voting_app`.`presidencial_candidates` ;

CREATE TABLE IF NOT EXISTS `voting_app`.`presidencial_candidates` (
  `id` INT(11) NOT NULL,
  `candidates_id` INT(11) NOT NULL,
  `electoral_process_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `candidates_presidencia_candidates_idx` (`candidates_id` ASC),
  INDEX `electoral_process_presidencial_candidate_idx` (`electoral_process_id` ASC),
  CONSTRAINT `electoral_process_presidencial_candidate`
    FOREIGN KEY (`electoral_process_id`)
    REFERENCES `voting_app`.`electoral_process` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `presidencial_candidates_candidates`
    FOREIGN KEY (`candidates_id`)
    REFERENCES `voting_app`.`candidates` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `voting_app`.`cities_admins`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voting_app`.`cities_admins` ;

CREATE TABLE IF NOT EXISTS `voting_app`.`cities_admins` (
  `id` INT NOT NULL,
  `department_id` INT(11) NOT NULL,
  `citizen_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cities_admins_departments1_idx` (`department_id` ASC),
  INDEX `fk_cities_admins_citizen1_idx` (`citizen_id` ASC),
  CONSTRAINT `fk_cities_admins_departments1`
    FOREIGN KEY (`department_id`)
    REFERENCES `voting_app`.`departments` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_cities_admins_citizen1`
    FOREIGN KEY (`citizen_id`)
    REFERENCES `voting_app`.`citizens` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
