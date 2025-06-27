CREATE SCHEMA IF NOT EXISTS usuarios;

USE usuarios;

CREATE TABLE IF NOT EXISTS Paciente (
    idPaciente INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Usuario (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    cadastroConfirmado BOOLEAN DEFAULT FALSE,
    role varchar(20) NOT NULL,
    idPaciente INT,
    FOREIGN KEY (idPaciente)
        REFERENCES Paciente(idPaciente)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);