CREATE TABLE IF NOT EXISTS Paciente (
    id_paciente SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
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

INSERT INTO Agendamento (titulo, descricao, data, local, id_paciente)
VALUES
    ('Consulta A', 'Doutor Carlos Miguel', '2024-01-19 15:30:45', 'Sala 3 - Predio A', 1),
    ('Consulta B', 'Doutora Mariana Silva', '2024-01-21 15:30:00', 'Sala 5 - Predio A', 1),
    ('Consulta C', 'Doutora Mariana Silva', '2024-01-21 13:30:00', 'Sala 5 - Predio A', 2);
