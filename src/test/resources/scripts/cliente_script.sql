-- --------------------------------------------------------
-- Servidor:                     localhost
-- Versão do servidor:           8.0.29 - MySQL Community Server - GPL
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.0.0.6468
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Copiando estrutura para tabela api_test.cliente
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
) ENGINE=InnoDB;

-- Copiando dados para a tabela api_test.cliente: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` (`id`, `nomeum`, `nomedois`, `registrounico`, `registrogeral`, `dataorigem`, `site`, `tipo`, `situacao`, `datahorainc`, `datahoraalt`, `datahoradel`) VALUES
	(1, 'CLIENTE 1', 'CLIENTE 1', '23958403000143', '4412205885', '2017-01-23', 'www.heitoredanielaassessoriajuridicaltda.com.br', 'J', 1, '2022-07-27 12:54:59', '2022-07-27 12:55:01', '2022-07-27 12:55:02'),
	(2, 'CLIENTE 2', 'CLIENTE 2', '58904448000110', '1112568375', '2017-01-23', 'www.vitoriaegaelcontabilltda.com.br', 'J', 1, '2022-07-27 12:54:59', '2022-07-27 12:55:01', '2022-07-27 12:55:02'),
	(3, 'CLIENTE 3', 'CLIENTE 3', '25249650902', '488851294', '2017-01-23', NULL, 'F', 1, '2022-07-27 12:54:59', '2022-07-27 12:55:01', '2022-07-27 12:55:02'),
	(4, 'CLIENTE 4', 'CLIENTE 4', '20024439983', '388894179', '2017-01-23', NULL, 'F', 0, '2022-07-27 12:54:59', '2022-07-27 12:55:01', '2022-07-27 12:55:02'),
	(5, 'CLIENTE 5', 'CLIENTE 5', '93914568984', '93914568984', '2017-01-23', NULL, 'F', 1, '2022-07-27 12:54:59', '2022-07-27 12:55:01', '2022-07-27 12:55:02');

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
