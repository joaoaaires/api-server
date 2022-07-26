-- --------------------------------------------------------
-- Servidor:                     localhost
-- Versão do servidor:           10.6.5-MariaDB-1:10.6.5+maria~focal - mariadb.org binary distribution
-- OS do Servidor:               debian-linux-gnu
-- HeidiSQL Versão:              11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Copiando estrutura para tabela api_test.usuario
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `situacao` int(11) NOT NULL,
  `datahorasyc` datetime NOT NULL,
  `datahorainc` datetime NOT NULL,
  `datahoraalt` datetime DEFAULT NULL,
  `datahoradel` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Copiando dados para a tabela api_test.usuario: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`, `email`, `password`, `situacao`, `datahorasyc`, `datahorainc`, `datahoraalt`, `datahoradel`) VALUES
	(1, 'lobortis.augue@aol.edu', '123321', 0, '2022-05-18 16:42:01', '2022-05-18 16:43:02', '2022-05-18 16:44:03', '2022-05-18 16:45:04'),
	(2, 'pede.malesuada@aol.ca', '123321', 0, '2022-05-18 16:42:01', '2022-05-18 16:43:02', '2022-05-18 16:44:03', '2022-05-18 16:45:04'),
	(3, 'pretium.et@outlook.ca', '123321', 0, '2022-05-18 16:42:01', '2022-05-18 16:43:02', '2022-05-18 16:44:03', '2022-05-18 16:45:04'),
	(4, 'etiam.gravida@google.ca', '123321', 0, '2022-05-18 16:42:01', '2022-05-18 16:43:02', '2022-05-18 16:44:03', '2022-05-18 16:45:04'),
	(5, 'eu.lacus@icloud.ca', '123321', 0, '2022-05-18 16:42:01', '2022-05-18 16:43:02', '2022-05-18 16:44:03', '2022-05-18 16:45:04');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
