# 🧠 Sentiment API – Back-End (Spring Boot)

## 📌 Visão Geral

Este módulo corresponde ao **Back-End** do projeto **SentimentAPI**, desenvolvido em **Java com Spring Boot**.
Ele expõe uma **API REST** responsável por receber textos, validar a entrada, delegar a análise de sentimento a um microserviço de **Machine Learning** (implementado em **FastAPI**) e fornecer resiliência quando o serviço de ML estiver indisponível.

> 🔒 **Princípio Fundamental**: O backend é **resiliente** - falhas no serviço de ML **não derrubam a aplicação**. Implementamos fallback automático para garantir disponibilidade contínua.

---

## 🎯 Objetivo do Back-End

* Receber textos enviados por clientes (comentários, avaliações, feedbacks, etc.)
* Validar a entrada do usuário com regras robustas
* Encaminhar a requisição ao microserviço de Machine Learning
* Implementar **fallback automático** em caso de indisponibilidade do ML
* Retornar a previsão de sentimento de forma padronizada (JSON)
* Coletar estatísticas de uso
* Tratar erros de forma clara, consistente e amigável

---

## 🧱 Arquitetura

### Diagrama de Fluxo

```
[ Cliente (Frontend/Postman/Insomnia) ]
                 ↓
[ Controller (Spring Boot - REST API) ]
                 ↓
      [ Sentiment Service ]
           ↙         ↘
   [ML Service Client]  [Fallback]
           ↓
[ Serviço ML (FastAPI/Python) ]
           ↓
[ Modelo de Machine Learning ]
```

### Responsabilidades por Camada

| Camada | Componente | Responsabilidade |
|--------|------------|------------------|
| **API Layer** | `SentimentController` | Receber requisições HTTP, validar entrada, formatar resposta |
| **Business Layer** | `SentimentService` | Orquestração da análise, fallback, lógica de negócio |
| **Integration Layer** | `MlServiceClient` | Comunicação com serviço externo de ML |
| **Data Layer** | `SentimentStatsService` | Coleta de estatísticas de uso |
| **Validation Layer** | `DTOs com Bean Validation` | Validação de dados de entrada |
| **Error Handling** | `GlobalExceptionHandler` | Tratamento global de exceções |

---

## 🛠️ Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot 3.x**
* **Spring Web**
* **PostgreSQL**
* **Bean Validation (Jakarta Validation 3.0)**
* **Spring Boot Actuator** (para health checks)
* **RestTemplate** (cliente HTTP com timeouts configurados)
* **Docker & Docker Compose** (containerização)
* **FastAPI** (microserviço consumido externamente)
* **SLF4J + Logback** (logging estruturado)
* **Spring Data JPA/Hibernate** (persistência de dados)
* **Spring Security JWT e Auth0** (autorização e autenticação)


---

## 🔗 Integração com o Serviço de ML

### Contrato de Comunicação

**Endpoint consumido:**
```http
POST {ml.service.url}/predict
```

**Payload enviado:**
```json
{
  "text": "Gostei muito do produto"
}
```

**Resposta esperada do ML:**
```json
{
   "previsao": "Positivo",
   "probabilidade": 0.9982,
   "mensagem_processada": "Texto analisado com sucesso.",
   "principais_palavras": [
      "gostei produto",
      "gostei",
      "produto"
   ],
   "debug_info": {
      "texto_original": "Gostei muito do produto",
      "texto_limpo": "gostei produto"
   }
}
```

### DTO de Integração

```java
@JsonIgnoreProperties(ignoreUnknown = true)
public record MlServiceResponse(
        String textoProcessado,  // Opcional - ignorado se não existir
        String previsao,         // "Positivo", "Negativo"
        Double probabilidade,     // 0.0 a 1.0
        List<String> principais_palavras // Principais palavras que influenciaram a análise
) {}
```

🔹 **Importante**: O backend utiliza apenas `previsao` e `probabilidade`
🔹 O campo `textoProcessado` é opcional e ignorado automaticamente
🔹 Timeout configurado: 5 segundos para conexão e leitura

---

## 🛡️ Sistema de Fallback (Resiliência)

