FROM python:3.9-slim

WORKDIR /app

# Instala as dependências do sistema necessárias, se houver
# RUN apt-get update && apt-get install -y gcc

COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Baixa o modelo do Spacy para português
RUN python -m spacy download pt_core_news_sm

# Copia o código da aplicação
COPY app ./app

# Expõe a porta
EXPOSE 8000

# Comando para rodar a API
CMD ["uvicorn", "app.main:app", "--host", "0.0.0.0", "--port", "8000"]
