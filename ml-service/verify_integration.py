import sys
import os

# Adiciona o diretório atual ao path para importar módulos
sys.path.append(os.getcwd())

from app.core.preprocessing import preprocessor
from app.services.model_service import model_service

def test_integration():
    texts = [
        "O produto chegou rápido e é muito bom, adorei!",
        "Péssima qualidade, quebrou no primeiro uso.",
        "Não gostei do atendimento.",
        "O produto é top demais vc vai amar"
    ]

    print("=== Testando Pré-processamento e Predição ===")
    
    for text in texts:
        print(f"\nOriginal: {text}")
        
        # Testar pré-processamento isolado
        processed = preprocessor.processar(text)
        print(f"Processado: {processed}")
        
        # Testar serviço completo
        result = model_service.predict(text)
        print(f"Resultado Serviço: {result}")
        
        # Verificações básicas
        if "bom" in text.lower() and result['previsao'] == "Negativo" and "não" not in text.lower():
             print("[ALERTA] Predição suspeita para texto positivo.")
             
        if "péssima" in text.lower() and result['previsao'] == "Positivo":
             print("[ALERTA] Predição suspeita para texto negativo.")

if __name__ == "__main__":
    if model_service.pipeline:
        print("Modelo carregado com sucesso.")
        test_integration()
    else:
        print("FALHA: Modelo não carregado.")
