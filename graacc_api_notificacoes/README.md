# GRAACC API Notificações
Microsserviço para gerenciamento das Notificações do Projeto Agendinha do GRAACC

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

| Método | Endpoint                        |                              Descrição                               | Requer Token de Autorização? |   Restrição de Acesso   |
|:------:|:--------------------------------|:--------------------------------------------------------------------:|:----------------------------:|:-----------------------:|
|  GET   | `/swagger-ui/index.html`        |                     Documentação completa da API                     |              ❌               |                         |
|  GET   | `/hello`                        |                             Hello World                              |              ❌               |                         |
|  POST  | `/notificacoes`                 |          Adicionar Notificações a partir de um Agendamento           |              ✅               |      ADMINISTRADOR      |  
|  POST  | `/notificacoes/conjunto`        |    Adicionar Notificações a partir de um conjunto de Agendamentos    |              ✅               |      ADMINISTRADOR      |  
|  POST  | `/notificacoes/{id}/lida`       |                     Marca Notificação como lida                      |              ✅               | ADMINISTRADOR e USUARIO |  
| DELETE | `/notificacoes/{id}`            |                         Deletar Notificação                          |              ✅               |      ADMINISTRADOR      |  
|  POST  | `/notificacoes/{idAgendamento}` |            Obter lista de Notificações de um Agendamento             |              ✅               | ADMINISTRADOR e USUARIO |
|  POST  | `/notificacoes/naoLidas`        | Obter lista de Notificações nao Lidas de um conjunto de Agendamentos |              ✅               | ADMINISTRADOR e USUARIO |

Acesse a documentação completa dos endpoints em: `http://localhost:8080/swagger-ui/index.html`
