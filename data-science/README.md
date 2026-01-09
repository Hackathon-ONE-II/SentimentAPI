# Data Science - Sentiment API

Este diretório contém os experimentos, análises e modelos de Machine Learning para a API de Análise de Sentimento.

## Estrutura de Pastas

- `notebooks/`: Notebooks Jupyter com as etapas de análise, pré-processamento e modelagem.
- `datasets/`: Arquivos de dados (JSON/CSV) com os reviews do Mercado Livre.
- `models/`: Modelos treinados serializados (.pkl).
- `requirements.txt`: Lista de dependências Python.

## Modelo de Produção

O modelo de produção (`production_model.pkl`) foi desenvolvido no notebook `4_production_model_dataset2.ipynb`. Ele classifica os reviews em **Positivo (1)** ou **Negativo (0)**.

### Características do Modelo
- **Algoritmo**: Regressão Logística (`LogisticRegression`)
- **Vetorização**: TF-IDF (`TfidfVectorizer`)
    - N-grams: (1, 2) (Unigramas e Bigramas)
    - Max Features: 15.000
    - Min Document Frequency: 5
- **Tratamento de Desbalanceamento**: `class_weight='balanced'` (Penalização automática de erros na classe minoritária).
- **Otimização**: Hiperparâmetros otimizados via GridSearchCV (F1-Score como métrica).

### Desempenho (Teste)

O modelo foi avaliado em um conjunto de teste com **20.340 amostras**.

| Métrica | Valor Global |
| :--- | :--- |
| **Acurácia** | **94%** |
| **F1-Score (Weighted)** | **0.94** |
| **Precision (Weighted)** | **0.95** |
| **Recall (Weighted)** | **0.94** |

#### Detalhes por Classe

| Classe | Precision | Recall | F1-Score | Suporte (Qtd) |
| :--- | :--- | :--- | :--- | :--- |
| **0 (Negativo)** | 0.61 | **0.83** | 0.70 | 1.831 |
| **1 (Positivo)** | 0.98 | 0.95 | 0.96 | 18.509 |

> **Nota:** O modelo prioriza o **Recall** da classe negativa (0.83) para garantir que insatisfações reais dos clientes sejam detectadas, mesmo que isso custe um pouco de precisão (alguns falsos negativos).



## Ordem de Execução dos Notebooks

Para reproduzir o pipeline de Data Science, execute os notebooks na seguinte ordem:

1.  `1_eda_dataset2.ipynb`: Análise Exploratória de Dados do Dataset 2.
2.  `2_preprocessing_dataset2.ipynb`: Limpeza e pré-processamento dos textos.
3.  `3_baseline_model_dataset2.ipynb`: Treinamento e avaliação do modelo baseline.
4.  `4_production_model_dataset2.ipynb`: Pipeline completo com otimização e geração do `production_model.pkl`.

## Dependências

Para instalar as dependências necessárias, execute:

```bash
pip install -r requirements.txt
```
