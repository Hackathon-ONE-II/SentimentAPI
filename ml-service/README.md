# Microsserviço API Python
 
API REST para análise de sentimento de reviews, construída com FastAPI e Scikit-Learn.

## Lógica de Funcionamento

O microsserviço segue um pipeline estruturado para processar cada requisição de análise:

1.  **Pré-processamento de Texto (`Input`)**:
    O texto bruto recebido passa pela classe `TextPreprocessor` (`app/core/preprocessing.py`), que realiza:
    - **Normalização**: Conversão para minúsculas e substituição de gírias (ex: "vc" -> "você", "top" -> "ótimo").
    - **Demojização**: Conversão de emojis em texto (biblioteca `emoji`).
    - **Limpeza**: Remoção de caracteres especiais irrelevantes, mantendo acentuação e pontuações expressivas (! ?).
    - **Tratamento de Negação**: Identifica termos negativos ("não", "jamais") e marca as palavras seguintes com sufixo `_NEG` até a próxima pontuação, para que o modelo entenda o contexto (ex: "não gostei" -> "não gostei_NEG").
    - **Stopwords**: Remoção de palavras comuns irrelevantes, preservando as negações.

2.  **Instanciação e Predição do Modelo**:
    O modelo treinado (`production_model.pkl`) é carregado uma única vez na inicialização do serviço (`app/services/model_service.py`) usando `joblib`.
    - O texto processado entra no **Pipeline do Scikit-Learn** carregado.
    - **Vetorização**: O `TfidfVectorizer` (parte do pipeline) converte o texto em vetores numéricos.
    - **Classificação**: O `LogisticRegression` calcula a probabilidade de ser Positivo (1) ou Negativo (0).

3.  **Pós-processamento (`Output`)**:
    Além da previsão, o serviço calcula quais palavras mais contribuíram para a decisão usando os coeficientes do modelo linear, retornando as "principais palavras" para explicabilidade.

## Estrutura do Projeto

- `app/`: Código fonte da aplicação.
    - `core/`: Utilitários centrais, como pré-processamento de texto.
    - `models/`: Modelos de Machine Learning serializados (.pkl).
    - `services/`: Lógica de negócio e carregamento do modelo.
- `requirements.txt`: Dependências do projeto.
- `Dockerfile`: Configuração para containerização.

## Como Rodar

### Localmente

1.  Instale as dependências:
    ```bash
    pip install -r requirements.txt
    ```
2.  Inicie o servidor:
    ```bash
    uvicorn app.main:app --reload
    ```
3.  Acesse a documentação interativa em: `http://localhost:8000/docs`

### Via Docker

1.  Construa a imagem:
    ```bash
    docker build -t sentiment-api .
    ```
2.  Rode o container:
    ```bash
    docker run -p 8000:8000 sentiment-api
    ```

## Endpoints

### `POST /predict`

Realiza a análise de sentimento de um texto.

**Payload:**
```json
{
  "text": "O produto é excelente, chegou antes do prazo!"
}
```

**Resposta:**
```json
{
  "previsao": "Positivo",
  "probabilidade": 0.9854,
  "mensagem_processada": "Texto analisado com sucesso.",
  "principais_palavras": ["excelente", "prazo"],
  "debug_info": {
    "texto_original": "O produto é excelente, chegou antes do prazo!",
    "texto_limpo": "produto excelente chegou prazo"
  }
}
```

### `GET /`

Health check da API.
