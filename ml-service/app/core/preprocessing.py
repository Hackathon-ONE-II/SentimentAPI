import re
import nltk
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
import emoji

# Configuração inicial (garante recursos do NLTK)
try:
    nltk.data.find('tokenizers/punkt')
    nltk.data.find('tokenizers/punkt_tab')
except LookupError:
    nltk.download('punkt', quiet=True)
    nltk.download('punkt_tab', quiet=True)

try:
    nltk.data.find('corpora/stopwords')
except LookupError:
    nltk.download('stopwords', quiet=True)

class TextPreprocessor:
    def __init__(self):
        self.stop_words = set(stopwords.words('portuguese'))
        # Remover palavras de negação das stopwords
        self.negation_terms = {'não', 'nao', 'nem', 'nunca', 'jamais', 'nada', 'ninguém', 'ninguem'}
        self.stop_words = self.stop_words - self.negation_terms

        # Mapa de gírias
        self.slang_map = {
            'vc': 'você', 'vcs': 'vocês', 'tb': 'também', 'tbm': 'também',
            'pq': 'porque', 'q': 'que', 'k': '', 'kk': '', 'kkk': '', 'kkkk': '', 'rs': '',
            'mto': 'muito', 'mt': 'muito', 'ta': 'está', 'eh': 'é', 'hj': 'hoje',
            'td': 'tudo', 'blz': 'beleza', 'flw': 'falou', 'abs': 'abraços',
            'tmj': 'tamo junto', 'n': 'não', 's': 'sim', 'obg': 'obrigado',
            'top': 'ótimo', 'show': 'ótimo'
        }

    def normalize_slang(self, text: str) -> str:
        words = text.split()
        normalized_words = [self.slang_map.get(word, word) for word in words]
        return " ".join(normalized_words)

    def clean_text_advanced(self, text: str) -> str:
        if not isinstance(text, str):
            return ""
        
        # Tratar Emojis
        text = emoji.demojize(text, language='pt')
        
        # Lowercase
        text = text.lower()
        
        # Hifens por espaço
        text = text.replace('-', ' ')
        
        # Normalizar Gírias
        text = self.normalize_slang(text)
        
        # Regex mantendo pontuação relevante (! ? : _)
        text = re.sub(r'[^a-zA-Z0-9\u00C0-\u00FF\s!\?:_]', '', text)
        
        # Espaçar pontuação
        text = re.sub(r'([!\?])', r' \1 ', text)
        
        # Remover espaços extras
        text = re.sub(r'\s+', ' ', text).strip()
        
        return text

    def mark_negation(self, text_tokens: list) -> list:
        punctuation = {'!', '?', '.', ',', ':', ';', '...'}
        result = []
        neg_state = False
        
        for word in text_tokens:
            if word in punctuation:
                neg_state = False
                result.append(word)
                continue
            
            if word in self.negation_terms:
                neg_state = True
                result.append(word)
                continue
            
            if neg_state:
                result.append(word + '_NEG')
            else:
                result.append(word)
                
        return result

    def processar(self, texto: str) -> str:
        """
        Pipeline completo: limpar -> tokenizar -> remover stopwords -> marcação de negação
        """
        cleaned = self.clean_text_advanced(texto)
        
        # Tokenização 
        tokens = word_tokenize(cleaned, language='portuguese')
        
        # Remover stopwords
        tokens = [t for t in tokens if t not in self.stop_words]
        
        # Marcação de Negação
        tokens = self.mark_negation(tokens)
        
        # Filtrar tokens curtos (mas manter ! ?)
        final_tokens = [t for t in tokens if len(t) > 1 or t in ['!', '?']]
        
        return " ".join(final_tokens)

# Instância global
preprocessor = TextPreprocessor()
