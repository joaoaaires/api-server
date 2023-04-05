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
DROP DATABASE IF EXISTS `api`;
CREATE DATABASE IF NOT EXISTS `api` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `api`;

-- Copiando estrutura para tabela api.cliente
DROP TABLE IF EXISTS `cliente`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela api.cliente: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela api.usuario
DROP TABLE IF EXISTS `usuario`;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela api.usuario: ~1 rows (aproximadamente)
INSERT INTO `usuario` (`id`, `email`, `password`, `situacao`, `datahorasyc`, `datahorainc`, `datahoraalt`, `datahoradel`) VALUES
	(1, 'joao.aires@gmail.com', '$2a$10$bnlP4puHInLFra1/HcdK/OC7p6I9PTumKwV5zkEVLcH46ERQsdE1K', 1, '2023-02-05 16:58:39', '2023-02-05 16:58:39', NULL, NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
