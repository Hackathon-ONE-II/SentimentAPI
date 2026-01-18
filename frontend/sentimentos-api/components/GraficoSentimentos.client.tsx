'use client';

import {
  Chart as ChartJS,
  ArcElement,
  Tooltip,
  Legend,
  type ChartOptions,
} from 'chart.js';
import { Doughnut } from 'react-chartjs-2';

// Registro obrigatório dos módulos do Chart.js
ChartJS.register(ArcElement, Tooltip, Legend);

interface Props {
  positivos: number;
  negativos: number;
  neutros: number;
}

export default function GraficoSentimentos({
  positivos,
  negativos,
  neutros,
}: Props) {

  const total = positivos + negativos + neutros;

  // Calcula percentual com proteção contra divisão por zero
  const percent = (value: number) =>
    total === 0 ? 0 : Number(((value / total) * 100).toFixed(1));

  const data = {
    labels: [
      `Positivos (${percent(positivos)}%)`,
      `Negativos (${percent(negativos)}%)`,
      `Neutros (${percent(neutros)}%)`,
    ],
    datasets: [
      {
        data: [positivos, negativos, neutros],
        backgroundColor: ['#22c55e', '#ef4444', '#eab308'],
        borderWidth: 1,
      },
    ],
  };

  const options: ChartOptions<'doughnut'> = {
    responsive: true,
    animation: {
      duration: 1200,
      easing: 'easeInOutQuart',
    },
    plugins: {
      legend: {
        position: 'bottom',
        labels: {
          color: '#e5e7eb',
        },
      },
      tooltip: {
        callbacks: {
          label(context) {
            const value = context.raw as number;
            return `${value} (${percent(value)}%)`;
          },
        },
      },
    },
  };

  return (
    <div className="w-full max-w-xs animate-fade-in">
      <Doughnut data={data} options={options} />
    </div>
  );
}
