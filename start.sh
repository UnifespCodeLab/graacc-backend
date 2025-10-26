#!/bin/bash
# Script para construir e iniciar todos os serviços do projeto com Docker Compose.
echo "Atualizando todos os microsserviços para o último commit..."
git submodule foreach git pull origin main

echo "Criando a rede local-network..."
docker network create local-network

echo "Iniciando todos os serviços em modo detached..."
docker-compose up --build -d

echo ""
echo "Serviços iniciados em background."
echo "Para visualizar os logs, execute o comando:"
echo "docker-compose logs -f"
