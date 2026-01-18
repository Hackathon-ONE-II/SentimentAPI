export default function Titulo() {
  return (
    <header
      className="
        backdrop-blur-md
        bg-black/60
        transition-all
        duration-500
        ease-in-out
        px-6
        py-4
      "
    >
      <h1 className="text-lg font-semibold tracking-tight text-white flex items-center">
        Sentiment
        <span
          className="
            ml-1
            bg-gradient-to-r
            from-blue-500
            to-purple-700
            bg-clip-text
            text-transparent
            inline-flex
            items-center
          "
        >
          AI
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
            fill="currentColor"
            className="w-5 h-5 ml-1 text-yellow-400"
            aria-hidden="true"
          >
            <path d="M12 2a3 3 0 00-3 3v1H8a3 3 0 00-3 3v1H4a1 1 0 000 2h1v1a3 3 0 003 3h1v1a3 3 0 006 0v-1h1a3 3 0 003-3v-1h1a1 1 0 000-2h-1V9a3 3 0 00-3-3h-1V5a3 3 0 00-3-3z" />
          </svg>
        </span>
      </h1>
    </header>
  );
}
