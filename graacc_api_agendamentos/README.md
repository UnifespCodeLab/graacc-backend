# GRAACC API Agendamentos
Microsserviço para gerenciamento dos Agendamentos do Projeto Agendinha do GRAACC 


--------------------
## 📋 Requisitos
* ☕ **Java:** versão 17 ou superior
* 🛠️ **Maven:** versão 3.6.3 ou superior

## 📍 Tecnologias
* **Spring Boot**
* **Maven**: gerenciador de dependências;
* **PostgresSQL**: banco de dados relacional;

## ⚙️ Configuração
1. Clone o repositório
``` bash
git clone 
```

2. Compile o projeto com Maven
``` bash
mvn clean install
```

3. Configure o arquivo .env na pasta raiz do projeto com as configurações de ambiente:

|   Configuracao    |                                            Descricao                                             |
|:-----------------:|:------------------------------------------------------------------------------------------------:|
|   DATABASE_URL    | URL de conexão do banco de dados, no formato 'jdbc:postgresql://localhost:5432/NOME_BANCO_DADOS' | 
| DATABASE_USERNAME |                                    Usuário do banco de dados                                     | 
| DATABASE_PASSWORD |                                     Senha do banco de dados                                      | 
|  SECURITY_TOKEN   |                                        Token de Segurança                                        | 
| SECURITY_EMISSOR  |                       Emissor do Serviço para Validar tokens de Segurança                        | 

## 📑 Endpoints

| Método | Endpoint                 |                     Descrição                     | Requer Token de Autorização? |   Restrição de Acesso   |
|:------:|:-------------------------|:-------------------------------------------------:|:----------------------------:|:-----------------------:|
|  GET   | `/swagger-ui/index.html` |           Documentação completa da API            |              ❌               |                         |
|  GET   | `/hello`                 |                    Hello World                    |              ❌               |                         |
|  POST  | `/pacientes`             |                Adicionar Paciente                 |              ✅               |      ADMINISTRADOR      |  
|  PUT   | `/pacientes/{id}`        |                  Editar Paciente                  |              ✅               |      ADMINISTRADOR      |  
| DELETE | `/pacientes/{id}`        |                 Deletar Paciente                  |              ✅               |      ADMINISTRADOR      |  
|  GET   | `/pacientes`             |          Obter lista de todos Pacientes           |              ✅               |      ADMINISTRADOR      |
|  POST  | `/pacientes/pesquisar`   |             Buscar Paciente pelo Nome             |              ❌               | ADMINISTRADOR E USUARIO |
|  POST  | `/agendamentos`          |               Adicionar Agendamento               |              ✅               |      ADMINISTRADOR      |  
|  PUT   | `/agendamentos/{id}`     |                Editar Agendamento                 |              ✅               |      ADMINISTRADOR      |  
| DELETE | `/agendamentos/{id}`     |                Deletar Agendamento                |              ✅               |      ADMINISTRADOR      |  
|  GET   | `/agendamentos`          |         Obter lista de todos Agendamentos         |              ✅               |      ADMINISTRADOR      |  
|  GET   | `/agendamentos/{id}`     |           Listar Agendamento específico           |              ✅               |      ADMINISTRADOR      |  
|  GET   | `/agendamentos/usuario`  | Obter lista de Agendamentos do usuário específico |              ✅               |         USUARIO         |  

Acesse a documentação completa dos endpoints em: `http://localhost:8080/swagger-ui/index.html`
