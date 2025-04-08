-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.19-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.7.0.6859
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
CREATE DATABASE IF NOT EXISTS `bilboskp` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `bilboskp`;

-- Volcando estructura para tabla bilboskp.centro_educativo
CREATE TABLE IF NOT EXISTS `centro_educativo` (
  `id_suscriptor` int(11) NOT NULL,
  `id_centro` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL DEFAULT '',
  `localidad` varchar(50) NOT NULL DEFAULT '',
  `etapas_educativas` int(11) NOT NULL DEFAULT 0,
  `num_alumnado` int(11) NOT NULL,
  PRIMARY KEY (`id_suscriptor`,`id_centro`),
  CONSTRAINT `FK_centros_educativos_suscriptores` FOREIGN KEY (`id_suscriptor`) REFERENCES `suscriptor` (`id_suscriptor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla bilboskp.centro_educativo: ~0 rows (aproximadamente)

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla bilboskp.cupon: ~0 rows (aproximadamente)

-- Volcando estructura para tabla bilboskp.historial_cupones
CREATE TABLE IF NOT EXISTS `historial_cupones` (
  `id_suscriptor` int(11) NOT NULL,
  `id_cupon` int(11) NOT NULL,
  PRIMARY KEY (`id_suscriptor`,`id_cupon`),
  KEY `FK_historial_cupones_cupon` (`id_cupon`),
  CONSTRAINT `FK_historial_cupones_cupon` FOREIGN KEY (`id_cupon`) REFERENCES `cupon` (`id_cupon`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_historial_cupones_suscriptor` FOREIGN KEY (`id_suscriptor`) REFERENCES `suscriptor` (`id_suscriptor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla bilboskp.historial_cupones: ~0 rows (aproximadamente)

-- Volcando estructura para tabla bilboskp.historial_partidas
CREATE TABLE IF NOT EXISTS `historial_partidas` (
  `id_partida` int(11) NOT NULL,
  `id_suscriptor` int(11) NOT NULL,
  PRIMARY KEY (`id_partida`,`id_suscriptor`),
  KEY `FK__suscriptor` (`id_suscriptor`),
  CONSTRAINT `FK__partida` FOREIGN KEY (`id_partida`) REFERENCES `partida` (`id_partida`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK__suscriptor` FOREIGN KEY (`id_suscriptor`) REFERENCES `suscriptor` (`id_suscriptor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla bilboskp.historial_partidas: ~0 rows (aproximadamente)

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
  PRIMARY KEY (`id_partida`),
  KEY `FK_partida_suscriptor` (`id_suscriptor`),
  KEY `FK_partida_sala` (`id_sala`),
  CONSTRAINT `FK_partida_sala` FOREIGN KEY (`id_sala`) REFERENCES `sala` (`id_sala`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_partida_suscriptor` FOREIGN KEY (`id_suscriptor`) REFERENCES `suscriptor` (`id_suscriptor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla bilboskp.partida: ~0 rows (aproximadamente)

-- Volcando estructura para tabla bilboskp.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id_rol` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla bilboskp.roles: ~2 rows (aproximadamente)
INSERT INTO `roles` (`id_rol`, `nombre`) VALUES
	(1, 'suscriptor'),
	(2, 'admin');

-- Volcando estructura para tabla bilboskp.sala
CREATE TABLE IF NOT EXISTS `sala` (
  `id_sala` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `tipo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_sala`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla bilboskp.sala: ~0 rows (aproximadamente)

-- Volcando estructura para tabla bilboskp.suscriptor
CREATE TABLE IF NOT EXISTS `suscriptor` (
  `id_suscriptor` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `usuario` varchar(100) NOT NULL,
  `contrasena` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `fecha_alta` date DEFAULT NULL,
  PRIMARY KEY (`id_suscriptor`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`usuario`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla bilboskp.suscriptor: ~2 rows (aproximadamente)
INSERT INTO `suscriptor` (`id_suscriptor`, `nombre`, `usuario`, `contrasena`, `email`, `fecha_alta`) VALUES
	(1, 'amaia', 'agb', '1234', 'amaia@gmail.com', '2025-04-01'),
	(2, 'Juan', 'juan', '1234', 'j@gmail.com', '2025-04-07');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
