CREATE DATABASE IF NOT EXISTS barestv;
USE barestv;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario (
    nick        CHAR(10) PRIMARY KEY,
    # clave: SHA-2, 512 bits, SHA2('laClaveSinCifrar', 512)
    clave       CHAR(128) NOT NULL,
    # permisos: 0 = usuario limitado; 1 = administrador
    permisos    TINYINT NOT NULL
);

DROP TABLE IF EXISTS bar;
CREATE TABLE bar (
    nickbar     CHAR(10) PRIMARY KEY,
    nombre      CHAR(50) NOT NULL,
    descrbar    VARCHAR(500),
    # activado: 0 = deshabilitado; 1 = habilitado
    activado    TINYINT NOT NULL,
    lat         DECIMAL(10, 8) NOT NULL,
    lng         DECIMAL(11, 8) NOT NULL,
    direccion   CHAR(150) NOT NULL,
    urlimagen   VARCHAR(300),
    FOREIGN KEY (nickbar) REFERENCES usuario(nick)
);

DROP TABLE IF EXISTS programa;
CREATE TABLE programa (
    titulo      CHAR(50) NOT NULL,
    bar         CHAR(10) NOT NULL,
    descr       VARCHAR(1000),
    # destacado: 0 = no; 1 = sí
    destacado   TINYINT NOT NULL,
    inicio      TIMESTAMP NOT NULL,
    fin         TIMESTAMP NOT NULL,
    cat         CHAR(50) NOT NULL,
    PRIMARY KEY (titulo, bar),
    FOREIGN KEY (bar) REFERENCES bar(nickbar),
    FOREIGN KEY (cat) REFERENCES categoria(nombrecat)
);

DROP TABLE IF EXISTS categoria;
CREATE TABLE categoria (
    nombrecat   CHAR(50) PRIMARY KEY
);

SET FOREIGN_KEY_CHECKS = 1;

# usuario administrador
INSERT INTO usuario(nick, clave, permisos) VALUES ('admin', SHA2('nimda', 512), 1);

# datos de ejemplo
INSERT INTO usuario(nick, clave, permisos) VALUES ('barejemplo', SHA2('barejemplo', 512), 0);
INSERT INTO bar(nickbar, nombre, descrbar, activado, lat, lng, direccion, urlimagen) VALUES ('barejemplo', 'Un bar de ejemplo', 'El mejor bar de tapas de ejemplo, con la mejor programación de ejemplo.', 1, 41.65111400, -0.89438000, 'Calle Manuel Escoriaza y Fabro, 51 - 50010 Zaragoza', 'https://media-cdn.tripadvisor.com/media/photo-s/09/1e/e2/45/la-fusteria-bar-de-tapas.jpg');
INSERT INTO categoria(nombrecat) VALUES ('ejemplo');
INSERT INTO programa(titulo, bar, descr, destacado, inicio, fin, cat) VALUES ('Los Teleñecos', 'barejemplo', 'Algunos Muppets famosos son Kermit the Frog (la Rana Gustavo en España o La Rana René en Latinoamérica), Miss Piggy conocida como Peggy, Fozzie el oso, El cocinero sueco, El gran Gonzo, Rowlf el perro Ruffo y Animal. En la TV, el programa más famoso es El Show de los Muppets, aunque han existido algunos otros, incluido un late night. La rana René (o Gustavo) además de ser el más famoso dentro del mundo de Los Muppets, también se le puede ver en varias ocasiones en el famoso programa infantil Sesame Street, también con personajes y creaciones de Jim Henson.', 1, '1998-04-01 14:20:00.00000', '1998-04-01 17:00:00.00000', 'ejemplo');
