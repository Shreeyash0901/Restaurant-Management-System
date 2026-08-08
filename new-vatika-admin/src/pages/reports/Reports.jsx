import { useCallback, useEffect, useState } from "react";
import {
  FaMoneyBillWave,
  FaShoppingCart,
  FaClock,
  FaCheckCircle,
  FaTimesCircle,
} from "react-icons/fa";

import { getDashboardReport } from "../../services/reportService";

export default function Reports() {
  const [report, setReport] = useState({
    totalRevenue: 0,
    totalOrders: 0,
    averageOrderValue: 0,
    pendingOrders: 0,
    completedOrders: 0,
    cancelledOrders: 0,
  });

  const [loading, setLoading] = useState(true);

  const loadReport = useCallback(async () => {
    try {
      const data = await getDashboardReport();
      setReport(data);
    } catch (err) {
      console.error("Failed to load reports", err);
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    loadReport();
  }, [loadReport]);

  if (loading) {
    return (
      <div className="flex justify-center items-center h-[70vh] text-2xl font-semibold">
        Loading Reports...
      </div>
    );
  }

  return (
    <div className="space-y-8">

      <div>
        <h1 className="text-4xl font-bold">
          Analytics Dashboard
        </h1>

        <p className="text-gray-500">
          Restaurant Business Overview
        </p>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-3 gap-6">

        <Card
          title="Revenue"
          value={`₹${report.totalRevenue.toFixed(2)}`}
          color="bg-green-600"
          icon={<FaMoneyBillWave size={28} />}
        />

        <Card
          title="Orders"
          value={report.totalOrders}
          color="bg-blue-600"
          icon={<FaShoppingCart size={28} />}
        />

        <Card
          title="Average Order"
          value={`₹${report.averageOrderValue.toFixed(2)}`}
          color="bg-purple-600"
          icon={<FaMoneyBillWave size={28} />}
        />

        <Card
          title="Pending Orders"
          value={report.pendingOrders}
          color="bg-yellow-500"
          icon={<FaClock size={28} />}
        />

        <Card
          title="Completed Orders"
          value={report.completedOrders}
          color="bg-emerald-600"
          icon={<FaCheckCircle size={28} />}
        />

        <Card
          title="Cancelled Orders"
          value={report.cancelledOrders}
          color="bg-red-600"
          icon={<FaTimesCircle size={28} />}
        />

      </div>
    </div>
  );
}

function Card({ title, value, icon, color }) {
  return (
    <div className="bg-white rounded-xl shadow-lg p-6 hover:shadow-xl transition">

      <div className="flex justify-between items-center">

        <div>
          <p className="text-gray-500 text-sm">
            {title}
          </p>

          <h2 className="text-3xl font-bold mt-2">
            {value}
          </h2>
        </div>

        <div className={`${color} text-white p-4 rounded-xl`}>
          {icon}
        </div>

      </div>

    </div>
  );
}