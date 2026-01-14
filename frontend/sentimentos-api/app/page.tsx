"use client";

import { useRouter } from "next/navigation";
import { useState } from "react";

import Footer from "@/components/Footer";
import TextoPrincipal from "@/components/TextoPrincipal";
import Titulo from "@/components/Titulo";

interface UsuarioApi {
  username: string;
  password: string;
}

export default function Home() {
  const router = useRouter();

  const [login, setLogin] = useState("");
  const [senha, setSenha] = useState("");
  const [erro, setErro] = useState("");

  async function handleLogin() {
    setErro("");

    try {
      const apiUrl = process.env.NEXT_PUBLIC_API_URL || "http://localhost:8080";
      const response = await fetch(`${apiUrl}/login`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          username: login,
          password: senha,
        }),
      });

      if (response.ok) {
        router.push("/tela-principal");
      } else {
        setErro("Login ou senha inválido");
      }
    } catch (error) {
      console.error("Erro ao conectar com a API:", error);
      setErro("Erro ao conectar com o servidor");
    }
  }

  return (
    <div className="flex min-h-screen items-center justify-center flex-col">
      <header className="fixed top-0 left-0 right-0 w-full z-50 shadow-md hover:shadow-lg transition-shadow duration-300 pt-5">
        <Titulo />
      </header>

      <main className="flex-col relative container mx-auto px-4 py-8 space-y-12 mt-20">
        <TextoPrincipal />

        <div className="w-full animate-slide-up text-center justify-center space-y-4">

          <div>
            <p>Login</p>
            <input
              type="text"
              value={login}
              onChange={(e) => setLogin(e.target.value)}
              placeholder="Digite seu login"
              className="w-70 h-12 px-4 py-3 bg-gray-800 text-white rounded border border-gray-600 focus:outline-none focus:border-blue-500 transition text-center"
            />
          </div>

          <div>
            <p>Senha</p>
            <input
              type="password"
              value={senha}
              onChange={(e) => setSenha(e.target.value)}
              placeholder="Digite sua senha"
              className="w-70 h-12 px-4 py-3 bg-gray-800 text-white rounded border border-gray-600 focus:outline-none focus:border-blue-500 transition text-center"
            />
          </div>

          {erro && (
            <p className="text-red-500 text-sm">{erro}</p>
          )}

          <button
            onClick={handleLogin}
            className="mt-4 px-6 py-2 bg-blue-600 hover:bg-blue-700 text-white rounded transition cursor-pointer"
          >
            Entrar
          </button>
        </div>

        <Footer />
      </main>
    </div>
  );
}
