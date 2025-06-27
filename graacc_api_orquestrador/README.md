# GRAACC API Orquestrador
Microsserviço para gerenciamento das chamadas das APISs do Projeto Agendinha do GRAACC.

--------------------
## 📋 Requisitos
* ☕ **Java:** versão 17 ou superior
* 🛠️ **Maven:** versão 3.6.3 ou superior

## 📍 Tecnologias
* **Spring Boot**
* **Maven**: gerenciador de dependências;

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

|     Configuracao     |                   Descricao                   |
|:--------------------:|:---------------------------------------------:|
|   URL_MS_USUARIOS    |   URL de conexão com microsserviço Usuários   | 
| URL_MS_AGENDAMENTOS  | URL de conexão com microsserviço Agendamentos | 
| URL_MS_NOTIFICACOES  | URL de conexão com microsserviço Notificações | 

## 📑 Endpoints

| Método | Endpoint                 |             Descrição              | Requer Token de Autorização? | Restrição de Acesso |
|:------:|:-------------------------|:----------------------------------:|:----------------------------:|:-------------------:|
|  GET   | `/swagger-ui/index.html` |    Documentação completa da API    |              ❌               |                     |
|  GET   | `/hello`                 |            Hello World             |              ❌               |                     |

## 📑 Endpoints do Administrador


| Método | Endpoint                                            |            Descrição            | Requer Token de Autorização? | Restrição de Acesso |
|:------:|:----------------------------------------------------|:-------------------------------:|:----------------------------:|:-------------------:|
|  POST  | `/graacc/api/auth/admin/registrar`                  |     Registrar Administrador     |              ❌               |                     |  
|  POST  | `/graacc/api/auth/admin/login`                      |     Login do Administrador      |              ❌               |                     |  
|  POST  | `/graacc/api/admin/pacientes/adicionar`             |       Adicionar Paciente        |              ✅               |    ADMINISTRADOR    |
|  POST  | `/graacc/api/admin/pacientes/pesquisar`             |   Pesquisar Paciente por nome   |              ✅               |    ADMINISTRADOR    |
|  GET   | `/graacc/api/admin/pacientes/listar/todos`          |       Lista dos Pacientes       |              ✅               |     ADMINISTRADOR   |
|  PUT   | `/graacc/api/admin/pacientes/editar/{id}`           |    Editar dados do Paciente     |              ✅               |    ADMINISTRADOR    |
| DELETE | `/graacc/api/admin/pacientes/deletar/{id}`          |    Deletar dados do Paciente    |              ✅               |    ADMINISTRADOR    |
|  POST  | `/graacc/api/admin/agendamentos/adicionar`          |      Adicionar Agendamento      |              ✅               |    ADMINISTRADOR    |
|  POST  | `/graacc/api/admin/agendamentos/adicionar/conjunto` | Adicionar conjunto Agendamentos |              ✅               |    ADMINISTRADOR    |
|  GET   | `/graacc/api/admin/agendamentos/listar/{id}`        |  Lista Agendamento Especifico   |              ✅               |     ADMINISTRADOR   |
|  GET   | `/graacc/api/admin/agendamentos/listar/todos`       |    Lista todos Agendamentos     |              ✅               |     ADMINISTRADOR   |
|  PUT   | `/graacc/api/admin/agendamentos/editar/{id}`        |   Editar dados do Agendamento   |              ✅               |    ADMINISTRADOR    |
| DELETE | `/graacc/api/admin/agendamentos/deletar/{id}`       |      Deletar  Agendamento       |              ✅               |    ADMINISTRADOR    |


## 📑 Endpoints do Usuário


| Método | Endpoint                                                   |                             Descrição                              | Requer Token de Autorização? | Restrição de Acesso |
|:------:|:-----------------------------------------------------------|:------------------------------------------------------------------:|:----------------------------:|:-------------------:|
|  POST  | `/graacc/api/auth/usuario/registrar`                       |                         Registrar Usuario                          |              ❌               |                     |  
|  POST  | `/graacc/api/auth/usuario/login`                           |                          Login do Usuario                          |              ❌               |                     |  
|  GET   | `/graacc/api/auth/usuario/listar`                          |                   Listar dados do Usuario logado                   |              ✅               |       USUÁRIO       |  
|  POST  | `/graacc/api/auth/usuario/confirmar-cadastro`              |                Confirmar Cadastro do Usuario logado                |              ✅               |       USUÁRIO       |  
|  PUT   | `/graacc/api/auth/usuario/editar`                          |                   Editar dados do Usuario logado                   |              ✅               |       USUÁRIO       |  
| DELETE | `/graacc/api/auth/usuario/deletar`                         |                       Deletar Usuario logado                       |              ✅               |       USUÁRIO       |  
|  POST  | `/graacc/api/admin/usuario/agendamentos`                   |               Listar Agendamentos do Usuario logado                |              ✅               |       USUÁRIO       |
|  POST  | `/graacc/api/admin/usuario/agendamentos/{id}`              |          Listar Agendamentos especifico do Usuario logado          |              ✅               |       USUÁRIO       |
|  POST  | `/graacc/api/admin/usuario/notificacoes`                   |               Listar Notificacoes do Usuario logado                |              ✅               |       USUÁRIO       |
|  POST  | `/graacc/api/admin/usuario/notificacoes/agendamentos/{id}` | Listar Notificacoes de um Agendamento especifico do Usuario logado |              ✅               |       USUÁRIO       |
|  POST  | `/graacc/api/admin/usuario/notificacoes/{id}/lida`         |                   Marcar Notificacoes como lida                    |              ✅               |       USUÁRIO       |


Acesse a documentação completa dos endpoints em: `http://localhost:8080/swagger-ui/index.html`
