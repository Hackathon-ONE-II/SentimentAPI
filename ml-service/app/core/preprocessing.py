import re
import spacy
import nltk
from unidecode import unidecode
from nltk.corpus import stopwords

class TextPreprocessor:
    def __init__(self):
        # Tenta carregar o modelo spacy, precisa estar instalado no Docker
        try:
            self.nlp = spacy.load("pt_core_news_sm")
        except OSError:
            print("AVISO: Modelo spacy 'pt_core_news_sm' não encontrado.")
            self.nlp = None

        # Garante que as stopwords estejam disponíveis
        try:
            nltk.data.find('corpora/stopwords')
        except LookupError:
            nltk.download('stopwords', quiet=True)
            
        self.stop_words = set(stopwords.words('portuguese'))
        # Palavras que não devem ser removidas pois alteram sentido
        self.stop_words.discard('nao')
        self.stop_words.discard('não')
        self.stop_words.discard('nunca')

    def limpar_texto(self, texto: str) -> str:
        """
        Realiza limpeza básica: lowercase, remoção de acentos, urls e caracteres especiais.
        """
        if not texto:
            return ""
        
        # Converte para string caso venha float/nulo
        texto = str(texto).lower()
        texto = unidecode(texto)
        
        # Remove URLs e menções
        texto = re.sub(r'http\S+|www\S+|@\S+', '', texto)
        
        # Mantém apenas letras e espaços
        texto = re.sub(r'[^a-z\s]', '', texto)
        
        return texto

    def processar(self, texto: str) -> str:
        """
        Pipeline completo: limpar -> tokenizar -> remover stopwords -> lematizar
        """
        texto_limpo = self.limpar_texto(texto)
        
        if not self.nlp:
            return texto_limpo

        doc = self.nlp(texto_limpo)
        
        tokens = [
            token.lemma_
            for token in doc
            if token.text not in self.stop_words
            and len(token.text) > 2 # Remove tokens muito curtos
        ]
        
        return " ".join(tokens)

# Instância global para ser importada
preprocessor = TextPreprocessor()
