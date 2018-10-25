-- phpMyAdmin SQL Dump
-- version 4.1.4
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 25-Out-2018 às 01:29
-- Versão do servidor: 5.6.15-log
-- PHP Version: 5.4.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `tp03`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `processo`
--

CREATE TABLE IF NOT EXISTS `processo` (
  `numProcesso` varchar(45) NOT NULL,
  `C_numProcesso` int(1) NOT NULL DEFAULT '0',
  `nomeAutor` varchar(45) DEFAULT NULL,
  `C_nomeAutor` int(1) NOT NULL DEFAULT '0',
  `nomeReu` varchar(45) DEFAULT NULL,
  `C_nomeReu` int(1) NOT NULL DEFAULT '0',
  `descricaoAuto` longtext,
  `C_descricaoAuto` int(1) NOT NULL DEFAULT '0',
  `sentenca` varchar(45) DEFAULT NULL,
  `C_sentenca` int(1) NOT NULL DEFAULT '0',
  `TC` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`numProcesso`,`TC`,`C_numProcesso`,`C_nomeAutor`,`C_nomeReu`,`C_descricaoAuto`,`C_sentenca`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `processo`
--

INSERT INTO `processo` (`numProcesso`, `C_numProcesso`, `nomeAutor`, `C_nomeAutor`, `nomeReu`, `C_nomeReu`, `descricaoAuto`, `C_descricaoAuto`, `sentenca`, `C_sentenca`, `TC`) VALUES
('65156', 0, 'autor 3', 1, 'reu 3', 1, 'auto', 2, 'sentenca 3', 3, 3);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
