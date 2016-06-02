
DROP TABLE IF EXISTS Carro;
DROP TABLE IF EXISTS Compras_registradas;
DROP TABLE IF EXISTS Compra;
DROP TABLE IF EXISTS Clientes;
DROP TABLE IF EXISTS Cantidades;
DROP TABLE IF EXISTS Recogida;
DROP TABLE IF EXISTS Precios;
DROP TABLE IF EXISTS Presentaciones;
DROP TABLE IF EXISTS Productos;
DROP TABLE IF EXISTS Marcas;
DROP TABLE IF EXISTS Tipo_producto;

CREATE TABLE Clientes (
    id INT NOT NULL auto_increment,
    correo VARCHAR(255) NOT NULL,
    nombre VARCHAR(255),
    pass VARCHAR(255) NOT NULL,
    salt VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE Tipo_producto (
    id INT NOT NULL auto_increment,
    nombre VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
    ) ENGINE=INNODB;

CREATE TABLE Marcas (
    id INT NOT NULL auto_increment,
    nombre VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE Productos (
    id INT NOT NULL auto_increment,
    tipo_producto INT NOT NULL,
    denominacion VARCHAR(255) NOT NULL,
    marca INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (marca)
        REFERENCES Marcas(id),
    FOREIGN KEY (tipo_producto)
        REFERENCES Tipo_producto(id)
) ENGINE=INNODB;

CREATE TABLE Presentaciones (
    id INT NOT NULL auto_increment,
    producto INT NOT NULL,
    stock INT NOT NULL,
    envase ENUM('Brick','Paquete 6','Unidad','Botella','Peso','Malla','Caja'),
    PRIMARY KEY (id),
    FOREIGN KEY (producto)
        REFERENCES Productos(id)
) ENGINE=INNODB;

CREATE TABLE Cantidades (
    id_presentacion INT NOT NULL,
    tipo_cantidad ENUM('volumen','peso escurrido','peso neto','peso bruto','unidades'),
	cantidad VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_presentacion,tipo_cantidad),
    FOREIGN KEY (id_presentacion)
        REFERENCES Presentaciones(id)
) ENGINE=INNODB;

CREATE TABLE Carro (
    id_cliente INT NOT NULL,
    id_presentacion INT NOT NULL,
    unidades INT NOT NULL,
    PRIMARY KEY (id_cliente,id_presentacion),
    FOREIGN KEY (id_cliente)
        REFERENCES Clientes(id),
    FOREIGN KEY (id_presentacion)
        REFERENCES Presentaciones(id)
) ENGINE=INNODB;

CREATE TABLE Recogida (
    id INT NOT NULL auto_increment,
    lugar VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE Compra (
    id INT NOT NULL auto_increment,
    id_cliente INT NOT NULL,
    id_recogida INT NOT NULL,
    importe FLOAT NOT NULL,
    fecha DATETIME NOT NULL,
    recogida DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_cliente)
        REFERENCES Clientes(id),
    FOREIGN KEY (id_recogida)
        REFERENCES Recogida(id)

) ENGINE=INNODB;

CREATE TABLE Precios (
    id_presentacion INT NOT NULL,
    fecha_inicio DATETIME,
    fecha_fin DATETIME,
    precio FLOAT NOT NULL,
    PRIMARY KEY (id_presentacion,fecha_inicio,fecha_fin),
    FOREIGN KEY (id_presentacion)
        REFERENCES Presentaciones(id)
) ENGINE=INNODB;

CREATE TABLE Compras_registradas (
    id_compra INT NOT NULL ,
    id_presentacion INT NOT NULL ,
    unidades INT NOT NULL ,
    PRIMARY KEY (id_compra,id_presentacion),
    FOREIGN KEY (id_presentacion)
        REFERENCES Presentaciones(id),
    FOREIGN KEY (id_compra)
        REFERENCES Compra(id)
) ENGINE=INNODB;
