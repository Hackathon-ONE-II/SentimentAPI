'use client';

import { useEffect, useState } from "react";
import dynamic from "next/dynamic";

const GraficoSentimentos = dynamic(
  () => import("./GraficoSentimentos.client"),
  { ssr: false }
);

interface SentimentStats {
  totalRequests: number;
  positivos: number;
  negativos: number;
  neutros: number;
  success: number;
  fallback: number;
  lastUpdated: string;
}

export default function MetricasSentimento() {
  const [stats, setStats] = useState<SentimentStats | null>(null);
  const [loading, setLoading] = useState(true);

  const apiUrl =
    process.env.NEXT_PUBLIC_API_URL || "http://localhost:8080";

  async function carregarMetricas() {
    try {
      const response = await fetch(`${apiUrl}/stats/v2`);
      if (!response.ok) return;

      const data = await response.json();
      setStats(data);
    } catch (error) {
      console.error("Erro ao carregar métricas", error);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    carregarMetricas();
    const interval = setInterval(carregarMetricas, 10000);
    return () => clearInterval(interval);
  }, []);

  if (loading) {
    return (
      <div className="p-6 rounded-lg bg-gray-900 text-center text-gray-300">
        Carregando métricas...
      </div>
    );
  }

  if (!stats) {
    return (
      <div className="p-6 rounded-lg bg-gray-900 text-center text-red-400">
        Métricas indisponíveis
      </div>
    );
  }

  return (
    <div className="h-full p-6 rounded-xl bg-gradient-to-br from-gray-900 to-gray-800 shadow-xl flex flex-col justify-between animate-fade-in space-y-6">

      {/* HEADER */}
      <div className="border-b border-gray-700 pb-3">
        <h2 className="text-xl font-semibold text-white">
          Métricas de Sentimento
        </h2>
        <p className="text-xs text-gray-400">
          Endpoint: <span className="text-blue-400">/stats/v2</span>
        </p>
      </div>

      {/* CONTADORES */}
      <div className="grid grid-cols-2 gap-4 text-sm text-gray-300">
        <MetricBox title="Total" value={stats.totalRequests} />
        <MetricBox title="Positivos" value={stats.positivos} color="text-green-400" />
        <MetricBox title="Neutros" value={stats.neutros} color="text-yellow-400" />
        <MetricBox title="Negativos" value={stats.negativos} color="text-red-400" />
      </div>

      {/* GRÁFICO */}
      <GraficoSentimentos
        positivos={stats.positivos}
        neutros={stats.neutros}
        negativos={stats.negativos}
      />

      {/* FOOTER */}
      <p className="text-xs text-gray-500 text-right">
        Última atualização:{" "}
        {new Date(stats.lastUpdated).toLocaleString("pt-BR")}
      </p>

    </div>
  );
}

function MetricBox({
  title,
  value,
  color = "text-white",
}: {
  title: string;
  value: number;
  color?: string;
}) {
  return (
    <div className="bg-gray-900 rounded-lg p-4">
      <p>{title}</p>
      <p className={`text-2xl font-bold ${color}`}>{value}</p>
    </div>
  );
}
