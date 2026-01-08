import os
import joblib
from app.core.preprocessing import preprocessor

class SentimentModel:
    def __init__(self):
        self.pipeline = None
        self.load_model()
    
    def load_model(self):
        try:
            # Caminho relativo ao arquivo (copiado para dentro do app)
            base_dir = os.path.dirname(os.path.abspath(__file__))
            # Subindo um nível pois services está em app/services
            model_path = os.path.join(base_dir, '..', 'production_model.pkl')
            
            if os.path.exists(model_path):
                self.pipeline = joblib.load(model_path)
                print(f"Modelo de Produção carregado de: {model_path}")
            else:
                print(f"AVISO: Modelo não encontrado em {model_path}")
        except Exception as e:
            print(f"Erro ao carregar modelo: {e}")

    def predict(self, raw_text: str):
        """
        Recebe texto bruto, processa e retorna predição e probabilidade.
        """
        # Pré-processamento
        processed_text = preprocessor.processar(raw_text)
        
        if not self.pipeline:
            # Fallback se o modelo não carregou
            return {
                "previsao": "Erro (Modelo não carregado)",
                "probabilidade": 0.0,
                "texto_processado": processed_text
            }

        # Predição
        # O modelo espera uma lista/iterable
        prediction = self.pipeline.predict([processed_text])[0]
        try:
            # Pega probabilidade da classe predita
            probs = self.pipeline.predict_proba([processed_text])[0]
            # O modelo retorna classes [0, 1] ou similar.
            # Assumindo 0=Negativo, 1=Positivo (conforme notebook)
            # A classe da predição é o índice ou valor.
            # Se prediction for 1, probabilidade é probs[1]
            probabilidade = probs[int(prediction)]
            
        except AttributeError:
            # Caso o modelo não suporte predict_proba
            probabilidade = 1.0

        sentimento = "Positivo" if prediction == 1 else "Negativo"
        
        return {
            "previsao": sentimento,
            "probabilidade": round(float(probabilidade), 4),
            "texto_processado": processed_text
        }

# Instância global
model_service = SentimentModel()
