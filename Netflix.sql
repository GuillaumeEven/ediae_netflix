DROP DATABASE IF EXISTS Netflix;

CREATE DATABASE Netflix;
USE Netflix;

CREATE TABLE Pais (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE Clasificacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Genero (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Filmografia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    fecha_estreno DATE,
    sinopsis TEXT,
    pais_id INT,
    clasificacion_id INT,
    FOREIGN KEY (pais_id) REFERENCES Pais(id),
    FOREIGN KEY (clasificacion_id) REFERENCES Clasificacion(id)
);

CREATE TABLE Film_Genero (
    film_id INT,
    genero_id INT,
    PRIMARY KEY (film_id, genero_id),
    FOREIGN KEY (film_id) REFERENCES Filmografia(id),
    FOREIGN KEY (genero_id) REFERENCES Genero(id)
);

CREATE TABLE Tipo_suscripcion (
    tipo_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    precio DECIMAL(10,2) NOT NULL,
    duracion_meses INT NOT NULL
);

CREATE TABLE Cuenta (
    id_cuenta INT AUTO_INCREMENT PRIMARY KEY,
    tipo_cuenta VARCHAR(50),
    nombre VARCHAR(150) NOT NULL,
    password_hash VARCHAR(255) NOT NULL
);

CREATE TABLE Suscripcion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cuenta_id INT NOT NULL,
    tipo_id INT NOT NULL,
    fecha_contratacion DATE NOT NULL,
    fecha_fin DATE,
    FOREIGN KEY (cuenta_id) REFERENCES Cuenta(id_cuenta),
    FOREIGN KEY (tipo_id) REFERENCES Tipo_suscripcion(tipo_id)
);

CREATE TABLE Factura (
    num_factura INT AUTO_INCREMENT PRIMARY KEY,
    suscripcion_id INT,
    cuenta_id INT,
    importe_pvp DECIMAL(10,2) NOT NULL,
    metodo_pago VARCHAR(50),
    fecha_factura DATE,
    FOREIGN KEY (suscripcion_id) REFERENCES Suscripcion(id),
    FOREIGN KEY (cuenta_id) REFERENCES Cuenta(id_cuenta)
);

CREATE TABLE Acceso (
    id_acceso INT AUTO_INCREMENT PRIMARY KEY,
    id_filmografia INT NOT NULL,
    id_cuenta INT,
    fecha_acceso DATETIME DEFAULT CURRENT_TIMESTAMP,
    tipo_suscripcion_id INT,
    FOREIGN KEY (id_filmografia) REFERENCES Filmografia(id),
    FOREIGN KEY (id_cuenta) REFERENCES Cuenta(id_cuenta),
    FOREIGN KEY (tipo_suscripcion_id) REFERENCES Tipo_suscripcion(tipo_id)
);

CREATE TABLE Reparto (
    id_reparto INT AUTO_INCREMENT PRIMARY KEY,
    id_filmografia INT NOT NULL,
    nombre_actor VARCHAR(150) NOT NULL,
    papel VARCHAR(150),
    FOREIGN KEY (id_filmografia) REFERENCES Filmografia(id)
);

-- Consultas: listar todo en cada tabla


-- Datos de ejemplo (5 registros por tabla)
INSERT INTO Pais (nombre) VALUES
('Spain'), ('USA'), ('UK'), ('France'), ('Japan');

INSERT INTO Clasificacion (nombre) VALUES
('G'), ('PG'), ('PG-13'), ('R'), ('NR');

INSERT INTO Genero (nombre) VALUES
('Drama'), ('Comedy'), ('Action'), ('Sci-Fi'), ('Thriller');

INSERT INTO Tipo_suscripcion (nombre, precio, duracion_meses) VALUES
('Basic', 5.99, 1),
('Standard', 9.99, 1),
('Premium', 13.99, 1),
('Family', 17.99, 1),
('Student', 4.99, 1);

INSERT INTO Cuenta (tipo_cuenta, nombre, password_hash) VALUES
('individual', 'Ana Gomez', 'hash_ana'),
('individual', 'Luis Perez', 'hash_luis'),
('family', 'Casa Martinez', 'hash_martinez'),
('individual', 'Sofia Ruiz', 'hash_sofia'),
('individual', 'Kenji Sato', 'hash_kenji');

INSERT INTO Filmografia (titulo, fecha_estreno, sinopsis, pais_id, clasificacion_id) VALUES
('El viaje', '2020-05-10', 'Un viaje emocional.', (SELECT id FROM Pais WHERE nombre='Spain'), (SELECT id FROM Clasificacion WHERE nombre='PG')),
('City Lights', '2018-11-20', 'Comedia romántica urbana.', (SELECT id FROM Pais WHERE nombre='USA'), (SELECT id FROM Clasificacion WHERE nombre='PG-13')),
('Night Chase', '2019-07-01', 'Acción trepidante.', (SELECT id FROM Pais WHERE nombre='UK'), (SELECT id FROM Clasificacion WHERE nombre='R')),
('Le rêve', '2021-02-14', 'Drama francés intenso.', (SELECT id FROM Pais WHERE nombre='France'), (SELECT id FROM Clasificacion WHERE nombre='G')),
('Robo Dawn', '2022-09-09', 'Ciencia ficción futurista.', (SELECT id FROM Pais WHERE nombre='Japan'), (SELECT id FROM Clasificacion WHERE nombre='PG-13'));

