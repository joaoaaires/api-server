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

-- Copiando estrutura para tabela api_test.usuario
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