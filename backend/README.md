# 🧠 Sentiment API – Back-End (Spring Boot)

Este módulo corresponde ao **Back-End** do projeto **SentimentAPI**, desenvolvido em **Java com Spring Boot**.
Ele expõe uma **API REST** responsável por receber textos, validar a entrada e delegar a análise de sentimento a um microserviço de **Machine Learning** implementado em **FastAPI**.

---

## 🎯 Objetivo do Back-End

* Receber textos enviados por clientes (comentários, avaliações, feedbacks, etc.)
* Validar a entrada do usuário
* Encaminhar a requisição ao microserviço de Machine Learning
* Retornar a previsão de sentimento de forma padronizada (JSON)
* Tratar erros de forma clara, consistente e amigável

---

## 🧩 Arquitetura

```
Cliente (Insomnia / Postman / Frontend)
        ↓
Spring Boot (Backend)
        ↓
FastAPI (ML Service)
        ↓
Modelo de Machine Learning
```

🔹 O backend **não contém lógica de Machine Learning**.
🔹 Ele atua como um **orquestrador**, garantindo validações, padronização de respostas e comunicação entre o cliente e o serviço de ML.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot**
* **Spring Web**
* **Bean Validation (Jakarta Validation)**
* **Docker & Docker Compose**
* **RestTemplate** (cliente HTTP)
* **FastAPI** (microserviço consumido externamente)

---

## 📁 Estrutura do Projeto

```
backend/
├── src/main/java/com/hackathon/SentimentAPI
│   ├── controller/
│   │   └── SentimentController.java
│   ├── service/
│   │   └── SentimentService.java
│   ├── client/
│   │   └── MlServiceClient.java
│   ├── dto/
│   │   ├── SentimentRequest.java
│   │   ├── SentimentResponse.java
│   │   └── MlServiceResponse.java
│   └── exception/
│       └── GlobalExceptionHandler.java
│
├── src/main/resources/
│   └── application.properties
│
└── Dockerfile
```

---

## 🔗 Contrato da API

### ▶ Endpoint Principal

**POST** `/sentiment`

---

### 📥 Request (JSON)

```json
{
  "text": "O produto chegou rápido e é de ótima qualidade"
}
```

---

### 📤 Response de Sucesso (JSON)

```json
{
  "previsao": "Positivo",
  "probabilidade": 0.87
}
```

---

### ❌ Response de Erro (Exemplo)

```json
{
  "erro": "Texto inválido ou muito curto"
}
```

---

## ✅ Validações Implementadas

* Campo **text** obrigatório
* Texto não pode ser vazio ou muito curto
* Tratamento global de exceções com `@ControllerAdvice`
* Mensagens de erro claras em caso de falha de validação ou indisponibilidade do serviço de ML

---

## ⚙️ Configuração

Arquivo `application.properties`:

```properties
spring.application.name=sentiment-api
server.port=8080

# Logs
logging.level.org.springframework.web=INFO

# URL do microserviço de ML
ml.service.url=${ML_SERVICE_URL:http://localhost:8000/predict}
```

📌 Em ambiente Docker, a variável `ML_SERVICE_URL` é definida no arquivo `docker-compose.yml`.

---

## 🐳 Executando com Docker (Recomendado)

### Pré-requisitos

* Docker Desktop instalado

### Subindo o backend junto com o ML Service

Na raiz do projeto, execute:

```bash
docker compose up --build
```

### Serviços Disponíveis

* **Backend:** [http://localhost:8080](http://localhost:8080)
* **ML Service:** [http://localhost:8000](http://localhost:8000)

---

## 🧪 Testando a API

Utilizando **Insomnia** ou **Postman**:

**POST**
`http://localhost:8080/sentiment/analyze`

**Body (JSON):**

```json
{
  "text": "O atendimento foi péssimo, não recomendo"
}
```

---

## 🧠 Observações Importantes

* O backend **não funciona de forma isolada**: depende do ML Service
* Em ambiente Docker, a comunicação ocorre via **nome do serviço** (ex: `ml-service`)
* O projeto segue boas práticas de **separação de responsabilidades**:

  * **Controller** → Entrada e saída de dados
  * **Service** → Lógica de negócio
  * **Client** → Comunicação com serviços externos
  * **DTOs** → Contrato de dados

---

## 🚀 Status do Projeto

* ✅ MVP funcional
* ✅ Integração real com Machine Learning
* ✅ Pronto para apresentação em Hackathon

---

## 👤 Autor

Projeto desenvolvido para **Hackathon Acadêmico**
**Back-End:** Spring Boot + Docker
**Integração ML:** FastAPI
