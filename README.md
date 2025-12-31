# SentimentAPI

## Docker

Para facilitar o desenvolvimento e testes, utilizamos Docker para containerizar a aplicação.

### Pré-requisitos
- Docker Desktop instalado ([Guia de Instalação](https://docs.docker.com/get-docker/))

### Comandos Principais

**Rodar todo o projeto (Backend + ML Service)**
```bash
docker compose up --build
```
Pode levar bastante tempo para terminar o build, especialmente se o ml-service precisar baixar o modelo do Spacy. Depois disso, os serviços devem iniciar rapidamente.

Isso vai iniciar:
- **Backend**: http://localhost:8080
- **ML Service**: http://localhost:8000

**Rodar apenas o Backend**
```bash
docker compose up backend
```
*Nota: O backend pode apresentar erros se tentar acessar o ML Service e ele não estiver rodando.*

**Rodar apenas o ML Service**
```bash
docker compose up ml-service
```

**Testes**
Para testar a API, use:
```bash
curl -X POST http://localhost:8080/sentiment `
-H "Content-Type: application/json" `
-d '{"text": "Estou muito feliz com o Docker"}'
```

**Parar tudo**
Pressione `Ctrl+C` no terminal ou rode:
```bash
docker compose down
```

### Estrutura
- `docker-compose.yml`: Orquestra os serviços e define a comunicação entre eles.
- `backend/Dockerfile`: Define como construir a imagem Java/Spring.
- `ml-service/Dockerfile`: Define como construir a imagem Python/FastAPI.
