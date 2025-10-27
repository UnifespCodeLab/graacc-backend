# GRAACC API

## Como executar o projeto

Para executar o projeto, você precisa ter o Docker e o Docker Compose instalados.

1.  Clone o repositório.
2.  Execute o script `start.sh` ou execute o comando `docker-compose up --build -d` na raiz do projeto.

```bash
./start.sh
```

ou

```bash
docker-compose up --build -d --remove-orphans
```

Para visualizar os logs, execute o comando:

```bash
docker-compose logs -f
```

Acessar banco de dados:
```bash
docker exec -it graacc-db psql -d graacc -U user
```

Atualizar microsserviços para o último commit:
```bash
git submodule foreach git pull origin master
```

### Documentação da api 
http://localhost:8080/swagger-ui/index.html

## Como o projeto funciona

O projeto é composto por quatro microserviços:

*   **graacc\_api\_usuarios:** Este microserviço é responsável por gerenciar os usuários do sistema.
*   **graacc\_api\_agendamentos:** Este microserviço é responsável por gerenciar os agendamentos.
*   **graacc\_api\_notificacoes:** Este microserviço é responsável por enviar notificações aos usuários.
*   **graacc\_api\_orquestrador:** Este microserviço é responsável por orquestrar os outros microserviços.

O projeto também utiliza um servidor de mock (Wiremock) para simular as APIs externas.

## Bancos de dados

O projeto utiliza bancos de dados PostgreSQL para cada microserviço. As configurações dos bancos de dados estão no arquivo `docker-compose.yaml`.
