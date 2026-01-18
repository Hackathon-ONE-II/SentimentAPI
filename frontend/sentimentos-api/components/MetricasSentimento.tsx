'use client';

import { useEffect, useState } from "react";
import dynamic from "next/dynamic";

// Importação dinâmica do gráfico (evita erro de SSR com Chart.js)
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

  // Carrega métricas do backend
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

  // Atualiza métricas a cada 10 segundos
  useEffect(() => {
    carregarMetricas();
    const interval = setInterval(carregarMetricas, 10000);
    return () => clearInterval(interval);
  }, []);

  if (loading) {
    return (
      <div className="p-6 rounded-xl bg-gray-900 text-center text-gray-300">
        Carregando métricas...
      </div>
    );
  }

  if (!stats) {
    return (
      <div className="p-6 rounded-xl bg-gray-900 text-center text-red-400">
        Métricas indisponíveis
      </div>
    );
  }

  return (
    <div className="grid grid-cols-1 lg:grid-cols-2 gap-6 items-stretch">

      {/* CARD ESQUERDO – CONTADORES */}
      <div className="h-full rounded-xl bg-gradient-to-br from-gray-900 to-gray-800 p-6 shadow-lg flex flex-col justify-between">

        {/* Header */}
        <div className="border-b border-gray-700 pb-3 mb-4">
          <h2 className="text-xl font-semibold text-white">
            Métricas de Sentimento
          </h2>
          <p className="text-xs text-gray-500">
            Métricas internas do sistema
          </p>
        </div>

        {/* Contadores */}
        <div className="grid grid-cols-2 gap-4 flex-1">
          <MetricBox title="Total" value={stats.totalRequests} />
          <MetricBox title="Positivos" value={stats.positivos} color="text-green-400" />
          <MetricBox title="Neutros" value={stats.neutros} color="text-yellow-400" />
          <MetricBox title="Negativos" value={stats.negativos} color="text-red-400" />
        </div>

        {/* Footer */}
        <p className="text-xs text-gray-500 text-right mt-4">
          Última atualização:{" "}
          {new Date(stats.lastUpdated).toLocaleString("pt-BR")}
        </p>
      </div>

      {/* CARD DIREITO – GRÁFICO */}
      <div className="h-full rounded-xl bg-gradient-to-br from-gray-900 to-gray-800 p-6 shadow-lg flex items-center justify-center">
        <GraficoSentimentos
          positivos={stats.positivos}
          neutros={stats.neutros}
          negativos={stats.negativos}
        />
      </div>
    </div>
  );
}

// Componente reutilizável para métricas
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
      <p className="text-sm text-gray-400">{title}</p>
      <p className={`text-2xl font-bold ${color}`}>{value}</p>
    </div>
  );
}
