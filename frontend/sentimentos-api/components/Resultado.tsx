'use client'


export default function Resultado() {
  return (
    <>
      <div className="w-full max-w-2xl p-8 bg-gradient-to-br from-slate-900 to-slate-800 border-2 border-green-500 rounded-lg">
        
        {/* Título e Badge */}
        <div className="flex justify-between items-start mb-8">
          <h2 className="text-xl text-green-400">Resultado da Análise</h2>
          <div className="flex items-center gap-2 px-3 py-1 bg-green-500/20 border border-green-500 rounded-full">
            <span className="text-2xl">👍</span>
            <span className="text-green-400 font-semibold">Positivo</span>
          </div>
        </div>

        {/* Confiança da Predição */}
        <div className="mb-8">
          <div className="flex items-center gap-2 mb-3">
            <span className="text-xl">📈</span>
            <span className="text-gray-400">Confiança da Predição</span>
            <span className="text-3xl font-bold text-green-400 ml-auto">85%</span>
          </div>
          {/* Barra de Progresso */}
          <div className="w-full bg-gray-700 rounded-full h-3 overflow-hidden">
            <div className="bg-green-500 h-full rounded-full" style={{ width: '85%' }}></div>
          </div>
        </div>

        {/* Texto Analisado */}
        <div className="mb-8">
          <h3 className="text-gray-400 text-sm mb-3">Texto Analisado</h3>
          <p className="text-gray-300 italic bg-gray-800/50 p-4 rounded border border-gray-700">
            "Excelente atendimento! O produto chegou antes do prazo e a qualidade superou minhas expectativas. Recomendo muito!"
          </p>
        </div>

        {/* Palavras Mais Influentes */}
        <div>
          <h3 className="text-gray-400 text-sm mb-3">Palavras mais influentes</h3>
          <div className="flex flex-wrap gap-3">
            <span className="px-4 py-2 bg-slate-700 border border-blue-500 text-blue-300 rounded-full text-sm">
              excelente
            </span>
            <span className="px-4 py-2 bg-slate-700 border border-blue-500 text-blue-300 rounded-full text-sm">
              recomendo
            </span>
            <span className="px-4 py-2 bg-slate-700 border border-blue-500 text-blue-300 rounded-full text-sm">
              qualidade
            </span>
          </div>
        </div>

      </div>
    </>
  )
}
