DROP TABLE IF EXISTS usuario;

CREATE TABLE IF NOT EXISTS usuario (
  id INT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  situacao INT NOT NULL,
  datahorasyc DATETIME NOT NULL,
  datahorainc DATETIME NOT NULL,
  datahoraalt DATETIME,
  datahoradel DATETIME
);

INSERT INTO `usuario` (
  `email`, `password`, `situacao`, `datahorasyc`,
  `datahorainc`, `datahoraalt`, `datahoradel`
)
VALUES
  (
    'lobortis.augue@aol.edu', '123321',
    0, '2022-05-18 16:42:01', '2022-05-18 16:43:02',
    '2022-05-18 16:44:03', '2022-05-18 16:45:04'
  ),
  (
    'pede.malesuada@aol.ca', '123321',
    0, '2022-05-18 16:42:01', '2022-05-18 16:43:02',
    '2022-05-18 16:44:03', '2022-05-18 16:45:04'
  ),
  (
    'pretium.et@outlook.ca', '123321',
    0, '2022-05-18 16:42:01', '2022-05-18 16:43:02',
    '2022-05-18 16:44:03', '2022-05-18 16:45:04'
  ),
  (
    'etiam.gravida@google.ca', '123321',
    0, '2022-05-18 16:42:01', '2022-05-18 16:43:02',
    '2022-05-18 16:44:03', '2022-05-18 16:45:04'
  ),
  (
    'eu.lacus@icloud.ca', '123321',
    0, '2022-05-18 16:42:01', '2022-05-18 16:43:02',
    '2022-05-18 16:44:03', '2022-05-18 16:45:04'
  );
