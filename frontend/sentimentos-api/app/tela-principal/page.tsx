'use client';

import { useState } from "react";

import CardPrincipal from "@/components/CardPrincipal";
import Footer from "@/components/Footer";
import ReferenciaTextual from "@/components/ReferenciaTextual";
import MetricasSentimento from "@/components/MetricasSentimento";
import Resultado from "@/components/Resultado";
import Subtitulo from "@/components/Subtitulo";
import TextoPrincipal from "@/components/TextoPrincipal";
import Titulo from "@/components/Titulo";

interface ResultadoAnalise {
  previsao?: string;
  prediction?: string;
  probabilidade?: number;
  confidence?: number;
  texto_processado?: string;
  [key: string]: unknown;
}

export default function TelaPrincipal() {
  const [textoAnalisado, setTextoAnalisado] = useState("");
  const [resultadoApi, setResultadoApi] =
    useState<ResultadoAnalise | null>(null);

  return (
    <div className="min-h-screen flex flex-col bg-background">

      {/* HEADER FIXO */}
      <header className="fixed top-0 left-0 right-0 z-50 bg-background shadow-md">
        <Titulo />
      </header>

      {/* CONTEÚDO */}
      <main className="flex-1 w-full pt-28 px-4 md:px-8 max-w-7xl mx-auto space-y-12">

        <TextoPrincipal />
        <Subtitulo />

        <div className="flex justify-center">
          <ReferenciaTextual />
        </div>

        {/* 🔷 ÁREA PRINCIPAL: ANÁLISE + MÉTRICAS */}
        <section className="grid grid-cols-1 lg:grid-cols-2 gap-10 items-start">

          {/* 🧠 COLUNA ESQUERDA — ANÁLISE */}
          <div className="space-y-10">

            <CardPrincipal
              onTextoChange={setTextoAnalisado}
              onAnalise={setResultadoApi}
            />

            {resultadoApi && (
              <Resultado
                textoAnalisado={textoAnalisado}
                resultadoApi={resultadoApi}
              />
            )}

          </div>

          {/* 📊 COLUNA DIREITA — MÉTRICAS */}
          <div className="w-full h-full">
            <MetricasSentimento />
          </div>

        </section>

        <Footer />
      </main>
    </div>
  );
}
