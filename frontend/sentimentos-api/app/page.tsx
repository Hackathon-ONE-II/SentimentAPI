
export default function Home() {
  return (
    <div className="flex min-h-screen items-center justify-center bg-zinc-50 font-sans dark:bg-black">
      <main className="flex min-h-screen w-full max-w-3xl flex-col items-center justify-between py-32 px-16 bg-white dark:bg-black sm:items-start">
          <h1 className="max-w-xs text-3xl font-semibold leading-50 tracking-tight text-black dark:text-zinc-50n m-1">
            Entenda o sentimento
          </h1>
          <h1 className="max-w-xs text-3xl font-semibold leading-50 bg-linear-to-r from-blue-400 to-indigo-500 bg-clip-text text-transparent">
            dos seus feedbacks
          </h1>
      </main>
    </div>
  );
}
