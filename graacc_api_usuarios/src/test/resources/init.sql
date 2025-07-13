CREATE SCHEMA IF NOT EXISTS usuarios;

-- USE usuarios;

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

INSERT INTO Agendamento (titulo, descricao, data, local, id_paciente)
VALUES
    ('Consulta A', 'Doutor Carlos Miguel', '2024-01-19 15:30:45', 'Sala 3 - Predio A', 1),
    ('Consulta B', 'Doutora Mariana Silva', '2024-01-21 15:30:00', 'Sala 5 - Predio A', 1),
    ('Consulta C', 'Doutora Mariana Silva', '2024-01-21 13:30:00', 'Sala 5 - Predio A', 2);

INSERT INTO Paciente (nome) VALUES ('João Silva Costa');
INSERT INTO Paciente (nome) VALUES ('Maria Oliveira Santos');

--INSERT INTO Usuario (nome, email, senha, cadastro_confirmado, role, id_paciente)
--VALUES ('Carlos Santos', 'carlos.santos@example.com', 'senha123', false, 'ROLE_USUARIO', 2);

--INSERT INTO Usuario (nome, email, senha, cadastro_confirmado, role, id_paciente)
--VALUES ('Ana Costa', 'ana.costa@example.com', '$2a$10$dUb.8/5D42Eg11Vsxr7W0eTv5UIeZFGP1OpWpHf4n0u77EnjNjEBS', false, 'ROLE_USUARIO', 1);
