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
            model_path = os.path.join(base_dir, '..', 'models', 'production_model.pkl')
            
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
            # O modelo retorna classes [0, 1], 0=Negativo, 1=Positivo (conforme notebook)
            # Se prediction for 1, probabilidade é probs[1]
            probabilidade = probs[int(prediction)]
            
        except AttributeError:
            # Caso o modelo não suporte predict_proba
            probabilidade = None

        sentimento = "Positivo" if prediction == 1 else "Negativo"
        
        # Lógica para Top 3 Palavras
        top_words = []
        try:
            # Acessar etapas do pipeline
            vectorizer = self.pipeline.named_steps['tfidf']
            clf = self.pipeline.named_steps['clf']
            
            # Recalcular transform para acesso aos índices (necessário pois predict recebe texto direto mas precisamos do vetor esparso)
            # O processamento já foi feito, então transformamos o processed_text
            tfidf_vector = vectorizer.transform([processed_text])
            feature_names = vectorizer.get_feature_names_out()
            coefs = clf.coef_[0] # Array de coeficientes (1, n_features)
            
            # Pegar índices das palavras presentes no texto (valores não-zero no vetor esparso)
            # tfidf_vector é (1, n_features)
            _, col_indices = tfidf_vector.nonzero()
            
            words_scores = []
            for idx in col_indices:
                word = feature_names[idx]
                tfidf_val = tfidf_vector[0, idx]
                coef_val = coefs[idx]
                contribution = tfidf_val * coef_val
                words_scores.append((word, contribution))
            
            # Ordenação dependendo da classe predita
            if prediction == 1: # Positivo -> Maiores contribuições positivas
                # Ordena decrescente
                words_scores.sort(key=lambda x: x[1], reverse=True)
            else: # Negativo -> Maiores contribuições negativas (menores valores absolutos, ou seja, mais negativos)
                # Ordena crescente (quanto mais negativo, mais contribui para classe 0)
                words_scores.sort(key=lambda x: x[1])
                
            # Pegar top 3
            top_words = [w[0] for w in words_scores[:3]]
            
        except Exception as e:
            print(f"Erro ao calcular top words: {e}")
            top_words = []

        return {
            "previsao": sentimento,
            "probabilidade": round(float(probabilidade), 4),
            "texto_processado": processed_text,
            "principais_palavras": top_words
        }

# Instância global
model_service = SentimentModel()
