# GRAACC API Usuários
Microsserviço para gerenciamento dos Usuários do Projeto Agendinha do GRAACC.

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

| Método | Endpoint                 |             Descrição              | Requer Token de Autorização? | Restrição de Acesso |
|:------:|:-------------------------|:----------------------------------:|:----------------------------:|:-------------------:|
|  GET   | `/swagger-ui/index.html` |    Documentação completa da API    |              ❌               |                     |
|  GET   | `/hello`                 |            Hello World             |              ❌               |                     |
|  POST  | `/usuarios/registrar`    |         Registrar Usuário          |              ❌               |                     |  
|  POST  | `/usuarios/login`        |          Login do Usuário          |              ❌               |                     |  
|  POST  | `/usuarios/confirmar`    |   Confirmar cadastro do Usuário    |              ✅               |       USUARIO       |
|  GET   | `/usuarios`              |       Obter dados do Usuário       |              ✅               |       USUARIO       |
|  PUT   | `/usuarios`              |      Editar dados do Usuário       |              ✅               |       USUARIO       |
| DELETE | `/usuarios`              |      Deletar dados do Usuário      |              ✅               |       USUARIO       |
|  POST  | `/admin/registrar`       |  Registrar Usuário Administrador   |              ❌               |                     |  
|  POST  | `/admin/login`           |     Login do Usuário Administrador |              ❌               |                     |  

Acesse a documentação completa dos endpoints em: `http://localhost:8080/swagger-ui/index.html`
