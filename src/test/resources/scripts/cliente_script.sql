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

-- Copiando estrutura para tabela api_test.cliente
DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nomeum` varchar(255) NOT NULL,
  `nomedois` varchar(255) NOT NULL,
  `registrounico` varchar(255) NOT NULL,
  `registrogeral` varchar(255) NOT NULL,
  `dataorigem` date NOT NULL,
  `site` varchar(50) DEFAULT NULL,
  `tipo` varchar(1) NOT NULL,
  `situacao` int(11) NOT NULL,
  `datahorainc` datetime NOT NULL,
  `datahoraalt` datetime DEFAULT NULL,
  `datahoradel` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- Copiando dados para a tabela api_test.cliente: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` (`id`, `nomeum`, `nomedois`, `registrounico`, `registrogeral`, `dataorigem`, `site`, `tipo`, `situacao`, `datahorainc`, `datahoraalt`, `datahoradel`) VALUES
	(1, 'Heitor e Daniela Assessoria Jurídica Ltda', 'Heitor e Daniela Assessoria Jurídica Ltda', '23958403000143', '4412205885', '2017-01-23', 'www.heitoredanielaassessoriajuridicaltda.com.br', 'J', 1, '2022-07-27 12:54:59', '2022-07-27 12:55:01', '2022-07-27 12:55:02'),
	(2, 'Vitória e Gael Contábil Ltda', 'Vitória e Gael Contábil Ltda', '58904448000110', '1112568375', '2017-01-23', 'www.vitoriaegaelcontabilltda.com.br', 'J', 1, '2022-07-27 12:54:59', '2022-07-27 12:55:01', '2022-07-27 12:55:02'),
	(3, 'Sebastiana', 'Débora Liz Moraes', '25249650902', '488851294', '2017-01-23', NULL, 'F', 1, '2022-07-27 12:54:59', '2022-07-27 12:55:01', '2022-07-27 12:55:02'),
	(4, 'Mariane', 'Sueli Silva', '20024439983', '388894179', '2017-01-23', NULL, 'F', 0, '2022-07-27 12:54:59', '2022-07-27 12:55:01', '2022-07-27 12:55:02'),
	(5, 'Nina', 'Catarina Alice Dias', '93914568984', '93914568984', '2017-01-23', NULL, 'F', 1, '2022-07-27 12:54:59', '2022-07-27 12:55:01', '2022-07-27 12:55:02');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
