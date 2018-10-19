-- MySQL Script generated by MySQL Workbench
-- Thu Oct 18 17:04:41 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema tp03
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tp03
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tp03` DEFAULT CHARACTER SET utf8 ;
USE `tp03` ;

-- -----------------------------------------------------
-- Table `tp03`.`usuarioBD`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp03`.`usuarioBD` (
  `user` VARCHAR(30) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `nivel` INT(1) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`user`),
  UNIQUE INDEX `user_UNIQUE` (`user` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tp03`.`processo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp03`.`processo` (
  `numProcesso` VARCHAR(45) NOT NULL,
  `C_numProcesso` INT(1) NOT NULL DEFAULT 0,
  `nomeAutor` VARCHAR(45) NULL,
  `C_nomeAutor` INT(1) NOT NULL DEFAULT 0,
  `nomeReu` VARCHAR(45) NULL,
  `C_nomeReu` INT(1) NOT NULL DEFAULT 0,
  `descricaoAuto` LONGTEXT NULL,
  `C_descricaoAuto` INT(1) NOT NULL DEFAULT 0,
  `sentenca` VARCHAR(45) NULL,
  `C_sentenca` INT(1) NOT NULL DEFAULT 0,
  `TC` INT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`numProcesso`, `TC`, `C_numProcesso`, `C_nomeAutor`, `C_nomeReu`, `C_descricaoAuto`, `C_sentenca`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
