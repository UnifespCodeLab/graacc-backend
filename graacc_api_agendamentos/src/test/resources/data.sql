INSERT INTO Paciente (nome) VALUES ('João Silva Costa');
INSERT INTO Paciente (nome) VALUES ('Maria Oliveira Santos');

INSERT INTO Agendamento (titulo, descricao, data, local, idPaciente)
VALUES
    ('Consulta A', 'Doutor Carlos Miguel', '2024-01-19 15:30:45', 'Sala 3 - Predio A', 1),
    ('Consulta B', 'Doutora Mariana Silva', '2024-01-21 15:30:00', 'Sala 5 - Predio A', 1),
    ('Consulta C', 'Doutora Mariana Silva', '2024-01-21 13:30:00', 'Sala 5 - Predio A', 2);