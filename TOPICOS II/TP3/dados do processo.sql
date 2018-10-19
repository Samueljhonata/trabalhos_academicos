-- phpMyAdmin SQL Dump
-- version 4.1.4
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 18-Out-2018 às 22:08
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
  PRIMARY KEY (`numProcesso`,`TC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `processo`
--

INSERT INTO `processo` (`numProcesso`, `C_numProcesso`, `nomeAutor`, `C_nomeAutor`, `nomeReu`, `C_nomeReu`, `descricaoAuto`, `C_descricaoAuto`, `sentenca`, `C_sentenca`, `TC`) VALUES
('0', 0, NULL, 0, NULL, 0, NULL, 0, NULL, 0, 0),
('100', 0, 'nome', 1, 'Jose', 2, 'des', 1, 'sentenca', 1, 2),
('5080287-28.2015.4.04.7100', 0, 'José Sérgio Gabrielli De Azevedo', 0, 'Luiz Inacio Lula Da Silva', 0, 'Improbidade Administrativa, Atos Administrativos, DIREITO ADMINISTRATIVO E OUTRAS MATÉRIAS DE DIREITO PÚBLICO', 0, 'Culpado', 0, 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
