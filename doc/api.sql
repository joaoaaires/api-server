-- --------------------------------------------------------
-- Servidor:                     localhost
-- Versão do servidor:           8.0.31 - MySQL Community Server - GPL
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.2.0.6576
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para api
CREATE DATABASE IF NOT EXISTS `api` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `api`;

-- Copiando estrutura para tabela api.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nomeum` varchar(255) NOT NULL,
  `nomedois` varchar(255) NOT NULL,
  `registrounico` varchar(255) NOT NULL,
  `registrogeral` varchar(255) NOT NULL,
  `dataorigem` date NOT NULL,
  `site` varchar(50) DEFAULT NULL,
  `tipo` varchar(1) NOT NULL,
  `situacao` int NOT NULL,
  `datahorainc` datetime NOT NULL,
  `datahoraalt` datetime DEFAULT NULL,
  `datahoradel` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela api.cliente: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela api.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `situacao` int NOT NULL,
  `datahorasyc` datetime NOT NULL,
  `datahorainc` datetime NOT NULL,
  `datahoraalt` datetime DEFAULT NULL,
  `datahoradel` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela api.usuario: ~2 rows (aproximadamente)
INSERT INTO `usuario` (`id`, `email`, `password`, `situacao`, `datahorasyc`, `datahorainc`, `datahoraalt`, `datahoradel`) VALUES
	(6, 'joao.aires@gmail.com', '$2a$10$zRA7ILQYOygKWXZzHiZuX.yJ9/NqJuzAEXAJPepfiYChdpWgySv/6', 1, '2022-12-07 00:37:24', '2022-12-07 00:37:24', NULL, NULL),
	(7, 'joao.aires2@gmail.com', '$2a$10$Ujn0IS3PSr/hJmG/It2Gj.FcdKavM6aF4MZQlXTZflj8o/coDoj4S', 1, '2022-12-07 01:05:45', '2022-12-07 01:05:46', NULL, NULL);


-- Copiando estrutura do banco de dados para api_test
CREATE DATABASE IF NOT EXISTS `api_test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `api_test`;

-- Copiando estrutura para tabela api_test.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nomeum` varchar(255) NOT NULL,
  `nomedois` varchar(255) NOT NULL,
  `registrounico` varchar(255) NOT NULL,
  `registrogeral` varchar(255) NOT NULL,
  `dataorigem` date NOT NULL,
  `site` varchar(50) DEFAULT NULL,
  `tipo` varchar(1) NOT NULL,
  `situacao` int NOT NULL,
  `datahorainc` datetime NOT NULL,
  `datahoraalt` datetime DEFAULT NULL,
  `datahoradel` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela api_test.cliente: ~5 rows (aproximadamente)
INSERT INTO `cliente` (`id`, `nomeum`, `nomedois`, `registrounico`, `registrogeral`, `dataorigem`, `site`, `tipo`, `situacao`, `datahorainc`, `datahoraalt`, `datahoradel`) VALUES
	(1, 'CLIENTE 1', 'CLIENTE 1', '23958403000143', '4412205885', '2017-01-23', 'www.heitoredanielaassessoriajuridicaltda.com.br', 'J', 1, '2022-07-27 12:54:59', '2022-07-27 12:55:01', '2022-07-27 12:55:02'),
	(2, 'CLIENTE 2', 'CLIENTE 2', '58904448000110', '1112568375', '2017-01-23', 'www.vitoriaegaelcontabilltda.com.br', 'J', 1, '2022-07-27 12:54:59', '2022-07-27 12:55:01', '2022-07-27 12:55:02'),
	(3, 'CLIENTE 3', 'CLIENTE 3', '25249650902', '488851294', '2017-01-23', NULL, 'F', 1, '2022-07-27 12:54:59', '2022-07-27 12:55:01', '2022-07-27 12:55:02'),
	(4, 'CLIENTE 4', 'CLIENTE 4', '20024439983', '388894179', '2017-01-23', NULL, 'F', 0, '2022-07-27 12:54:59', '2022-07-27 12:55:01', '2022-07-27 12:55:02'),
	(5, 'CLIENTE 5', 'CLIENTE 5', '93914568984', '93914568984', '2017-01-23', NULL, 'F', 1, '2022-07-27 12:54:59', '2022-07-27 12:55:01', '2022-07-27 12:55:02');

-- Copiando estrutura para tabela api_test.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `situacao` int NOT NULL,
  `datahorasyc` datetime NOT NULL,
  `datahorainc` datetime NOT NULL,
  `datahoraalt` datetime DEFAULT NULL,
  `datahoradel` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela api_test.usuario: ~5 rows (aproximadamente)
INSERT INTO `usuario` (`id`, `email`, `password`, `situacao`, `datahorasyc`, `datahorainc`, `datahoraalt`, `datahoradel`) VALUES
	(1, 'lobortis.augue@aol.edu', '123321', 0, '2022-05-18 16:42:01', '2022-05-18 16:43:02', '2022-05-18 16:44:03', '2022-05-18 16:45:04'),
	(2, 'pede.malesuada@aol.ca', '$2a$10$9PW4.CwlS8iGsVcPlHkmWeBZhyxjEZioHhEN9z6mw/B10BMxmNwdK', 0, '2022-05-18 16:42:01', '2022-05-18 16:43:02', '2022-05-18 16:44:03', '2022-05-18 16:45:04'),
	(3, 'pretium.et@outlook.ca', '123321', 0, '2022-05-18 16:42:01', '2022-05-18 16:43:02', '2022-05-18 16:44:03', '2022-05-18 16:45:04'),
	(4, 'etiam.gravida@google.ca', '123321', 0, '2022-05-18 16:42:01', '2022-05-18 16:43:02', '2022-05-18 16:44:03', '2022-05-18 16:45:04'),
	(5, 'eu.lacus@icloud.ca', '123321', 0, '2022-05-18 16:42:01', '2022-05-18 16:43:02', '2022-05-18 16:44:03', '2022-05-18 16:45:04');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
