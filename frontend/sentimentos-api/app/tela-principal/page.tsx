'use client'

import CardPrincipal from "@/components/CardPrincipal";
import Footer from "@/components/Footer";
import ReferenciaTextual from "@/components/ReferenciaTextual";
import Resultado from "@/components/Resultado";
import Subtitulo from "@/components/Subtitulo";
import TextoPrincipal from "@/components/TextoPrincipal";
import Titulo from "@/components/Titulo";
import { useState } from "react";

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
  const [resultadoApi, setResultadoApi] = useState<ResultadoAnalise | null>(null);

return (
  <div className="min-h-screen flex flex-col">
    <header className="fixed top-0 left-0 right-0 z-50 bg-background shadow-md">
      <Titulo />
    </header>

    <main className="flex-1 w-full pt-24 px-4 md:px-8 max-w-7xl mx-auto space-y-12">
      <TextoPrincipal />
      <Subtitulo />

      <div className="flex justify-center">
        <ReferenciaTextual />
      </div>

      {/* Card + Resultado */}
      <div className="grid grid-cols-1 lg:grid-cols-1 gap-8 place-items-center">
        
        {/* CardPrincipal */}
        <div className="w-full max-w-xl">
          <CardPrincipal
            onTextoChange={setTextoAnalisado}
            onAnalise={setResultadoApi}
          />
        </div>

        {/* Resultado */}
        {resultadoApi && (
          <div className="w-full max-w-xl">
            <Resultado
              textoAnalisado={textoAnalisado}
              resultadoApi={resultadoApi}
            />
          </div>
        )}

      </div>

      <Footer />
    </main>
  </div>
);
}