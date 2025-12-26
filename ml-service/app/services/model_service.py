import random
from app.core.preprocessing import preprocessor

# Por enquanto, o modelo é um mock. Assim que tivermos o modelo treinado, ele será carregado aqui.

class SentimentModel:
    def __init__(self):
        # AQUI carregariamos o modelo real
        pass

    def predict(self, raw_text: str):
        """
        Recebe texto bruto, processa e retorna predição e probabilidade.
        """
        # Pré-processamento (Limpeza + NLP)
        # Garante que o texto chegue ao modelo limpo como no treino
        processed_text = preprocessor.processar(raw_text)
        
        # Simulação de resposta enquanto não há modelo treinado
        sentimentos = ["Positivo", "Negativo", "Neutro"]

        if "bom" in processed_text or "otim" in processed_text:
             predicao = "Positivo"
             probabilidade = 0.95
        elif "ruim" in processed_text or "pessim" in processed_text:
             predicao = "Negativo"
             probabilidade = 0.95
        else:
             predicao = random.choice(sentimentos)
             probabilidade = round(random.uniform(0.6, 0.99), 2)

        return {
            "previsao": predicao,
            "probabilidade": probabilidade,
            "texto_processado": processed_text
        }

# Instância global
model_service = SentimentModel()
