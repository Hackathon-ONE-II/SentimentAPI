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
  lastUpdated: string;
}

export default function MetricasSentimento() {
  const [stats, setStats] = useState<SentimentStats | null>(null);

  const apiUrl =
    process.env.NEXT_PUBLIC_API_URL || "http://localhost:8080";

  useEffect(() => {
    async function carregar() {
      const res = await fetch(`${apiUrl}/stats/v2`);
      if (res.ok) {
        setStats(await res.json());
      }
    }

    carregar();
    const interval = setInterval(carregar, 10000);
    return () => clearInterval(interval);
  }, []);

  if (!stats) return null;

  return (
    <div className="
      h-full
      rounded-xl
      bg-gradient-to-br
      from-gray-900
      to-gray-800
      p-6
      shadow-xl
      flex
      flex-col
      justify-between
    ">
      <h2 className="text-lg font-semibold text-white">
        Métricas de Sentimento
      </h2>

      <div className="grid grid-cols-2 gap-4 mt-4">
        <Metric label="Total" value={stats.totalRequests} />
        <Metric label="Positivos" value={stats.positivos} color="text-green-400" />
        <Metric label="Neutros" value={stats.neutros} color="text-yellow-400" />
        <Metric label="Negativos" value={stats.negativos} color="text-red-400" />
      </div>

      <div className="mt-6 flex justify-center">
        <GraficoSentimentos
          positivos={stats.positivos}
          neutros={stats.neutros}
          negativos={stats.negativos}
        />
      </div>

      <p className="text-xs text-gray-500 text-right mt-4">
        Última atualização:{" "}
        {new Date(stats.lastUpdated).toLocaleString("pt-BR")}
      </p>
    </div>
  );
}

function Metric({
  label,
  value,
  color = "text-white",
}: {
  label: string;
  value: number;
  color?: string;
}) {
  return (
    <div className="bg-black/40 rounded-lg p-3">
      <p className="text-xs text-gray-400">{label}</p>
      <p className={`text-xl font-bold ${color}`}>{value}</p>
    </div>
  );
}
