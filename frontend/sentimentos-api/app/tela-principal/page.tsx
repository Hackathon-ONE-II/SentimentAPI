'use client'

import CardPrincipal from "@/components/CardPrincipal";
import Footer from "@/components/Footer";
import ReferenciaTextual from "@/components/ReferenciaTextual";
import Resultado from "@/components/Resultado";
import Subtitulo from "@/components/Subtitulo";
import TextoPrincipal from "@/components/TextoPrincipal";
import Titulo from "@/components/Titulo";
import { useState } from "react";

export default function TelaPrincipal() {
  const [textoAnalisado, setTextoAnalisado] = useState("");

  return (
    <div className="flex min-h-screen items-center justify-center flex-col">
      <header className="fixed top-0 left-0 right-0 w-full z-50 shadow-md hover:shadow-lg transition-shadow duration-300 pt-5">
        <Titulo />
      </header>
      <main className="relative container mx-auto px-4 py-8 space-y-12 mt-20">
        <TextoPrincipal />
        <Subtitulo />
        
        <div className="flex flex-wrap items-center justify-center gap-6 pt-4 animate-slide-up" style={{ animationDelay: '0.3s' }}>
          <ReferenciaTextual />
        </div>

        <div className="flex flex-wrap items-center justify-center gap-6 pt-4 animate-slide-up" style={{ animationDelay: '0.3s' }}>
          <div className="w-200 flex-col items-center gap-2 mb-10">
            <CardPrincipal onTextoChange={setTextoAnalisado} />
          </div>
        </div>

        <div className="flex flex-wrap items-center justify-center gap-6 pt-4 animate-slide-up" style={{ animationDelay: '0.3s' }}>
          <div className="w-200 flex-col items-center gap-2 mb-10 ml-27">
            <Resultado textoAnalisado={textoAnalisado} />
          </div>
        </div>
        
        <Footer />
      </main>
    </div>
  );
}
