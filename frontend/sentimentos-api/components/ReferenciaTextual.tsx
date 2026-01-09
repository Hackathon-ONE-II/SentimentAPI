
export default function ReferenciaTextual() {
  return (
    <>
        <div className="flex items-center gap-2 text-sm text-gray-500">
          <span className="w-4 h-4 flex items-center justify-center text-yellow-400">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" className="w-4 h-4" aria-hidden="true">
              <path d="M13 2L3 14h7l-1 8 10-12h-7l1-8z" />
            </svg>
          </span>
          <span>Resposta instantânea</span>
        </div>
        <div className="flex items-center gap-2 text-sm text-gray-500">
          <span className="w-4 h-4 flex items-center justify-center text-blue-400">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" className="w-4 h-4" aria-hidden="true">
              <path d="M3 7a2 2 0 012-2h14a2 2 0 012 2v10a2 2 0 01-2 2H5a2 2 0 01-2-2V7zm4 2h10v6H7V9z" />
            </svg>
          </span>
          <span>Modelo treinado</span>
        </div>
    </>
  )
}