### Cenários de Fallback

O sistema implementa fallback automático quando o serviço de ML:

1. ❌ Está fora do ar
2. ❌ Retorna erro HTTP (4xx, 5xx)
3. ❌ Excede timeout (5 segundos)
4. ❌ Retorna resposta inválida ou nula

### Comportamento de Fallback

```json
{
  "previsao": "Indefinido",
  "probabilidade": 0.0,
  "observacao": "Serviço de análise temporariamente indisponível"
}
```

### Implementação

O fallback é tratado **exclusivamente no `SentimentService`**, garantindo:

1. **Isolamento de falhas**: Erros no ML não propagam para o cliente
2. **Logging apropriado**: Todos os erros são registrados para monitoramento
3. **Estatísticas**: Fallbacks são contabilizados separadamente
4. **Resposta consistente**: Formato de resposta mantido mesmo em falha

---

## 🚀 Endpoints Disponíveis

### 🔹 1. Análise de Sentimento (Principal)

```http
POST /sentiment/analyze
```

**Request:**
```json
{
  "text": "O produto chegou rápido e é de ótima qualidade"
}
```

**Response (sucesso):**
```json
{
   "previsao": "Positivo",
   "probabilidade": 0.9138,
   "status": "SUCCESS",
   "principais_palavras": [
      "qualidade",
      "ótima",
      "ótima qualidade"
   ]
}
```

**Response (fallback - ML indisponível):**
```json
{
  "previsao": "Indefinido",
  "probabilidade": 0.0
}
```

### 🔹 2. Estatísticas de Uso

```http
GET /stats
```

**Response:**
```json
{
  "Positivo": 5,
  "Negativo": 3,
  "Neutro": 2,
  "Indefinido": 1,
  "Total": 11
}
```

### 🔹 3. Health Check

```http
GET /health
```

**Response:**
```json
{
  "status": "UP",
  "timestamp": "2024-01-15T10:30:00Z",
  "ml_service": {
    "status": "UP",
    "url": "http://ml-service:8000/predict"
  }
}
```

### 🔹 4. Endpoint de Teste

```http
GET /hello
```

**Response:**
```
Hello World! TESTE
```

### 🔹 5. Endpoint de Login

```http
POST /login
```

**Request:**
```json
{
   "username": "ana.souza@gmail.com",
   "password": "123456"
}
```

**Response (sucesso):**
```json
{
   "token": "<JWT_TOKEN>"
}
```

---

## ✅ Validações Implementadas

O campo `text` é rigorosamente validado:

| Validação | Regra | Mensagem de Erro |
|-----------|-------|------------------|
| **Obrigatoriedade** | `@NotBlank` | "Texto não pode ser vazio" |
| **Tamanho mínimo** | `@Size(min = 3)` | "Texto deve ter pelo menos 3 caracteres" |
| **Tamanho máximo** | `@Size(max = 1300)` | "Texto deve ter no máximo 1300 caracteres" |
| **Conteúdo válido** | `@Pattern(regexp = ".*[a-zA-Z].*")` | "O texto deve conter pelo menos uma palavra" |
| **Auto-trim** | `text = text.trim()` | Remove espaços extras automaticamente |

**Exemplo de erro de validação:**
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "erros": {
    "text": "Texto deve ter pelo menos 3 caracteres"
  }
}
```

---

## ⚙️ Configuração

### Arquivo `application.properties`

```properties
# Aplicação
spring.application.name=sentiment-api
server.port=8080

# Logging
logging.level.com.hackathon.SentimentAPI=INFO
logging.level.org.springframework.web=INFO
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n

