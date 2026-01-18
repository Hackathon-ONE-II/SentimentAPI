'use client';

import {
  Chart as ChartJS,
  ArcElement,
  Tooltip,
  Legend,
  ChartOptions,
} from 'chart.js';
import { Doughnut } from 'react-chartjs-2';

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

  const percent = (value: number) =>
    total === 0 ? 0 : ((value / total) * 100).toFixed(1);

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
      easing: 'easeInOutQuart', // ✅ agora tipado corretamente
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
    <div className="w-full flex justify-center animate-fade-in">
      <Doughnut data={data} options={options} />
    </div>
  );
}
