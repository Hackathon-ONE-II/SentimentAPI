// rfc: Cria um componente funcional com export nomeado

export default function CardPrincipal() {
  return (
    <>
      <div className="w-full animate-slide-up">
        <textarea 
          placeholder="Digite aqui..." 
          className="w-full h-80 px-4 py-3 bg-gray-800 text-white rounded border border-gray-600 focus:outline-none focus:border-blue-500 transition resize-none placeholder-shown:text-sm"
        />
      </div>
      <div className="mt-6">
        <button className="w-full px-6 py-3 bg-blue-600 text-white font-semibold rounded-lg hover:bg-blue-700 active:bg-blue-800 cursor-pointer transition duration-200 shadow-md">
          Analisar Sentimento
        </button>
      </div>
    </>
  )
}