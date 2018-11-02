-- phpMyAdmin SQL Dump
-- version 4.1.4
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 02-Nov-2018 às 17:50
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
  `C_nomeAutor` int(1) NOT NULL DEFAULT '1',
  `nomeReu` varchar(45) DEFAULT NULL,
  `C_nomeReu` int(1) NOT NULL DEFAULT '1',
  `descricaoAuto` longtext,
  `C_descricaoAuto` int(1) NOT NULL DEFAULT '2',
  `sentenca` varchar(45) DEFAULT NULL,
  `C_sentenca` int(1) DEFAULT '3',
  `TC` int(1) NOT NULL DEFAULT '3',
  PRIMARY KEY (`numProcesso`,`TC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `processo`
--

INSERT INTO `processo` (`numProcesso`, `C_numProcesso`, `nomeAutor`, `C_nomeAutor`, `nomeReu`, `C_nomeReu`, `descricaoAuto`, `C_descricaoAuto`, `sentenca`, `C_sentenca`, `TC`) VALUES
('A-002', 0, 'Silvio Santos', 0, 'Hebe Camargo', 0, 'Beijo roubado', 0, 'Em curso', 0, 0),
('A-002', 0, 'Silvio Santos', 0, 'Hebe Camargo', 0, 'Beijo roubado', 0, 'Culpado', 1, 1),
('A-002', 0, 'Silvio Santos', 0, 'Hebe Camargo', 0, 'Beijo roubado', 0, 'Recurso', 2, 2),
('A-002', 0, 'Silvio Santos', 0, 'Hebe Camargo', 0, 'Beijo roubado', 0, 'Inocente', 3, 3),
('B-001', 2, 'Maria da Silva', 2, 'Jose da Silva', 2, 'Roubo', 2, 'Culpado', 2, 2),
('B-002', 2, 'Bolsonaro', 2, 'Lula', 2, 'Corrupção passiva', 2, 'Culpado', 2, 2),
('D-001', 3, 'OAB', 3, 'Bolsonaro', 3, 'Zueira acima dos limites', 3, 'Em Curso', 3, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuariobd`
--

CREATE TABLE IF NOT EXISTS `usuariobd` (
  `user` varchar(30) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `nivel` int(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`user`),
  UNIQUE KEY `user_UNIQUE` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuariobd`
--

INSERT INTO `usuariobd` (`user`, `senha`, `nivel`) VALUES
('cidadao', '', 1),
('funcionario', '', 2),
('juiz', '', 3),
('visitante', '', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
