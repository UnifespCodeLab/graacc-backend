#!/bin/bash
# Script para construir e iniciar todos os serviços do projeto com Docker Compose.

echo "Iniciando todos os serviços em modo detached..."
docker-compose up --build -d

echo ""
echo "Serviços iniciados em background."
echo "Para visualizar os logs, execute o comando:"
echo "docker-compose logs -f"