INSERT INTO Film_Genero (film_id, genero_id) VALUES
((SELECT id FROM Filmografia WHERE titulo='El viaje'), (SELECT id FROM Genero WHERE nombre='Drama')),
((SELECT id FROM Filmografia WHERE titulo='City Lights'), (SELECT id FROM Genero WHERE nombre='Comedy')),
((SELECT id FROM Filmografia WHERE titulo='Night Chase'), (SELECT id FROM Genero WHERE nombre='Action')),
((SELECT id FROM Filmografia WHERE titulo='Le rêve'), (SELECT id FROM Genero WHERE nombre='Drama')),
((SELECT id FROM Filmografia WHERE titulo='Robo Dawn'), (SELECT id FROM Genero WHERE nombre='Sci-Fi'));

INSERT INTO Suscripcion (cuenta_id, tipo_id, fecha_contratacion, fecha_fin) VALUES
((SELECT id_cuenta FROM Cuenta WHERE nombre='Ana Gomez'), (SELECT tipo_id FROM Tipo_suscripcion WHERE nombre='Premium'), '2023-01-01', '2024-01-01'),
((SELECT id_cuenta FROM Cuenta WHERE nombre='Luis Perez'), (SELECT tipo_id FROM Tipo_suscripcion WHERE nombre='Standard'), '2023-03-15', NULL),
((SELECT id_cuenta FROM Cuenta WHERE nombre='Casa Martinez'), (SELECT tipo_id FROM Tipo_suscripcion WHERE nombre='Family'), '2022-06-10', NULL),
((SELECT id_cuenta FROM Cuenta WHERE nombre='Sofia Ruiz'), (SELECT tipo_id FROM Tipo_suscripcion WHERE nombre='Basic'), '2024-01-10', NULL),
((SELECT id_cuenta FROM Cuenta WHERE nombre='Kenji Sato'), (SELECT tipo_id FROM Tipo_suscripcion WHERE nombre='Student'), '2024-02-01', NULL);

INSERT INTO Factura (suscripcion_id, cuenta_id, importe_pvp, metodo_pago, fecha_factura) VALUES
((SELECT id FROM Suscripcion WHERE cuenta_id=(SELECT id_cuenta FROM Cuenta WHERE nombre='Ana Gomez')), (SELECT id_cuenta FROM Cuenta WHERE nombre='Ana Gomez'), 13.99, 'card', '2023-01-02'),
((SELECT id FROM Suscripcion WHERE cuenta_id=(SELECT id_cuenta FROM Cuenta WHERE nombre='Luis Perez')), (SELECT id_cuenta FROM Cuenta WHERE nombre='Luis Perez'), 9.99, 'paypal', '2023-03-16'),
((SELECT id FROM Suscripcion WHERE cuenta_id=(SELECT id_cuenta FROM Cuenta WHERE nombre='Casa Martinez')), (SELECT id_cuenta FROM Cuenta WHERE nombre='Casa Martinez'), 17.99, 'card', '2022-06-11'),
((SELECT id FROM Suscripcion WHERE cuenta_id=(SELECT id_cuenta FROM Cuenta WHERE nombre='Sofia Ruiz')), (SELECT id_cuenta FROM Cuenta WHERE nombre='Sofia Ruiz'), 5.99, 'card', '2024-01-11'),
((SELECT id FROM Suscripcion WHERE cuenta_id=(SELECT id_cuenta FROM Cuenta WHERE nombre='Kenji Sato')), (SELECT id_cuenta FROM Cuenta WHERE nombre='Kenji Sato'), 4.99, 'card', '2024-02-02');

INSERT INTO Acceso (id_filmografia, id_cuenta, fecha_acceso, tipo_suscripcion_id) VALUES
((SELECT id FROM Filmografia WHERE titulo='El viaje'), (SELECT id_cuenta FROM Cuenta WHERE nombre='Ana Gomez'), '2024-01-05 20:00:00', (SELECT tipo_id FROM Tipo_suscripcion WHERE nombre='Premium')),
((SELECT id FROM Filmografia WHERE titulo='City Lights'), (SELECT id_cuenta FROM Cuenta WHERE nombre='Luis Perez'), '2024-02-10 21:30:00', (SELECT tipo_id FROM Tipo_suscripcion WHERE nombre='Standard')),
((SELECT id FROM Filmografia WHERE titulo='Night Chase'), (SELECT id_cuenta FROM Cuenta WHERE nombre='Casa Martinez'), '2024-03-12 22:00:00', (SELECT tipo_id FROM Tipo_suscripcion WHERE nombre='Family')),
((SELECT id FROM Filmografia WHERE titulo='Le rêve'), (SELECT id_cuenta FROM Cuenta WHERE nombre='Sofia Ruiz'), '2024-01-20 19:00:00', (SELECT tipo_id FROM Tipo_suscripcion WHERE nombre='Basic')),
((SELECT id FROM Filmografia WHERE titulo='Robo Dawn'), (SELECT id_cuenta FROM Cuenta WHERE nombre='Kenji Sato'), '2024-02-15 18:30:00', (SELECT tipo_id FROM Tipo_suscripcion WHERE nombre='Student'));

INSERT INTO Reparto (id_filmografia, nombre_actor, papel) VALUES
((SELECT id FROM Filmografia WHERE titulo='El viaje'), 'Marina López', 'Protagonista'),
((SELECT id FROM Filmografia WHERE titulo='City Lights'), 'John Carter', 'Compañero'),
((SELECT id FROM Filmografia WHERE titulo='Night Chase'), 'David Brown', 'Villano'),
((SELECT id FROM Filmografia WHERE titulo='Le rêve'), 'Claire Dubois', 'Protagonista'),
((SELECT id FROM Filmografia WHERE titulo='Robo Dawn'), 'Hiro Tanaka', 'Científico');

