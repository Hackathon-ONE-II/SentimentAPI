from fastapi import FastAPI
from pydantic import BaseModel
from app.services.model_service import model_service

app = FastAPI(title="API de Sentimento do Hackathon")

class InputText(BaseModel):
    text: str

@app.get("/")
def health_check():
    return {"status": "API de ML rodando", "service": "SentimentAnalysis"}

@app.post("/predict")
def predict_sentiment(data: InputText):
    # Delega a lógica para o serviço
    result = model_service.predict(data.text)
    
    return {
        "previsao": result["previsao"],
        "probabilidade": result["probabilidade"],
        "mensagem_processada": "Texto analisado com sucesso.",
        "principais_palavras": result.get("principais_palavras", []),
        "debug_info": {
            "texto_original": data.text,
            "texto_limpo": result["texto_processado"]
        }
    }