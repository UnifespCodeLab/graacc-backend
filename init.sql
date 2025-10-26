CREATE SCHEMA IF NOT EXISTS usuarios;

CREATE TABLE IF NOT EXISTS Paciente (
    id_paciente SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Usuario (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    cadastro_confirmado BOOLEAN DEFAULT FALSE,
    role varchar(20) NOT NULL,
    id_paciente INT,
    FOREIGN KEY (id_paciente)
        REFERENCES Paciente(id_paciente)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Agendamento (
     id_agendamento SERIAL PRIMARY KEY,
     titulo VARCHAR(100) NOT NULL,
     descricao VARCHAR(255) NOT NULL,
     data TIMESTAMP NOT NULL,
     local VARCHAR(100) NOT NULL,
     id_paciente INT NOT NULL,
     FOREIGN KEY (id_paciente)
         REFERENCES Paciente(id_paciente)
         ON DELETE SET NULL
         ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Notificacao (
     id_notificacao SERIAL PRIMARY KEY,
     lida BOOLEAN NOT NULL,
     data TIMESTAMP NOT NULL,
     id_agendamento INT NOT NULL,
     FOREIGN KEY (id_agendamento)
         REFERENCES Agendamento(id_agendamento)
         ON DELETE SET NULL
         ON UPDATE CASCADE
);

INSERT INTO Paciente (nome) VALUES ('João Silva Costa');
INSERT INTO Paciente (nome) VALUES ('Maria Oliveira Santos');
