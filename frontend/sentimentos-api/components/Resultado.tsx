'use client'

interface ResultadoAnalise {
  previsao?: string;
  prediction?: string;
  probabilidade?: number;
  confidence?: number;
  texto_processado?: string;
  principais_palavras?: string[];
  [key: string]: unknown;
}

interface ResultadoProps {
  textoAnalisado: string;
  resultadoApi: ResultadoAnalise;
}

export default function Resultado({ textoAnalisado, resultadoApi }: ResultadoProps) {

  const isPositivo = resultadoApi.previsao === 'Positivo' || resultadoApi.prediction === 'Positivo';
  const corBorda = isPositivo ? 'green-500' : 'red-500';
  const corTexto = isPositivo ? 'green-400' : 'red-400';
  const corBg = isPositivo ? 'green-500/30' : 'red-500/30';
  const corBarraProgresso = isPositivo ? 'bg-green-500' : 'bg-red-500';
  const emoji = isPositivo ? '👍' : '👎';

  return (
    <div className={`w-full p-4 md:p-6 bg-linear-to-br from-slate-900 to-slate-800 border border-${corBorda} rounded-xl space-y-6`}>
      
      {/* Header */}
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
        <h2 className={`text-lg md:text-xl text-${corTexto} font-semibold`}>
          Resultado da Análise
        </h2>

        <div className={`flex items-center gap-2 px-3 py-1 bg-${corBg} border border-${corBorda} rounded-full w-fit`}>
          <span className="text-xl">{emoji}</span>
          <span className={`text-${corTexto} font-semibold text-sm md:text-base`}>
            {resultadoApi.previsao || resultadoApi.prediction}
          </span>
        </div>
      </div>

      {/* Confiança */}
      <div className="space-y-2">
        <div className="flex items-center gap-2 text-sm md:text-base">
          <span>📈</span>
          <span className="text-gray-400">Confiança da Predição</span>
          <span className={`ml-auto text-xl md:text-2xl font-bold text-${corTexto}`}>
            {Math.round((resultadoApi.probabilidade || resultadoApi.confidence || 0) * 100)}%
          </span>
        </div>

        <div className="w-full bg-gray-700 rounded-full h-3 overflow-hidden">
          <div
            className={`${corBarraProgresso} h-full rounded-full transition-all duration-300`}
            style={{ width: `${(resultadoApi.probabilidade || resultadoApi.confidence || 0) * 100}%` }}
          />
        </div>
      </div>

    {/* Texto analisado */}
    <div className="space-y-2">
      <h3 className="text-gray-400 text-sm">Texto Analisado</h3>
      <p className="text-gray-300 italic bg-gray-800/50 p-3 md:p-4 rounded border border-gray-700 text-sm md:text-base wrap-break-word">
        {textoAnalisado}
      </p>
    </div>

    {/* Palavras-chave */}
    {resultadoApi.principais_palavras && resultadoApi.principais_palavras.length > 0 && (
      <div className="space-y-3">
        <div className="flex items-center gap-2 text-sm md:text-base">
          <span>🔑</span>
          <span className="text-gray-400">Principais Palavras</span>
        </div>

        <div className="flex flex-wrap gap-2">
          {resultadoApi.principais_palavras.map((palavra, index) => (
            <span
              key={index}
              className="
                px-3 py-1 
                bg-blue-500/20 
                border border-blue-500 
                rounded-full 
                text-blue-300 
                text-xs md:text-sm 
                font-medium 
                hover:bg-blue-500/30 
                transition
              "
            >
              {palavra}
            </span>
          ))}
        </div>
      </div>
    )}
  </div>
);
}