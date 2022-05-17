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
  `datahorainc`, `datahoraalt`
)
VALUES
  (
    'lobortis.augue@aol.edu', '123321',
    0, '2022-09-08 05:16:31', '2022-09-14 18:08:30',
    '2023-02-14 17:41:41'
  ),
  (
    'pede.malesuada@aol.ca', '123321',
    0, '2023-02-12 09:29:35', '2022-07-08 17:18:50',
    '2022-08-02 23:30:44'
  ),
  (
    'pretium.et@outlook.ca', '123321',
    1, '2022-09-19 21:56:23', '2022-01-27 07:57:47',
    '2022-12-28 11:32:22'
  ),
  (
    'etiam.gravida@google.ca', '123321',
    0, '2021-12-19 02:24:07', '2021-06-14 22:50:58',
    '2022-05-13 21:45:58'
  ),
  (
    'eu.lacus@icloud.ca', '123321',
    1, '2021-06-20 02:04:27', '2021-08-02 08:08:39',
    '2021-05-23 11:19:47'
  );
