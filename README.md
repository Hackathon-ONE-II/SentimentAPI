# 🧠 SentimentAPI - Hackathon Acadêmico

Bem-vindo ao **SentimentAPI**, uma solução completa e robusta de análise de sentimentos desenvolvida para o Hackathon Acadêmico. 

Este projeto integra um **Backend resiliente** em Java (Spring Boot), um **Microsserviço de Machine Learning** em Python (FastAPI + Scikit-Learn) e um **Frontend moderno** (Next.js), todos orquestrados via Docker para garantir consistência e facilidade de implantação.

---

## 🏗️ Arquitetura do Sistema

O sistema foi desenhado priorizando a **resiliência** e o **desacoplamento**. O Backend atua como orquestrador, garantindo que a aplicação continue funcional mesmo se o serviço de ML estiver temporariamente indisponível.

```mermaid
graph TD
    User["Usuário / Frontend"] -->|HTTP POST /sentiment| Backend["Spring Boot Backend"]
    
    subgraph "Core System"
        Backend -->|Validação & Lógica| Service["Sentiment Service"]
        Service -->|Requests com Timeout| ML["ML Service (Python/FastAPI)"]
        ML -->|Processamento NLP| Model["Modelo Scikit-Learn"]
        
        Service -.->|Fallback em caso de falha| Fallback["Fallback Response"]
    end
    
    ML -->|JSON: Previsão| Service
    Service -->|JSON Final| Backend
    Backend -->|Resposta HTTP| User
    
    Backend -->|"Persistência (Futuro)"| DB[(PostgreSQL)]
```

### Componentes Principais

| Componente | Tecnologia | Função |
|------------|------------|--------|
| **Backend** | Java 17, Spring Boot 3 | API Gateway, validação de segurança, orquestração e **resiliência** (Fallback Pattern). |
| **ML Service** | Python 3.10, FastAPI | Pré-processamento de texto (NLP), execução do modelo de Machine Learning e explicabilidade dos dados. |
| **Frontend** | React, Next.js | Interface de usuário para interação com a API. |
| **Infraestrutura** | Docker Compose | Orquestração de containers e rede interna. |

---

## ✨ Funcionalidades Chave

### 1. Pipeline de NLP Avançado
O serviço de ML não apenas roda um modelo, ele entende o contexto do idioma informal da internet:
- **Demojização**: Transforma emojis em texto (ex: "👍" vira ":thumbs_up:").
- **Tratamento de Negação**: Entende que "não gostei" é o oposto de "gostei" (sufixação de contexto).
- **Limpeza Inteligente**: Remove ruídos mantendo a pontuação expressiva (!, ?).

### 2. Resiliência e Alta Disponibilidade
O Backend foi projetado para **nunca falhar** silenciosamente:
- Se o serviço de ML cair, o backend retorna uma resposta de **Fallback** ("Indefinido") com metadados explicativos.
- O sistema usa timeouts configurados para evitar travamentos em cascata.

### 3. Independência de Modelo
O contrato de API entre Backend e ML Service é flexível. O time de Data Science pode evoluir o modelo, adicionar novos campos de retorno ou mudar a biblioteca de ML sem quebrar o Backend.

---

## 🚀 Como Rodar (Quick Start)

A maneira recomendada de rodar o projeto é utilizando **Docker Compose**. Isso garante que todas as dependências (Java, Python, Bancos de Dados) estejam isoladas e configuradas corretamente.

### Pré-requisitos
- [Docker Desktop](https://www.docker.com/products/docker-desktop/) instalado e rodando.

### Passo a Passo

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/SentimentAPI.git
   cd SentimentAPI
   ```

2. **Inicie a aplicação:**
   ```bash
   docker compose up --build
   ```
   > ☕ *A primeira execução pode demorar alguns minutos enquanto as imagens são baixadas e construídas.*

3. **Acesse os serviços:**
   - **Backend API**: [http://localhost:8080](http://localhost:8080)
   - **Documentação ML (Swagger)**: [http://localhost:8000/docs](http://localhost:8000/docs)
   - **Frontend**: [http://localhost:3000](http://localhost:3000) (se iniciado)

4. **Para parar:**
   Pressione `Ctrl+C` ou rode:
   ```bash
   docker compose down
   ```

---

## 📂 Estrutura do Projeto

```text
/SentimentAPI
├── backend/            # Aplicação Spring Boot (API Gateway & Lógica)
├── ml-service/         # Microsserviço Python (Modelo de IA)
├── frontend/           # Aplicação Next.js (Interface Web)
├── data-science/       # Notebooks de treinamento e exploração de dados
├── docker-compose.yml  # Orquestração dos serviços
└── README.md           # Este arquivo
```

---

## 📚 Documentação Detalhada

Cada módulo possui sua própria documentação técnica detalhada. Recomendamos a leitura para desenvolvedores que desejam contribuir:

- ☕ **[Backend Documentation](./backend/README.md)**: Detalhes sobre endpoints, DTOs, arquitetura de fallback e configurações do Spring.
- 🐍 **[ML Service Documentation](./ml-service/README.md)**: Detalhes sobre o pipeline de pré-processamento, modelo `pkl` e endpoints do FastAPI.
- ⚛️ **[Frontend Documentation](./frontend/sentimentos-api/README.md)**: Guia de desenvolvimento do Next.js.

---

## 🧪 Como Testar Agora Mesmo

Com o projeto rodando (`docker compose up`), você pode testar a análise de sentimento diretamente pelo terminal:

**Teste Positivo:**
```bash
curl -X POST http://localhost:8080/sentiment/analyze \
  -H "Content-Type: application/json" \
  -d '{"text": "O entregador foi super rápido e educado! Adorei."}'
```

**Teste Negativo (com ironia/gíria):**
```bash
curl -X POST http://localhost:8080/sentiment/analyze \
  -H "Content-Type: application/json" \
  -d '{"text": "O produto chegou todo quebrado, parabens aos envolvidos."}'
```

---

## 👥 Autores e Créditos (em desenvolvimento)

Desenvolvido pela equipe... [COMPLETAR]

- **Data Science**: Treinamento de modelos, limpeza de dados.
- **Backend Engineering**: Arquitetura resiliente, Spring Boot.
- **Frontend Development**: UI/UX, Next.js.
