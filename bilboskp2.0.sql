-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.32-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para bilboskp
CREATE DATABASE IF NOT EXISTS `bilboskp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `bilboskp`;

-- Volcando estructura para tabla bilboskp.centro_educativo
CREATE TABLE IF NOT EXISTS `centro_educativo` (
  `id_suscriptor` int(11) NOT NULL,
  `id_centro` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_centro` varchar(50) NOT NULL DEFAULT '',
  `localidad` varchar(50) NOT NULL DEFAULT '',
  `etapas_educativas` int(11) NOT NULL DEFAULT 0,
  `num_alumnado` int(11) NOT NULL,
  `estado` varchar(50) NOT NULL,
  PRIMARY KEY (`id_centro`) USING BTREE,
  KEY `FK_centro_educativo_suscriptor` (`id_suscriptor`),
  CONSTRAINT `FK_centro_educativo_suscriptor` FOREIGN KEY (`id_suscriptor`) REFERENCES `suscriptor` (`id_suscriptor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla bilboskp.centro_educativo: ~3 rows (aproximadamente)
INSERT INTO `centro_educativo` (`id_suscriptor`, `id_centro`, `nombre_centro`, `localidad`, `etapas_educativas`, `num_alumnado`, `estado`) VALUES
	(8, 4, 'IES Castro', 'Castro Urdiales', 2, 100, 'aceptado'),
	(11, 5, 'Centro San Luis', 'Bilbao', 4, 20, 'aceptado'),
	(12, 6, 'IES Laudio', 'Llodio', 5, 30, 'aceptado'),
	(18, 23, 'CEIP Barakaldo', 'Barakaldo', 4, 50, 'pendiente');

-- Volcando estructura para tabla bilboskp.cupon
CREATE TABLE IF NOT EXISTS `cupon` (
  `id_cupon` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_compra` date NOT NULL,
  `fecha_caducidad` date DEFAULT NULL,
  `estado` varchar(50) NOT NULL,
  `precio` float NOT NULL,
  `fecha_devolucion` date DEFAULT NULL,
  `tipo` varchar(50) NOT NULL,
  PRIMARY KEY (`id_cupon`)
) ENGINE=InnoDB AUTO_INCREMENT=1302 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla bilboskp.cupon: ~71 rows (aproximadamente)
INSERT INTO `cupon` (`id_cupon`, `fecha_compra`, `fecha_caducidad`, `estado`, `precio`, `fecha_devolucion`, `tipo`) VALUES
	(1, '2025-04-11', '2125-04-11', 'disponible', 0, NULL, 'arcoiris'),
	(2, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(3, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(4, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(5, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(6, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(7, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(8, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(9, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(10, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(11, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(12, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(13, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(14, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(15, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(16, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(17, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(18, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(19, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(20, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(21, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(22, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(23, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(24, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(25, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(26, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(27, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(28, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(29, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(30, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(31, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(32, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(33, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(34, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(35, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(36, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(37, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(38, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(39, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(40, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(41, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(42, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(43, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(44, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(45, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(46, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(47, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(48, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(49, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(50, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(51, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(52, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(53, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(54, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(55, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(56, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(57, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(58, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(59, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(60, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(61, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(62, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(63, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(64, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(65, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(66, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(67, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(68, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(69, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(70, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro'),
	(71, '2025-04-21', '2025-05-21', 'disponible', 0, NULL, 'centro');

-- Volcando estructura para tabla bilboskp.historial_cupones
CREATE TABLE IF NOT EXISTS `historial_cupones` (
  `id_suscriptor` int(11) NOT NULL,
  `id_cupon` int(11) NOT NULL,
  PRIMARY KEY (`id_suscriptor`,`id_cupon`),
  KEY `FK_historial_cupones_cupon` (`id_cupon`),
  CONSTRAINT `FK_historial_cupones_cupon` FOREIGN KEY (`id_cupon`) REFERENCES `cupon` (`id_cupon`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_historial_cupones_suscriptor` FOREIGN KEY (`id_suscriptor`) REFERENCES `suscriptor` (`id_suscriptor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla bilboskp.historial_cupones: ~71 rows (aproximadamente)
INSERT INTO `historial_cupones` (`id_suscriptor`, `id_cupon`) VALUES
	(8, 1),
	(11, 2),
	(11, 3),
	(11, 4),
	(11, 5),
	(11, 6),
	(11, 7),
	(11, 8),
	(11, 9),
	(11, 10),
	(11, 11),
	(11, 12),
	(11, 13),
	(11, 14),
	(11, 15),
	(11, 16),
	(11, 17),
	(11, 18),
	(11, 19),
	(11, 20),
	(11, 21),
	(11, 52),
	(11, 53),
	(11, 54),
	(11, 55),
	(11, 56),
	(11, 57),
	(11, 58),
	(11, 59),
	(11, 60),
	(11, 61),
	(11, 62),
	(11, 63),
	(11, 64),
	(11, 65),
	(11, 66),
	(11, 67),
	(11, 68),
	(11, 69),
	(11, 70),
	(11, 71),
	(12, 22),
	(12, 23),
	(12, 24),
	(12, 25),
	(12, 26),
	(12, 27),
	(12, 28),
	(12, 29),
	(12, 30),
	(12, 31),
	(12, 32),
	(12, 33),
	(12, 34),
	(12, 35),
	(12, 36),
	(12, 37),
	(12, 38),
	(12, 39),
	(12, 40),
	(12, 41),
	(12, 42),
	(12, 43),
	(12, 44),
	(12, 45),
	(12, 46),
	(12, 47),
	(12, 48),
	(12, 49),
	(12, 50),
	(12, 51);

-- Volcando estructura para tabla bilboskp.partida
CREATE TABLE IF NOT EXISTS `partida` (
  `id_partida` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` datetime NOT NULL,
  `jugadores` int(11) NOT NULL,
  `descripcion` varchar(50) DEFAULT '',
  `estado` varchar(50) NOT NULL DEFAULT '',
  `puntuacion` int(11) DEFAULT NULL,
  `id_suscriptor` int(11) NOT NULL DEFAULT 0,
  `id_sala` int(11) NOT NULL,
  `codigo` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`id_partida`),
  KEY `FK_partida_suscriptor` (`id_suscriptor`),
  KEY `FK_partida_sala` (`id_sala`),
  CONSTRAINT `FK_partida_sala` FOREIGN KEY (`id_sala`) REFERENCES `sala` (`id_sala`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_partida_suscriptor` FOREIGN KEY (`id_suscriptor`) REFERENCES `suscriptor` (`id_suscriptor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla bilboskp.partida: ~2 rows (aproximadamente)
INSERT INTO `partida` (`id_partida`, `fecha`, `jugadores`, `descripcion`, `estado`, `puntuacion`, `id_suscriptor`, `id_sala`, `codigo`) VALUES
	(29, '2025-04-11 10:44:00', 7, '2 DAW', 'programada', 0, 9, 7, 'ickkck'),
	(30, '2025-04-28 12:00:00', 5, '1 DAW', 'programada', 0, 12, 7, 'aaa');

-- Volcando estructura para tabla bilboskp.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id_rol` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla bilboskp.roles: ~3 rows (aproximadamente)
INSERT INTO `roles` (`id_rol`, `nombre`) VALUES
	(1, 'admin'),
	(2, 'suscriptor'),
	(3, 'centro');

-- Volcando estructura para tabla bilboskp.sala
CREATE TABLE IF NOT EXISTS `sala` (
  `id_sala` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `tipo` varchar(50) DEFAULT NULL,
  `estado` varchar(50) DEFAULT '',
  PRIMARY KEY (`id_sala`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla bilboskp.sala: ~4 rows (aproximadamente)
INSERT INTO `sala` (`id_sala`, `nombre`, `tipo`, `estado`) VALUES
	(7, 'School Escape Room', 'centro', 'habilitada'),
	(8, 'Expediente X', 'suscriptor', 'habilitada'),
	(9, 'Apocalipsis zombie', 'suscriptor', 'habilitada'),
	(10, 'El enigma de Pozas', 'centro', 'deshabilitada');

-- Volcando estructura para tabla bilboskp.suscriptor
CREATE TABLE IF NOT EXISTS `suscriptor` (
  `id_suscriptor` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `usuario` varchar(100) NOT NULL,
  `contrasena` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `fecha_alta` date DEFAULT NULL,
  `id_rol` int(11) NOT NULL,
  PRIMARY KEY (`id_suscriptor`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`usuario`) USING BTREE,
  KEY `FK_suscriptor_roles` (`id_rol`),
  CONSTRAINT `FK_suscriptor_roles` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla bilboskp.suscriptor: ~5 rows (aproximadamente)
INSERT INTO `suscriptor` (`id_suscriptor`, `nombre`, `usuario`, `contrasena`, `email`, `fecha_alta`, `id_rol`) VALUES
	(8, 'juan', 'juan', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'j@gmail.com', '2025-04-11', 3),
	(9, 'iban', 'iban', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'i@gmail.com', '2025-04-11', 2),
	(10, 'admin', 'admin', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'admin@gmail.com', '2025-04-21', 1),
	(11, 'Alberto', 'alberto', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'alb@gmail.com', '2025-04-21', 3),
	(12, 'Amaia', 'amaia', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'amaia@gmail.com', '2025-04-21', 3),
	(18, 'Sergio', 'sergio', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'sergio@gmail.com', '2025-04-22', 3);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
