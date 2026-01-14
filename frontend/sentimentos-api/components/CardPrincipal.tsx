'use client'

import { useState } from 'react';

interface ResultadoAnalise {
  previsao?: string;
  prediction?: string;
  probabilidade?: number;
  confidence?: number;
  texto_processado?: string;
  [key: string]: unknown;
}

interface CardPrincipalProps {
  onTextoChange: (texto: string) => void;
  onAnalise: (resultado: ResultadoAnalise) => void;
}

export default function CardPrincipal({ onTextoChange, onAnalise }: CardPrincipalProps) {
  const [texto, setTexto] = useState('');
  const [carregando, setCarregando] = useState(false);

  const handleTextoChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    const novoTexto = e.target.value;
    setTexto(novoTexto);
    onTextoChange(novoTexto);
  };

  const handleAnalisar = async () => {
    if (!texto.trim()) {
      alert('Por favor, digite algo para analisar');
      return;
    }

    setCarregando(true);
    try {
      const apiUrl = process.env.NEXT_PUBLIC_API_URL || 'http://localhost:8080';
      const response = await fetch(`${apiUrl}/sentiment/analyze`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ text: texto }),
      });

      if (!response.ok) {
        throw new Error(`Erro na API: ${response.status} ${response.statusText}`);
      }

      const resultado = await response.json();
      onAnalise(resultado);
    } catch (error) {
      console.error('Erro completo:', error);
      const mensagem = error instanceof Error ? error.message : String(error);

      if (mensagem.includes('Failed to fetch')) {
        alert('Erro: Não conseguimos conectar ao servidor de análise.\n\nCertifique-se de que:\n1. O servidor em localhost:8000 está rodando\n2. A API está acessível');
      } else {
        alert(`Erro ao analisar: ${mensagem}`);
      }
    } finally {
      setCarregando(false);
    }
  };

  return (
    <div className="w-full space-y-4">
      <textarea
        placeholder="Digite aqui..."
        value={texto}
        onChange={handleTextoChange}
        className="
        w-full 
        min-h-[200px] 
        md:min-h-[320px]
        px-4 py-3 
        bg-gray-800 
        text-white 
        rounded-lg 
        border border-gray-600 
        focus:outline-none 
        focus:border-blue-500 
        transition 
        resize-none
      "
      />

      <button
        onClick={handleAnalisar}
        disabled={carregando}
        className="
        w-full 
        px-6 py-3 
        bg-blue-600 
        text-white 
        font-semibold 
        rounded-lg 
        hover:bg-blue-700 
        active:bg-blue-800 
        transition 
        duration-200 
        shadow-md 
        disabled:opacity-50 
        disabled:cursor-not-allowed
        cursor-pointer
      "
      >
        {carregando ? 'Analisando...' : 'Analisar Sentimento'}
      </button>
    </div>
  );
}