# ML Service
ml.service.url=${ML_SERVICE_URL:http://localhost:8000/predict}

# Actuator
management.endpoints.web.exposure.include=health,info,metrics
management.endpoint.health.show-details=always
```

### Variáveis de Ambiente

| Variável | Default | Descrição |
|----------|---------|-----------|
| `ML_SERVICE_URL` | `http://localhost:8000/predict` | URL do serviço de ML |
| `SERVER_PORT` | `8080` | Porta da aplicação Spring |
| `SPRING_PROFILES_ACTIVE` | `dev` | Perfil ativo (dev/prod) |

---

## 🐳 Execução com Docker

### Pré-requisitos
- Docker Desktop instalado
- 2GB de RAM disponível
- Portas 8080 e 8000 livres

### 1. Subir toda a stack (Backend + ML Service)

```bash
# Na raiz do projeto
docker compose up --build
```

### 2. Serviços expostos

| Serviço | Porta | URL de Acesso |
|---------|-------|---------------|
| **Backend (Spring Boot)** | 8080 | http://localhost:8080 |
| **ML Service (FastAPI)** | 8000 | http://localhost:8000 |
| **ML Service Docs** | 8000 | http://localhost:8000/docs |

### 3. Comandos úteis

```bash
# Ver logs em tempo real
docker compose logs -f

# Parar serviços
docker compose down

# Rebuildar e subir
docker compose up --build --force-recreate

# Executar testes
docker compose exec backend ./mvnw test
```

---

## 🧪 Testando a API

### Com Insomnia/Postman

1. **Coleção de Testes:**
   ```http
   POST http://localhost:8080/sentiment/analyze
   Content-Type: application/json
   
   {
     "text": "O atendimento foi excelente, muito atencioso!"
   }
   ```

2. **Teste de Validação (erro esperado):**
   ```http
   POST http://localhost:8080/sentiment/analyze
   
   {
     "text": "oi"
   }
   ```

3. **Ver Estatísticas:**
   ```http
   GET http://localhost:8080/stats
   ```

### Com cURL

```bash
# Análise de sentimento
curl -X POST http://localhost:8080/sentiment/analyze \
  -H "Content-Type: application/json" \
  -d '{"text":"Produto de qualidade inferior, não recomendo"}'

# Estatísticas
curl http://localhost:8080/stats

# Health check
curl http://localhost:8080/actuator/health
```

---

## 🔍 Monitoramento e Observabilidade

### Logs Estruturados
- Todas as chamadas ao ML são logadas
- Fallbacks são registrados com nível WARN
- Erros são registrados com stack trace completo

### Métricas
- Contador de requisições por tipo de sentimento
- Tempo de resposta do serviço ML
- Taxa de fallbacks

### Health Checks
- Verificação de conectividade com ML service
- Status de recursos do sistema

---

## 🧠 Decisões Técnicas Importantes

### 1. **Resiliência em Primeiro Lugar**
- Fallback automático e transparente
- Timeouts configuráveis (5s)
- Circuit breaker pattern preparado para implementação futura

### 2. **Desacoplamento Total**
- Backend não depende do modelo ML específico
- Aceita mudanças no payload do ML (campos extras ignorados)
- Interface clara entre sistemas

### 3. **Manutenibilidade**
- DTOs com `record` (imutabilidade nativa)
- Injeção de dependência via construtor
- Logs estratégicos (podem ser removidos em produção)
- Código auto-documentado

### 4. **Preparado para Escala**
- Estatísticas em memória (podem ser migradas para Redis)
- Configuração externalizada
- Containerizado com Docker
- Health checks prontos para Kubernetes

### 5. **Experiência do Desenvolvedor**
- Mensagens de erro claras e padronizadas
- API documentada via exemplos
- Fácil configuração via Docker
- Endpoints de teste incluídos

---

## 🙌 Conclusão

Este backend foi projetado para ser:

✅ **Resiliente** - Sobrevive a falhas do serviço ML  
✅ **Escalável** - Pronto para containerização e orchestration  
✅ **Manutenível** - Código limpo, bem estruturado e documentado  
✅ **Flexível** - Adapta-se a mudanças no serviço ML  
✅ **Monitorável** - Logs, métricas e health checks inclusos  
✅ **Pronto para Hackathon** - Funcional, testado e fácil de apresentar

---

## 👥 Autores

**Back-End Team** - Hackathon Acadêmico  
**Integração ML**: FastAPI + Spring Boot  
**Containerização**: Docker + Docker Compose  
**Foco**: Resiliência e Experiência do Desenvolvedor

---

## 📄 Licença

Projeto acadêmico desenvolvido para fins educacionais.
