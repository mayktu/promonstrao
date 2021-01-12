DROP
DATABASE IF EXISTS ProMonstrao;

CREATE
DATABASE ProMonstrao;

USE ProMonstrao;

CREATE TABLE usuario
(
    id    BIGINT       NOT NULL AUTO_INCREMENT,
    email VARCHAR(256) NOT NULL UNIQUE,
    senha VARCHAR(64)  NOT NULL,
    papel VARCHAR(6)   NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE site
(
    id       BIGINT       NOT NULL,
    email    VARCHAR(256) NOT NULL UNIQUE,
    nome     VARCHAR(256) NOT NULL,
    endereco VARCHAR(256) NOT NULL,
    telefone VARCHAR(30),
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES usuario (id) ON DELETE CASCADE
);

CREATE TABLE teatro
(
    id     BIGINT       NOT NULL,
    email  VARCHAR(256) NOT NULL,
    cnpj   VARCHAR(18)  NOT NULL UNIQUE, -- Exemplo de CNPJ: 11.536.612/0001-10 (18 caracteres)
    nome   VARCHAR(256) NOT NULL,
    cidade VARCHAR(90),
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES usuario (id) ON DELETE CASCADE
);

CREATE TABLE promocao
(
    id        BIGINT       NOT NULL AUTO_INCREMENT,
    id_site   BIGINT       NOT NULL,
    id_teatro BIGINT       NOT NULL,
    nome_peca VARCHAR(256) NOT NULL,
    preco     FLOAT        NOT NULL,
    data_peca VARCHAR(64)  NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_site) REFERENCES site (id) ON DELETE CASCADE,
    FOREIGN KEY (id_teatro) REFERENCES teatro (id) ON DELETE CASCADE
);

-- Admins
INSERT INTO usuario (id, email, senha, papel)
VALUES (1, 'admin1@admin.com', 'admin1', 'ADMIN');
INSERT INTO usuario (id, email, senha, papel)
VALUES (2, 'admin2@admin.com', 'admin2', 'ADMIN');

-- Sites
INSERT INTO usuario (id, email, senha, papel)
VALUES (3, 'site1@site.com', 'site1', 'SITE');
INSERT INTO usuario (id, email, senha, papel)
VALUES (4, 'site2@site.com', 'site2', 'SITE');
INSERT INTO usuario (id, email, senha, papel)
VALUES (5, 'site3@site.com', 'site3', 'SITE');

INSERT INTO site (id, email, nome, endereco, telefone)
VALUES (3, 'site1@site.com', 'Site Monstrao1', 'http://site.com', '169134134');
INSERT INTO site (id, email, nome, endereco, telefone)
VALUES (4, 'site2@site.com', 'Site Monstrao2', 'http://site.com', '169132341');
INSERT INTO site (id, email, nome, endereco, telefone)
VALUES (5, 'site3@site.com', 'Site Monstrao3', 'http://site.com', '234694124');

-- Teatros
INSERT INTO usuario (id, email, senha, papel)
VALUES (6, 'teatro1@teatro.com', 'teatro1', 'TEATRO');
INSERT INTO usuario (id, email, senha, papel)
VALUES (7, 'teatro2@teatro.com', 'teatro2', 'TEATRO');
INSERT INTO usuario (id, email, senha, papel)
VALUES (8, 'teatro3@teatro.com', 'teatro3', 'TEATRO');
INSERT INTO usuario (id, email, senha, papel)
VALUES (9, 'teatro4@teatro.com', 'teatro4', 'TEATRO');
INSERT INTO usuario (id, email, senha, papel)
VALUES (10, 'teatro5@teatro.com', 'teatro5', 'TEATRO');

INSERT INTO usuario (id, email, senha, papel)
VALUES (11, 'teatro55@teatro.com', 'teatro5', 'TEATRO');

INSERT INTO teatro (id, email, cnpj, nome, cidade)
VALUES (6, 'teatro1@teatro.com', '01.234.537/1211-23', 'Teatro Monstrao1', 'Sao Carlos City');
INSERT INTO teatro (id, email, cnpj, nome, cidade)
VALUES (7, 'teatro2@teatro.com', '01.234.563/1301-23', 'Teatro Monstrao2', 'Sao Carlos City');
INSERT INTO teatro (id, email, cnpj, nome, cidade)
VALUES (11, 'teatro55@teatro.com', '01.422.591/3531-23', 'Teatro Monstrao3', 'Araguari');
INSERT INTO teatro (id, email, cnpj, nome, cidade)
VALUES (13, 'teatro44@teatro.com', '01.264.767/8411-23', 'Teatro Monstrao4', 'Uberaba');
INSERT INTO teatro (id, email, cnpj, nome, cidade)
VALUES (10, 'teatro5@teatro.com', '01.934.557/8901-23', 'Teatro Monstrao5', 'Sao Carlos City');

INSERT INTO promocao (id_site, id_teatro, nome_peca, preco, data_peca)
VALUES (3, 6, 'Peca Monstrao de Natal', 34.50, '2020-12-18');
INSERT INTO promocao (id_site, id_teatro, nome_peca, preco, data_peca)
VALUES (4, 7, 'Peca Monstrao de Ano Novo', 34.50, '2020-12-05');
