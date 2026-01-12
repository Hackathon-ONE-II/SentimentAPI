# 🎨 Frontend - SentimentAPI

## 📋 Índice

1. [Visão Geral](#-visão-geral)
2. [Objetivos do Frontend](#-objetivos-do-frontend)
3. [Tecnologias Utilizadas](#-tecnologias-utilizadas)
4. [Estrutura do Projeto](#-estrutura-do-projeto)
5. [As Telas da Aplicação](#-as-telas-da-aplicação)
6. [Funcionalidades Principais](#-funcionalidades-principais)
7. [Dependências e Versões](#-dependências-e-versões)
8. [Como Rodar o Frontend](#-como-rodar-o-frontend)
9. [Variáveis de Ambiente](#-variáveis-de-ambiente)
10. [Integração com Backend e Data Science](#-integração-com-backend-e-data-science)
11. [Guia de Desenvolvimento](#-guia-de-desenvolvimento)
12. [Build para Produção](#-build-para-produção)
13. [Troubleshooting](#-troubleshooting)

---

## 🎯 Visão Geral

O **Frontend SentimentAPI** é uma aplicação web moderna desenvolvida com **Next.js 16** e **React 19**, projetada para fornecer uma experiência de usuário intuitiva e responsiva na análise de sentimentos em feedback de clientes.

A aplicação oferece:
- ✅ **Autenticação de usuários** simples e segura
- ✅ **Interface intuitiva** para análise de sentimentos
- ✅ **Visualização de resultados** em tempo real
- ✅ **Design responsivo** adaptado para mobile, tablet e desktop
- ✅ **Feedback visual** durante operações assíncronas
- ✅ **Integração perfeita** com Backend Java e Serviço de ML

---

## 🎪 Objetivos do Frontend

### Objetivo Geral
Criar uma interface amigável que permita usuários autenticados analisarem o sentimento de textos (comentários, avaliações, feedbacks) de forma rápida e intuitiva, recebendo classificações automáticas como **Positivo**, **Neutro** ou **Negativo**, acompanhadas de métricas de confiança.

### Objetivos Específicos

1. **Facilitar o acesso à análise de sentimentos**
   - Interface limpa e intuitiva, sem necessidade de conhecimento técnico
   - Múltiplos formatos de entrada (texto livre, copiar-colar)

2. **Fornecer feedback visual claro**
   - Exibição imediata do resultado com visualizações gráficas
   - Indicadores de confiança percentuais
   - Palavras-chave destacadas para maior contexto

3. **Garantir segurança**
   - Sistema de autenticação obrigatório
   - Validação de entrada de dados
   - Tratamento robusto de erros

4. **Otimizar para performance**
   - Carregamento rápido das páginas
   - Requisições otimizadas ao backend
   - Caching quando apropriado

5. **Melhorar experiência do usuário**
   - Design moderno e atrativo
   - Animações suaves para transições
   - Responsividade em todos os dispositivos

---

## 🛠️ Tecnologias Utilizadas

### Core Framework
- **Next.js 16.1.1** - Framework React com SSR (Server-Side Rendering) e SSG (Static Site Generation)
- **React 19.2.3** - Biblioteca de interface de usuário
- **React DOM 19.2.3** - Renderização de componentes React no DOM

### Estilização
- **Tailwind CSS 4** - Framework de CSS utilitário para estilização rápida e consistente
- **PostCSS 4** - Processador de CSS avançado

### Linguagem & Type Safety
- **TypeScript 5** - Superset de JavaScript com tipagem estática
- **Node.js 20 (Alpine)** - Runtime JavaScript para execução

### Desenvolvimento
- **ESLint 9** - Linter para qualidade de código
- **ESLint Config Next** - Configuração de linting otimizada para Next.js

### DevOps
- **Docker** - Containerização da aplicação para consistência entre ambientes

### Requisitos do Sistema
- Node.js ≥ 18
- npm ≥ 8 ou yarn/pnpm equivalentes
- Docker (opcional, para containerização)

---

## 📁 Estrutura do Projeto

```
frontend/sentimentos-api/
│
├── 📄 package.json                 # Configuração do projeto e dependências
├── 📄 package-lock.json            # Lock file do npm
├── 📄 tsconfig.json                # Configuração do TypeScript
├── 📄 next.config.ts               # Configuração do Next.js
├── 📄 postcss.config.mjs           # Configuração do PostCSS
├── 📄 eslint.config.mjs            # Configuração do ESLint
├── 📄 next-env.d.ts                # Tipos auto-gerados do Next.js
├── 📄 Dockerfile                   # Configuração para containerização
│
├── 📁 app/                          # Diretório raiz da aplicação (App Router)
│   ├── 📄 layout.tsx                # Layout base da aplicação (header, footer)
│   ├── 📄 page.tsx                  # 🔐 Página de Login (rota: /)
│   ├── 📄 globals.css               # Estilos globais da aplicação
│   │
│   └── 📁 tela-principal/           # Páginas autenticadas
│       └── 📄 page.tsx              # 🏠 Página Principal (rota: /tela-principal)
│
├── 📁 components/                   # Componentes reutilizáveis
│   ├── 📄 Titulo.tsx                # Componente do título "SentimentAI"
│   ├── 📄 TextoPrincipal.tsx        # Texto principal: "Entenda o sentimento dos seus feedbacks"
│   ├── 📄 Subtitulo.tsx             # Subtítulo descritivo
│   ├── 📄 ReferenciaTextual.tsx     # Referências visuais (Resposta Instantânea, Modelo Treinado)
│   ├── 📄 CardPrincipal.tsx         # Componente de entrada de texto e análise
│   ├── 📄 Resultado.tsx             # Componente de exibição de resultados
│   └── 📄 Footer.tsx                # Rodapé da aplicação
│
├── 📁 public/                       # Arquivos estáticos públicos
│   └── (ícones, imagens, etc.)
│
├── 📁 styles/                       # Estilos adicionais (se houver)
│
├── 📁 types/                        # Definições de tipos TypeScript
│
├── 📁 services/                     # Serviços (chamadas à API, etc.)
│
└── 📁 .next/                        # Diretório de build (ignorado no git)
```

### Fluxo de Roteamento (Next.js App Router)

```
/                          ← Página de Login (público)
  ↓ (após autenticação)
/tela-principal            ← Página Principal (análise de sentimentos)
```

---

## 🖥️ As Telas da Aplicação

### 1️⃣ Tela de Login (`/`)

**Arquivo**: [app/page.tsx](app/page.tsx)

#### Características:
- **Autenticação obrigatória** para acessar a aplicação
- Campos de entrada para **username** e **password**
- Validação de credenciais contra o Backend
- **Tratamento de erros** com mensagens claras

#### Componentes Utilizados:
- `Titulo` - Logo/título "SentimentAI"
- `TextoPrincipal` - Mensagem de boas-vindas
- `Footer` - Informações do rodapé

#### Fluxo de Autenticação:
```typescript
Usuário digita credenciais
         ↓
Clica em "Entrar"
         ↓
Requisição POST → http://localhost:8080/login
         ↓
Se sucesso (200 OK) → Redirecionamento para /tela-principal
Se erro → Exibição de mensagem de erro
```

#### Validações:
- ✅ Verifica se username e password não estão vazios
- ✅ Trata conexões perdidas com o servidor
- ✅ Diferencia entre erros de autenticação e erros de conectividade

#### Interface Visual:
```
┌─────────────────────────────────────┐
│      SentimentAI (logo)             │
│─────────────────────────────────────│
│ Entenda o sentimento dos seus       │
│ feedbacks                           │
│─────────────────────────────────────│
│ Login:                              │
│ [ Digite seu login        ]         │
│                                     │
│ Senha:                              │
│ [ Digite sua senha        ]         │
│                                     │
│ [ Entrar ]                          │
│                                     │
│ Hackathon • Análise de Sentimentos  │
└─────────────────────────────────────┘
```

---

### 2️⃣ Tela Principal - Análise de Sentimentos (`/tela-principal`)

**Arquivo**: [app/tela-principal/page.tsx](app/tela-principal/page.tsx)

#### Características:
- **Interface principal** para análise de sentimentos
- **Múltiplas zonas** com diferentes funcionalidades
- **Estado dinâmico** que muda baseado em ações do usuário
- **Animações de entrada** para melhor UX

#### Componentes Utilizados:
1. `Titulo` - Logo no topo
2. `TextoPrincipal` - Título principal
3. `Subtitulo` - Descrição funcional
4. `ReferenciaTextual` - Ícones de recursos
5. `CardPrincipal` - Área de entrada de texto
6. `Resultado` - Exibição de resultados (condicional)
7. `Footer` - Informações do rodapé

#### Fluxo de Uso:
```
Usuário acessa /tela-principal
         ↓
Visualiza formulário vazio
         ↓
Digita texto para análise
         ↓
Clica em "Analisar Sentimento"
         ↓
Requisição POST → http://localhost:8080/sentiment/analyze
         ↓
Se sucesso → Exibição de resultados (Resultado.tsx)
Se erro → Mensagem de erro no CardPrincipal
```

#### Estrutura das Seções:

**Seção 1: Cabeçalho e Apresentação**
```
┌─────────────────────────────────────┐
│      SentimentAI (logo fixo)        │
│─────────────────────────────────────│
│ Entenda o sentimento dos seus       │
│ feedbacks                           │
│                                     │
│ Classifique automaticamente comentários, 
│ avaliações e mensagens como positivos, │
│ neutros ou negativos usando IA      │
└─────────────────────────────────────┘
```

**Seção 2: Referências**
```
┌─────────────────────────────────────┐
│ ⚡ Resposta instantânea   □ Modelo treinado
└─────────────────────────────────────┘
```

**Seção 3: Input - CardPrincipal**
```
┌─────────────────────────────────────┐
│ [                                   │
│  Digite aqui...                     │
│  (textarea com 320px de altura)     │
│                                     │
│ ]                                   │
│                                     │
│ [ Analisar Sentimento ]             │
└─────────────────────────────────────┘
```

**Seção 4: Resultados - Resultado (Condicional)**
```
┌──────────────────────────────────────────┐
│ Resultado da Análise        👍 Positivo  │
├──────────────────────────────────────────┤
│ 📈 Confiança da Predição        95%     │
│ ▓▓▓▓▓▓▓▓▓░░░░░░░░░░░░░░░░░░░░░░░     │
│                                          │
│ Texto Analisado:                        │
│ "Adorei o produto, muito bom mesmo!"   │
│                                          │
│ 🔑 Principais Palavras:                 │
│ [adorei] [produto] [bom]               │
└──────────────────────────────────────────┘
```

---

## 💡 Funcionalidades Principais

### 1. **Autenticação de Usuários**
- Login obrigatório antes de acessar análise de sentimentos
- Integração com backend Spring Boot (`POST /login`)
- Tratamento de erros de autenticação
- Redirecionamento automático para login se sessão expirar (implementação futura)

### 2. **Análise de Sentimentos em Tempo Real**
- **Input**: Aceita texto livre (comentários, avaliações, feedbacks)
- **Processing**: Envia ao Backend que orquestra ML Service
- **Output**: Retorna classificação (Positivo/Neutro/Negativo) com confiança

### 3. **Visualização de Resultados**
- **Classificação**: Exibe sentimento com ícone visual
- **Confiança**: Mostra percentual em progresso bar
- **Texto Processado**: Exibe o texto analisado
- **Palavras-Chave**: Mostra principais palavras identificadas pelo modelo

### 4. **Interface Responsiva**
- Adapta-se para mobile (< 390px)
- Tablet (390px - 1024px)
- Desktop (> 1024px)
- Todos os inputs redimensionam apropriadamente

### 5. **Feedback Visual Aprimorado**
- **Animações de entrada**: Slides suaves dos elementos
- **Estado de carregamento**: "Analisando..." no botão
- **Mensagens de erro**: Claras e acionáveis
- **Desabilitação de botão**: Impede múltiplos envios

### 6. **Tratamento de Erros Robusto**
- Erros de conectividade com instruções
- Erros de API com descrição detalhada
- Feedback específico sobre status do servidor
- Recuperação graceful sem travamento

---

## 📦 Dependências e Versões

### Dependências de Produção

| Pacote | Versão | Descrição |
|--------|--------|-----------|
| **next** | 16.1.1 | Framework React com SSR/SSG e otimizações |
| **react** | 19.2.3 | Biblioteca de UI com hooks e componentes |
| **react-dom** | 19.2.3 | Renderização de componentes React no DOM |

### Dependências de Desenvolvimento

| Pacote | Versão | Descrição |
|--------|--------|-----------|
| **@tailwindcss/postcss** | ^4 | Integração do Tailwind com PostCSS |
| **@types/node** | ^20 | Tipos TypeScript para Node.js |
| **@types/react** | ^19 | Tipos TypeScript para React |
| **@types/react-dom** | ^19 | Tipos TypeScript para React DOM |
| **eslint** | ^9 | Ferramenta de linting JavaScript |
| **eslint-config-next** | 16.1.1 | Configuração ESLint otimizada para Next.js |
| **tailwindcss** | ^4 | Framework de CSS utilitário |
| **typescript** | ^5 | Superset de JavaScript com tipagem |

### Package.json Scripts

```json
{
  "dev": "next dev",                    // Inicia servidor de desenvolvimento
  "build": "next build",                // Build otimizado para produção
  "start": "next start",                // Inicia servidor de produção
  "lint": "eslint"                      // Executa linting no código
}
```

---

## 🚀 Como Rodar o Frontend

### Pré-requisitos
- ✅ Node.js 18+ instalado
- ✅ npm 8+ ou yarn/pnpm equivalentes
- ✅ Backend Java rodando em `http://localhost:8080`
- ✅ ML Service rodando em `http://localhost:8000` (para análise real)

### Método 1: Desenvolvimento Local (Recomendado)

#### Passo 1: Instalar Dependências
```bash
cd frontend/sentimentos-api
npm install
# ou
yarn install
# ou
pnpm install
```

#### Passo 2: Iniciar Servidor de Desenvolvimento
```bash
npm run dev
# ou
yarn dev
# ou
pnpm dev
```

#### Passo 3: Acessar a Aplicação
- Abra o navegador em: **http://localhost:3000**
- O servidor inicia automaticamente com Hot Reload habilitado

#### Características do Desenvolvimento:
- ✅ **Hot Module Replacement (HMR)**: Atualizações instantâneas ao salvar código
- ✅ **TypeScript Checking**: Validação em tempo de desenvolvimento
- ✅ **ESLint**: Verificação de qualidade contínua
- ✅ **Fast Refresh**: Preservação de estado durante edições

---

### Método 2: Build para Produção

#### Passo 1: Criar Build Otimizado
```bash
npm run build
# Ou com yarn/pnpm
yarn build
# pnpm build
```

#### Passo 2: Iniciar Servidor de Produção
```bash
npm start
# ou
yarn start
# ou
pnpm start
```

A aplicação estará disponível em: **http://localhost:3000**

#### Saída Esperada do Build:
```
▲ Next.js 16.1.1

✓ Compiled successfully
✓ Linting and type checking passed
✓ Created optimized production build
✓ Collected static files (public)
```

---

### Método 3: Docker (Containerização)

#### Pré-requisitos:
- ✅ Docker instalado e rodando
- ✅ Docker Compose (para orquestração com outros serviços)

#### Passo 1: Build da Imagem Docker
```bash
docker build -t sentimentos-api-frontend:latest .
```

#### Passo 2: Executar Container
```bash
docker run -p 3000:3000 \
  -e NEXT_PUBLIC_API_URL=http://localhost:8080 \
  sentimentos-api-frontend:latest
```

#### Passo 3: Com Docker Compose (Recomendado)
Na raiz do projeto:
```bash
docker-compose up frontend
```

Ou para toda a stack:
```bash
docker-compose up
```

#### Dockerfile Breakdown:

**Stage 1: Instalação de Dependências**
```dockerfile
FROM node:20-alpine AS deps
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm ci  # Install dependencies (mais eficiente que npm install)
```

**Stage 2: Build da Aplicação**
```dockerfile
FROM node:20-alpine AS builder
WORKDIR /app
COPY --from=deps /app/node_modules ./node_modules
COPY . .
ENV NEXT_TELEMETRY_DISABLED 1
RUN npm run build  # Gera arquivo otimizado em .next/
```

**Stage 3: Imagem de Produção**
```dockerfile
FROM node:20-alpine AS runner
WORKDIR /app
ENV NODE_ENV production
ENV NEXT_TELEMETRY_DISABLED 1
RUN addgroup --system --gid 1001 nodejs
RUN adduser --system --uid 1001 nextjs
COPY --from=builder /app/public ./public
# Servidor standalone escuta na porta 3000
```

---

## 🔧 Variáveis de Ambiente

### Desenvolvimento

Crie um arquivo `.env.local` na raiz do projeto:

```bash
# .env.local (NÃO commitar no git)

# URL do Backend Java
NEXT_PUBLIC_BACKEND_URL=http://localhost:8080

# URL do serviço de ML (opcional, se usado diretamente)
NEXT_PUBLIC_ML_SERVICE_URL=http://localhost:8000

# Ambiente
NEXT_PUBLIC_ENV=development

# Habilitar logs de debug
NEXT_PUBLIC_DEBUG=true
```

### Produção

```bash
# .env.production (ou via Docker/secrets)

NEXT_PUBLIC_BACKEND_URL=https://api.production.com
NEXT_PUBLIC_ML_SERVICE_URL=https://ml.production.com
NEXT_PUBLIC_ENV=production
NEXT_PUBLIC_DEBUG=false
```

### Variáveis Disponíveis no Cliente

Todas as variáveis prefixadas com `NEXT_PUBLIC_` são expostas ao navegador. Use para URLs públicas apenas.

---

## 🔗 Integração com Backend e Data Science

### Fluxo de Integração Geral

```
FRONTEND (Next.js)
    ↓
[POST /login]
    ↓
BACKEND (Spring Boot)
    ↓
[Autenticação]
    ↓
[POST /sentiment/analyze]
    ↓
BACKEND (Orquestração)
    ↓
[Chamada ao ML Service]
    ↓
ML SERVICE (Python/FastAPI)
    ↓
[Preprocessing + Modelo ML]
    ↓
[Response com classificação]
    ↓
FRONTEND (Exibição de resultado)
```

### 1. Integração com Backend Java

#### Endpoints Utilizados:

**1.1 Autenticação (Login)**
```http
POST /login
Content-Type: application/json

{
  "username": "user@example.com",
  "password": "senha123"
}

Response 200 OK:
{}

Response 401 Unauthorized:
{
  "error": "Invalid credentials"
}
```

**Implementação no Frontend**:
```typescript
// app/page.tsx
async function handleLogin() {
  const response = await fetch("http://localhost:8080/login", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ username: login, password: senha }),
  });
  
  if (response.ok) {
    router.push("/tela-principal");  // Redireciona se sucesso
  } else {
    setErro("Login ou senha inválido");
  }
}
```

---

**1.2 Análise de Sentimentos**
```http
POST /sentiment/analyze
Content-Type: application/json

{
  "text": "Adorei o produto, muito bom mesmo!"
}

Response 200 OK:
{
  "previsao": "Positivo",           // ou "Negativo", "Neutro"
  "probabilidade": 0.95,            // Confiança (0-1)
  "texto_processado": "adorei produto bom",
  "principais_palavras": ["adorei", "produto", "bom"]
}
```

**Implementação no Frontend**:
```typescript
// components/CardPrincipal.tsx
const response = await fetch('http://localhost:8080/sentiment/analyze', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({ text: texto }),
});

const resultado = await response.json();
// Resultado contém: previsao, probabilidade, principais_palavras
```

---

#### Mapeamento de Campos:

| Campo Frontend | Campo Backend | Descrição |
|----------------|---------------|-----------|
| `resultadoApi.previsao` | `response.previsao` | Classificação: Positivo/Neutro/Negativo |
| `resultadoApi.probabilidade` | `response.probabilidade` | Confiança (valor float 0-1) |
| `resultadoApi.texto_processado` | `response.texto_processado` | Texto após processamento NLP |
| `resultadoApi.principais_palavras` | `response.principais_palavras` | Array de palavras-chave |

---

### 2. Integração com ML Service (Data Science)

O Frontend **não comunica diretamente** com o ML Service. A comunicação acontece via Backend:

```
Frontend → Backend (endpoint /sentiment/analyze)
                ↓
         Backend → ML Service (orquestração interna)
                ↓
         ML Service (Análise com modelo)
                ↓
         Backend (resposta formatada)
                ↓
Frontend (resultado exibido)
```

#### O que o ML Service fornece (indiretamente):

1. **Classificação de Sentimento**
   - Modelo treinado em dataset MercadoLivre
   - Identifica: Positivo, Neutro, Negativo

2. **Confiança/Probabilidade**
   - Score de 0-1 indicando certeza da predição
   - Utilizado para exibir barra de progresso

3. **Palavras-Chave**
   - Identificação das principais palavras que influenciaram a classificação
   - Contribui para explicabilidade

4. **Processamento de Texto**
   - Normalização, limpeza, demojização
   - Mantém contexto de negações e pontuação expressiva

---

### 3. Fluxo Completo de Análise

```
┌─────────────────────────────────────────────────────────────┐
│ FRONTEND (Next.js) - Tela Principal                         │
├─────────────────────────────────────────────────────────────┤
│                                                              │
│  Usuário digita: "Adorei! Produto excelente!"              │
│         ↓                                                    │
│  CardPrincipal captura texto                               │
│         ↓                                                    │
│  onClick(Analisar Sentimento)                              │
│         ↓ POST /sentiment/analyze                          │
│                                                              │
└─────────────────────────────────────────────────────────────┘
                           ↓
┌─────────────────────────────────────────────────────────────┐
│ BACKEND (Spring Boot) - API Gateway                         │
├─────────────────────────────────────────────────────────────┤
│                                                              │
│  SentimentController recebe POST                           │
│         ↓                                                    │
│  SentimentService valida entrada                           │
│         ↓                                                    │
│  Chama ML Service com timeout (fallback se falhar)        │
│         ↓                                                    │
│  Formata resposta                                          │
│                                                              │
└─────────────────────────────────────────────────────────────┘
                           ↓
┌─────────────────────────────────────────────────────────────┐
│ ML SERVICE (Python/FastAPI) - NLP & Modelo                 │
├─────────────────────────────────────────────────────────────┤
│                                                              │
│  TextPreprocessor:                                         │
│  - Demojização                                             │
│  - Limpeza (stopwords, pontuação)                          │
│  - Lematização                                             │
│         ↓                                                    │
│  ModelService:                                             │
│  - Vetorização (TF-IDF/Word2Vec)                           │
│  - Execução do modelo (Scikit-Learn)                       │
│  - Extração de palavras-chave                              │
│         ↓                                                    │
│  Retorna: {                                                │
│    "prediction": "Positivo",                              │
│    "confidence": 0.95,                                     │
│    "top_words": ["adorei", "excelente"]                   │
│  }                                                         │
│                                                              │
└─────────────────────────────────────────────────────────────┘
                           ↓
                    Response formatado
                           ↓
┌─────────────────────────────────────────────────────────────┐
│ FRONTEND (Next.js) - Resultado                             │
├─────────────────────────────────────────────────────────────┤
│                                                              │
│  Resultado.tsx recebe dados                                │
│         ↓                                                    │
│  Exibe:                                                    │
│  ✓ Ícone + "Positivo"                                     │
│  ✓ Barra de progresso (95%)                               │
│  ✓ Texto analisado                                        │
│  ✓ Palavras-chave: [adorei] [excelente]                  │
│         ↓                                                    │
│  Animações suaves de entrada                              │
│                                                              │
└─────────────────────────────────────────────────────────────┘
```

---

## 📚 Guia de Desenvolvimento

### Estrutura de Componentes

#### 1. **Componentes de Layout**
- `Titulo.tsx` - Logo e identidade visual
- `TextoPrincipal.tsx` - Mensagens principais
- `Subtitulo.tsx` - Textos descritivos
- `Footer.tsx` - Rodapé consistente

#### 2. **Componentes Funcionais**
- `CardPrincipal.tsx` - Input e lógica de envio
- `Resultado.tsx` - Exibição de resultados
- `ReferenciaTextual.tsx` - Informações contextuais

### Criando Novos Componentes

**Exemplo: Novo componente de resultado alternativo**

```typescript
// components/ResultadoExpandido.tsx
'use client'

interface ResultadoExpandidoProps {
  resultado: ResultadoAnalise;
}

export default function ResultadoExpandido({ resultado }: ResultadoExpandidoProps) {
  return (
    <div className="p-6 bg-slate-800 rounded-lg border border-gray-700">
      <h3 className="text-lg font-bold text-white mb-4">
        Análise Detalhada
      </h3>
      
      {/* Seu conteúdo aqui */}
      
      <p className="text-gray-400">
        Sentimento: {resultado.previsao}
      </p>
    </div>
  );
}
```

### Padrões de Código

#### 1. **Componentes com Estado**
```typescript
'use client'  // Indica Client Component (necessário para useState, eventos)

import { useState } from 'react';

export default function MeuComponente() {
  const [valor, setValor] = useState('');
  
  return (
    <input 
      value={valor}
      onChange={(e) => setValor(e.target.value)}
    />
  );
}
```

#### 2. **Chamadas à API**
```typescript
const response = await fetch('http://localhost:8080/endpoint', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify(data),
});

if (!response.ok) {
  throw new Error(`Erro: ${response.status}`);
}

const resultado = await response.json();
```

#### 3. **Tratamento de Erros**
```typescript
try {
  // lógica
} catch (error) {
  const mensagem = error instanceof Error ? error.message : String(error);
  
  if (mensagem.includes('Failed to fetch')) {
    // Erro de conectividade
  } else {
    // Outro tipo de erro
  }
}
```

### Estilização com Tailwind CSS

#### Convenções Utilizadas:

```typescript
// Dark mode (padrão do projeto)
className="bg-gray-800 text-white"

// Cores de destaque
className="text-blue-500 border-blue-500"

// Bordas e arredondamentos
className="border border-gray-600 rounded-lg"

// Responsividade
className="w-full md:w-1/2 lg:w-1/3"

// Transições e animações
className="transition duration-200 hover:bg-blue-700"
```

---

## 🏗️ Build para Produção

### Otimizações Aplicadas

1. **Code Splitting**: Next.js divide o código em chunks
2. **Image Optimization**: Otimização de imagens automática
3. **Tree Shaking**: Remove código não utilizado
4. **Minification**: Compressão de JS/CSS
5. **Static Generation**: Páginas estáticas quando possível

### Checklist Pré-Deploy

- [ ] Todas as variáveis `NEXT_PUBLIC_*` definidas
- [ ] URLs de API apontam para produção
- [ ] ESLint passa sem erros: `npm run lint`
- [ ] Build completa sem warnings: `npm run build`
- [ ] Testar localmente: `npm start`
- [ ] Verificar responsividade em múltiplos dispositivos
- [ ] Testar fluxo de autenticação completo
- [ ] Testar análise com múltiplos tipos de texto

### Deploy na Vercel (Recomendado)

```bash
# 1. Conectar repositório GitHub à Vercel
# 2. Vercel detecta automatically que é Next.js
# 3. Configurar variáveis de ambiente na Dashboard
# 4. Deploy automático em cada push para main
```

### Deploy em Servidor Próprio

```bash
# 1. Fazer build
npm run build

# 2. Instalar produção apenas
npm ci --production

# 3. Iniciar servidor
npm start

# 4. Usar reverse proxy (nginx) apontando para http://localhost:3000
```

---

## 🐛 Troubleshooting

### Problema: Erro de Conexão com Backend

**Sintoma**: "Erro ao conectar com o servidor"

**Causas Possíveis**:
1. Backend não está rodando em `http://localhost:8080`
2. CORS não está configurado no Backend
3. Firewall bloqueando conexão

**Solução**:
```bash
# 1. Verificar se backend está rodando
curl http://localhost:8080/sentiment/analyze

# 2. Se precisar CORS, adicionar no Backend:
@CrossOrigin(origins = "http://localhost:3000")
```

---

### Problema: Página em Branco

**Sintoma**: Frontend carrega mas mostra página branca

**Causas Possíveis**:
1. Erro de JavaScript no console
2. Erro de tipo TypeScript
3. Dependência não instalada

**Solução**:
```bash
# 1. Verificar console do navegador (F12)
# 2. Checar logs do terminal: npm run dev
# 3. Reinstalar dependências:
rm -rf node_modules package-lock.json
npm install
```

---

### Problema: Hot Reload Não Funciona

**Sintoma**: Alterações no código não refletem automaticamente

**Solução**:
```bash
# 1. Verificar se usando 'next dev'
# 2. Limpar cache do Next.js
rm -rf .next
npm run dev

# 3. Verificar se arquivo tem 'use client' (Client Component)
# ou se está em app/ (App Router)
```

---

### Problema: Tipo TypeScript Não Reconhecido

**Sintoma**: Erro como "Property 'xyz' does not exist"

**Solução**:
```bash
# 1. Verificar interface está correta:
interface ResultadoAnalise {
  previsao?: string;  // ? para opcional
  probabilidade?: number;
}

# 2. Usar 'unknown' para propriedades dinâmicas:
[key: string]: unknown;

# 3. Regenerar tipos:
rm -rf .next
npm run dev
```

---

### Problema: Build Falha

**Sintoma**: `npm run build` retorna erro

**Debug**:
```bash
# 1. Verificar erros de linting:
npm run lint

# 2. Verificar erros de tipo:
npx tsc --noEmit

# 3. Ver erro completo do build:
npm run build -- --debug
```

---

### Problema: Port 3000 Já em Uso

**Sintoma**: "Port 3000 is already in use"

**Solução**:
```bash
# Windows - Encontrar processo na porta 3000
netstat -ano | findstr :3000
taskkill /PID <PID> /F

# macOS/Linux
lsof -i :3000
kill -9 <PID>

# Ou iniciar em porta diferente:
npm run dev -- -p 3001
```

---

## 📱 Responsividade e Testes

### Breakpoints Utilizados (Tailwind)

```
sm:  640px   (tablets pequenos)
md:  768px   (tablets)
lg:  1024px  (desktops)
xl:  1280px  (desktops grandes)
```

### Testar Responsividade

```bash
# No navegador:
1. Abrir DevTools (F12)
2. Clicar em "Toggle Device Emulation" (Ctrl+Shift+M)
3. Testar em: iPhone SE, iPad, Desktop

# Ou usar Firefox Responsive Design Mode
```

---

## 📖 Documentação Adicional

- **[Next.js Docs](https://nextjs.org/docs)** - Documentação oficial
- **[React 19 Docs](https://react.dev)** - Novo site do React
- **[Tailwind CSS](https://tailwindcss.com)** - CSS Utilitário
- **[TypeScript Handbook](https://www.typescriptlang.org/docs)** - Tipagem
- **[Docker Docs](https://docs.docker.com)** - Containerização

---

## 📝 Licença

Projeto desenvolvido para Hackathon Acadêmico.

---

## 👥 Contato e Suporte

- **Backend Issues**: Consultar [backend/README.md](../backend/README.md)
- **Data Science Issues**: Consultar [data-science/README.md](../data-science/README.md)
- **Projeto Geral**: Ver [README.md](../README.md) raiz

---

**Última Atualização**: Janeiro 2026
**Versão**: 1.0.0
