INSERT INTO Paciente (nome) VALUES ('João Silva Costa');
INSERT INTO Paciente (nome) VALUES ('Maria Oliveira Santos');

INSERT INTO Usuario (nome, email, senha, cadastroConfirmado, role, idPaciente)
VALUES ('Carlos Santos', 'carlos.santos@example.com', 'senha123', false, 'ROLE_USUARIO', 2);

INSERT INTO Usuario (nome, email, senha, cadastroConfirmado, role, idPaciente)
VALUES ('Ana Costa', 'ana.costa@example.com', '$2a$10$dUb.8/5D42Eg11Vsxr7W0eTv5UIeZFGP1OpWpHf4n0u77EnjNjEBS', false, 'ROLE_USUARIO', 1);
