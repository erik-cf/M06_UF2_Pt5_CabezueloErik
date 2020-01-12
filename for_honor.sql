-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-01-2020 a las 15:58:48
-- Versión del servidor: 10.3.15-MariaDB
-- Versión de PHP: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `for_honor`
--
CREATE DATABASE IF NOT EXISTS `for_honor` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `for_honor`;

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `Change_Faction`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Change_Faction` (IN `id_personaje` INT(11), IN `id_faccion_destino` INT(11))  NO SQL
UPDATE personaje SET faccion_id = id_faccion_destino WHERE personaje_id = id_personaje$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `faccion`
--

DROP TABLE IF EXISTS `faccion`;
CREATE TABLE `faccion` (
  `faccion_id` int(11) NOT NULL,
  `nombre_faccion` varchar(15) DEFAULT NULL,
  `lore` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `faccion`
--

INSERT INTO `faccion` (`faccion_id`, `nombre_faccion`, `lore`) VALUES
(1, 'Caballeros', 'Los caballeros de Ashfeld son paradigmas del poder. La Legión de Hierro los envió para llevar la paz a esas tierras. Desde entonces disfrutan de la libertad y han hecho de Ashfeld su hogar.'),
(2, 'Vikingos', 'Los vikingos desaparecieron hace siglos, tras escapar de sus derruidas tierras natales en busca de costas desconocidas. Los caballeros conquistaron a aquellos que se quedaron atrás y los incorporaron a su cultura.'),
(3, 'Samuráis', 'La historia no ha sido amable con los samuráis. Originarios de una tierra muy lejana, allende los mares, cuentan la historia de un emperador y una patria que desaparecieron entre el mar y el fuego.'),
(4, 'Wu Lin', 'Los Wu Lin son una facción de guerreros del interior de las murallas de la antigua China. Ahora, cuatro guerreros Wu Lin viajan al oeste en busca de venganza por las guerras, las traiciones y las tragedias personales. Combaten para reclamar su sitio en la próxima dinastía.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personaje`
--

DROP TABLE IF EXISTS `personaje`;
CREATE TABLE `personaje` (
  `personaje_id` int(11) NOT NULL,
  `nombre_personaje` varchar(15) DEFAULT NULL,
  `ataque` int(11) DEFAULT NULL,
  `defensa` int(11) DEFAULT NULL,
  `faccion_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `personaje`
--

INSERT INTO `personaje` (`personaje_id`, `nombre_personaje`, `ataque`, `defensa`, `faccion_id`) VALUES
(1, 'Aramusha', 35, 20, 3),
(2, 'Orochi', 80, 45, 3),
(3, 'Berserker', 30, 30, 2),
(4, 'The Raider', 100, 95, 2),
(5, 'The Warden', 90, 120, 1),
(6, 'Ademar', 70, 80, 1),
(7, 'Julius', 80, 105, 1),
(8, 'Shidou', 50, 40, 4),
(9, 'Wei Chang', 70, 55, 4);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `faccion`
--
ALTER TABLE `faccion`
  ADD PRIMARY KEY (`faccion_id`);

--
-- Indices de la tabla `personaje`
--
ALTER TABLE `personaje`
  ADD PRIMARY KEY (`personaje_id`),
  ADD KEY `faccion_id` (`faccion_id`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `personaje`
--
ALTER TABLE `personaje`
  ADD CONSTRAINT `personaje_ibfk_1` FOREIGN KEY (`faccion_id`) REFERENCES `faccion` (`faccion_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
