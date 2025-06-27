CREATE TABLE IF NOT EXISTS Paciente (
    idPaciente INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Agendamento (
     idAgendamento INT AUTO_INCREMENT PRIMARY KEY,
     titulo VARCHAR(100) NOT NULL,
     descricao VARCHAR(255) NOT NULL,
     data TIMESTAMP NOT NULL,
     local VARCHAR(100) NOT NULL,
     idPaciente INT NOT NULL,
     FOREIGN KEY (idPaciente)
         REFERENCES Paciente(idPaciente)
         ON DELETE SET NULL
         ON UPDATE CASCADE
);
