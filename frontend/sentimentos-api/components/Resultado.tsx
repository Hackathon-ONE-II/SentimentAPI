'use client'

const respostaApi = {
  "texto_processado": "gostei do produto",
  "previsao": "Positivo",
  "probabilidade": 0.95
};

export default function Resultado({ textoAnalisado }: { textoAnalisado: string }) {

  return (
    <div className="w-full max-w-2xl p-8 bg-linear-to-br from-slate-900 to-slate-800 border-2 border-green-500 rounded-lg">
      <div className="flex justify-between items-start mb-8">
        <h2 className="text-xl text-green-400">Resultado da Análise</h2>
        <div className="flex items-center gap-2 px-3 py-1 bg-green-500/20 border border-green-500 rounded-full">
          <span className="text-2xl">👍</span>
          <span className="text-green-400 font-semibold">{respostaApi.previsao}</span>
        </div>
      </div>

      <div className="mb-8">
        <div className="flex items-center gap-2 mb-3">
          <span className="text-xl">📈</span>
          <span className="text-gray-400">Confiança da Predição</span>
          <span className="text-3xl font-bold text-green-400 ml-auto">{Math.round(respostaApi.probabilidade * 100)}%</span>
        </div>
        <div className="w-full bg-gray-700 rounded-full h-3 overflow-hidden">
          <div className="bg-green-500 h-full rounded-full" style={{ width: `${respostaApi.probabilidade * 100}%` }}></div>
        </div>
      </div>

      <div className="mb-8">
        <h3 className="text-gray-400 text-sm mb-3">Texto Analisado</h3>
        <p className="text-gray-300 italic bg-gray-800/50 p-4 rounded border border-gray-700">
          {textoAnalisado}
        </p>
      </div>
    </div>
  )
}