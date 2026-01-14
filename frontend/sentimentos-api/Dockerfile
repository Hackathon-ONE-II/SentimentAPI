# Stage 1: Instalação das dependências
FROM node:20-alpine AS deps
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm ci

# Stage 2: Build da aplicação
FROM node:20-alpine AS builder
WORKDIR /app
COPY --from=deps /app/node_modules ./node_modules
COPY . .
# Desabilita telemetria do Next.js durante o build
ENV NEXT_TELEMETRY_DISABLED 1

# Argumentos de build para injetar variáveis de ambiente no Next.js
ARG NEXT_PUBLIC_API_URL
ENV NEXT_PUBLIC_API_URL=$NEXT_PUBLIC_API_URL

RUN npm run build

# Stage 3: Imagem de produção, copia todos os arquivos e executa next
FROM node:20-alpine AS runner
WORKDIR /app

ENV NODE_ENV production
ENV NEXT_TELEMETRY_DISABLED 1

# Cria grupo e usuário para rodar a aplicação de forma segura
RUN addgroup --system --gid 1001 nodejs
RUN adduser --system --uid 1001 nextjs

# Copia public folder (se necessário para assets estáticos)
COPY --from=builder /app/public ./public

# Copia arquivos necessários para rodar o servidor Next.js standalone (mode output: standalone no next.config.ts é recomendado, mas vamos usar o padrão por enquanto)

COPY --from=builder --chown=nextjs:nodejs /app/.next ./.next
COPY --from=builder /app/node_modules ./node_modules
COPY --from=builder /app/package.json ./package.json

USER nextjs

EXPOSE 3000

ENV PORT 3000
# set hostname to localhost
ENV HOSTNAME "0.0.0.0"

CMD ["npm", "start"]
