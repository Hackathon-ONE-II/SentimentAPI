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

  return (
    <div className="w-full max-w-2xl p-8 bg-linear-to-br from-slate-900 to-slate-800 border-2 border-green-500 rounded-lg">
      <div className="flex justify-between items-start mb-8">
        <h2 className="text-xl text-green-400">Resultado da Análise</h2>
        <div className="flex items-center gap-2 px-3 py-1 bg-green-500/20 border border-green-500 rounded-full">
          <span className="text-2xl">👍</span>
          <span className="text-green-400 font-semibold">{resultadoApi.previsao || resultadoApi.prediction}</span>
        </div>
      </div>

      <div className="mb-8">
        <div className="flex items-center gap-2 mb-3">
          <span className="text-xl">📈</span>
          <span className="text-gray-400">Confiança da Predição</span>
          <span className="text-3xl font-bold text-green-400 ml-auto">{Math.round((resultadoApi.probabilidade || resultadoApi.confidence || 0) * 100)}%</span>
        </div>
        <div className="w-full bg-gray-700 rounded-full h-3 overflow-hidden">
          <div className="bg-green-500 h-full rounded-full" style={{ width: `${(resultadoApi.probabilidade || resultadoApi.confidence || 0) * 100}%` }}></div>
        </div>
      </div>

      <div className="mb-8">
        <h3 className="text-gray-400 text-sm mb-3">Texto Analisado</h3>
        <p className="text-gray-300 italic bg-gray-800/50 p-4 rounded border border-gray-700">
          {textoAnalisado}
        </p>
      </div>

      {resultadoApi.principais_palavras && resultadoApi.principais_palavras.length > 0 && (
        <div className="mb-8">
          <div className="flex items-center gap-2 mb-3">
            <span className="text-xl">🔑</span>
            <span className="text-gray-400">Principais Palavras</span>
          </div>
          <div className="flex flex-wrap gap-2">
            {resultadoApi.principais_palavras.map((palavra, index) => (
              <span
                key={index}
                className="px-3 py-1 bg-blue-500/20 border border-blue-500 rounded-full text-blue-300 text-sm font-medium hover:bg-blue-500/30 transition"
              >
                {palavra}
              </span>
            ))}
          </div>
        </div>
      )}
    </div>
  )
}