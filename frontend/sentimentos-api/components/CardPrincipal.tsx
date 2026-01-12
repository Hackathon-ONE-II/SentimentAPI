'use client'

import { useState } from 'react';

interface CardPrincipalProps {
  onTextoChange: (texto: string) => void;
  onAnalise: (resultado: any) => void;
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
      const response = await fetch('http://localhost:8000/predict', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ text: texto }),
      });

      if (!response.ok) {
        throw new Error('Erro ao analisar o sentimento');
      }

      const resultado = await response.json();
      onAnalise(resultado);
    } catch (error) {
      console.error('Erro:', error);
      alert('Erro ao conectar com a API');
    } finally {
      setCarregando(false);
    }
  };

  return (
    <>
      <div className="w-full animate-slide-up">
        <textarea 
          placeholder="Digite aqui..." 
          value={texto}
          onChange={handleTextoChange}
          className="w-full h-80 px-4 py-3 bg-gray-800 text-white rounded border border-gray-600 focus:outline-none focus:border-blue-500 transition resize-none placeholder-shown:text-sm"
        />
      </div>
      <div className="mt-6">
        <button 
          onClick={handleAnalisar}
          disabled={carregando}
          className="w-full px-6 py-3 bg-blue-600 text-white font-semibold rounded-lg hover:bg-blue-700 active:bg-blue-800 cursor-pointer transition duration-200 shadow-md disabled:opacity-50 disabled:cursor-not-allowed"
        >
          {carregando ? 'Analisando...' : 'Analisar Sentimento'}
        </button>
      </div>
    </>
  )
}